package th.go.excise.edbarcode.report.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;

/**
 * @Author: Taechapon Himarat (Su)
 * @Create: September 11, 2013
 */
public class ReportUtil {
	
	private static final Logger logger = LogManager.getLogger(ReportUtil.class);
	
	public static InputStream getReportFile(String fileName) {
		logger.debug("Inside - getReportFile()");
		String inputPath = ReportConstant.rbMyReport.getString("path.input.jrxml");
		logger.debug("inputPath: " + inputPath);
		// App Path
		InputStream jrxmlFile = ReportUtil.class.getResourceAsStream(inputPath + fileName);
		if (jrxmlFile == null) {
			// Web Path
			jrxmlFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath + fileName);
		}
		logger.debug("jrxmlFile: " + jrxmlFile);
		return jrxmlFile;
	}
	
	// For Test
	public static String getReportOutputPath(String fileName) throws IOException {
		String outputPath = ReportConstant.rbMyReport.getString("path.output");
		File outputFile = new File(outputPath);
		if (!outputFile.isDirectory()) {
			throw new IOException("Wrong Output Directory");
		}
		outputFile = new File(outputFile.getPath() + File.separator + fileName);
		logger.debug("outputFile: " + outputFile.getAbsolutePath());
		return outputFile.getPath();
	}
	
	public static InputStream getImageFile(String imageName) {
		logger.debug("Inside - getImageFile()");
		String inputPath = ReportConstant.rbMyReport.getString("path.input.image");
		logger.debug("inputPath: " + inputPath);
		// App Path
		InputStream imageFile = ReportUtil.class.getResourceAsStream(inputPath + imageName + "." + ReportConstant.FILE.PNG);
		if (imageFile == null) {
			// Web Path
			imageFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath + imageName + "." + ReportConstant.FILE.PNG);
		}
		logger.debug("imageFile: " + imageFile);
		return imageFile;
	}
	
	public static JasperPrint complieReportWithJrxml(String jrxmlFileName, Map<String, Object> paramMap) throws JRException, IOException {
		logger.info("Complie Report from jrxml file");
		
		InputStream jrxmlFile = getReportFile(jrxmlFileName + "." + ReportConstant.FILE.JRXML);
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
		return jasperPrint;
	}
	
	public static JasperPrint complieReportWithJrxml(String jrxmlFileName, Map<String, Object> paramMap, JRAbstractBeanDataSource dataSource) throws JRException, IOException {
		logger.info("Complie Report from jrxml file");
		
		InputStream jrxmlFile = getReportFile(jrxmlFileName + "." + ReportConstant.FILE.JRXML);
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
		return jasperPrint;
	}
	
	public static JasperPrint getJasperPrintWithJasper(String jasperFileName, Map<String, Object> paramMap) throws JRException, IOException {
		logger.info("Get JasperPrint from jasper file");
		
		InputStream jrxmlFile = getReportFile(jasperFileName + "." + ReportConstant.FILE.JASPER);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jrxmlFile, paramMap, new JREmptyDataSource());
		return jasperPrint;
	}
	
	public static JasperPrint getJasperPrintWithJasper(String jasperFileName, Map<String, Object> paramMap, JRAbstractBeanDataSource dataSource) throws JRException, IOException {
		logger.info("Get JasperPrint from jasper file");
		
		InputStream jrxmlFile = getReportFile(jasperFileName + "." + ReportConstant.FILE.JASPER);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jrxmlFile, paramMap, dataSource);
		return jasperPrint;
	}
	
}
