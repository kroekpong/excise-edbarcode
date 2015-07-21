package th.go.excise.edbarcode.ws.provider.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.ws.provider.dao.SyncMasterDataDao;
import th.go.excise.edbarcode.ws.provider.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.provider.oxm.Product;

@Service("syncMasterDataService")
public class SyncMasterDataServiceImpl implements SyncMasterDataService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SyncMasterDataDao syncMasterDataDao;
	
	@Override
	public Entrepreneur getMasterData(String licenseNo) {
		logger.info("Inside getEntrepreneurData()");
		
		Entrepreneur entrepreneur = syncMasterDataDao.getEntrepreneur(licenseNo);
		List<Product> productList = syncMasterDataDao.getProductListByEnt(licenseNo);
		
		for (Product product : productList) {
			entrepreneur.getProductList().add(product);
		}
		
		return entrepreneur;
	}

}
