package th.go.excise.edbarcode.report.common.util;

import java.io.File;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.go.excise.edbarcode.report.common.bean.SR12011Form;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public class EDBarcodeReportUtil {
	
	private static final Log logger = LogFactory.getLog(EDBarcodeReportUtil.class);
	
	public static SR12011Form prepareDataWithXmlString(String xml) throws EDBarcodeReportException {
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
	
	public static SR12011Form prepareDataWithXmlFile(String xmlFile) throws EDBarcodeReportException {
		logger.info("Inside prepareData() - Start");
		
		logger.debug("XML File: " + xmlFile);
		
		SR12011Form form = null;
		try {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(SR12011Form.class).createUnmarshaller();
			
			File file = new File(xmlFile);
			logger.debug("file.isFile(): " + file.isFile());
			logger.debug("file.canRead(): " + file.canRead());
			
			form = (SR12011Form) jaxbUnmarshaller.unmarshal(file);
			
			logger.debug("Object: " + form.toString());
			
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			throw new EDBarcodeReportException(e.getMessage(), e);
		}
		
		logger.info("Inside prepareData() - End");
		
		return form;
	}
	
}
