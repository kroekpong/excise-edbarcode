package th.go.excise.edbarcode.report.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public class EDBarcodeReportServiceTest {
	
	private static String ROOT_OUTPUT_PATH = "C:\\iReport\\EXCISE\\";
	private static String PATH_1_PAGE     = "1_Page\\";
	private static String PATH_1_PAGE_REF = "1_Page_Ref\\";
	private static String PATH_X_PAGE     = "X_Page\\";
	private static String PATH_X_PAGE_REF = "X_Page_Ref\\";
	
	private static String OUTPUT_PATH_BEER_TIP = ROOT_OUTPUT_PATH +  "BeerTip\\";
	private static String OUTPUT_PATH_MOCK_DATA = ROOT_OUTPUT_PATH +  "MockData\\";
	
	
	@BeforeClass
	public static void clearOutput() throws IOException {
		File[] files = new File[] {
			new File(OUTPUT_PATH_BEER_TIP),
			new File(OUTPUT_PATH_MOCK_DATA)
		};
		for (int i = 0; i < files.length; i++) {
			FileUtils.deleteDirectory(files[i]);
		}
	}
	
	private String getXmlFile(String fileName) {
		return (new File(ClassLoader.getSystemResource("xml/" + fileName).getPath())).getPath();
	}
	
	@Test
	public void test_GenerateReport_BeerTip_1_Page() {
		String xmlFile = getXmlFile("BeerTip_1_Page.xml");
		String outputPath = OUTPUT_PATH_BEER_TIP + PATH_1_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_BeerTip_1_Page_2() {
		String xmlFile = getXmlFile("BeerTip_1_Page_2.xml");
		String outputPath = OUTPUT_PATH_BEER_TIP + "1_Page_2\\";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_BeerTip_1_Page_Ref() {
		String xmlFile = getXmlFile("BeerTip_1_Page.xml");
		String outputPath = OUTPUT_PATH_BEER_TIP + PATH_1_PAGE_REF;
		
		String referenceNumber = "5840000005";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, referenceNumber);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_BeerTip_X_Page() {
		String xmlFile = getXmlFile("BeerTip_X_Page.xml");
		String outputPath = OUTPUT_PATH_BEER_TIP + PATH_X_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_BeerTip_X_Page_Ref() {
		String xmlFile = getXmlFile("BeerTip_X_Page.xml");
		String outputPath = OUTPUT_PATH_BEER_TIP + PATH_X_PAGE_REF;
		
		String referenceNumber = "5840000005";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, referenceNumber);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_MockData_1_Page() {
		String xmlFile = getXmlFile("MockData_1_Page.xml");
		String outputPath = OUTPUT_PATH_MOCK_DATA + PATH_1_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_MockData_X_Page() {
		String xmlFile = getXmlFile("MockData_X_Page.xml");
		String outputPath = OUTPUT_PATH_MOCK_DATA + PATH_X_PAGE;
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_GenerateReport_MockData_X_Page_Ref() {
		String xmlFile = getXmlFile("MockData_X_Page.xml");
		String outputPath = OUTPUT_PATH_MOCK_DATA + PATH_X_PAGE_REF;
		
		String referenceNumber = "5840000005";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, referenceNumber);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
