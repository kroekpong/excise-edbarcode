package th.go.excise.edbarcode.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocDetail;
import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocMaster;
import th.go.excise.edbarcode.demo.bean.EntrepreneurDemo;
import th.go.excise.edbarcode.demo.bean.ExciseDataFromRefCode;
import th.go.excise.edbarcode.demo.bean.ExciseTaxSummaryDemo;
import th.go.excise.edbarcode.demo.bean.ProductDemo;
import th.go.excise.edbarcode.demo.dao.ExciseDemoDao;

@Service("exciseDemoService")
public class ExciseDemoServiceImpl implements ExciseDemoService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private ExciseDemoDao exciseDemoDao;
	
	@Override
	public EntrepreneurDemo getEntrepreneurDetail(String licenseNo) {
		logger.info("Inside getEntrepreneurDetail()");
		
		return exciseDemoDao.getEntrepreneurDetail(licenseNo);
	}

	@Override
	public ProductDemo getProductDetail(String productCode) {
		logger.info("Inside getProductDetail()");
		
		return exciseDemoDao.getProductDetail(productCode);
	}

	@Override
	public ExciseDataFromRefCode getDataFromRefCode(String referenceCode) {
		logger.info("Inside getDataFromRefCode()");
		
		TmpTaxDocMaster master = exciseDemoDao.getTmpTaxDocMaster(referenceCode);
		List<TmpTaxDocDetail> detailList = exciseDemoDao.getTmpTaxDocDetail(referenceCode);
		
		EntrepreneurDemo entDemo = exciseDemoDao.getEntrepreneurDetail(master.getLicenseNo());
		
		List<ProductDemo> productList = new ArrayList<ProductDemo>();
		ProductDemo productDemo = null;
		for (TmpTaxDocDetail detail : detailList) {
			productDemo = exciseDemoDao.getProductDetail(detail.getProductCode());
			productDemo.setProductCode(detail.getProductCode());
			productDemo.setPiece(new BigDecimal(detail.getProductPiece()));
			productDemo.setSellingPriceByOwner(detail.getSellingPriceByOwner());
			productDemo.setSellingPriceByDepartment(detail.getSellingPriceByDepartment());
			productDemo.setTaxByValue(detail.getTaxByValue());
			productDemo.setTaxByCapacity(detail.getTaxByCapacity());
			productDemo.setTaxByValuePlus(detail.getTaxByValuePlus());
			productList.add(productDemo);
		}
		
		ExciseTaxSummaryDemo summaryDemo = new ExciseTaxSummaryDemo();
		summaryDemo.setSumTaxByValue(master.getSumTaxByValue());
		summaryDemo.setSumTaxByCapacity(master.getSumTaxByCapacity());
		summaryDemo.setReceipt(master.getReceipt());
		summaryDemo.setReduceTaxProductBaht(master.getReduceTaxProductBaht());
		summaryDemo.setReduceTaxByDepBookNoBaht(master.getReduceTaxByDepBookNoBaht());
		summaryDemo.setTaxByMOI(master.getTaxByMOI());
		summaryDemo.setTaxByThaiHealth(master.getTaxByThaiHealth());
		summaryDemo.setTaxByThaiPBS(master.getTaxByThaiPBS());
		summaryDemo.setTaxByNSDF(master.getTaxByNSDF());
		
		ExciseDataFromRefCode data = new ExciseDataFromRefCode();
		data.setEntrepreneur(entDemo);
		data.setProcudeList(productList);
		data.setSummary(summaryDemo);
		
		return data;
	}
	
}
