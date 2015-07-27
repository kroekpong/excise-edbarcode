package th.go.excise.edbarcode.ws.client.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.oxm.Address;
import th.go.excise.edbarcode.ws.client.oxm.Body;
import th.go.excise.edbarcode.ws.client.oxm.Company;
import th.go.excise.edbarcode.ws.client.oxm.Goods;
import th.go.excise.edbarcode.ws.client.oxm.GoodsList;
import th.go.excise.edbarcode.ws.client.oxm.HeaderResponse;
import th.go.excise.edbarcode.ws.client.oxm.LicenseInfo;
import th.go.excise.edbarcode.ws.client.oxm.LicenseList;
import th.go.excise.edbarcode.ws.client.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.oxm.StaBacResponse;
import th.go.excise.edbarcode.ws.client.oxm.TaxpayerDetail;
import th.go.excise.edbarcode.ws.client.oxm.TaxpayerList;

@Service("getLicenseNGoodsInfoService")
public class GetLicenseNGoodsInfoServiceImpl implements GetLicenseNGoodsInfoService {
	
	private static final Logger logger = LogManager.getLogger();
	
//	@Autowired
//	private WebServiceTemplate webServiceTemplate;
	
	@Override
	public StaBacResponse doService(StaBacRequest request) {
		StaBacResponse response = new StaBacResponse();
		
		logger.info(" ##################################### Step 1  In EAUT SubmitUploadInfoService.doService");
		
		//response = (StaBacResponse) webServiceTemplate.marshalSendAndReceive(request);
		
		// Mock Data
		HeaderResponse wsHeader = new HeaderResponse();
		wsHeader.setResultCode(WebServiceConstant.STA_HEADER.RESULT_CODE_OK);
		response.setHeader(wsHeader);
		
		
		Company wsCompany = new Company();
		wsCompany.setInternetUniqueId("");
		wsCompany.setCusId("51175744");
		wsCompany.setCompanyId("3031163126");
		wsCompany.setCompanyTitleCode("0013");
		wsCompany.setCompanyTitleName("บ.");
		wsCompany.setCompanyName("บริษัท สุราสรรพสามิต จำกัด");
		wsCompany.setCompanyBranchCode("0");
		wsCompany.setExciseOfficeId("060102");
		wsCompany.setCompanyUserPwd("9525-128-14-74-54121-10365-8-46-6833-2-90");
		
		Address wsCompanyAddress = new Address();
		wsCompanyAddress.setHouseIdNumber("");
		wsCompanyAddress.setBuildingName("");
		wsCompanyAddress.setRoomNumber("");
		wsCompanyAddress.setFloorNumber("");
		wsCompanyAddress.setVillageName("");
		wsCompanyAddress.setHouseNumber("157");
		wsCompanyAddress.setMooNumber("7");
		wsCompanyAddress.setTrokSoiName("-");
		wsCompanyAddress.setStreetName("-");
		wsCompanyAddress.setThambolCode("630114");
		wsCompanyAddress.setThambolName("วังประจบ");
		wsCompanyAddress.setAmphurCode("6301");
		wsCompanyAddress.setAmphurName("เมืองตาก");
		wsCompanyAddress.setProvinceCode("63");
		wsCompanyAddress.setProvinceName("ตาก");
		wsCompanyAddress.setPostcode("63000");
		wsCompanyAddress.setTelNumber("06-0215411");
		wsCompanyAddress.setEmailAddress("");
		wsCompany.setCompanyAddress(wsCompanyAddress);
		
		TaxpayerList wsTaxpayerList = new TaxpayerList();
		
		// Factory 1
		TaxpayerDetail wsTaxpayerDetail1 = getFactory1();
		wsTaxpayerList.getTaxpayerDetail().add(wsTaxpayerDetail1);
		
		// Factory 2
		TaxpayerDetail wsTaxpayerDetail2 = getFactory2();
		wsTaxpayerList.getTaxpayerDetail().add(wsTaxpayerDetail2);
		
		wsCompany.setTaxpayerList(wsTaxpayerList);
		
		Body wsBody = new Body();
		wsBody.setCompany(wsCompany);
		response.setBody(wsBody);
		
		logger.info(" ##################################### After Call WSSupport response:  "+response);
		
		return response;
	}

	private TaxpayerDetail getFactory1() {
		
		TaxpayerDetail wsTaxpayerDetail = new TaxpayerDetail();
		wsTaxpayerDetail.setTaxpayerId("49L15746");
		wsTaxpayerDetail.setTaxpayerName("ตะวันแดงสุรากลั่น");
		
		Address taxpayerAddress = new Address();
		taxpayerAddress.setHouseIdNumber("");
		taxpayerAddress.setBuildingName("");
		taxpayerAddress.setRoomNumber("");
		taxpayerAddress.setFloorNumber("");
		taxpayerAddress.setVillageName("");
		taxpayerAddress.setHouseNumber("");
		taxpayerAddress.setMooNumber("7");
		taxpayerAddress.setTrokSoiName("-");
		taxpayerAddress.setStreetName("0026");
		taxpayerAddress.setThambolCode("630114");
		taxpayerAddress.setThambolName("");
		taxpayerAddress.setAmphurCode("");
		taxpayerAddress.setAmphurName("");
		taxpayerAddress.setProvinceCode("");
		taxpayerAddress.setProvinceName("");
		taxpayerAddress.setPostcode("63000");
		taxpayerAddress.setTelNumber("06-0215411");
		taxpayerAddress.setEmailAddress("");
		wsTaxpayerDetail.setTaxpayerAddress(taxpayerAddress);
		
		LicenseList wsLicenseList = new LicenseList();
		LicenseInfo wsLicenseInfo = new LicenseInfo();
		wsLicenseInfo.setLicenseNo("60305817500001");
		wsLicenseInfo.setLicenseType("ส.1/72");
		wsLicenseInfo.setLicenseCode("18");
		wsLicenseInfo.setLicenseSeq("");
		wsLicenseInfo.setLicenseName("สุรากลั่น (สุราชุมชน)");
		wsLicenseInfo.setLicenseCurrentPrice(0D);
		wsLicenseInfo.setEffectiveDate("20150115");
		wsLicenseInfo.setExpireDate("20151231");
		wsLicenseInfo.setFirstDate("");
		wsLicenseInfo.setOfficeCode("");
		wsLicenseInfo.setLicBook("2558");
		wsLicenseInfo.setLicDate("20150115");
		wsLicenseInfo.setDocNo("255800000144");
		wsLicenseInfo.setDocType("05");
		wsLicenseInfo.setLineNo("2");
		wsLicenseList.getLicenseInfo().add(wsLicenseInfo);
		wsTaxpayerDetail.setLicenseList(wsLicenseList);
		
		GoodsList wsGoodsList = new GoodsList();
		
		Goods goods1 = new Goods();
		goods1.setGoodsCode("70010101#001,00,000,008,97#1#0#00##-");
		goods1.setProductTypeCode("70010101");
		goods1.setProductTypeDescriptionText("เบียร์");
		goods1.setGoodsDescriptionText("สิงห์ OLD 0.330 4.9000");
		goods1.setGoodsPrice(0D);
		goods1.setGoodsSize("0.330");
		goods1.setGoodsSizeUnitDescriptionText("");
		goods1.setTaxRateByPriceAmount(0D);
		goods1.setTaxRateByQuantityAmount(0D);
		goods1.setFundSSSRateAmount(0D);
		goods1.setFundSSTRateAmount(0D);
		goods1.setDegree("4.9000");
		goods1.setPriceFlag("P");
		goods1.setDeclarePrice(0D);
		goods1.setUnitCode("00");
		goods1.setGoodsUnitsDescriptionText("");
		goods1.setIncomeCode("204020");
		goods1.setSeqNo("1");
		goods1.setBrandName("สิงห์ OLD");
		goods1.setSubbrandName("");
		goods1.setModelName("");
		goods1.setInformDate("");
		goods1.setProductNameEng("");
		goods1.setRatePerLitre(0D);
		goods1.setDegreeMin(0D);
		goods1.setRateDegreeOver(0D);
		goods1.setWholesaleMin(0D);
		goods1.setRateWholesaleOver(0D);
		goods1.setRatePerLitreMax(0D);
		wsGoodsList.getGoods().add(goods1);
		
		Goods goods3 = new Goods();
		goods3.setGoodsCode("");
		goods3.setProductTypeCode("");
		goods3.setProductTypeDescriptionText("");
		goods3.setGoodsDescriptionText("");
		goods3.setGoodsPrice(0D);
		goods3.setGoodsSize("");
		goods3.setGoodsSizeUnitDescriptionText("");
		goods3.setTaxRateByPriceAmount(0D);
		goods3.setTaxRateByQuantityAmount(0D);
		goods3.setFundSSSRateAmount(0D);
		goods3.setFundSSTRateAmount(0D);
		goods3.setDegree("");
		goods3.setPriceFlag("");
		goods3.setDeclarePrice(0D);
		goods3.setUnitCode("");
		goods3.setGoodsUnitsDescriptionText("");
		goods3.setIncomeCode("");
		goods3.setSeqNo("");
		goods3.setBrandName("");
		goods3.setSubbrandName("");
		goods3.setModelName("");
		goods3.setInformDate("");
		goods3.setProductNameEng("");
		goods3.setRatePerLitre(0D);
		goods3.setDegreeMin(0D);
		goods3.setRateDegreeOver(0D);
		goods3.setWholesaleMin(0D);
		goods3.setRateWholesaleOver(0D);
		goods3.setRatePerLitreMax(0D);
		wsGoodsList.getGoods().add(goods3);
		
		wsTaxpayerDetail.setGoodsList(wsGoodsList);
		
		return wsTaxpayerDetail;
	}
	
	private TaxpayerDetail getFactory2() {
		TaxpayerDetail wsTaxpayerDetail = new TaxpayerDetail();
		wsTaxpayerDetail.setTaxpayerId("");
		wsTaxpayerDetail.setTaxpayerName("");
		
		Address taxpayerAddress = new Address();
		taxpayerAddress.setHouseIdNumber("");
		taxpayerAddress.setBuildingName("");
		taxpayerAddress.setRoomNumber("");
		taxpayerAddress.setFloorNumber("");
		taxpayerAddress.setVillageName("");
		taxpayerAddress.setHouseNumber("");
		taxpayerAddress.setMooNumber("");
		taxpayerAddress.setTrokSoiName("");
		taxpayerAddress.setStreetName("");
		taxpayerAddress.setThambolCode("");
		taxpayerAddress.setThambolName("");
		taxpayerAddress.setAmphurCode("");
		taxpayerAddress.setAmphurName("");
		taxpayerAddress.setProvinceCode("");
		taxpayerAddress.setProvinceName("");
		taxpayerAddress.setPostcode("");
		taxpayerAddress.setTelNumber("");
		taxpayerAddress.setEmailAddress("");
		wsTaxpayerDetail.setTaxpayerAddress(taxpayerAddress);
		
		LicenseList wsLicenseList = new LicenseList();
		LicenseInfo wsLicenseInfo = new LicenseInfo();
		wsLicenseInfo.setLicenseNo("");
		wsLicenseInfo.setLicenseType("");
		wsLicenseInfo.setLicenseCode("");
		wsLicenseInfo.setLicenseSeq("");
		wsLicenseInfo.setLicenseName("");
		wsLicenseInfo.setLicenseCurrentPrice(0D);
		wsLicenseInfo.setEffectiveDate("");
		wsLicenseInfo.setExpireDate("");
		wsLicenseInfo.setFirstDate("");
		wsLicenseInfo.setOfficeCode("");
		wsLicenseInfo.setLicBook("");
		wsLicenseInfo.setLicDate("");
		wsLicenseInfo.setDocNo("");
		wsLicenseInfo.setDocType("");
		wsLicenseInfo.setLineNo("");
		wsLicenseList.getLicenseInfo().add(wsLicenseInfo);
		wsTaxpayerDetail.setLicenseList(wsLicenseList);
		
		GoodsList wsGoodsList = new GoodsList();
		
		Goods goods1 = new Goods();
		goods1.setGoodsCode("");
		goods1.setProductTypeCode("");
		goods1.setProductTypeDescriptionText("");
		goods1.setGoodsDescriptionText("");
		goods1.setGoodsPrice(0D);
		goods1.setGoodsSize("");
		goods1.setGoodsSizeUnitDescriptionText("");
		goods1.setTaxRateByPriceAmount(0D);
		goods1.setTaxRateByQuantityAmount(0D);
		goods1.setFundSSSRateAmount(0D);
		goods1.setFundSSTRateAmount(0D);
		goods1.setDegree("");
		goods1.setPriceFlag("");
		goods1.setDeclarePrice(0D);
		goods1.setUnitCode("");
		goods1.setGoodsUnitsDescriptionText("");
		goods1.setIncomeCode("");
		goods1.setSeqNo("");
		goods1.setBrandName("");
		goods1.setSubbrandName("");
		goods1.setModelName("");
		goods1.setInformDate("");
		goods1.setProductNameEng("");
		goods1.setRatePerLitre(0D);
		goods1.setDegreeMin(0D);
		goods1.setRateDegreeOver(0D);
		goods1.setWholesaleMin(0D);
		goods1.setRateWholesaleOver(0D);
		goods1.setRatePerLitreMax(0D);
		wsGoodsList.getGoods().add(goods1);
		
		wsTaxpayerDetail.setGoodsList(wsGoodsList);
		
		return wsTaxpayerDetail;
	}

}
