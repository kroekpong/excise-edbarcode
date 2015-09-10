package th.go.excise.edbarcode.report.service;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import th.go.excise.edbarcode.report.bean.FundEntryReport;
import th.go.excise.edbarcode.report.bean.SR12011FormReport;
import th.go.excise.edbarcode.report.common.constant.ReportConstant;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class FundBarcodeData {
	
	public void generateBarcodeForFundReport(StringBuilder builder, SR12011FormReport form, FundEntryReport fundEntry) {
		generateBarcodePageData(builder, form.getFormId(), fundEntry.getFundType(), 1, 1);
		generateBarcodeHeaderData(builder, form);
		generateBarcodeSummaryData(builder, form, fundEntry);
	}
	
	public void generateBarcodeForFundReportRef(StringBuilder builder, SR12011FormReport form, String fundType) {
		generateBarcodePageData(builder, form.getFormId(), fundType, 1, 1);
		generateBarcodeReferenceData(builder, form.getSummaryReport().getReferenceNumber());
	}
	
	private void generateBarcodePageData(StringBuilder builder, String formId, String fundType, int currentPage, int totalPage) {
		builder.append(ReportConstant.EVENT_CODE.PAGE);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(formId);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(fundType);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(currentPage);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(totalPage);
	}
	
	private void generateBarcodeHeaderData(StringBuilder builder, SR12011FormReport form) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.HEADER);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getTaxpayerInfoReport().getCusId());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getTaxpayerInfoReport().getTaxpayerId());
	}
	
	private void generateBarcodeSummaryData(StringBuilder builder, SR12011FormReport form, FundEntryReport fundEntry) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.SUMMARY);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getSummaryReport().getSumAllTax().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(form.getSummaryReport().getExciseAmountSubtractTaxLessAmount().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(fundEntry.getFundRate());
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(fundEntry.getFundAmt().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(fundEntry.getCreditAmt().replaceAll(",", ""));
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(fundEntry.getNetAmt().replaceAll(",", ""));
	}
	
	private void generateBarcodeReferenceData(StringBuilder builder, String referenceNumber) {
		builder.append(ReportConstant.SEPERATE_LINE);
		builder.append(ReportConstant.EVENT_CODE.REFERENCE);
		builder.append(ReportConstant.SEPERATE_STRING);
		builder.append(referenceNumber);
	}
	
	public JasperDesign getJasperDesignWithResizeQrCode(String reportName) throws JRException {
		InputStream jrxmlFile = ReportUtil.getReportFile(reportName + "." + ReportConstant.FILE.JRXML);
		JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFile);
		
		JRDesignElement qrCode = (JRDesignElement) jasperDesign.getSummary().getElementByKey("qrCode");
		qrCode.setX(285);
		qrCode.setHeight(55);
		qrCode.setWidth(55);
		
		return jasperDesign;
	}
	
}
