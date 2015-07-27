package th.go.excise.edbarcode.ws.provider.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.oxm.Body;
import th.go.excise.edbarcode.ws.client.oxm.HeaderRequest;
import th.go.excise.edbarcode.ws.client.oxm.InternetUser;
import th.go.excise.edbarcode.ws.client.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.oxm.StaBacResponse;
import th.go.excise.edbarcode.ws.client.service.GetLicenseNGoodsInfoService;
import th.go.excise.edbarcode.ws.provider.oxm.Address;
import th.go.excise.edbarcode.ws.provider.oxm.Company;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSyncMasterDataResponse;
import th.go.excise.edbarcode.ws.provider.oxm.Goods;
import th.go.excise.edbarcode.ws.provider.oxm.GoodsList;
import th.go.excise.edbarcode.ws.provider.oxm.LicenseInfo;
import th.go.excise.edbarcode.ws.provider.oxm.LicenseList;
import th.go.excise.edbarcode.ws.provider.oxm.TaxpayerDetail;
import th.go.excise.edbarcode.ws.provider.oxm.TaxpayerList;

@Service("syncMasterDataService")
public class SyncMasterDataServiceImpl implements SyncMasterDataService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private GetLicenseNGoodsInfoService getLicenseNGoodsInfoService;

	@Override
	public EbarcodeSyncMasterDataResponse getResponse(EbarcodeSyncMasterDataRequest request) {
		
		StaBacRequest wsRequest = prepareWsRequest(request);
		
		// Call Service
		EbarcodeSyncMasterDataResponse response = null;
		try {
			StaBacResponse wsResponse = getLicenseNGoodsInfoService.doService(wsRequest);
			
			if (WebServiceConstant.STA_HEADER.RESULT_CODE_OK.equalsIgnoreCase(wsResponse.getHeader().getResultCode())) {
				response = prepareResponse(wsResponse);
				
			} else {
				// error
				response = new EbarcodeSyncMasterDataResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new EbarcodeSyncMasterDataResponse();
		}
		
		return response;
	}
	
	private StaBacRequest prepareWsRequest(EbarcodeSyncMasterDataRequest request) {
		// Prepare Request Header
		HeaderRequest wsHeader = new HeaderRequest();
		wsHeader.setSourceSystem(WebServiceConstant.STA_HEADER.SOURCE_SYSTEM);
		wsHeader.setDestinationSystem(WebServiceConstant.STA_HEADER.DESTINATION_SYSTEM);
		wsHeader.setTransactionCode("");
		
		// Prepare Request Body
		Date currentDate = new Date();
		InternetUser wsInternetUser = new InternetUser();
		wsInternetUser.setCompanyId(request.getInternetUser().getCompanyId());
		wsInternetUser.setCompanyUserId(request.getInternetUser().getCompanyUserId());
		wsInternetUser.setCompanyUserPwd(request.getInternetUser().getCompanyUserPwd());
		wsInternetUser.setBeginDate(DateUtils.wsDateFormat.format(currentDate));
		wsInternetUser.setEndDate(DateUtils.wsDateFormat.format(currentDate));
		
		Body wsBody = new Body();
		wsBody.setInternetUser(wsInternetUser);
		
		StaBacRequest wsRequest = new StaBacRequest();
		wsRequest.setHeader(wsHeader);
		wsRequest.setBody(wsBody);
		
		return wsRequest;
	}
	
	private EbarcodeSyncMasterDataResponse prepareResponse(StaBacResponse wsResponse) {
		
		th.go.excise.edbarcode.ws.client.oxm.Company wsCompany = wsResponse.getBody().getCompany();
		
		// Common data for Company
		Company company = new Company();
		company.setInternetUniqueId(wsCompany.getInternetUniqueId());
		company.setCusId(wsCompany.getCusId());
		company.setCompanyId(wsCompany.getCompanyId());
		company.setCompanyTitleCode(wsCompany.getCompanyTitleCode());
		company.setCompanyTitleName(wsCompany.getCompanyTitleName());
		company.setCompanyName(wsCompany.getCompanyName());
		company.setCompanyBranchCode(wsCompany.getCompanyBranchCode());
		company.setExciseOfficeId(wsCompany.getExciseOfficeId());
		
		// Company Address
		Address companyAddress = new Address();
		companyAddress.setHouseIdNumber(wsCompany.getCompanyAddress().getHouseIdNumber());
		companyAddress.setBuildingName(wsCompany.getCompanyAddress().getBuildingName());
		companyAddress.setRoomNumber(wsCompany.getCompanyAddress().getRoomNumber());
		companyAddress.setFloorNumber(wsCompany.getCompanyAddress().getFloorNumber());
		companyAddress.setVillageName(wsCompany.getCompanyAddress().getVillageName());
		companyAddress.setHouseNumber(wsCompany.getCompanyAddress().getHouseNumber());
		companyAddress.setMooNumber(wsCompany.getCompanyAddress().getMooNumber());
		companyAddress.setTrokSoiName(wsCompany.getCompanyAddress().getTrokSoiName());
		companyAddress.setStreetName(wsCompany.getCompanyAddress().getStreetName());
		companyAddress.setThambolCode(wsCompany.getCompanyAddress().getThambolCode());
		companyAddress.setThambolName(wsCompany.getCompanyAddress().getThambolName());
		companyAddress.setAmphurCode(wsCompany.getCompanyAddress().getAmphurCode());
		companyAddress.setAmphurName(wsCompany.getCompanyAddress().getAmphurName());
		companyAddress.setProvinceCode(wsCompany.getCompanyAddress().getProvinceCode());
		companyAddress.setProvinceName(wsCompany.getCompanyAddress().getProvinceName());
		companyAddress.setPostcode(wsCompany.getCompanyAddress().getPostcode());
		companyAddress.setTelNumber(wsCompany.getCompanyAddress().getTelNumber());
		companyAddress.setEmailAddress(wsCompany.getCompanyAddress().getEmailAddress());
		company.setCompanyAddress(companyAddress);
		
		TaxpayerDetail taxpayerDetail = null;
		Address taxpayerAddress = null;
		TaxpayerList taxpayerList = new TaxpayerList();
		LicenseList licenseList = null;
		LicenseInfo licenseInfo = null;
		GoodsList goodsList = null;
		Goods goods = null;
		for (th.go.excise.edbarcode.ws.client.oxm.TaxpayerDetail wsTaxpayerDetail : wsCompany.getTaxpayerList().getTaxpayerDetail()) {
			
			taxpayerDetail = new TaxpayerDetail();
			taxpayerDetail.setTaxpayerId(wsTaxpayerDetail.getTaxpayerId());
			taxpayerDetail.setTaxpayerName(wsTaxpayerDetail.getTaxpayerName());
			
			taxpayerAddress = new Address();
			taxpayerAddress.setHouseIdNumber(wsTaxpayerDetail.getTaxpayerAddress().getHouseIdNumber());
			taxpayerAddress.setBuildingName(wsTaxpayerDetail.getTaxpayerAddress().getBuildingName());
			taxpayerAddress.setRoomNumber(wsTaxpayerDetail.getTaxpayerAddress().getRoomNumber());
			taxpayerAddress.setFloorNumber(wsTaxpayerDetail.getTaxpayerAddress().getFloorNumber());
			taxpayerAddress.setVillageName(wsTaxpayerDetail.getTaxpayerAddress().getVillageName());
			taxpayerAddress.setHouseNumber(wsTaxpayerDetail.getTaxpayerAddress().getHouseNumber());
			taxpayerAddress.setMooNumber(wsTaxpayerDetail.getTaxpayerAddress().getMooNumber());
			taxpayerAddress.setTrokSoiName(wsTaxpayerDetail.getTaxpayerAddress().getTrokSoiName());
			taxpayerAddress.setStreetName(wsTaxpayerDetail.getTaxpayerAddress().getStreetName());
			taxpayerAddress.setThambolCode(wsTaxpayerDetail.getTaxpayerAddress().getThambolCode());
			taxpayerAddress.setThambolName(wsTaxpayerDetail.getTaxpayerAddress().getThambolName());
			taxpayerAddress.setAmphurCode(wsTaxpayerDetail.getTaxpayerAddress().getAmphurCode());
			taxpayerAddress.setAmphurName(wsTaxpayerDetail.getTaxpayerAddress().getAmphurName());
			taxpayerAddress.setProvinceCode(wsTaxpayerDetail.getTaxpayerAddress().getProvinceCode());
			taxpayerAddress.setProvinceName(wsTaxpayerDetail.getTaxpayerAddress().getProvinceName());
			taxpayerAddress.setPostcode(wsTaxpayerDetail.getTaxpayerAddress().getPostcode());
			taxpayerAddress.setTelNumber(wsTaxpayerDetail.getTaxpayerAddress().getTelNumber());
			taxpayerAddress.setEmailAddress(wsTaxpayerDetail.getTaxpayerAddress().getEmailAddress());
			taxpayerDetail.setTaxpayerAddress(taxpayerAddress);
			
			licenseList = new LicenseList();
			for (th.go.excise.edbarcode.ws.client.oxm.LicenseInfo wsLicenseInfo : wsTaxpayerDetail.getLicenseList().getLicenseInfo()) {
				licenseInfo = new LicenseInfo();
				licenseInfo.setLicenseNo(wsLicenseInfo.getLicenseNo());
				licenseInfo.setLicenseType(wsLicenseInfo.getLicenseType());
				licenseInfo.setLicenseCode(wsLicenseInfo.getLicenseCode());
				licenseInfo.setLicenseSeq(wsLicenseInfo.getLicenseSeq());
				licenseInfo.setLicenseName(wsLicenseInfo.getLicenseName());
				licenseInfo.setLicenseCurrentPrice(wsLicenseInfo.getLicenseCurrentPrice());
				licenseInfo.setEffectiveDate(wsLicenseInfo.getEffectiveDate());
				licenseInfo.setExpireDate(wsLicenseInfo.getEffectiveDate());
				licenseInfo.setFirstDate(wsLicenseInfo.getFirstDate());
				licenseInfo.setOfficeCode(wsLicenseInfo.getOfficeCode());
				licenseInfo.setLicBook(wsLicenseInfo.getLicBook());
				licenseInfo.setLicDate(wsLicenseInfo.getLicDate());
				licenseInfo.setDocNo(wsLicenseInfo.getDocNo());
				licenseInfo.setDocType(wsLicenseInfo.getDocType());
				licenseInfo.setLineNo(wsLicenseInfo.getLineNo());
				licenseList.getLicenseInfo().add(licenseInfo);
			}
			taxpayerDetail.setLicenseList(licenseList);
			
			goodsList = new GoodsList();
			for (th.go.excise.edbarcode.ws.client.oxm.Goods wsGoods : wsTaxpayerDetail.getGoodsList().getGoods()) {
				goods = new Goods();
				goods.setGoodsCode(wsGoods.getGoodsCode());
				goods.setProductTypeCode(wsGoods.getProductTypeCode());
				goods.setProductTypeDescriptionText(wsGoods.getProductTypeDescriptionText());
				goods.setGoodsDescriptionText(wsGoods.getGoodsDescriptionText());
				goods.setGoodsPrice(wsGoods.getGoodsPrice());
				goods.setGoodsSize(wsGoods.getGoodsSize());
				goods.setGoodsSizeUnitDescriptionText(wsGoods.getGoodsSizeUnitDescriptionText());
				goods.setTaxRateByPriceAmount(wsGoods.getTaxRateByPriceAmount());
				goods.setTaxRateByQuantityAmount(wsGoods.getTaxRateByQuantityAmount());
				goods.setFundSSSRateAmount(wsGoods.getFundSSSRateAmount());
				goods.setFundSSTRateAmount(wsGoods.getFundSSTRateAmount());
				goods.setDegree(wsGoods.getDegree());
				goods.setPriceFlag(wsGoods.getPriceFlag());
				goods.setDeclarePrice(wsGoods.getDeclarePrice());
				goods.setUnitCode(wsGoods.getUnitCode());
				goods.setGoodsUnitsDescriptionText(wsGoods.getGoodsUnitsDescriptionText());
				goods.setIncomeCode(wsGoods.getIncomeCode());
				goods.setSeqNo(wsGoods.getSeqNo());
				goods.setBrandName(wsGoods.getBrandName());
				goods.setSubbrandName(wsGoods.getSubbrandName());
				goods.setModelName(wsGoods.getModelName());
				goods.setInformDate(wsGoods.getInformDate());
				goods.setProductNameEng(wsGoods.getProductNameEng());
				goods.setRatePerLitre(wsGoods.getRatePerLitre());
				goods.setDegreeMin(wsGoods.getDegreeMin());
				goods.setRateDegreeOver(wsGoods.getRateDegreeOver());
				goods.setWholesaleMin(wsGoods.getWholesaleMin());
				goods.setRateWholesaleOver(wsGoods.getRateWholesaleOver());
				goods.setRatePerLitreMax(wsGoods.getRatePerLitreMax());
				goodsList.getGoods().add(goods);
			}
			taxpayerDetail.setGoodsList(goodsList);
			
			taxpayerList.getTaxpayerDetail().add(taxpayerDetail);
		}
		company.setTaxpayerList(taxpayerList);
		
		EbarcodeSyncMasterDataResponse response = new EbarcodeSyncMasterDataResponse();
		response.setCompany(company);
		
		return response;
	}

}
