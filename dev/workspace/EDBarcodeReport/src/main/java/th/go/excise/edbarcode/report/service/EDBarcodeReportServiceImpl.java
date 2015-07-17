package th.go.excise.edbarcode.report.service;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.go.excise.edbarcode.report.common.bean.SR12011Form;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.common.util.EDBarcodeReportUtil;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class EDBarcodeReportServiceImpl implements EDBarcodeReportService {
	
	private static final Log logger = LogFactory.getLog(EDBarcodeReportServiceImpl.class);
	
	@Override
	public void generateReport(String xml, String outputFile) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		
		logger.info("Output File: " + outputFile);
		
		try {
			SR12011Form form = EDBarcodeReportUtil.prepareDataWithXmlString(xml);
			List<ExporterInputItem> items = getExportInputItemList(form);
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
			exporter.exportReport();
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			new EDBarcodeReportException(e.getMessage(), e);
		}
		logger.info("Generate Report - Finished");
		logger.info("PDF creation time: " + (System.currentTimeMillis() - start) + " ms");
	}
	
	@Override
	public int generateReport(String xmlFile, String outputPath, String mode) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		
		logger.info("Output Path: " + outputPath);
		
		try {
			SR12011Form form = EDBarcodeReportUtil.prepareDataWithXmlFile(xmlFile);
			List<ExporterInputItem> items = getExportInputItemList(form);
			
			File outputDic = new File(outputPath);
			if (outputDic.mkdirs()) {
				logger.debug("Create output directory successed");
			}
			
			JRPdfExporter exporter = new JRPdfExporter();
			if (ReportConstant.MODE.SEPERATE.equalsIgnoreCase(mode)) {
				for (int i = 0; i < items.size(); i++) {
					exporter.setExporterInput(new SimpleExporterInput(items.get(i).getJasperPrint()));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + ReportConstant.REPORT.NAME_LIST[i] + "." + ReportConstant.FILE.PDF));
					exporter.exportReport();
				}
			} else if (ReportConstant.MODE.ALL.equalsIgnoreCase(mode)) {
				exporter.setExporterInput(new SimpleExporterInput(items));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath + "EXCISE.pdf"));
				exporter.exportReport();
			} else{
				
			}
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			new EDBarcodeReportException(e.getMessage(), e);
		}
		logger.info("Generate Report - Finished");
		logger.info("PDF creation time: " + (System.currentTimeMillis() - start) + " ms");
		return 0;
	}

	private List<ExporterInputItem> getExportInputItemList(SR12011Form form) throws EDBarcodeReportException {
		
		JasperPrint sr12011Report;
		JasperPrint sss101Report;
		JasperPrint sst101Report;
		JasperPrint kkt101Report;
		try {
			sr12011Report = getSR12011Report(form);
			sss101Report = getSSS101Report(form);
			sst101Report = getSST101Report(form);
			kkt101Report = getKKT101Report(form);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(sr12011Report));
		items.add(new SimpleExporterInputItem(sss101Report));
		items.add(new SimpleExporterInputItem(sst101Report));
		items.add(new SimpleExporterInputItem(kkt101Report));
		
		return items;
	}
	
	private JasperPrint getSR12011Report(SR12011Form form) throws JRException, IOException {
		logger.info("Inside getSR12011Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyName", form.getTaxpayerInformation().getCompanyName());
		paramMap.put("taxpayerName", form.getTaxpayerInformation().getTaxpayerAddress().getTaxpayerName());
		paramMap.put("licenseNo", form.getTaxpayerInformation().getLicenseInfo().getLicenseNo());
		paramMap.put("effectiveDate", form.getTaxpayerInformation().getLicenseInfo().getEffectiveDate());
		paramMap.put("expireDate", form.getTaxpayerInformation().getLicenseInfo().getExpireDate());
		paramMap.put("tin", form.getTaxpayerInformation().getTin());
		paramMap.put("taxpayerAddress", "MOCK ADDRESS");
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SR120_11));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		JasperPrint jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.SR120_11, paramMap, datasource);
		
		logger.info("Inside getSR12011Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSSS101Report(SR12011Form form) throws JRException, IOException {
		logger.info("Inside getSSS101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SSS1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.SSS1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getSSS101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSST101Report(SR12011Form form) throws JRException, IOException {
		logger.info("Inside getSST101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.SST1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.SST1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getSST101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getKKT101Report(SR12011Form form) throws JRException, IOException {
		logger.info("Inside getKKT101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImageFile(ReportConstant.REPORT.KKT1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = ReportUtil.complieReportWithJrxml(ReportConstant.REPORT.KKT1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getKKT101Report() - End");
		
		return jasperPrint;
	}
	
//	private JasperPrint getFundReport(String fileName, TaxpayerInformation taxpayerInfo) throws JRException, IOException {
//		logger.info("Prepare data for Fund Report");
//		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("companyName", taxpayerInfo.getCompanyName());
//		paramMap.put("taxpayerName", taxpayerInfo.getTaxpayerAddress().getTaxpayerName());
//		paramMap.put("tin", taxpayerInfo.getTin());
//		paramMap.put("houseNumber", taxpayerInfo.getTaxpayerAddress().getHouseNumber());
//		paramMap.put("mooNumber", taxpayerInfo.getTaxpayerAddress().getMooNumber());
//		paramMap.put("trokSoiName", taxpayerInfo.getTaxpayerAddress().getTrokSoiName());
//		paramMap.put("streetName", taxpayerInfo.getTaxpayerAddress().getStreetName());
//		paramMap.put("thambolName", taxpayerInfo.getTaxpayerAddress().getThambolName());
//		paramMap.put("amphurName", taxpayerInfo.getTaxpayerAddress().getAmphurName());
//		paramMap.put("provinceName", taxpayerInfo.getTaxpayerAddress().getProvinceName());
//		paramMap.put("postcode", taxpayerInfo.getTaxpayerAddress().getPostcode());
//		paramMap.put("telNumber", taxpayerInfo.getTaxpayerAddress().getTelNumber());
//		
//		// TODO Add paymentTax for Fund Report
//		
//		return ReportUtil.complieReportWithJrxml(fileName, paramMap);
//	}

}
