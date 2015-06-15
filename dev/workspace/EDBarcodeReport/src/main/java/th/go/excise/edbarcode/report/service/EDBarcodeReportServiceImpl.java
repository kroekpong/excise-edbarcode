package th.go.excise.edbarcode.report.service;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.go.excise.edbarcode.report.common.bean.SR12011Form;
import th.go.excise.edbarcode.report.common.bean.TaxpayerInformation;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class EDBarcodeReportServiceImpl implements EDBarcodeReportService {
	
	private static final Log logger = LogFactory.getLog(EDBarcodeReportServiceImpl.class);
	
	@Override
	public void generateReport(String xml, String outputFile) throws EDBarcodeReportException {
		logger.info("Generate Report - Start");
		long start = System.currentTimeMillis();
		
		logger.info("Output File: " + outputFile);
		
		try {
			List<ExporterInputItem> items = getExportInputItemList(xml);
			
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

	private List<ExporterInputItem> getExportInputItemList(String xml) throws EDBarcodeReportException {
		
		SR12011Form form = prepareData(xml);
		
		JasperPrint sr12011Report;
		JasperPrint sss101Report;
		JasperPrint sst101Report;
		JasperPrint kkt101Report;
		try {
			sr12011Report = getSR12011Report(form);
			sss101Report = getSSS101Report(form);
			sst101Report = getSST101Report(form);
			kkt101Report = getKKT101Report(form);
		} catch (JRException e) {
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
	
	private SR12011Form prepareData(String xml) throws EDBarcodeReportException {
		logger.info("Inside prepareData() - Start");
		
		logger.debug("XML: " + xml);
		
		SR12011Form form = null;
		try {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(SR12011Form.class).createUnmarshaller();
			
			StringReader reader = new StringReader(xml);
			form = (SR12011Form) jaxbUnmarshaller.unmarshal(reader);
			
			logger.debug("Object: " + form.toString());

		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		logger.info("Inside prepareData() - End");
		
		return form;
	}
	
	private JasperPrint getSR12011Report(SR12011Form form) throws JRException {
		logger.info("Inside getSR12011Report() - Start");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyName", form.getTaxpayerInformation().getCompanyName());
		paramMap.put("taxpayerName", form.getTaxpayerInformation().getTaxpayerAddress().getTaxpayerName());
		paramMap.put("licenseNo", form.getTaxpayerInformation().getLicenseInfo().getLicenseNo());
		paramMap.put("effectiveDate", form.getTaxpayerInformation().getLicenseInfo().getEffectiveDate());
		paramMap.put("expireDate", form.getTaxpayerInformation().getLicenseInfo().getExpireDate());
		paramMap.put("tin", form.getTaxpayerInformation().getTin());
		paramMap.put("taxpayerAddress", "MOCK ADDRESS");
		paramMap.put("logoImage", ReportUtil.getImagePath(ReportConstant.REPORT.SR120_11));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		JasperPrint jasperPrint = complieReportWithJrxml(ReportConstant.REPORT.SR120_11, paramMap, datasource);
		
		logger.info("Inside getSR12011Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSSS101Report(SR12011Form form) throws JRException {
		logger.info("Inside getSSS101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImagePath(ReportConstant.REPORT.SSS1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = complieReportWithJrxml(ReportConstant.REPORT.SSS1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getSSS101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getSST101Report(SR12011Form form) throws JRException {
		logger.info("Inside getSST101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImagePath(ReportConstant.REPORT.SST1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = complieReportWithJrxml(ReportConstant.REPORT.SST1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getSST101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getKKT101Report(SR12011Form form) throws JRException {
		logger.info("Inside getKKT101Report() - Start");
		//<Sharge
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("logoImage", ReportUtil.getImagePath(ReportConstant.REPORT.KKT1_01));
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(form.getGoodsList(), true);
		
		JasperPrint jasperPrint = complieReportWithJrxml(ReportConstant.REPORT.KKT1_01, paramMap, datasource);
		//Sharge>
		logger.info("Inside getKKT101Report() - End");
		
		return jasperPrint;
	}
	
	private JasperPrint getFundReport(String fileName, TaxpayerInformation taxpayerInfo) throws JRException {
		logger.info("Prepare data for Fund Report");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyName", taxpayerInfo.getCompanyName());
		paramMap.put("taxpayerName", taxpayerInfo.getTaxpayerAddress().getTaxpayerName());
		paramMap.put("tin", taxpayerInfo.getTin());
		paramMap.put("houseNumber", taxpayerInfo.getTaxpayerAddress().getHouseNumber());
		paramMap.put("mooNumber", taxpayerInfo.getTaxpayerAddress().getMooNumber());
		paramMap.put("trokSoiName", taxpayerInfo.getTaxpayerAddress().getTrokSoiName());
		paramMap.put("streetName", taxpayerInfo.getTaxpayerAddress().getStreetName());
		paramMap.put("thambolName", taxpayerInfo.getTaxpayerAddress().getThambolName());
		paramMap.put("amphurName", taxpayerInfo.getTaxpayerAddress().getAmphurName());
		paramMap.put("provinceName", taxpayerInfo.getTaxpayerAddress().getProvinceName());
		paramMap.put("postcode", taxpayerInfo.getTaxpayerAddress().getPostcode());
		paramMap.put("telNumber", taxpayerInfo.getTaxpayerAddress().getTelNumber());
		
		// TODO Add paymentTax for Fund Report
		
		return complieReportWithJrxml(fileName, paramMap);
	}
	
	private JasperPrint complieReportWithJrxml(String jrxmlFile, Map<String, Object> paramMap) throws JRException {
		logger.info("Complie Report from jrxml file");
		logger.debug("jrxmlFile: " + jrxmlFile);
		
		InputStream inputStream = ReportUtil.getReportInputStream(jrxmlFile + "." + ReportConstant.FILE.JRXML);
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
		return jasperPrint;
	}
	
	private JasperPrint complieReportWithJrxml(String jrxmlFile, Map<String, Object> paramMap, JRAbstractBeanDataSource dataSource) throws JRException {
		logger.info("Complie Report from jrxml file");
		logger.debug("jrxmlFile: " + jrxmlFile);
		
		InputStream inputStream = ReportUtil.getReportInputStream(jrxmlFile + "." + ReportConstant.FILE.JRXML);
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
		return jasperPrint;
	}
	
	private JasperPrint getJasperPrintWithJasper(String jasperFile, Map<String, Object> paramMap) throws JRException {
		logger.info("Get JasperPrint from jasper file");
		logger.debug("jasperFile: " + jasperFile);
		
		InputStream inputStream = ReportUtil.getReportInputStream(jasperFile + "." + ReportConstant.FILE.JASPER);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramMap, new JREmptyDataSource());
		return jasperPrint;
	}
	
	private JasperPrint getJasperPrintWithJasper(String jasperFile, Map<String, Object> paramMap, JRAbstractBeanDataSource dataSource) throws JRException {
		logger.info("Get JasperPrint from jasper file");
		logger.debug("jasperFile: " + jasperFile);
		
		InputStream inputStream = ReportUtil.getReportInputStream(jasperFile + "." + ReportConstant.FILE.JASPER);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramMap, dataSource);
		return jasperPrint;
	}
	
}
