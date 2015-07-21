package th.go.excise.edbarcode.ws.provider.dao;

import java.util.List;

import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocDetail;
import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocMaster;

public interface SubmitOnlineDao {
	
	public int createTempTaxDocMaster(TmpTaxDocMaster master);
	
	public void createTempTaxDocDetail(List<TmpTaxDocDetail> detailList);
	
}
