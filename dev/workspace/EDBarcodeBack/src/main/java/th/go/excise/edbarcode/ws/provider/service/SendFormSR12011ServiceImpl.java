package th.go.excise.edbarcode.ws.provider.service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.client.InsertPOSO0112OperationService;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Body;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Credit;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Fund;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Header;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InquiryBcsReqHeader;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112Operation;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112OperationResponse;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.POSO0112FormInfo;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.PSO112Goods;
import th.go.excise.edbarcode.ws.provider.bean.XmlData;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Request;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendFormSR12011Response;
import th.go.excise.edbarcode.ws.provider.oxm.FundEntryInfo;
import th.go.excise.edbarcode.ws.provider.oxm.GoodsEntryInfo;
import th.go.excise.edbarcode.ws.provider.oxm.SR12011Info;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineHeader;

@Service("sendFormSR12011Service")
public class SendFormSR12011ServiceImpl implements SendFormSR12011Service {
	
	private static final Logger logger = LogManager.getLogger(SendFormSR12011ServiceImpl.class);
	
	@Autowired
	private InsertPOSO0112OperationService insertPOSO0112OperationService;
	
	@Override
	public EbarcodeSendFormSR12011Response getResponse(EbarcodeSendFormSR12011Request request) {
		logger.info("getResponse method");
		
		EbarcodeSendFormSR12011Response response = null;
		
		try {
			// Create WebService Request
			InsertPOSO0112Operation wsRequest = prepareWsRequest(request);
			
			// Call Service
			InsertPOSO0112OperationResponse wsResponse = insertPOSO0112OperationService.doService(wsRequest);
			
			if ("E00000".equalsIgnoreCase(wsResponse.getReturn().getReturnCode())) {
				// success
				response = new EbarcodeSendFormSR12011Response();
				response.setSendFormSR12011Status(WebServiceConstant.STATUS_CODE.OK);
				response.setSendFormSR12011Desc(wsResponse.getReturn().getReturnDesc());
			} else {
				// error
				response = new EbarcodeSendFormSR12011Response();
				response.setSendFormSR12011Status(wsResponse.getReturn().getReturnCode());
				response.setSendFormSR12011Desc(wsResponse.getReturn().getReturnDesc());
				logger.error("Call SendToBackendService Failed: {}: {}", response.getSendFormSR12011Status(), response.getSendFormSR12011Desc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSendFormSR12011Response();
			response.setSendFormSR12011Status(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSendFormSR12011Desc(e.getMessage());
		}
		
		return response;
	}

	private InsertPOSO0112Operation prepareWsRequest(EbarcodeSendFormSR12011Request request) throws JAXBException {
		
		Calendar calendar = Calendar.getInstance(Locale.US);
		
		DecimalFormat decimalFormatZeroDigit = new DecimalFormat("0");
		DecimalFormat decimalFormatTwoDigit = new DecimalFormat("0.00");
		DecimalFormat decimalFormatFourDigit = new DecimalFormat("0.0000");
		
		String xmlDataString = request.getBinaryInformation().getXmlDataBinary();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		StringReader reader = new StringReader(xmlDataString);
		XmlData xmlData = (XmlData) jaxbUnmarshaller.unmarshal(reader);
		reader.close();
		
		SubmitOnlineHeader submitOnlineHeader = xmlData.getSubmitOnlineHeader();
		SR12011Info sr12011Info = xmlData.getSr12011Info();
		
		// Header
		Header wsHeader = new Header();
		wsHeader.setSystemID(WebServiceConstant.PCC.SYSTEM_ID);
		wsHeader.setServiceID(WebServiceConstant.PCC.SERVICE_ID);
		wsHeader.setUserId("BARCODE");// FIXME move to PARAM_CONFIG
		wsHeader.setPassword("11111111");// FIXME move to PARAM_CONFIG
		wsHeader.setIPAddress(submitOnlineHeader.getIpAddress());
		
		// Body
		POSO0112FormInfo wsPoso0112FormInfo = new POSO0112FormInfo();
		wsPoso0112FormInfo.setRegistrationId(submitOnlineHeader.getRegistratronId());
		wsPoso0112FormInfo.setCustomerId(submitOnlineHeader.getCusId());
		wsPoso0112FormInfo.setFactoryId(submitOnlineHeader.getTaxpayerId());
		wsPoso0112FormInfo.setTin(sr12011Info.getTaxpayerInfo().getTin());
		wsPoso0112FormInfo.setPinNitId(sr12011Info.getTaxpayerInfo().getTin());
		wsPoso0112FormInfo.setIpAddress(submitOnlineHeader.getIpAddress());
		wsPoso0112FormInfo.setInternetId(submitOnlineHeader.getInternetUniqueId());
		wsPoso0112FormInfo.setSubbmissionEmail(submitOnlineHeader.getSubmissionEmail());
		wsPoso0112FormInfo.setSubmitDateTime(DateUtils.wsDateFormat.format(calendar));
		wsPoso0112FormInfo.setFormType("");// No Data, empty String
		wsPoso0112FormInfo.setFormCode(WebServiceConstant.PCC.FORM_CODE);// FIXME Move to PT
		wsPoso0112FormInfo.setFormEffectiveDate(submitOnlineHeader.getSubmissionDate());
		wsPoso0112FormInfo.setFormReferenceNumber(request.getDataInformation().getReferenceNumber());
		wsPoso0112FormInfo.setPaymentReferenceId(0);// No Data, default 0
		wsPoso0112FormInfo.setBankReferenceId("0");// No Data, default 0
		wsPoso0112FormInfo.setPayType12(WebServiceConstant.PCC.PAY_TYPE);
		wsPoso0112FormInfo.setTaxMonth(calendar.get(Calendar.MONTH) + 1);// Current Month, 1-12
		wsPoso0112FormInfo.setTaxYear(calendar.get(Calendar.YEAR));// Current Year, YYYY -> 2015
		wsPoso0112FormInfo.setIncomeCode("0");// No Data, default 0
		wsPoso0112FormInfo.setFactoryDateBegin("");
		wsPoso0112FormInfo.setFactoryDateEnd("");
		wsPoso0112FormInfo.setTaxvalAmt(decimalFormatZeroDigit.format(Math.floor(sr12011Info.getSummaryInfo().getSumAllTaxByValue().doubleValue())));
		wsPoso0112FormInfo.setTaxqtyAmt(decimalFormatTwoDigit.format(sr12011Info.getSummaryInfo().getSumAllTaxByQuantity()));
		wsPoso0112FormInfo.setExciseTax(sr12011Info.getSummaryInfo().getSumAllTax().doubleValue());
		wsPoso0112FormInfo.setReduceAmt(sr12011Info.getSummaryInfo().getTaxLessAmount().doubleValue());
		wsPoso0112FormInfo.setPenaltyAmt(0.0);// No Data, Can default to zero
		wsPoso0112FormInfo.setSurchargeAmt(0.0);// No Data, Can default to zero
		wsPoso0112FormInfo.setMoiRate(decimalFormatZeroDigit.format(sr12011Info.getSummaryInfo().getMoiRate()));
		wsPoso0112FormInfo.setMoiTax(decimalFormatTwoDigit.format(sr12011Info.getSummaryInfo().getPaymentMunicipalAmount()));
		wsPoso0112FormInfo.setSumCreditExciseTax(sr12011Info.getSummaryInfo().getTaxDeductionOnBookAmount().doubleValue());
		wsPoso0112FormInfo.setSumCreditMoiTax(sr12011Info.getSummaryInfo().getSumCreditMoiTax().doubleValue());
		wsPoso0112FormInfo.setNetExciseTax(sr12011Info.getSummaryInfo().getPaymentExciseAmount().doubleValue());
		wsPoso0112FormInfo.setNetMoiTax(sr12011Info.getSummaryInfo().getPaymentMunicipalAmount().doubleValue());
		wsPoso0112FormInfo.setNetSssTax(sr12011Info.getSummaryInfo().getPaymentFundHealthAmount().doubleValue());
		wsPoso0112FormInfo.setNetSstTax(sr12011Info.getSummaryInfo().getPaymentFundTVAmount().doubleValue());
		wsPoso0112FormInfo.setNetKprTax(sr12011Info.getSummaryInfo().getPaymentFundSportAmount().doubleValue());
		wsPoso0112FormInfo.setPrnType(sr12011Info.getSummaryInfo().getPrintType());
		wsPoso0112FormInfo.setRecType(WebServiceConstant.PCC.RECORD_TYPE);
		
		PSO112Goods wsPso112Goods = null;
		for (GoodsEntryInfo goodsEntryInfo : sr12011Info.getGoodsListInfo().getGoodsEntryInfo()) {
			wsPso112Goods = new PSO112Goods();
			wsPso112Goods.setProductCode(goodsEntryInfo.getProductCode());
			wsPso112Goods.setCategoryCode1(goodsEntryInfo.getCategoryCode1());
			wsPso112Goods.setCategoryCode2(goodsEntryInfo.getCategoryCode2());
			wsPso112Goods.setCategoryCode3(goodsEntryInfo.getCategoryCode3());
			wsPso112Goods.setCategoryCode4(goodsEntryInfo.getCategoryCode4());
			wsPso112Goods.setCategoryCode5(goodsEntryInfo.getCategoryCode5());
			wsPso112Goods.setCategoryName1("");
			wsPso112Goods.setCategoryName2("");
			wsPso112Goods.setCategoryName3("");
			wsPso112Goods.setCategoryName4("");
			wsPso112Goods.setCategoryName5("");
			wsPso112Goods.setUnitCode(goodsEntryInfo.getUnitCode());
			wsPso112Goods.setRateFlag(goodsEntryInfo.getRateFlag());
			wsPso112Goods.setTaxQuantity(decimalFormatFourDigit.format(goodsEntryInfo.getTaxQuantity()));
			wsPso112Goods.setTaxQuantityNumber(decimalFormatFourDigit.format(goodsEntryInfo.getTaxQuantityNumber()));
			wsPso112Goods.setTaxQuantityPerUnit(decimalFormatFourDigit.format(goodsEntryInfo.getTaxQuantityPerUnit()));
			wsPso112Goods.setTaxValue(decimalFormatFourDigit.format(goodsEntryInfo.getTaxValue()));
			wsPso112Goods.setPriceFlag(goodsEntryInfo.getPriceFlag());
			wsPso112Goods.setInformPrice(decimalFormatFourDigit.format(goodsEntryInfo.getInformPrice()));
			wsPso112Goods.setDeclarePrice(decimalFormatFourDigit.format(goodsEntryInfo.getDeclarePrice()));
			wsPso112Goods.setUnitPirce(decimalFormatFourDigit.format(goodsEntryInfo.getUnitPrice()));
			wsPso112Goods.setGoodsNum(decimalFormatFourDigit.format(goodsEntryInfo.getGoodsNum()));
			wsPso112Goods.setGoodsValue(decimalFormatFourDigit.format(goodsEntryInfo.getGoodsValue()));
			wsPso112Goods.setTaxvalUnit(decimalFormatFourDigit.format(goodsEntryInfo.getTaxByValue()));
			wsPso112Goods.setTaxqtyUnit1(decimalFormatFourDigit.format(goodsEntryInfo.getTaxByQuantity()));
			wsPso112Goods.setTaxqtyUnit2(decimalFormatFourDigit.format(goodsEntryInfo.getTaxByQuantityOver()));
			wsPso112Goods.setTaxvalAmt(decimalFormatTwoDigit.format(goodsEntryInfo.getNetTaxByValue()));
			wsPso112Goods.setTaxqtyAmt(decimalFormatTwoDigit.format(goodsEntryInfo.getNetTaxByQuantity()));
			wsPoso0112FormInfo.getPso112Goods().add(wsPso112Goods);
		}
		
		Fund wsFund = null;
		for (FundEntryInfo fundEntryInfo : sr12011Info.getFundListInfo().getFundEntryInfo()) {
			wsFund = new Fund();
			wsFund.setFundType(fundEntryInfo.getFundType());
			wsFund.setFundRate(fundEntryInfo.getFundRate().doubleValue());
			wsFund.setFundAmt(fundEntryInfo.getFundAmt().doubleValue());
			wsFund.setCreditAmt(fundEntryInfo.getCreditAmt().doubleValue());
			wsFund.setNetAmt(fundEntryInfo.getNetAmt().doubleValue());
			wsPoso0112FormInfo.getFund().add(wsFund);
		}
		
		if (StringUtils.isNotEmpty(sr12011Info.getSummaryInfo().getTaxDeductionOnBookNo())) {
			Credit wsCredit = new Credit();
			wsCredit.setRTNCTLNO(sr12011Info.getSummaryInfo().getTaxDeductionOnBookNo());
			wsCredit.setCreditExciseTax(decimalFormatTwoDigit.format(sr12011Info.getSummaryInfo().getTaxDeductionOnBookAmount()));
			wsCredit.setCredtiMoiTax(decimalFormatTwoDigit.format(BigDecimal.ZERO));
		}
		
		Body wsBody = new Body();
		wsBody.setPoso112FormInfo(wsPoso0112FormInfo);
		
		InquiryBcsReqHeader wsInquiryBcsReqHeader = new InquiryBcsReqHeader();
		wsInquiryBcsReqHeader.setHeader(wsHeader);
		wsInquiryBcsReqHeader.setBody(wsBody);
		
		InsertPOSO0112Operation wsRequest = new InsertPOSO0112Operation();
		wsRequest.setArg0(wsInquiryBcsReqHeader);
		
		return wsRequest;
	}
}
