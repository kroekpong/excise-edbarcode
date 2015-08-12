package th.go.excise.edbarcode.report.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.common.util.EDBarcodeReportUtil;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;

public class TestSR12011Report {
	
	//@Test
	public void testConvertXml2Object() {
		String xmlFile = "C:\\Users\\SU\\Desktop\\example_SR12011.txt";
		try {
			EDBarcodeReportUtil.prepareDataWithXmlFile(xmlFile);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testWithXmlFile() {
		String xmlFile = "C:\\Users\\SU\\Desktop\\xmlData.txt";
		String outputPath = "C:\\iReport\\TEST\\";
		String mode = "S";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			reportService.generateReport(xmlFile, outputPath, mode);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
//		System.out.println("45678.99".split("\\.")[0]);
//		System.out.println(new DecimalFormat("#,##0").format(new BigDecimal("5679900")));
	}

}
