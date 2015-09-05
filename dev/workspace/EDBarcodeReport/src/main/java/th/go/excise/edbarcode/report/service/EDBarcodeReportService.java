package th.go.excise.edbarcode.report.service;

import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;

public interface EDBarcodeReportService {
	
	public byte[] generateReport(SR12011FormReport form) throws EDBarcodeReportException;
	
	public int generateReport(String xmlFile, String outputPath) throws EDBarcodeReportException;
	
	public int generateReport(String xmlFile, String outputPath, String referenceNumber) throws EDBarcodeReportException;
	
}
