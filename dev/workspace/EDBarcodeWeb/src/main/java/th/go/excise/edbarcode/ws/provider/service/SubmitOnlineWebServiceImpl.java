package th.go.excise.edbarcode.ws.provider.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
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
	SubmitOnlineBackService submitOnlineBackService;
	
	@Override
	public EbarcodeSubmitOnlineResponse getResponse(EbarcodeSubmitOnlineRequest request) {
		logger.info("getResponse method");
		
		// Call Service
		EbarcodeSubmitOnlineResponse response = null;
		try {
			// Create WebService Request
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = prepareWsRequest(request);
			
			th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse = submitOnlineBackService.doService(wsRequest);
			
			if (WebServiceConstant.STA_HEADER.RESULT_CODE_OK.equalsIgnoreCase(wsResponse.getSubmitOnlineStatus())) {
				// success
				response = prepareWsResponse(wsResponse);
			} else {
				// error
				response = new EbarcodeSubmitOnlineResponse();
				// TODO
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSubmitOnlineResponse();
			// TODO
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
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader wsSummitOnlineHeader = new th.go.excise.edbarcode.ws.client.barcode.oxm.SubmitOnlineHeader();
		// TODO
		
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
		
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info wsSR12011Info = new th.go.excise.edbarcode.ws.client.barcode.oxm.SR12011Info();
		// TODO
		th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsListInfo goodsListInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsListInfo();
		
		for(GoodsEntryInfo loop : request.getSR12011Info().getGoodsListInfo().getGoodsEntryInfo() )
		{
			th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsEntryInfo goodsEntryInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.GoodsEntryInfo();
			goodsEntryInfo.setCategoryCode1(loop.getCategoryCode1());
			goodsEntryInfo.setCategoryCode2(loop.getCategoryCode2());
			goodsEntryInfo.setCategoryCode3(loop.getCategoryCode3());
			goodsEntryInfo.setCategoryCode4(loop.getCategoryCode4());
			goodsEntryInfo.setCategoryCode5(loop.getCategoryCode5());
			goodsEntryInfo.setDeclarePrice(loop.getDeclarePrice());
			goodsEntryInfo.setDegree(loop.getDegree());
			goodsEntryInfo.setGoodsDesc(loop.getGoodsDesc());
			goodsEntryInfo.setGoodsNum(loop.getGoodsNum());
			goodsEntryInfo.setGoodsPiece(loop.getGoodsPiece());
			goodsEntryInfo.setGoodsQuantity(loop.getGoodsQuantity());
			goodsEntryInfo.setGoodsSize(loop.getGoodsSize());
			goodsEntryInfo.setGoodsValue(loop.getGoodsValue());
			goodsEntryInfo.setInformPrice(loop.getInformPrice());
			goodsEntryInfo.setNetTaxByQuantity(loop.getNetTaxByQuantity());
			goodsEntryInfo.setNetTaxByValue(loop.getNetTaxByValue());
			goodsEntryInfo.setPriceFlag(loop.getPriceFlag());
			goodsEntryInfo.setProductCode(loop.getProductCode());
			goodsEntryInfo.setProductTypeDesc(loop.getProductTypeDesc());
			goodsEntryInfo.setRateFlag(loop.getRateFlag());
			goodsEntryInfo.setSeqNo(loop.getSeqNo());
			goodsEntryInfo.setTaxAmount(loop.getTaxAmount());
			goodsEntryInfo.setTaxByQuantity(loop.getTaxByQuantity());
			goodsEntryInfo.setTaxByQuantityOver(loop.getTaxByQuantityOver());
			goodsEntryInfo.setTaxByQuantityWithOver(loop.getTaxByQuantityWithOver());
			goodsEntryInfo.setTaxByValue(loop.getTaxByValue());
			goodsEntryInfo.setTaxQuantity(loop.getTaxQuantity());
			goodsEntryInfo.setTaxQuantityNumber(loop.getTaxQuantityNumber());
			goodsEntryInfo.setTaxQuantityPerUnit(loop.getTaxQuantityPerUnit());
			goodsEntryInfo.setTaxValue(loop.getTaxValue());
			goodsEntryInfo.setUnitCode(loop.getUnitCode());
			goodsEntryInfo.setUnitPrice(loop.getUnitPrice());
			
			goodsListInfo.getGoodsEntryInfo().add(goodsEntryInfo);
		}
		wsSR12011Info.setGoodsListInfo(goodsListInfo);
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.SummaryInfo summaryInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.SummaryInfo();
		summaryInfo.setMoiRate(request.getSR12011Info().getSummaryInfo().getMoiRate());
		summaryInfo.setPaymentExciseAmount(request.getSR12011Info().getSummaryInfo().getPaymentExciseAmount());
		summaryInfo.setPaymentExciseAndMunicipalTaxAmount(request.getSR12011Info().getSummaryInfo().getPaymentExciseAndMunicipalTaxAmount());
		summaryInfo.setPaymentFundHealthAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundHealthAmount());
		summaryInfo.setPaymentFundSportAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundSportAmount());
		summaryInfo.setPaymentFundTVAmount(request.getSR12011Info().getSummaryInfo().getPaymentFundTVAmount());
		summaryInfo.setPaymentMunicipalAmount(request.getSR12011Info().getSummaryInfo().getPaymentMunicipalAmount());
		summaryInfo.setPaymentNetTaxAmount(request.getSR12011Info().getSummaryInfo().getPaymentNetTaxAmount());
		summaryInfo.setPaymentOtherAmount(request.getSR12011Info().getSummaryInfo().getPaymentOtherAmount());
		summaryInfo.setPrintType(request.getSR12011Info().getSummaryInfo().getPrintType());
		summaryInfo.setRecType(request.getSR12011Info().getSummaryInfo().getRecType());
		summaryInfo.setSumAllTax(request.getSR12011Info().getSummaryInfo().getSumAllTax());
		summaryInfo.setSumAllTaxByQuantity(request.getSR12011Info().getSummaryInfo().getSumAllTaxByQuantity());
		summaryInfo.setSumAllTaxByValue(request.getSR12011Info().getSummaryInfo().getSumAllTaxByValue());
		summaryInfo.setTaxDeductionOnBookAmount(request.getSR12011Info().getSummaryInfo().getTaxDeductionOnBookAmount());
		summaryInfo.setTaxDeductionOnBookNo(request.getSR12011Info().getSummaryInfo().getTaxDeductionOnBookNo());
		summaryInfo.setTaxLessAmount(request.getSR12011Info().getSummaryInfo().getTaxLessAmount());
		summaryInfo.setTaxLessFrom(request.getSR12011Info().getSummaryInfo().getTaxLessType());
		summaryInfo.setTaxLessType(request.getSR12011Info().getSummaryInfo().getTaxLessType());
		wsSR12011Info.setSummaryInfo(summaryInfo);
		
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerInfo taxpayerInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerInfo();
		taxpayerInfo.setCompanyName(request.getSR12011Info().getTaxpayerInfo().getCompanyName());
		taxpayerInfo.setEffectiveDate(request.getSR12011Info().getTaxpayerInfo().getEffectiveDate());
		taxpayerInfo.setExpireDate(request.getSR12011Info().getTaxpayerInfo().getExpireDate());
		taxpayerInfo.setLicenseNo(request.getSR12011Info().getTaxpayerInfo().getLicenseNo());
		//address
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerAddressInfo taxpayerAddressInfo = new th.go.excise.edbarcode.ws.client.barcode.oxm.TaxpayerAddressInfo();
		
		try {
			BeanUtils.copyProperties(taxpayerAddressInfo, request.getSR12011Info().getTaxpayerInfo().getTaxpayerAddressInfo());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEE");
		}
		taxpayerInfo.setTaxpayerAddressInfo(taxpayerAddressInfo);
		taxpayerInfo.setTaxpayerName(request.getSR12011Info().getTaxpayerInfo().getTaxpayerName());
		taxpayerInfo.setTin(request.getSR12011Info().getTaxpayerInfo().getTin());
		
		wsSR12011Info.setTaxpayerInfo(taxpayerInfo);
		
		
		
		
		th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest wsRequest = new th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineRequest();
		wsRequest.setSubmitOnlineHeader(wsSummitOnlineHeader);
		wsRequest.setSR12011Info(wsSR12011Info);
		
		return wsRequest;
	}
	
	private EbarcodeSubmitOnlineResponse prepareWsResponse(th.go.excise.edbarcode.ws.client.barcode.oxm.EbarcodeSubmitOnlineResponse wsResponse) throws IllegalAccessException, InvocationTargetException {
		
		EbarcodeSubmitOnlineResponse response = new EbarcodeSubmitOnlineResponse();
		response.setSubmitOnlineStatus(wsResponse.getSubmitOnlineStatus());
		response.setSubmitOnlineDesc(wsResponse.getSubmitOnlineDesc());
		response.setReferenceNumber(wsResponse.getReferenceNumber());
		
		return response;
	}

}
