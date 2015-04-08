package th.go.excise.edbarcode.ws.service;

import th.go.excise.edbarcode.ws.oxm.Entrepreneur;

public interface SyncMasterDataService {
	
	public Entrepreneur getMasterData(String licenseNo);
	
}
