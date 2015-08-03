package th.go.excise.edbarcode.ws.client.service;

import th.go.excise.edbarcode.ws.client.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.oxm.StaBacResponse;

public interface GetLicenseNGoodsInfoService {
	
	public StaBacResponse doService(StaBacRequest request);
	
}
