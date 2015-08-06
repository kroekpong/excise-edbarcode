package th.go.excise.edbarcode.ws.provider.endpoint;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;
import th.go.excise.edbarcode.ws.provider.oxm.PDFDocument;

@Endpoint
public class GetSR12011ReportEndpoint {
	
	private static final Log logger = LogFactory.getLog(GetSR12011ReportEndpoint.class);
	
	private EDBarcodeReportService barcodeReportService = new EDBarcodeReportServiceImpl();
	
	@PayloadRoot(localPart = "EbarcodeGetSR12011ReportRequest", namespace = WebServiceConstant.NAMESPACE_URI)
	@ResponsePayload
	public EbarcodeGetSR12011ReportResponse doEndpoint(@RequestPayload EbarcodeGetSR12011ReportRequest request) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint GetSR12011ReportRequest");
		
		EbarcodeGetSR12011ReportResponse response = null;
		
		try {
			String referenceNumber = request.getReferenceNumber();
			String xmlData = request.getSR12011ReportData();
			
			byte[] content = barcodeReportService.generateReport(xmlData, referenceNumber);
			
			PDFDocument document = new PDFDocument();
			document.setMimetype("application/pdf");
			document.setContent(content);
			
			response = new EbarcodeGetSR12011ReportResponse();
			response.setStatusCode("OK");
			response.setPDFDocument(document);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeGetSR12011ReportResponse();
			response.setStatusCode("ERROR");
		}
		
		return response;
	}
	
}
