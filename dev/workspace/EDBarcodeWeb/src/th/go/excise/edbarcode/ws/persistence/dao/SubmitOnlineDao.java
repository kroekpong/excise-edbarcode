package th.go.excise.edbarcode.ws.persistence.dao;

import java.util.List;

import th.go.excise.edbarcode.ws.persistence.model.TmpTaxDocDetail;
import th.go.excise.edbarcode.ws.persistence.model.TmpTaxDocMaster;

public interface SubmitOnlineDao {
	
	public int createTempTaxDocMaster(TmpTaxDocMaster master);
	
	public void createTempTaxDocDetail(List<TmpTaxDocDetail> detailList);
	
}
