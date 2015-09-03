package th.go.excise.edbarcode.report.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public class EDBarcodeReportServiceTest {
	
	private static String ROOT_OUTPUT_PATH = "C:\\iReport\\EXCISE\\";
	private static String PATH_1_PAGE     = "1_Page\\";
	private static String PATH_1_PAGE_REF = "1_Page_Ref\\";
	private static String PATH_X_PAGE     = "X_Page\\";
	private static String PATH_X_PAGE_REF = "X_Page_Ref\\";
	
	@BeforeClass
	public static void clearOutput() throws IOException {
		File outputDirectory = new File(ROOT_OUTPUT_PATH);
		FileUtils.deleteDirectory(outputDirectory);
	}
	
	private String getXmlFile(String fileName) {
		return (new File(ClassLoader.getSystemResource("xml/" + fileName).getPath())).getPath();
	}
	
	@Test
	public void test_GenerateReport_BeerTip_1_Page() {
		String xmlFile = getXmlFile("BeerTip_1_Page.xml");
		String outputPath = ROOT_OUTPUT_PATH + "BeerTip\\" + PATH_1_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, ReportConstant.MODE.ALL);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_BeerTip_1_Page_Ref() {
		String xmlFile = getXmlFile("BeerTip_1_Page.xml");
		String outputPath = ROOT_OUTPUT_PATH + "BeerTip\\" + PATH_1_PAGE_REF;
		
		String referenceNumber = "5840000005";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, ReportConstant.MODE.ALL, referenceNumber);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//@Test
	public void test_GenerateReport_BeerTip_X_Page() {
		String xmlFile = getXmlFile("BeerTip_X_Page.xml");
		String outputPath = ROOT_OUTPUT_PATH + "BeerTip\\" + PATH_X_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, ReportConstant.MODE.ALL);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//@Test
	public void test_GenerateReport_BeerTip_X_Page_Ref() {
		String xmlFile = getXmlFile("BeerTip_X_Page.xml");
		String outputPath = ROOT_OUTPUT_PATH + "BeerTip\\" + PATH_X_PAGE_REF;
		
		String referenceNumber = "5840000005";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, ReportConstant.MODE.ALL, referenceNumber);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
