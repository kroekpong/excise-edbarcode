package th.go.excise.edbarcode.ws.provider.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.ws.client.barcode.service.SubmitOnlineBackService;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSubmitOnlineResponse;
import th.go.excise.edbarcode.ws.provider.oxm.GoodsEntryInfo;

@Service("submitOnlineWebService")
public class SubmitOnlineWebServiceImpl implements SubmitOnlineWebService {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineWebServiceImpl.class);
	
	@Autowired
	private SubmitOnlineBackService submitOnlineBackService;
	
	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSubmitOnlineResponse response = null;
		
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = prepareWsRequest(request);
			
			// Call Service
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse = submitOnlineBackService.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getSubmitOnlineStatus())) {
				// success
				response = prepareWsResponse(wsResponse);
				response.setSubmitOnlineStatus(WebServiceConstant.STATUS_CODE.OK);
				response.setSubmitOnlineDesc(WebServiceConstant.STATUS_DESC.SUCCESS);
			} else {
				// error
				response = new EbarcodeSubmitOnlineResponse();
				response.setSubmitOnlineStatus(wsResponse.getSubmitOnlineStatus());
				response.setSubmitOnlineDesc(wsResponse.getSubmitOnlineDesc());
				logger.error("Call SubmitOnlineWebService Failed: {}: {}", response.getSubmitOnlineStatus(), response.getSubmitOnlineDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSubmitOnlineResponse();
			response.setSubmitOnlineStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSubmitOnlineDesc(e.getMessage());
		}
		
		return response;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest prepareWsRequest(EbarcodeSubmitOnlineRequest request) throws IllegalAccessException, InvocationTargetException {
		
		// SubmitOnlineHeader
		th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader wsSummitOnlineHeader = new th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader();
		wsSummitOnlineHeader.setCompanyId(request.getSubmitOnlineHeader().getCompanyId());
		wsSummitOnlineHeader.setCompanyUserId(request.getSubmitOnlineHeader().getCompanyUserId());
		wsSummitOnlineHeader.setCompanyUserPwd(request.getSubmitOnlineHeader().getCompanyUserPwd());
		wsSummitOnlineHeader.setCusId(request.getSubmitOnlineHeader().getCusId());
		wsSummitOnlineHeader.setExciseOfficeId(request.getSubmitOnlineHeader().getExciseOfficeId());
		wsSummitOnlineHeader.setInternetUniqueId(request.getSubmitOnlineHeader().getInternetUniqueId());
		wsSummitOnlineHeader.setIpAddress(request.getSubmitOnlineHeader().getIpAddress());
		wsSummitOnlineHeader.setRegistratronId(request.getSubmitOnlineHeader().getRegistratronId());
		wsSummitOnlineHeader.setSubmissionEmail(request.getSubmitOnlineHeader().getSubmissionEmail());
		wsSummitOnlineHeader.setTaxpayerId(request.getSubmitOnlineHeader().getTaxpayerId());
		
		// SR12011Info
		// TaxpayerInfo
		th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerInfo wsTaxpayerInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerInfo();
		wsTaxpayerInfo.setCompanyName(request.getSR12011Info().getTaxpayerInfo().getCompanyName());
		wsTaxpayerInfo.setEffectiveDate(request.getSR12011Info().getTaxpayerInfo().getEffectiveDate());
		wsTaxpayerInfo.setExpireDate(request.getSR12011Info().getTaxpayerInfo().getExpireDate());
		wsTaxpayerInfo.setLicenseNo(request.getSR12011Info().getTaxpayerInfo().getLicenseNo());
		wsTaxpayerInfo.setTaxpayerName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerName());
		wsTaxpayerInfo.setTin(request.getSR12011Info().getTaxpayerInfo().getTin());
		
		// TaxpayerAddressInfo
		th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerAddressInfo wsTaxpayerAddressInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerAddressInfo();
		wsTaxpayerAddressInfo.setBuildingName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getBuildingName());
		wsTaxpayerAddressInfo.setRoomNumber(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getRoomNumber());
		wsTaxpayerAddressInfo.setFloorNumber(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getFloorNumber());
		wsTaxpayerAddressInfo.setVillageName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getVillageName());
		wsTaxpayerAddressInfo.setHouseNumber(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getHouseNumber());
		wsTaxpayerAddressInfo.setMooNumber(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getMooNumber());
		wsTaxpayerAddressInfo.setTrokSoiName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getTrokSoiName());
		wsTaxpayerAddressInfo.setStreetName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getStreetName());
		wsTaxpayerAddressInfo.setThambolName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getThambolName());
		wsTaxpayerAddressInfo.setAmphurName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getAmphurName());
		wsTaxpayerAddressInfo.setProvinceName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getProvinceName());
		wsTaxpayerAddressInfo.setPostcode(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getPostcode());
		wsTaxpayerAddressInfo.setTelNumber(request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo().getTelNumber());
		wsTaxpayerInfo.setTaxpayerAddressInfo(wsTaxpayerAddressInfo);
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsListInfo wsGoodsListInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsListInfo();
		th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsEntryInfo wsGoodsEntryInfo = null;
		for(GoodsEntryInfo goodsEntryInfo : request.getSR12011Info().getGoodsListInfo().getGoodsEntryInfo() ) {
			wsGoodsEntryInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsEntryInfo();
			wsGoodsEntryInfo.setCategoryCode1(goodsEntryInfo.getCategoryCode1());
			wsGoodsEntryInfo.setCategoryCode2(goodsEntryInfo.getCategoryCode2());
			wsGoodsEntryInfo.setCategoryCode3(goodsEntryInfo.getCategoryCode3());
			wsGoodsEntryInfo.setCategoryCode4(goodsEntryInfo.getCategoryCode4());
			wsGoodsEntryInfo.setCategoryCode5(goodsEntryInfo.getCategoryCode5());
			wsGoodsEntryInfo.setDeclarePrice(goodsEntryInfo.getDeclarePrice());
			wsGoodsEntryInfo.setDegree(goodsEntryInfo.getDegree());
			wsGoodsEntryInfo.setGoodsDesc(goodsEntryInfo.getGoodsDesc());
			wsGoodsEntryInfo.setGoodsNum(goodsEntryInfo.getGoodsNum());
			wsGoodsEntryInfo.setGoodsPiece(goodsEntryInfo.getGoodsPiece());
			wsGoodsEntryInfo.setGoodsQuantity(goodsEntryInfo.getGoodsQuantity());
			wsGoodsEntryInfo.setGoodsSize(goodsEntryInfo.getGoodsSize());
			wsGoodsEntryInfo.setGoodsValue(goodsEntryInfo.getGoodsValue());
			wsGoodsEntryInfo.setInformPrice(goodsEntryInfo.getInformPrice());
			wsGoodsEntryInfo.setNetTaxByQuantity(goodsEntryInfo.getNetTaxByQuantity());
			wsGoodsEntryInfo.setNetTaxByValue(goodsEntryInfo.getNetTaxByValue());
			wsGoodsEntryInfo.setPriceFlag(goodsEntryInfo.getPriceFlag());
			wsGoodsEntryInfo.setProductCode(goodsEntryInfo.getProductCode());
			wsGoodsEntryInfo.setProductTypeDesc(goodsEntryInfo.getProductTypeDesc());
			wsGoodsEntryInfo.setRateFlag(goodsEntryInfo.getRateFlag());
			wsGoodsEntryInfo.setSeqNo(goodsEntryInfo.getSeqNo());
			wsGoodsEntryInfo.setTaxAmount(goodsEntryInfo.getTaxAmount());
			wsGoodsEntryInfo.setTaxByQuantity(goodsEntryInfo.getTaxByQuantity());
			wsGoodsEntryInfo.setTaxByQuantityOver(goodsEntryInfo.getTaxByQuantityOver());
			wsGoodsEntryInfo.setTaxByQuantityWithOver(goodsEntryInfo.getTaxByQuantityWithOver());
			wsGoodsEntryInfo.setTaxByValue(goodsEntryInfo.getTaxByValue());
			wsGoodsEntryInfo.setTaxQuantity(goodsEntryInfo.getTaxQuantity());
			wsGoodsEntryInfo.setTaxQuantityNumber(goodsEntryInfo.getTaxQuantityNumber());
			wsGoodsEntryInfo.setTaxQuantityPerUnit(goodsEntryInfo.getTaxQuantityPerUnit());
			wsGoodsEntryInfo.setTaxValue(goodsEntryInfo.getTaxValue());
			wsGoodsEntryInfo.setUnitCode(goodsEntryInfo.getUnitCode());
			wsGoodsEntryInfo.setUnitPrice(goodsEntryInfo.getUnitPrice());
			wsGoodsListInfo.getGoodsEntryInfo().add(wsGoodsEntryInfo);
		}
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SummaryInfo wsSummaryInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.SummaryInfo();
		wsSummaryInfo.setMoiRate(request.getSR12011Info().getSummaryInfo().getMoiRate());
		wsSummaryInfo.setPaymentExciseAmount(request.getSR12011Info().getSummaryInfo().getPaymentExciseAmount());
		wsSummaryInfo.setPaymentExciseAndMunicipalTaxAmount(request.getSR12011Info().getSummaryInfo().getPaymentExciseAndMunicipalTaxAmount());
		wsSummaryInfo.setPaymentFundHealthAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundHealthAmount());
		wsSummaryInfo.setPaymentFundSportAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundSportAmount());
		wsSummaryInfo.setPaymentFundTVAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundTVAmount());
		wsSummaryInfo.setPaymentMunicipalAmount(request.getSR12011Info().getSummaryInfo().getPaymentMunicipalAmount());
		wsSummaryInfo.setPaymentNetTaxAmount(request.getSR12011Info().getSummaryInfo().getPaymentNetTaxAmount());
		wsSummaryInfo.setPaymentOtherAmount(request.getSR12011Info().getSummaryInfo().getPaymentOtherAmount());
		wsSummaryInfo.setPrintType(request.getSR12011Info().getSummaryInfo().getPrintType());
		wsSummaryInfo.setRecType(request.getSR12011Info().getSummaryInfo().getRecType());
		wsSummaryInfo.setSumAllTax(request.getSR12011Info().getSummaryInfo().getSumAllTax());
		wsSummaryInfo.setSumAllTaxByQuantity(request.getSR12011Info().getSummaryInfo().getSumAllTaxByQuantity());
		wsSummaryInfo.setSumAllTaxByValue(request.getSR12011Info().getSummaryInfo().getSumAllTaxByValue());
		wsSummaryInfo.setTaxDeductionOnBookAmount(request.getSR12011Info().getSummaryInfo().getTaxDeductionOnBookAmount());
		wsSummaryInfo.setTaxDeductionOnBookNo(request.getSR12011Info().getSummaryInfo().getTaxDeductionOnBookNo());
		wsSummaryInfo.setTaxLessAmount(request.getSR12011Info().getSummaryInfo().getTaxLessAmount());
		wsSummaryInfo.setTaxLessFrom(request.getSR12011Info().getSummaryInfo().getTaxLessType());
		wsSummaryInfo.setTaxLessType(request.getSR12011Info().getSummaryInfo().getTaxLessType());
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info wsSR12011Info = new th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info();
		wsSR12011Info.setTaxpayerInfo(wsTaxpayerInfo);
		wsSR12011Info.setGoodsListInfo(wsGoodsListInfo);
		wsSR12011Info.setSummaryInfo(wsSummaryInfo);
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = new th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest();
		wsRequest.setSubmitOnlineHeader(wsSummitOnlineHeader);
		wsRequest.setSR12011Info(wsSR12011Info);
		
		return wsRequest;
	}
	
	private EbarcodeSubmitOnlineResponse prepareWsResponse(th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse) throws IllegalAccessException, InvocationTargetException {
		
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		response.setReferenceNumber(wsResponse.getReferenceNumber());
		
		return response;
	}

}
