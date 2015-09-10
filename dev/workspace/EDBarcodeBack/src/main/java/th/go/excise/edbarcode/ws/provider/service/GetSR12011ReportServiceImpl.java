package th.go.excise.edbarcode.ws.provider.service;

import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.report.bean.FundEntryReport;
import th.go.excise.edbarcode.report.bean.GoodsEntryReport;
import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.bean.SummaryReport;
import th.go.excise.edbarcode.report.bean.TaxpayerAddressReport;
import th.go.excise.edbarcode.report.bean.TaxpayerInfoReport;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;
import th.go.excise.edbarcode.ws.provider.bean.XmlData;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportRequest;
import th.go.excise.edbarcode.ws.provider.oxm.EbarcodeGetSR12011ReportResponse;
import th.go.excise.edbarcode.ws.provider.oxm.FundEntryInfo;
import th.go.excise.edbarcode.ws.provider.oxm.GoodsEntryInfo;
import th.go.excise.edbarcode.ws.provider.oxm.PDFDocument;
import th.go.excise.edbarcode.ws.provider.oxm.SR12011Info;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineHeader;

import com.baiwa.framework.common.util.NumberUtils;

@Service("getSR12011ReportService")
public class GetSR12011ReportServiceImpl implements GetSR12011ReportService {
	
	private static final Logger logger = LogManager.getLogger(GetSR12011ReportServiceImpl.class);
	
	@Override
	public EbarcodeGetSR12011ReportResponse getResponse(EbarcodeGetSR12011ReportRequest request) {
		logger.info("getResponse method");
		
		EbarcodeGetSR12011ReportResponse response = null;
		
		try {
			SR12011FormReport formObj = prepareSR12011FormObject(request);
			
			EDBarcodeReportService barcodeReportService = new EDBarcodeReportServiceImpl();
			byte[] content = barcodeReportService.generateReport(formObj);
			
			PDFDocument document = new PDFDocument();
			document.setMimeType("application/pdf");
			document.setContent(content);
			
			response = new EbarcodeGetSR12011ReportResponse();
			response.setGetSR12011ReportStatus(WebServiceConstant.STATUS_CODE.OK);
			response.setGetSR12011ReportDesc("Generate byte[] PDF Success");
			response.setPDFDocument(document);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new EbarcodeGetSR12011ReportResponse();
			response.setGetSR12011ReportStatus(WebServiceConstant.STATUS_CODE.ERROR);
			response.setGetSR12011ReportDesc(e.getMessage());
		}
		
		return response;
	}
	
	private SR12011FormReport prepareSR12011FormObject(EbarcodeGetSR12011ReportRequest request) throws JAXBException, ParseException {
		String xmlDataString = request.getBinaryInformation().getXmlDataBinary();
		System.out.println(xmlDataString);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		StringReader reader = new StringReader(xmlDataString);
		XmlData xmlData = (XmlData) jaxbUnmarshaller.unmarshal(reader);
		reader.close();
		
		SubmitOnlineHeader submitOnlineHeader = xmlData.getSubmitOnlineHeader();
		SR12011Info sr12011Info = xmlData.getSr12011Info();
		
		// TaxpayerInfo
		TaxpayerInfoReport taxpayerInfo = new TaxpayerInfoReport();
		taxpayerInfo.setCusId(submitOnlineHeader.getCusId());
		taxpayerInfo.setTaxpayerId(submitOnlineHeader.getTaxpayerId());
		taxpayerInfo.setCompanyName(sr12011Info.getTaxpayerInfo().getCompanyName());
		taxpayerInfo.setTaxpayerName(sr12011Info.getTaxpayerInfo().getTaxpayerName());
		taxpayerInfo.setTin(sr12011Info.getTaxpayerInfo().getTin());
		taxpayerInfo.setLicenseNo(sr12011Info.getTaxpayerInfo().getLicenseNo());
		taxpayerInfo.setEffectiveDate(sr12011Info.getTaxpayerInfo().getEffectiveDate());
		taxpayerInfo.setExpireDate(sr12011Info.getTaxpayerInfo().getExpireDate());
		
		// Taxpayer
		TaxpayerAddressReport taxpayerAddress = new TaxpayerAddressReport();
		taxpayerAddress.setHouseNumber(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getHouseNumber());
		taxpayerAddress.setMooNumber(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getMooNumber());
		taxpayerAddress.setTrokSoiName(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getTrokSoiName());
		taxpayerAddress.setStreetName(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getStreetName());
		taxpayerAddress.setThambolName(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getThambolName());
		taxpayerAddress.setAmphurName(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getAmphurName());
		taxpayerAddress.setProvinceName(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getProvinceName());
		taxpayerAddress.setPostcode(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getPostcode());
		taxpayerAddress.setTelNumber(sr12011Info.getTaxpayerInfo().getTaxpayerAddressInfo().getTelNumber());
		taxpayerInfo.setTaxpayerAddressReport(taxpayerAddress);
		
		// GoodsEntryList
		List<GoodsEntryReport> goodsEntryReportList = new ArrayList<GoodsEntryReport>();
		GoodsEntryReport goodsEntryReport = null;
		for (GoodsEntryInfo goodsEntryInfo : sr12011Info.getGoodsListInfo().getGoodsEntryInfo()) {
			goodsEntryReport = new GoodsEntryReport();
			goodsEntryReport.setSeqNo(goodsEntryInfo.getSeqNo());
			goodsEntryReport.setProductTypeCode(goodsEntryInfo.getProductCode());
			goodsEntryReport.setProductTypeDesc(goodsEntryInfo.getProductTypeDesc());
			goodsEntryReport.setGoodsDesc(goodsEntryInfo.getGoodsDesc());
			goodsEntryReport.setDegree(goodsEntryInfo.getDegree());
			goodsEntryReport.setGoodsSize(goodsEntryInfo.getGoodsSize());
			goodsEntryReport.setGoodsPiece(NumberUtils.nullToZero(goodsEntryInfo.getGoodsPiece()).toString());
			goodsEntryReport.setGoodsQuantity(NumberUtils.nullToZero(goodsEntryInfo.getGoodsQuantity()).toString());
			goodsEntryReport.setUnitPrice(NumberUtils.nullToZero(goodsEntryInfo.getUnitPrice()).toString());
			goodsEntryReport.setDeclarePrice(NumberUtils.nullToZero(goodsEntryInfo.getDeclarePrice()).toString());
			goodsEntryReport.setTaxByValue(NumberUtils.nullToZero(goodsEntryInfo.getTaxByValue()).toString());
			goodsEntryReport.setTaxByQuantity(NumberUtils.nullToZero(goodsEntryInfo.getTaxByQuantity()).toString());
			goodsEntryReport.setTaxByQuantityOver(NumberUtils.nullToZero(goodsEntryInfo.getTaxByQuantityOver()).toString());
			goodsEntryReport.setTaxByQuantityWithOver(NumberUtils.nullToZero(goodsEntryInfo.getTaxByQuantityWithOver()).toString());
			goodsEntryReport.setNetTaxByValue(NumberUtils.nullToZero(goodsEntryInfo.getNetTaxByValue()).toString());
			goodsEntryReport.setNetTaxByQuantity(NumberUtils.nullToZero(goodsEntryInfo.getNetTaxByQuantity()).toString());
			goodsEntryReportList.add(goodsEntryReport);
		}
		
		// Summary
		SummaryReport summary = new SummaryReport();
		summary.setSumAllTaxByValue(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getSumAllTaxByValue()).toString());
		summary.setSumAllTaxByQuantity(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getSumAllTaxByQuantity()).toString());
		summary.setSumAllTax(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getSumAllTax()).toString());
		summary.setTaxLessFrom(sr12011Info.getSummaryInfo().getTaxLessFrom());
		summary.setTaxLessAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getTaxLessAmount()).toString());
		summary.setTaxDeductionOnBookNo(sr12011Info.getSummaryInfo().getTaxDeductionOnBookNo());
		summary.setTaxDeductionOnBookAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getTaxDeductionOnBookAmount()).toString());
		summary.setPaymentExciseAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentExciseAmount()).toString());
		summary.setPaymentMunicipalAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentMunicipalAmount()).toString());
		summary.setPaymentFundHealthAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentFundHealthAmount()).toString());
		summary.setPaymentFundTVAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentFundTVAmount()).toString());
		summary.setPaymentFundSportAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentFundSportAmount()).toString());
		summary.setPrintType(sr12011Info.getSummaryInfo().getPrintType());
		summary.setPaymentMunicipalPercent(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getMoiRate()).toString());
		summary.setPaymentExciseAndMunicipalTaxAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentExciseAndMunicipalTaxAmount()).toString());
		summary.setPaymentOtherAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentOtherAmount()).toString());
		summary.setPaymentNetTaxAmount(NumberUtils.nullToZero(sr12011Info.getSummaryInfo().getPaymentNetTaxAmount()).toString());
		
		// For Staff (2)
		summary.setReferenceNumber(request.getDataInformation().getReferenceNumber());
		summary.setSubmissionDate(submitOnlineHeader.getSubmissionDate());
		
		// FundEntryList
		List<FundEntryReport> fundEntryReportList = new ArrayList<FundEntryReport>();
		FundEntryReport fundEntryReport = null;
		for (FundEntryInfo fundEntryInfo : sr12011Info.getFundListInfo().getFundEntryInfo()) {
			fundEntryReport = new FundEntryReport();
			fundEntryReport.setFundType(fundEntryInfo.getFundType());
			fundEntryReport.setFundRate(NumberUtils.nullToZero(fundEntryInfo.getFundRate()).toString());
			fundEntryReport.setFundAmt(NumberUtils.nullToZero(fundEntryInfo.getFundAmt()).toString());
			fundEntryReport.setCreditAmt(NumberUtils.nullToZero(fundEntryInfo.getCreditAmt()).toString());
			fundEntryReport.setNetAmt(NumberUtils.nullToZero(fundEntryInfo.getNetAmt()).toString());
			fundEntryReportList.add(fundEntryReport);
		}
		
		SR12011FormReport formReport = new SR12011FormReport();
		formReport.setTaxpayerInfoReport(taxpayerInfo);
		formReport.setGoodsListReport(goodsEntryReportList);
		formReport.setSummaryReport(summary);
		formReport.setFundListReport(fundEntryReportList);
		
		return formReport;
	}

}
