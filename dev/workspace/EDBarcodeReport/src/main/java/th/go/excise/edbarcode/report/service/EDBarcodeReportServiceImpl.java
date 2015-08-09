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

import th.go.excise.edbarcode.report.common.bean.GoodsEntryReport;
import th.go.excise.edbarcode.report.common.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.common.bean.SummaryReport;
import th.go.excise.edbarcode.report.common.bean.TaxpayerInfoReport;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.common.util.EDBarcodeReportUtil;
import th.go.excise.edbarcode.report.common.util.ThaiNumberUtils;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class EDBarcodeReportServiceImpl implements EDBarcodeReportService {
	
	private static final Logger logger = LogManager.getLogger(EDBarcodeReportServiceImpl.class);
	
	@Override
	public byte[] generateReport(String xml, String referenceNumber) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		ByteArrayOutputStream outputStream = null;
		byte[] content = null;
		
		try {
			SR12011FormReport form = EDBarcodeReportUtil.prepareDataWithXmlString(xml);
			form.getSummaryReport().setReferenceNumber(referenceNumber);
			
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
	public int generateReport(String xmlFile, String outputPath, String mode) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		
		logger.info("Output Path: " + outputPath);
		
		try {
			SR12011FormReport form = EDBarcodeReportUtil.prepareDataWithXmlFile(xmlFile);
			
			File outputDic = new File(outputPath);
			if (outputDic.mkdirs()) {
				logger.debug("Create output directory successed");
			}
			
			JRPdfExporter exporter = new JRPdfExporter();
			if (ReportConstant.MODE.SEPERATE.equalsIgnoreCase(mode)) {
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
			} else if (ReportConstant.MODE.ALL.equalsIgnoreCase(mode)) {
				// SR120-11 and Fund Report
				List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
				items.addAll(getSR12011ExportInputItemList(form));
				items.addAll(getFundExportInputItemList(form));
				
				exporter.setExporterInput(new SimpleExporterInput(items));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + "ALL_EXCISE.pdf"));
				exporter.exportReport();
			} else{
				logger.warn("Incorrect mode, Please enter 'S' or 'A'");
			}
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			new EDBarcodeReportException(e.getMessage(), e);
		}
		logger.info("Generate Report - Finished");
		logger.info("PDF creation time: " + (System.currentTimeMillis() - start) + " ms");
		return 0;
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
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyName", form.getTaxpayerInfoReport().getCompanyName());
		paramMap.put("taxpayerName", form.getTaxpayerInfoReport().getTaxpayerName());
		paramMap.put("licenseNo", form.getTaxpayerInfoReport().getLicenseNo());
		paramMap.put("effectiveDate", EDBarcodeReportUtil.toThaiDateFormat(form.getTaxpayerInfoReport().getEffectiveDate()));
		paramMap.put("expireDate", EDBarcodeReportUtil.toThaiDateFormat(form.getTaxpayerInfoReport().getExpireDate()));
		paramMap.put("tin", form.getTaxpayerInfoReport().getTin());
		putAddressData(paramMap, form.getTaxpayerInfoReport());
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
		paramMap.put("paymentMunicipalPercent", form.getSummaryReport().getPaymentMunicipalPercent());
		paramMap.put("paymentMunicipalAmount", "");
		paramMap.put("paymentExciseAndMunicipalTaxAmount", "");
		paramMap.put("paymentOtherAmount", "");
		paramMap.put("paymentNetTaxAmount", "");
		paramMap.put("paymentNetTaxAmountText", "");
		
		// Find totalPages
		int totalPages = (int) Math.ceil((float) form.getGoodsListReport().size() / ReportConstant.GOODS_PER_PAGE);
		
		// Split List to each pages
		List<List<GoodsEntryReport>> goodsPerPageList = new ArrayList<List<GoodsEntryReport>>(totalPages);
		List<GoodsEntryReport> goodsEntryList = null;
		GoodsEntryReport goodsEntry = null;
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < totalPages; i++) {
			endIndex = ((i + 1) * ReportConstant.GOODS_PER_PAGE) - 1;
			goodsEntryList = new ArrayList<GoodsEntryReport>();
			for (int j = startIndex; j <= endIndex; j++) {
				goodsEntry = form.getGoodsListReport().get(j);
				goodsEntry.setGoodsPiece(decimalFormatZeroDigit.format(new BigDecimal(goodsEntry.getGoodsPiece())));
				goodsEntry.setGoodsQuantity(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getGoodsQuantity())));
				goodsEntry.setUnitPrice(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getUnitPrice())));
				goodsEntry.setDeclarePrice(decimalFormatFourDigit.format(new BigDecimal(goodsEntry.getDeclarePrice())));
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
			startIndex = (i + 1) * ReportConstant.GOODS_PER_PAGE;
		}
		
		JasperPrint jasperPrint = null;
		JRBeanCollectionDataSource datasource = null;
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>(totalPages);
		StringBuilder builder = null;
		boolean isRefNumFlag = StringUtils.isNotEmpty(form.getSummaryReport().getReferenceNumber());
		
		for (int i = 0; i < totalPages; i++) {
			
			builder = new StringBuilder();
			
			// Set header data in barcode
			if (i == 0) {
				if (!isRefNumFlag) {
					generateBarcodeHeaderData(builder, form.getTaxpayerInfoReport());
				}
			}
			
			// Set detail data in barcode
			if (!isRefNumFlag) {
				generateBarcodeDetailData(builder, goodsPerPageList.get(i));
			}
			
			if (i == (totalPages - 1)) {
				// Last Page, Set summary data
				paramMap.put("sumAllTaxByValue", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTaxByValue())));
				paramMap.put("sumAllTaxByValue2", decimalFormatZeroDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTaxByValue())));
				paramMap.put("sumAllTaxByQuantity", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTaxByQuantity())));
				paramMap.put("sumAllTax", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getSumAllTax())));
				paramMap.put("taxLessType", form.getSummaryReport().getTaxLessType());
				paramMap.put("taxLessFrom", form.getSummaryReport().getTaxLessFrom());
				paramMap.put("taxLessAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getTaxLessAmount()))));
				paramMap.put("taxDeductionOnBookNo", form.getSummaryReport().getTaxDeductionOnBookNo());
				paramMap.put("taxDeductionOnBookAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getTaxDeductionOnBookAmount()))));
				paramMap.put("paymentExciseAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount())));
				paramMap.put("paymentMunicipalAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentMunicipalAmount())));
				paramMap.put("paymentExciseAndMunicipalTaxAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAndMunicipalTaxAmount())));
				paramMap.put("paymentOtherAmount", decimalFormatTwoDigit.format(new BigDecimal(EDBarcodeReportUtil.blankToZero(form.getSummaryReport().getPaymentOtherAmount()))));
				paramMap.put("paymentNetTaxAmount", decimalFormatTwoDigit.format(new BigDecimal(form.getSummaryReport().getPaymentNetTaxAmount())));
				paramMap.put("paymentNetTaxAmountString", ThaiNumberUtils.toThaiBaht(form.getSummaryReport().getPaymentNetTaxAmount()));
				
				// Set summary data in barcode
				if (!isRefNumFlag) {
					generateBarcodeSummaryData(builder, form.getSummaryReport());
				}
			}
			
			if (isRefNumFlag) {
				generateBarcodeReferenceData(builder, form.getSummaryReport().getReferenceNumber());
			}
			
			System.out.println(builder);
			paramMap.put("barcodeData", builder.toString());
			
			// InputStream will load every page
			paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SR120_11));
			
			datasource = new JRBeanCollectionDataSource(goodsPerPageList.get(i), true);
			//jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.SR120_11, paramMap, datasource);
			jasperPrint = ReportUtil.getJasperPrintWithJasper(ReportConstant.REPORT.SR120_11, paramMap, datasource);
			jasperPrintList.add(jasperPrint);
		}
		
		logger.info("Inside getSR12011Report() - End");
		
		return jasperPrintList;
	}
	
	private JasperPrint getSSS101Report(SR12011FormReport form) throws JRException, IOException {
		logger.info("Inside getSSS101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SSS1_01));
		
		// Fund
		DecimalFormat decimalFormat = new DecimalFormat(ReportConstant.FORMAT_FUND_AMT);
		String paymentExciseAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount().split("\\.")[0]));
		String paymentExciseAmountStang = form.getSummaryReport().getPaymentExciseAmount().split("\\.")[1];
		String paymentFundHealthAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentFundHealthAmount().split("\\.")[0]));
		String paymentFundHealthAmountStang = form.getSummaryReport().getPaymentFundHealthAmount().split("\\.")[1];
		String paymentFundHealthAmountString = ThaiNumberUtils.toThaiBaht(form.getSummaryReport().getPaymentFundHealthAmount());
		
		paramMap.put("paymentExciseAmountBaht", paymentExciseAmountBaht);
		paramMap.put("paymentExciseAmountStang", paymentExciseAmountStang);
		paramMap.put("paymentFundHealthAmountBaht", paymentFundHealthAmountBaht);
		paramMap.put("paymentFundHealthAmountStang", paymentFundHealthAmountStang);
		paramMap.put("paymentFundHealthAmountString", paymentFundHealthAmountString);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.SSS1_01, paramMap, form.getTaxpayerInfoReport());
		
		logger.info("Inside getSSS101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSST101Report(SR12011FormReport form) throws JRException, IOException {
		logger.info("Inside getSST101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SST1_01));
		
		// Fund
		DecimalFormat decimalFormat = new DecimalFormat(ReportConstant.FORMAT_FUND_AMT);
		String paymentExciseAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount().split("\\.")[0]));
		String paymentExciseAmountStang = form.getSummaryReport().getPaymentExciseAmount().split("\\.")[1];
		String paymentFundTVAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentFundTVAmount().split("\\.")[0]));
		String paymentFundTVAmountStang = form.getSummaryReport().getPaymentFundTVAmount().split("\\.")[1];
		String paymentFundTVAmountString = ThaiNumberUtils.toThaiBaht(form.getSummaryReport().getPaymentFundTVAmount());
		
		paramMap.put("paymentExciseAmountBaht", paymentExciseAmountBaht);
		paramMap.put("paymentExciseAmountStang", paymentExciseAmountStang);
		paramMap.put("paymentFundTVAmountBaht", paymentFundTVAmountBaht);
		paramMap.put("paymentFundTVAmountStang", paymentFundTVAmountStang);
		paramMap.put("paymentFundTVAmountString", paymentFundTVAmountString);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.SST1_01, paramMap, form.getTaxpayerInfoReport());
		
		logger.info("Inside getSST101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getKKT101Report(SR12011FormReport form) throws JRException, IOException {
		logger.info("Inside getKKT101Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.KKT1_01));
		
		// Fund
		DecimalFormat decimalFormat = new DecimalFormat(ReportConstant.FORMAT_FUND_AMT);
		String paymentExciseAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentExciseAmount().split("\\.")[0]));
		String paymentExciseAmountStang = form.getSummaryReport().getPaymentExciseAmount().split("\\.")[1];
		String paymentFundSportAmountBaht = decimalFormat.format(new BigDecimal(form.getSummaryReport().getPaymentFundSportAmount().split("\\.")[0]));
		String paymentFundSportAmountStang = form.getSummaryReport().getPaymentFundSportAmount().split("\\.")[1];
		String paymentFundSportAmountString = ThaiNumberUtils.toThaiBaht(form.getSummaryReport().getPaymentFundSportAmount());
		
		paramMap.put("paymentExciseAmountBaht", paymentExciseAmountBaht);
		paramMap.put("paymentExciseAmountStang", paymentExciseAmountStang);
		paramMap.put("paymentFundSportAmountBaht", paymentFundSportAmountBaht);
		paramMap.put("paymentFundSportAmountStang", paymentFundSportAmountStang);
		paramMap.put("paymentFundSportAmountString", paymentFundSportAmountString);
		
		JasperPrint jasperPrint = getFundReport(ReportConstant.REPORT.KKT1_01, paramMap, form.getTaxpayerInfoReport());
		
		logger.info("Inside getKKT101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getFundReport(String fileName, Map<String, Object> paramMap, TaxpayerInfoReport taxpayerInfo) throws JRException, IOException {
		logger.info("Prepare data for Fund Report");
		
		paramMap.put("companyName", taxpayerInfo.getCompanyName());
		paramMap.put("taxpayerName", taxpayerInfo.getTaxpayerName());
		paramMap.put("tin", taxpayerInfo.getTin());
		putAddressData(paramMap, taxpayerInfo);
		
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
	
	private void generateBarcodeHeaderData(StringBuilder builder, TaxpayerInfoReport taxpayerInfo) {
		builder.append(ReportConstant.EVENT_CODE.HEADER);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(taxpayerInfo.getLicenseNo());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(taxpayerInfo.getCusId());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(taxpayerInfo.getTaxpayerId());
	}
	
	private void generateBarcodeDetailData(StringBuilder builder, List<GoodsEntryReport> goodsEntryList) {
		// Flag for delete first seperate_line in case data more than one page like page 2 or 3
		boolean deleteSeperateLineFlag = false;
		if (builder.length() == 0) {
			deleteSeperateLineFlag = true;
		}
		
		for (GoodsEntryReport goodsEntry : goodsEntryList) {
			builder.append(ReportConstant.SEPERATE_LINE);
			builder.append(ReportConstant.EVENT_CODE.DETAIL);
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getSeqNo());
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getProductTypeCode());
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getGoodsPiece().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getGoodsQuantity().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getUnitPrice().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getDeclarePrice().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByValue().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByQuantity().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByQuantityOver().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByQuantityWithOver().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getNetTaxByValue().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getNetTaxByQuantity().replaceAll(",", ""));
		}
		
		if (deleteSeperateLineFlag) {
			// Delete seperate_line
			builder.delete(0, ReportConstant.SEPERATE_LINE.length());
		}
	}
	
	private void generateBarcodeSummaryData(StringBuilder builder, SummaryReport summary) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.SUMMARY);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getSumAllTaxByValue().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getSumAllTaxByQuantity().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getSumAllTax().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxLessFrom());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxLessAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxDeductionOnBookNo());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxDeductionOnBookAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentExciseAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentMunicipalPercent());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentMunicipalAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentExciseAndMunicipalTaxAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentOtherAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentNetTaxAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundHealthAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundTVAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundSportAmount().replaceAll(",", ""));
	}
	
	private void generateBarcodeReferenceData(StringBuilder builder, String referenceNumber) {
		builder.append(ReportConstant.EVENT_CODE.REFERENCE);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(referenceNumber);
	}
	
}
