package th.go.excise.edbarcode.report.service;

import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import th.go.excise.edbarcode.report.bean.GoodsEntryReport;
import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.bean.SummaryReport;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class SR12011BarcodeData {
	
	public void generateBarcodePageData(StringBuilder builder, String formId, int currentPage, int totalPage) {
		builder.append(ReportConstant.EVENT_CODE.PAGE);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(formId);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(ReportConstant.FORM_CODE_SR);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(currentPage);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(totalPage);
	}
	
	public void generateBarcodeHeaderData(StringBuilder builder, SR12011FormReport form) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.HEADER);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getTaxpayerInfoReport().getLicenseNo());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getTaxpayerInfoReport().getCusId());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getTaxpayerInfoReport().getTaxpayerId());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getSummaryReport().getPrintType());
	}
	
	public void generateBarcodeDetailData(StringBuilder builder, List<GoodsEntryReport> goodsEntryList) {
		for (GoodsEntryReport goodsEntry : goodsEntryList) {
			builder.append(ReportConstant.SEPERATE_LINE);
			builder.append(ReportConstant.EVENT_CODE.DETAIL);
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getSeqNo());
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getProductTypeCode());
			builder.append("#");
			builder.append(goodsEntry.getCategoryCode1());
			builder.append("#");
			builder.append(goodsEntry.getCategoryCode2());
			builder.append("#");
			builder.append(goodsEntry.getCategoryCode3());
			builder.append("#");
			builder.append(goodsEntry.getCategoryCode4());
			builder.append("#");
			builder.append(goodsEntry.getCategoryCode5());
			builder.append("#");
			builder.append(goodsEntry.getUnitCode());			
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getGoodsPiece().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getGoodsQuantity().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getUnitPrice().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getDeclarePrice().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByValue().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByQuantity().replaceAll(",", ""));
			builder.append(ReportConstant.SEPERATE_STRING);
			builder.append(goodsEntry.getTaxByQuantityOver().replaceAll(",", ""));
		}
	}
	
	public void generateBarcodeSummaryData(StringBuilder builder, SummaryReport summary) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.SUMMARY);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getSumAllTaxByValue().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getSumAllTaxByQuantity().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxLessFrom());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxLessAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxDeductionOnBookNo());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getTaxDeductionOnBookAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentExciseAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentMunicipalPercent());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentMunicipalAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentExciseAndMunicipalTaxAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentOtherAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentNetTaxAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundHealthAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundTVAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(summary.getPaymentFundSportAmount().replaceAll(",", ""));
	}
	
	public void generateBarcodeReferenceData(StringBuilder builder, String referenceNumber) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.REFERENCE);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(referenceNumber);
	}
	
	public JasperDesign getJasperDesignWithResizeQrCode() throws JRException {
		InputStream jrxmlFile = ReportUtil.getReportFile(ReportConstant.REPORT.SR120_11 + "." + ReportConstant.FILE.JRXML);
		JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFile);
		
		final int heightChange = 100;
		
		JRDesignElement qrCode = (JRDesignElement) jasperDesign.getBackground().getElementByKey("qrCode");
		qrCode.setX(100);
		qrCode.setHeight(55);
		qrCode.setWidth(55);
		
		JRDesignElement summaryLeftFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("summaryLeftFrame");
		summaryLeftFrame.setHeight(summaryLeftFrame.getHeight() - heightChange);
		summaryLeftFrame.setY(summaryLeftFrame.getY() + heightChange);
		
		JRDesignElement summaryCenterFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("summaryCenterFrame");
		summaryCenterFrame.setHeight(summaryCenterFrame.getHeight() - heightChange);
		summaryCenterFrame.setY(summaryCenterFrame.getY() + heightChange);
		
		JRDesignElement summaryRightFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("summaryRightFrame");
		summaryRightFrame.setHeight(summaryRightFrame.getHeight() - heightChange);
		summaryRightFrame.setY(summaryRightFrame.getY() + heightChange);
		
		JRDesignElement summaryFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("summaryFrame");
		summaryFrame.setY(summaryFrame.getY() + heightChange);
		
		JRDesignElement detailFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("detailFrame");
		detailFrame.setHeight(detailFrame.getHeight() + heightChange);
		
		JRDesignElement detailXFrame = null;
		for (int i = 0; i < 8; i++) {
			detailXFrame = (JRDesignElement) jasperDesign.getBackground().getElementByKey("d" + (i + 1));
			detailXFrame.setHeight(detailXFrame.getHeight() + heightChange);
		}
		
		return jasperDesign;
	}
	
}
