package th.go.excise.edbarcode.report.service;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public interface EDBarcodeReportService {
	
	public void generateReport(String xml, String outputFile) throws EDBarcodeReportException;
	
	public int generateReport(String xmlFile, String outputPath, String mode) throws EDBarcodeReportException;
	
}
