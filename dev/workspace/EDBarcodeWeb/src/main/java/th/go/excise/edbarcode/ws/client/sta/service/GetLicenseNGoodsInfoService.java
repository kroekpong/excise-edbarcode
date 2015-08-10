package th.go.excise.edbarcode.ws.client.sta.service;

import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

public interface GetLicenseNGoodsInfoService {
	
	public StaBacResponse doService(StaBacRequest request);
	
}
