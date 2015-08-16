package th.go.excise.edbarcode.ws.provider.service;

import java.io.StringReader;
import java.text.DecimalFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.client.InsertPOSO0112OperationService;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Body;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.Header;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InquiryBcsReqHeader;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112Operation;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.InsertPOSO0112OperationResponse;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.POSO0112FormInfo;
import th.go.excise.edbarcode.ws.client.pcc.insert0112.oxm.PSO112Goods;
import th.go.excise.edbarcode.ws.provider.bean.XmlData;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeSendToBackendResponse;
import th.go.excise.edbarcode.ws.provider.oxm.GoodsEntryInfo;
import th.go.excise.edbarcode.ws.provider.oxm.SR12011Info;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineHeader;

@Service("sendToBackendService")
public class SendToBackendServiceImpl implements SendToBackendService {
	
	private static final Logger logger = LogManager.getLogger(SendToBackendServiceImpl.class);
	
	@Autowired
	private InsertPOSO0112OperationService insertPOSO0112OperationService;
	
	@Override
	public EbarcodeSendToBackendResponse getResponse(EbarcodeSendToBackendRequest request) {
		logger.info("getResponse method");
		
		EbarcodeSendToBackendResponse response = null;
		
		try {
			// Create WebService Request
			InsertPOSO0112Operation wsRequest = prepareWsRequest(request);
			
			// Call Service
			InsertPOSO0112OperationResponse wsResponse = insertPOSO0112OperationService.doService(wsRequest);
			
			if (WebServiceConstant.STATUS_CODE.OK.equalsIgnoreCase(wsResponse.getReturn().getReturnCode())) {
				// success
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getReturn().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getReturn().getReturnDesc());
			} else {
				// error
				response = new EbarcodeSendToBackendResponse();
				response.setSendTobackendStatus(wsResponse.getReturn().getReturnCode());
				response.setSendTobackendDesc(wsResponse.getReturn().getReturnDesc());
				logger.error("Call SendToBackendService Failed: {}: {}", response.getSendTobackendStatus(), response.getSendTobackendDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeSendToBackendResponse();
			response.setSendTobackendStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setSendTobackendDesc(e.getMessage());
		}
		
		return response;
	}

	private InsertPOSO0112Operation prepareWsRequest(EbarcodeSendToBackendRequest request) throws JAXBException {
		
		DecimalFormat decimalFormatZeroDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.ZERO_DIGIT);
		DecimalFormat decimalFormatTwoDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.TWO_DIGIT);
		DecimalFormat decimalFormatFourDigit = new DecimalFormat(ReportConstant.DECIMAL_FORMAT.FOUR_DIGIT);
		
		String xmlDataString = request.getBinaryInformation().getXmlDataBinary();
		System.out.println(xmlDataString);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		StringReader reader = new StringReader(xmlDataString);
		XmlData xmlData = (XmlData) jaxbUnmarshaller.unmarshal(reader);
		reader.close();
		
		SubmitOnlineHeader submitOnlineHeader = xmlData.getSubmitOnlineHeader();
		SR12011Info sr12011Info = xmlData.getSr12011Info();
		
		// Header
		Header wsHeader = new Header();
		wsHeader.setServiceID("007BAR0001");
		wsHeader.setSystemID("007");
		wsHeader.setUserId("BARCODE");
		wsHeader.setPassword("11111111");
		wsHeader.setIPAddress(submitOnlineHeader.getIpAddress());
		
		// Body
		POSO0112FormInfo wsPoso0112FormInfo = new POSO0112FormInfo(); 
		wsPoso0112FormInfo.setRegId(submitOnlineHeader.getRegistratronId());
		wsPoso0112FormInfo.setCustomerId(submitOnlineHeader.getCusId());
		wsPoso0112FormInfo.setTin(sr12011Info.getTaxpayerInfo().getTin());
		wsPoso0112FormInfo.setPinNitId(sr12011Info.getTaxpayerInfo().getTin());
		wsPoso0112FormInfo.setSubbmissionEmail(submitOnlineHeader.getSubmissionEmail());
		wsPoso0112FormInfo.setFormType("");// Wait PCC
		wsPoso0112FormInfo.setFormCode("");// Wait PCC
		wsPoso0112FormInfo.setFormEffectiveDate("");// Current Date
		wsPoso0112FormInfo.setFormReferenceNumber(request.getDataInformation().getReferenceNumber());
		wsPoso0112FormInfo.setPaymentReferenceId(0);// No Data, Input??
		wsPoso0112FormInfo.setBankReferenceId("");// No Data, Can input empty String??
		wsPoso0112FormInfo.setPayType12("");// No Data, Can input empty String??
		wsPoso0112FormInfo.setTaxMonth(1);// Current Month, 1-12
		wsPoso0112FormInfo.setTaxYear(2015);// Current Year, YYYY -> 2015
		wsPoso0112FormInfo.setIncCode("");// No Data, Can input empty String??
		wsPoso0112FormInfo.setFactoryDateBegin("");// No Data, Can input empty String??
		wsPoso0112FormInfo.setFactoryDateEnd("");// No Data, Can input empty String??
		wsPoso0112FormInfo.setExciseTax(sr12011Info.getSummaryInfo().getSumAllTax().doubleValue());
		wsPoso0112FormInfo.setReduceAmt(sr12011Info.getSummaryInfo().getTaxLessAmount().doubleValue());
		wsPoso0112FormInfo.setPenaltyAmt(0.0);// No Data, Can default to zero
		wsPoso0112FormInfo.setSurchargeAmt(0.0);// No Data, Can default to zero
		wsPoso0112FormInfo.setMoiRate(decimalFormatZeroDigit.format(sr12011Info.getSummaryInfo().getMoiRate()));
		wsPoso0112FormInfo.setMoitax(decimalFormatTwoDigit.format(sr12011Info.getSummaryInfo().getPaymentMunicipalAmount()));
		wsPoso0112FormInfo.setSumCreditExciseTax(0);// FIXME
		wsPoso0112FormInfo.setSumCreditMoiTax(0);// FIXME
		wsPoso0112FormInfo.setNetExciseTax(sr12011Info.getSummaryInfo().getPaymentExciseAmount().doubleValue());
		wsPoso0112FormInfo.setNetMoiTax(sr12011Info.getSummaryInfo().getPaymentMunicipalAmount().doubleValue());// FIXME check
		wsPoso0112FormInfo.setPrnType(sr12011Info.getSummaryInfo().getPrintType());
		wsPoso0112FormInfo.setRecType(sr12011Info.getSummaryInfo().getRecType());
		
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
			wsPso112Goods.setTaxQuantity(goodsEntryInfo.getTaxQuantity().toString());
			wsPso112Goods.setTaxQuantityNumber("");
			wsPso112Goods.setTaxQuantityPerUnit("");
			wsPso112Goods.setTaxValue("");
			wsPso112Goods.setPriceFlag(goodsEntryInfo.getPriceFlag());
			wsPso112Goods.setInformPrice(decimalFormatFourDigit.format(goodsEntryInfo.getInformPrice()));
			wsPso112Goods.setDeclarePrice(decimalFormatFourDigit.format(goodsEntryInfo.getDeclarePrice()));
			wsPso112Goods.setUnitPirce(decimalFormatFourDigit.format(goodsEntryInfo.getUnitPrice()));
			wsPso112Goods.setGoodsNum(decimalFormatFourDigit.format(goodsEntryInfo.getGoodsNum()));
			wsPso112Goods.setGoodsValue(decimalFormatFourDigit.format(goodsEntryInfo.getGoodsValue()));
			wsPso112Goods.setTaxAmount(decimalFormatFourDigit.format(goodsEntryInfo.getTaxAmount()));
			wsPoso0112FormInfo.getPso112Goods().add(wsPso112Goods);
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
