package th.go.excise.edbarcode.report.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import th.go.excise.edbarcode.report.bean.FundEntryReport;
import th.go.excise.edbarcode.report.bean.GoodsEntryReport;
import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.bean.TaxpayerInfoReport;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.common.util.EDBarcodeReportUtil;
import th.go.excise.edbarcode.report.common.util.ReportUtil;
import th.go.excise.edbarcode.report.common.util.ThaiNumberUtils;

public class EDBarcodeReportServiceImpl implements EDBarcodeReportService {
	
	private static final Logger logger = LogManager.getLogger(EDBarcodeReportServiceImpl.class);
	
	@Override
	public byte[] generateReport(SR12011FormReport form) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		ByteArrayOutputStream outputStream = null;
		byte[] content = null;
		
		try {
			// Generate formId
			String formId = UUID.randomUUID().toString();
			form.setFormId(formId);
			
			List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
			items.addAll(getSR12011ExportInputItemList(form));
			items.addAll(getFundExportInputItemList(form));
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));
			
			outputStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			
			exporter.exportReport();
			content = outputStream.toByteArray();
			
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			new EDBarcodeReportException(e.getMessage(), e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				new EDBarcodeReportException(e.getMessage(), e);
			}
		}
		
		logger.info("Generate Report - Finished");
		logger.info("PDF creation time: " + (System.currentTimeMillis() - start) + " ms");
		
		return content;
	}
	
	@Override
	public int generateReport(String xmlFile, String outputPath) throws EDBarcodeReportException {
		return generateReport(xmlFile, outputPath, "");
	}
	
	@Override
	public int generateReport(String xmlFile, String outputPath, String referenceNumber) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		int result = 0;
		
		logger.info("Output Path: " + outputPath);
		
		try {
			SR12011FormReport form = EDBarcodeReportUtil.prepareDataWithXmlFile(xmlFile);
			form.getSummaryReport().setReferenceNumber(referenceNumber);
			
			// Generate formId
			String formId = UUID.randomUUID().toString();
			form.setFormId(formId);
			
			File outputDic = new File(outputPath);
			if (outputDic.mkdirs()) {
				logger.debug("Create output directory successed");
			}
			
			JRPdfExporter exporter = new JRPdfExporter();
			
			// SR120-11 Report
			List<ExporterInputItem> sr12011Items = getSR12011ExportInputItemList(form);
			exporter.setExporterInput(new SimpleExporterInput(sr12011Items));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + ReportConstant.REPORT.NAME_LIST[0] + "." + ReportConstant.FILE.PDF));
			exporter.exportReport();
			
			// Fund Report
			List<ExporterInputItem> fundItems = getFundExportInputItemList(form);
			for (int i = 0; i < fundItems.size(); i++) {
				exporter.setExporterInput(new SimpleExporterInput(fundItems.get(i).getJasperPrint()));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + ReportConstant.REPORT.NAME_LIST[(i + 1)] + "." + ReportConstant.FILE.PDF));
				exporter.exportReport();
			}
		
			// SR120-11 and Fund Report
			List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
			items.addAll(sr12011Items);
			items.addAll(fundItems);
			
			exporter.setExporterInput(new SimpleExporterInput(items));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + ReportConstant.REPORT.ALL + "." + ReportConstant.FILE.PDF));
			exporter.exportReport();
			
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			result = 1;
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		logger.info("Generate Report - Finished");
		logger.info("PDF creation time: " + (System.currentTimeMillis() - start) + " ms");
		return result;
	}

	private List<ExporterInputItem> getSR12011ExportInputItemList(SR12011FormReport form) throws EDBarcodeReportException {
		
		List<JasperPrint> sr12011ReportList;
		try {
			sr12011ReportList = getSR12011ReportList(form);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		for (JasperPrint sr12011Report : sr12011ReportList) {
			items.add(new SimpleExporterInputItem(sr12011Report));
		}
		
		return items;
	}
	
	private List<ExporterInputItem> getFundExportInputItemList(SR12011FormReport form) throws EDBarcodeReportException {
		
		JasperPrint sss101Report;
		JasperPrint sst101Report;
		JasperPrint kkt101Report;
		try {
			sss101Report = getSSS101Report(form);
			sst101Report = getSST101Report(form);
			kkt101Report = getKKT101Report(form);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(sss101Report));
		items.add(new SimpleExporterInputItem(sst101Report));
		items.add(new SimpleExporterInputItem(kkt101Report));
		
		return items;
	}
	
	private List<JasperPrint> getSR12011ReportList(SR12011FormReport form) throws JRException, IOException, ParseException {
		logger.info("Inside getSR12011Report() - Start");
		
		DecimalFormat decimalFormatZeroDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.ZERO_DIGIT);
		DecimalFormat decimalFormatTwoDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.TWO_DIGIT);
		DecimalFormat decimalFormatFourDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.FOUR_DIGIT);
		
		boolean isRefNumFlag = StringUtils.isNotEmpty(form.getSummaryReport().getReferenceNumber());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// Page Header
		paramMap.put("companyName", form.getTaxpayerInfoReport().getCompanyName());
		paramMap.put("taxpayerName", form.getTaxpayerInfoReport().getTaxpayerName());
		paramMap.put("licenseNo", form.getTaxpayerInfoReport().getLicenseNo());
		paramMap.put("effectiveDate", EDBarcodeReportUtil.toThaiDateFormat(form.getTaxpayerInfoReport().getEffectiveDate()));
		paramMap.put("expireDate", EDBarcodeReportUtil.toThaiDateFormat(form.getTaxpayerInfoReport().getExpireDate()));
		paramMap.put("tin", form.getTaxpayerInfoReport().getTin());
		putAddressData(paramMap, form.getTaxpayerInfoReport());
		
		// For Staff
		if (isRefNumFlag) {
			paramMap.put("referenceNumber", form.getSummaryReport().getReferenceNumber());
			paramMap.put("submissionDate", EDBarcodeReportUtil.toThaiDateFormat(form.getSummaryReport().getSubmissionDate()));
		} else {
			paramMap.put("referenceNumber", "");
			paramMap.put("submissionDate", "");
		}
				
		// Initial Summary Data
		paramMap.put("printType", form.getSummaryReport().getPrintType());
		paramMap.put("sumAllTaxByValue", "");
		paramMap.put("sumAllTaxByQuantity", "");
		paramMap.put("sumAllTax", "");
		paramMap.put("taxLessType", "");
		paramMap.put("taxLessFrom", "");
		paramMap.put("taxLessAmount", "");
		paramMap.put("taxDeductionOnBookNo", "");
		paramMap.put("taxDeductionOnBookAmount", "");
		paramMap.put("paymentExciseAmount", "");
		paramMap.put("paymentMunicipalPercent", "");
		paramMap.put("paymentMunicipalAmount", "");
		paramMap.put("paymentExciseAndMunicipalTaxAmount", "");
		paramMap.put("paymentOtherAmount", "");
		paramMap.put("paymentNetTaxAmount", "");
		paramMap.put("paymentNetTaxAmountText", "");
		
		// Initial Paging
		int goodsPerPage;
		// Offline
		if (!isRefNumFlag) {
			goodsPerPage = ReportConstant.GOODS_PER_PAGE_OFFLINE;
		} else {
			goodsPerPage = ReportConstant.GOODS_PER_PAGE_ONLINE;
		}
		
		// Find totalPages
		int totalPages = (int) Math.ceil((float) form.getGoodsListReport().size() / goodsPerPage);
		
		// Split List to each pages
		List<List<GoodsEntryReport>> goodsPerPageList = new ArrayList<List<GoodsEntryReport>>(totalPages);
		List<GoodsEntryReport> goodsEntryList = null;
		GoodsEntryReport goodsEntry = null;
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < totalPages; i++) {
			endIndex = ((i + 1) * goodsPerPage) - 1;
			goodsEntryList = new ArrayList<GoodsEntryReport>();
			for (int j = startIndex; j <= endIndex; j++) {
				goodsEntry = form.getGoodsListReport().get(j);
				goodsEntry.setDegree(decimalFormatTwoDigit.format(new BigDecimal((goodsEntry.getDegree()))));
				goodsEntry.setGoodsSize(decimalFormatFourDigit.format(new BigDecimal((goodsEntry.getGoodsSize()))));
				goodsEntry.setGoodsPiece(decimalFormatZeroDigit.format(new BigDecimal(goodsEntry.getGoodsPiece())));
				goodsEntry.setGoodsQuantity(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getGoodsQuantity())));
				goodsEntry.setUnitPrice(decimalFormatTwoDigit.format(new BigDecimal(goodsEntry.getUnitPrice())));
				goodsEntry.setDeclarePrice(decimalFormatTwoDigit.format(new BigDecimal(goodsEntry.getDeclarePrice())));
				goodsEntry.setTaxByValue(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getTaxByValue())));
				goodsEntry.setTaxByQuantity(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getTaxByQuantity())));
				goodsEntry.setTaxByQuantityOver(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getTaxByQuantityOver())));
				goodsEntry.setTaxByQuantityWithOver(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getTaxByQuantityWithOver())));
				goodsEntry.setNetTaxByValue(decimalFormatTwoDigit.format(new BigDecimal(goodsEntry.getNetTaxByValue())));
				goodsEntry.setNetTaxByQuantity(decimalFormatTwoDigit.format(new BigDecimal(goodsEntry.getNetTaxByQuantity())));
				goodsEntryList.add(goodsEntry);
				if (j == endIndex || j == form.getGoodsListReport().size() - 1) {
					goodsPerPageList.add(goodsEntryList);
					break;
				}
			}
			startIndex = (i + 1) * goodsPerPage;
		}
		
		JasperPrint jasperPrint = null;
		JRBeanCollectionDataSource dataSource = null;
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>(totalPages);
		StringBuilder builder = null;
		SR12011BarcodeData sr12011BarcodeData = new SR12011BarcodeData();
		
		for (int i = 0; i < totalPages; i++) {
			
			if (i == (totalPages - 1)) {
				// Last Page, Set summary data
				paramMap.put("sumAllTaxByValue", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTaxByValue())));
				paramMap.put("sumAllTaxByValue2", decimalFormatZeroDigit.format(Math.floor(new BigDecimal(form.getSummaryReport().getSumAllTaxByValue()).doubleValue())));
				paramMap.put("sumAllTaxByQuantity", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTaxByQuantity())));
				paramMap.put("sumAllTax", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTax())));
				paramMap.put("taxLessFrom", form.getSummaryReport().getTaxLessFrom());
				paramMap.put("taxLessAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getTaxLessAmount()))));
				paramMap.put("taxDeductionOnBookNo", form.getSummaryReport().getTaxDeductionOnBookNo());
				paramMap.put("taxDeductionOnBookAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getTaxDeductionOnBookAmount()))));
				paramMap.put("paymentExciseAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount())));
				paramMap.put("paymentMunicipalPercent", form.getSummaryReport().getPaymentMunicipalPercent());
				paramMap.put("paymentMunicipalAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentMunicipalAmount())));
				paramMap.put("paymentExciseAndMunicipalTaxAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAndMunicipalTaxAmount())));
				paramMap.put("paymentOtherAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getPaymentOtherAmount()))));
				paramMap.put("paymentNetTaxAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentNetTaxAmount())));
				paramMap.put("paymentNetTaxAmountString", ThaiNumberUtils.toThaiBaht(form.getSummaryReport().getPaymentNetTaxAmount()));
			}
			
			builder = new StringBuilder();
			
			// set page information
			sr12011BarcodeData.generateBarcodePageData(builder, form.getFormId(), (i + 1), totalPages);
			
			// Offline
			if (!isRefNumFlag) {
				
				// Set header data in barcode
				if (i == 0) {
					sr12011BarcodeData.generateBarcodeHeaderData(builder, form);
				}
				
				// Set detail data in barcode
				sr12011BarcodeData.generateBarcodeDetailData(builder, goodsPerPageList.get(i));
				
				// Set summary data in last page
				if (i == (totalPages - 1)) {
					sr12011BarcodeData.generateBarcodeSummaryData(builder, form.getSummaryReport());
				}
				
			// Online
			} else {
				sr12011BarcodeData.generateBarcodeReferenceData(builder, form.getSummaryReport().getReferenceNumber());
			}
			
			logger.debug("Data in barcode:\n" + builder);
			paramMap.put("barcodeData", builder.toString());
			
			// InputStream will load every page
			paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SR120_11));
			
			dataSource = new JRBeanCollectionDataSource(goodsPerPageList.get(i), true);
			
			// Offline
			if (!isRefNumFlag) {
				jasperPrint = ReportUtil.getJasperPrintWithJasper(ReportConstant.REPORT.SR120_11, paramMap, dataSource);
			// Online
			} else {
				// Resize QR-Code
				jasperPrint = ReportUtil.complieReportWithJasperDesign(sr12011BarcodeData.getJasperDesignWithResizeQrCode(), paramMap, dataSource);
			}
			
			//jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.SR120_11, paramMap, dataSource);
			jasperPrintList.add(jasperPrint);
		}
		
		logger.info("Inside getSR12011Report() - End");
		
		return jasperPrintList;
	}
	
	private JasperPrint getSSS101Report(SR12011FormReport form) throws JRException, IOException, ParseException {
		logger.info("Inside getSSS101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SSS1_01));
		
		// Get FundEntry from fundList
		FundEntryReport fundEntry = getFundEntryFromFundList(form.getFundListReport(), ReportConstant.FUND_TYPE.SSS1_01);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.SSS1_01, paramMap, form, fundEntry);
		
		logger.info("Inside getSSS101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSST101Report(SR12011FormReport form) throws JRException, IOException, ParseException {
		logger.info("Inside getSST101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SST1_01));
		
		// Get FundEntry from fundList
		FundEntryReport fundEntry = getFundEntryFromFundList(form.getFundListReport(), ReportConstant.FUND_TYPE.SST1_01);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.SSS1_01, paramMap, form, fundEntry);
		
		logger.info("Inside getSST101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getKKT101Report(SR12011FormReport form) throws JRException, IOException, ParseException {
		logger.info("Inside getKKT101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.KKT1_01));
		
		// Get FundEntry from fundList
		FundEntryReport fundEntry = getFundEntryFromFundList(form.getFundListReport(), ReportConstant.FUND_TYPE.KKT1_01);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.SSS1_01, paramMap, form, fundEntry);
		
		logger.info("Inside getKKT101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getFundReport(String fileName, Map<String, Object> paramMap, SR12011FormReport form, FundEntryReport fundEntry) throws JRException, IOException, ParseException {
		logger.info("Prepare data for Fund Report");
		
		// Header Data
		paramMap.put("companyName", form.getTaxpayerInfoReport().getCompanyName());
		paramMap.put("taxpayerName", form.getTaxpayerInfoReport().getTaxpayerName());
		paramMap.put("tin", form.getTaxpayerInfoReport().getTin());
		putAddressData(paramMap, form.getTaxpayerInfoReport());
		
		boolean isRefNumFlag = StringUtils.isNotEmpty(form.getSummaryReport().getReferenceNumber());
		
		// For Staff
		if (isRefNumFlag) {
			paramMap.put("referenceNumber", form.getSummaryReport().getReferenceNumber());
			paramMap.put("submissionDate", EDBarcodeReportUtil.toThaiDateFormat(form.getSummaryReport().getSubmissionDate()));
		} else {
			paramMap.put("referenceNumber", "");
			paramMap.put("submissionDate", "");
		}
		
		// Summary Data
		DecimalFormat decimalFormat = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.TWO_DIGIT);
		
		// Excise Amount
		String exciseAmount = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount()));
		paramMap.put("exciseAmountBaht", exciseAmount.split("\\.")[0]);
		paramMap.put("exciseAmountStang", exciseAmount.split("\\.")[1]);
		
		// Fund, Credit and Net Amount
		String fundAmount = decimalFormat.format(new BigDecimal(fundEntry.getFundAmt()));
		String creditAmount = decimalFormat.format(new BigDecimal(fundEntry.getCreditAmt()));
		String netAmount = decimalFormat.format(new BigDecimal(fundEntry.getNetAmt()));
		
		paramMap.put("fundAmountBaht", fundAmount.split("\\.")[0]);
		paramMap.put("fundAmountStang", fundAmount.split("\\.")[1]);
		paramMap.put("taxDeductionOnBookNo", form.getSummaryReport().getTaxDeductionOnBookNo());
		paramMap.put("creditAmountBaht", creditAmount.split("\\.")[0]);
		paramMap.put("creditAmountStang", creditAmount.split("\\.")[1]);
		paramMap.put("netAmountBaht", netAmount.split("\\.")[0]);
		paramMap.put("netAmountStang", netAmount.split("\\.")[1]);
		paramMap.put("netAmountString", ThaiNumberUtils.toThaiBaht(netAmount.replaceAll(",", "")));
		
		
		// Prepare data in barcode
		StringBuilder builder = new StringBuilder();
		FundBarcodeData fundBarcodeData = new FundBarcodeData();
		if (!isRefNumFlag) {
			// Offline
			fundBarcodeData.generateBarcodeForFundReport(builder, form, fundEntry);
		} else {
			// Online
			fundBarcodeData.generateBarcodeForFundReportRef(builder, form, fundEntry.getFundType());
		}
		
		logger.debug("Data in barcode:\n" + builder);
		paramMap.put("barcodeData", builder.toString());
		
		//return ReportUtil.complieReportWithJrxml(fileName, paramMap);
		return ReportUtil.getJasperPrintWithJasper(fileName, paramMap);
	}
	
	private void putAddressData(Map<String, Object> paramMap, TaxpayerInfoReport taxpayerInfo) {
		paramMap.put("houseNumber", taxpayerInfo.getTaxpayerAddressReport().getHouseNumber());
		paramMap.put("mooNumber", taxpayerInfo.getTaxpayerAddressReport().getMooNumber());
		paramMap.put("trokSoiName", taxpayerInfo.getTaxpayerAddressReport().getTrokSoiName());
		paramMap.put("streetName", taxpayerInfo.getTaxpayerAddressReport().getStreetName());
		paramMap.put("thambolName", taxpayerInfo.getTaxpayerAddressReport().getThambolName());
		paramMap.put("amphurName", taxpayerInfo.getTaxpayerAddressReport().getAmphurName());
		paramMap.put("provinceName", taxpayerInfo.getTaxpayerAddressReport().getProvinceName());
		paramMap.put("postcode", taxpayerInfo.getTaxpayerAddressReport().getPostcode());
		paramMap.put("telNumber", taxpayerInfo.getTaxpayerAddressReport().getTelNumber());
	}
	
	private FundEntryReport getFundEntryFromFundList(List<FundEntryReport> fundList, String fundType) {
		FundEntryReport fundEntry = null;
		for (FundEntryReport fundEntryInfo : fundList) {
			if (fundType.equals(fundEntryInfo.getFundType())) {
				fundEntry = fundEntryInfo;
				break;
			}
		}
		return fundEntry;
	}
	
}
