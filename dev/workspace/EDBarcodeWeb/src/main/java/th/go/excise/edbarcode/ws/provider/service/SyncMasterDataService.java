package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.Entrepreneur;

public interface SyncMasterDataService {
	
	public Entrepreneur getMasterData(String licenseNo);
	
}
