package th.go.excise.edbarcode.report.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;

public class EDBarcodeReportApp {
	
	private static final Logger logger = LogManager.getLogger(EDBarcodeReportApp.class);
	
	/*
	 * For command mode, support for call by ED-Barcode Application
	 */
	public static void main(String[] args) {
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			String xmlFile = null;
			String outputPath = null;
			String referenceNumber = null;
			if (args.length == 2) {
				xmlFile = args[0];
				outputPath = args[1];
				reportService.generateReport(xmlFile, outputPath);
			} else if (args.length == 3) {
				xmlFile = args[0];
				outputPath = args[1];
				referenceNumber = args[2];
				reportService.generateReport(xmlFile, outputPath, referenceNumber);
			} else {
				logger.warn("Wrong no. of input, can not create pdf file.");
			}
		} catch (EDBarcodeReportException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
