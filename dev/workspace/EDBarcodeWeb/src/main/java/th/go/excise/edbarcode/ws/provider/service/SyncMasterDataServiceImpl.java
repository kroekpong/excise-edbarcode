package th.go.excise.edbarcode.ws.provider.service;

import java.math.BigDecimal;
import java.util.Date;

import local.scc.dev.sta.bac.utils.PasswordEncryptDecrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.sta.service.GetLicenseNGoodsInfoService;
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

import com.baiwa.framework.common.util.NumberUtils;

@Service("syncMasterDataService")
public class SyncMasterDataServiceImpl implements SyncMasterDataService {
	
	private static final Logger logger = LogManager.getLogger(SyncMasterDataServiceImpl.class);
	
	@Autowired
	private GetLicenseNGoodsInfoService getLicenseNGoodsInfoService;

	@Override
	public EbarcodeSyncMasterDataResponse getResponse(EbarcodeSyncMasterDataRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSyncMasterDataResponse response = null;
		
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest wsRequest = prepareWsRequest(request);
			
			// Call Service
			th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse wsResponse = getLicenseNGoodsInfoService.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getHeader().getResultCode())) {
				// success
				response = prepareWsResponse(wsResponse);
				response.setSyncMasterDataStatus(WebServiceConstant.STATUS_CODE.OK);
				response.setSyncMasterDataDesc(WebServiceConstant.STATUS_DESC.SUCCESS);
			} else {
				// error
				response = new EbarcodeSyncMasterDataResponse();
				response.setSyncMasterDataStatus(wsResponse.getBody().getError().getCode());
				response.setSyncMasterDataDesc(wsResponse.getBody().getError().getDescription());
				logger.error("Call SyncMasterDataService Failed: {}: {}", response.getSyncMasterDataStatus(), response.getSyncMasterDataDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSyncMasterDataResponse();
			response.setSyncMasterDataStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSyncMasterDataDesc(e.getMessage());
		}
		
		return response;
	}
	
	private th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest prepareWsRequest(EbarcodeSyncMasterDataRequest request) {
		// Prepare Request Header
		th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest wsHeader = new th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest();
		wsHeader.setSourceSystem(WebServiceConstant.STA_HEADER.SYSTEM_BARCODE);
		wsHeader.setDestinationSystem(WebServiceConstant.STA_HEADER.SYSTEM_STA_BACK);
		wsHeader.setTransactionCode(WebServiceConstant.STA_HEADER.TRAN_CODE_GET_LICENSE_AND_GOODS_INFO);
		
		// Prepare Request Body
		PasswordEncryptDecrypt staPasswordUitl = new PasswordEncryptDecrypt();
		
		Date currentDate = new Date();
		th.go.excise.edbarcode.ws.client.sta.oxm.InternetUser wsInternetUser = new th.go.excise.edbarcode.ws.client.sta.oxm.InternetUser();
		wsInternetUser.setCompanyId(request.getInternetUser().getCompanyId());
		wsInternetUser.setCompanyUserId(request.getInternetUser().getCompanyUserId());
		wsInternetUser.setCompanyUserPwd(staPasswordUitl.encrypt(request.getInternetUser().getCompanyUserId(), request.getInternetUser().getCompanyUserPwd()));
		wsInternetUser.setBeginDate(DateUtils.wsDateFormat.format(currentDate));
		wsInternetUser.setEndDate(DateUtils.wsDateFormat.format(currentDate));
		
		th.go.excise.edbarcode.ws.client.sta.oxm.Body wsBody = new th.go.excise.edbarcode.ws.client.sta.oxm.Body();
		wsBody.setInternetUser(wsInternetUser);
		
		th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest wsRequest = new th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest();
		wsRequest.setHeader(wsHeader);
		wsRequest.setBody(wsBody);
		
		return wsRequest;
	}
	
	private EbarcodeSyncMasterDataResponse prepareWsResponse(th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse wsResponse) {
		
		th.go.excise.edbarcode.ws.client.sta.oxm.Company wsCompany = wsResponse.getBody().getCompany();
		
		// Common data for Company
		Company company = new Company();
		company.setInternetUniqueId(wsCompany.getInternetUniqueId());
		company.setCusId(wsCompany.getCusId());
		company.setCompanyId(wsCompany.getCompanyId());
		company.setCompanyUserId(wsCompany.getCompanyUserId());
		company.setCompanyUserPwd(wsCompany.getCompanyUserPwd());
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
		for (th.go.excise.edbarcode.ws.client.sta.oxm.TaxpayerDetail wsTaxpayerDetail : wsCompany.getTaxpayerList().getTaxpayerDetail()) {
			
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
			for (th.go.excise.edbarcode.ws.client.sta.oxm.LicenseInfo wsLicenseInfo : wsTaxpayerDetail.getLicenseList().getLicenseInfo()) {
				licenseInfo = new LicenseInfo();
				licenseInfo.setLicenseNo(wsLicenseInfo.getLicenseNo());
				licenseInfo.setLicenseType(wsLicenseInfo.getLicenseType());
				licenseInfo.setLicenseCode(wsLicenseInfo.getLicenseCode());
				licenseInfo.setLicenseSeq(wsLicenseInfo.getLicenseSeq());
				licenseInfo.setLicenseName(wsLicenseInfo.getLicenseName());
				licenseInfo.setLicenseCurrentPrice(NumberUtils.nullToZero(wsLicenseInfo.getLicenseCurrentPrice()));
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
			for (th.go.excise.edbarcode.ws.client.sta.oxm.Goods wsGoods : wsTaxpayerDetail.getGoodsList().getGoods()) {
				goods = new Goods();
				goods.setGoodsCode(wsGoods.getGoodsCode());
				goods.setProductTypeCode(wsGoods.getProductTypeCode());
				goods.setProductTypeDescriptionText(wsGoods.getProductTypeDescriptionText());
				goods.setGoodsDescriptionText(wsGoods.getGoodsDescriptionText());
				goods.setGoodsPrice(NumberUtils.nullToZero(wsGoods.getGoodsPrice()));
				goods.setGoodsSize(wsGoods.getGoodsSize());
				goods.setGoodsSizeUnitDescriptionText(wsGoods.getGoodsSizeUnitDescriptionText());
				goods.setTaxRateByPriceAmount(NumberUtils.nullToZero(wsGoods.getTaxRateByPriceAmount()));
				goods.setTaxRateByQuantityAmount(NumberUtils.nullToZero(wsGoods.getTaxRateByQuantityAmount()));
				//goods.setMunicipalRateAmount(NumberUtils.nullToZero(wsGoods.getMunicipalRateAmount()));
				goods.setMunicipalRateAmount(NumberUtils.nullToZero(new BigDecimal("10")));// FIXME
				goods.setFundSSSRateAmount(NumberUtils.nullToZero(wsGoods.getFundSSSRateAmount()));
				goods.setFundSSTRateAmount(NumberUtils.nullToZero(wsGoods.getFundSSTRateAmount()));
				//goods.setFundKKTRateAmount(NumberUtils.nullToZero(wsGoods.getFundKKTRateAmount()));
				goods.setFundKKTRateAmount(NumberUtils.nullToZero(new BigDecimal("2")));// FIXME
				goods.setDegree(wsGoods.getDegree());
				goods.setPriceFlag(wsGoods.getPriceFlag());
				goods.setDeclarePrice(NumberUtils.nullToZero(wsGoods.getDeclarePrice()));
				goods.setUnitCode(wsGoods.getUnitCode());
				goods.setGoodsUnitsDescriptionText(wsGoods.getGoodsUnitsDescriptionText());
				goods.setIncomeCode(wsGoods.getIncomeCode());
				goods.setSeqNo(wsGoods.getSeqNo());
				goods.setBrandName(wsGoods.getBrandName());
				goods.setSubbrandName(wsGoods.getSubbrandName());
				goods.setModelName(wsGoods.getModelName());
				goods.setInformDate(wsGoods.getInformDate());
				goods.setProductNameEng(wsGoods.getProductNameEng());
				goods.setRatePerLitre(NumberUtils.nullToZero(wsGoods.getRatePerLitre()));
				goods.setDegreeMin(NumberUtils.nullToZero(wsGoods.getDegreeMin()));
				goods.setRateDegreeOver(NumberUtils.nullToZero(wsGoods.getRateDegreeOver()));
				goods.setWholesaleMin(NumberUtils.nullToZero(wsGoods.getWholesaleMin()));
				goods.setRateWholesaleOver(NumberUtils.nullToZero(wsGoods.getRateWholesaleOver()));
				goods.setRatePerLitreMax(NumberUtils.nullToZero(wsGoods.getRatePerLitreMax()));
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
