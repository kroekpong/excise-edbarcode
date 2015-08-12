package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;

public interface GetSR12011ReportService {
	
	public EbarcodeGetSR12011ReportResponse getResponse(EbarcodeGetSR12011ReportRequest request);
	
}
