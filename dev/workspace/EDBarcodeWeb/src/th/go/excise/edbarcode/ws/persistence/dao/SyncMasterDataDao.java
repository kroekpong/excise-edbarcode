package th.go.excise.edbarcode.ws.persistence.dao;

import java.util.List;

import th.go.excise.edbarcode.ws.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.oxm.Product;

public interface SyncMasterDataDao {
	
	public Entrepreneur getEntrepreneur(String licenseNo);
	
	public List<Product> getProductListByEnt(String licenseNo);
	
}
