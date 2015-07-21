package th.go.excise.edbarcode.ws.provider.dao;

import java.util.List;

import th.go.excise.edbarcode.ws.provider.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.provider.oxm.Product;

public interface SyncMasterDataDao {
	
	public Entrepreneur getEntrepreneur(String taxNo);
	
	public List<Product> getProductListByEnt(String taxNo);
	
}
