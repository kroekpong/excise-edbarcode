package th.go.excise.edbarcode.ws.provider.service;

import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataResponse;

public interface SyncMasterDataService {
	
	public EbarcodeSyncMasterDataResponse getResponse(EbarcodeSyncMasterDataRequest request);
	
}
