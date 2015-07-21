package th.go.excise.edbarcode.ws.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocDetail;
import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocMaster;
import th.go.excise.edbarcode.ws.provider.dao.SubmitOnlineDao;
import th.go.excise.edbarcode.ws.provider.oxm.AlcoholTaxFormSummary;
import th.go.excise.edbarcode.ws.provider.oxm.ProductTax;

@Service("submitOnlineService")
public class SubmitOnlineServiceImpl implements SubmitOnlineService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private SubmitOnlineDao submitOnlineDao;
	
	@Override
	public String createTmpData(String licenseNo, List<ProductTax> productTaxList, AlcoholTaxFormSummary alcoholTaxFormSummary) {
		logger.info("Inside createTmpData()");
		
		TmpTaxDocMaster master = new TmpTaxDocMaster();
		master.setReferenceCode(genReferenceCode());
		master.setLicenseNo(licenseNo);
		master.setSumTaxByValue(alcoholTaxFormSummary.getSumTaxByValue());
		master.setSumTaxByCapacity(alcoholTaxFormSummary.getSumTaxByCapacity());
		master.setReceipt(alcoholTaxFormSummary.getReceipt());
		master.setReduceTaxProductBaht(alcoholTaxFormSummary.getReduceTaxProductBaht());
		master.setReduceTaxByDepBookNoBaht(alcoholTaxFormSummary.getReduceTaxByDepBookNoBaht());
		master.setTaxByMOI(alcoholTaxFormSummary.getTaxByMOI());
		master.setTaxByThaiHealth(alcoholTaxFormSummary.getTaxByThaiHealth());
		master.setTaxByThaiPBS(alcoholTaxFormSummary.getTaxByThaiPBS());
		master.setTaxByNSDF(alcoholTaxFormSummary.getTaxByNSDF());
		
		int masterKey = submitOnlineDao.createTempTaxDocMaster(master);
		
		List<TmpTaxDocDetail> detailList = new ArrayList<TmpTaxDocDetail>();
		TmpTaxDocDetail detail = null;
		for (ProductTax productTax : productTaxList) {
			detail = new TmpTaxDocDetail();
			detail.setTmpTaxDocMasterId(masterKey);
			detail.setProductCode(productTax.getProductCode());
			detail.setProductPiece(productTax.getPiece().intValue());
			detail.setSellingPriceByOwner(productTax.getSellingPriceByOwner());
			detail.setSellingPriceByDepartment(productTax.getSellingPriceByDepartment());
			detail.setTaxByValue(productTax.getTaxByValue());
			detail.setTaxByCapacity(productTax.getTaxByCapacity());
			detail.setTaxByValuePlus(productTax.getTaxByValuePlus());
			detailList.add(detail);
		}
		submitOnlineDao.createTempTaxDocDetail(detailList);
		
		return master.getReferenceCode();
	}
	
	private String genReferenceCode() {
		StringBuilder builder = new StringBuilder(10);
		builder.append("EX");
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			builder.append(random.nextInt(100) % 10);
		}
		return builder.toString();
	}

}
