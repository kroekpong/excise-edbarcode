package th.go.excise.edbarcode.report.service;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public interface EDBarcodeReportService {
	
	public byte[] generateReport(String xml, String referenceNumber) throws EDBarcodeReportException;
	
	public int generateReport(String xmlFile, String outputPath, String mode) throws EDBarcodeReportException;
	
}
