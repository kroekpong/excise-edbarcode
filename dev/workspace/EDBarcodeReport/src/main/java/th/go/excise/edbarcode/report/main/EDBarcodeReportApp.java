package th.go.excise.edbarcode.report.main;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;

public class EDBarcodeReportApp {
	
	/*
	 * For command mode, support for call by ED-Barcode Application
	 */
	public static void main(String[] args) {
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
//			String xmlFile = "C:\\Users\\SU\\Desktop\\New Text Document (2).txt";
//			String outputPath = "C:\\iReport\\TEST\\";
//			String mode = ReportConstant.MODE.ALL;
			String xmlFile = args[0];
			String outputPath = args[1];
			String mode = args[2];
			reportService.generateReport(xmlFile, outputPath, mode);
		} catch (EDBarcodeReportException e) {
			e.printStackTrace();
		}
	}
	
}
