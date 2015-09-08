package th.go.excise.edbarcode.report.common.util;

import java.io.File;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.bean.XmlData;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public class EDBarcodeReportUtil {
	
	private static final Logger logger = LogManager.getLogger(EDBarcodeReportUtil.class);
	
	public static SR12011FormReport prepareDataWithXmlString(String xml) throws EDBarcodeReportException {
		logger.info("Inside prepareData() - Start");
		
		logger.debug("XML: " + xml);
		
		SR12011FormReport form = null;
		try {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(SR12011FormReport.class).createUnmarshaller();
			
			StringReader reader = new StringReader(xml);
			form = (SR12011FormReport) jaxbUnmarshaller.unmarshal(reader);
			
			//logger.debug("Object: \n" + form.toString());

		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		logger.info("Inside prepareData() - End");
		
		return form;
	}
	
	public static SR12011FormReport prepareDataWithXmlFile(String xmlFile) throws EDBarcodeReportException {
		logger.info("Inside prepareData() - Start");
		
		logger.debug("XML File: " + xmlFile);
		
		XmlData xmlData = null;
		SR12011FormReport form = null;
		
		try {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(XmlData.class).createUnmarshaller();
			
			File file = new File(xmlFile);
			logger.debug("file.isFile(): " + file.isFile());
			logger.debug("file.canRead(): " + file.canRead());
			
			xmlData = (XmlData) jaxbUnmarshaller.unmarshal(file);
			form = xmlData.getSr12011FormReport();
			
			// Set Extra parameter
			form.getTaxpayerInfoReport().setCusId(xmlData.getSubmitOnlineHeader().getCusId());
			form.getTaxpayerInfoReport().setTaxpayerId(xmlData.getSubmitOnlineHeader().getTaxpayerId());
			form.getSummaryReport().setSubmissionDate(xmlData.getSubmitOnlineHeader().getSubmissionDate());
			
			//logger.debug("Object: \n" + form.toString());
			
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		logger.info("Inside prepareData() - End");
		
		return form;
	}
	
	public static String blankToZero(String input) {
		if (StringUtils.isEmpty(input)) {
			return "0";
		} else {
			return input;
		}
	}
	
	/*
	 * Input will format yyyyMMdd in US
	 */
	public static String toThaiDateFormat(String input) throws ParseException {
		SimpleDateFormat dateFormatWS = new SimpleDateFormat(ReportConstant.DATE_FORMAT.WS, Locale.UK);
		SimpleDateFormat dateFormatThai = new SimpleDateFormat(ReportConstant.DATE_FORMAT.SHORT, new Locale("th", "TH"));
		Date date = dateFormatWS.parse(input);
		return dateFormatThai.format(date);
	}
	
}