package th.go.excise.edbarcode.report.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SummaryReport {
	
	private String sumAllTaxByValue;
	private String sumAllTaxByQuantity;
	private String sumAllTax;
	private String taxLessType;
	private String taxLessFrom;
	private String taxLessAmount;
	private String taxDeductionOnBookNo;
	private String taxDeductionOnBookAmount;
	private String paymentExciseAmount;
	private String paymentMunicipalPercent;
	private String paymentMunicipalAmount;
	private String paymentExciseAndMunicipalTaxAmount;
	private String paymentOtherAmount;
	private String paymentNetTaxAmount;
	private String printType;
	private String paymentFundHealthAmount;
	private String paymentFundTVAmount;
	private String paymentFundSportAmount;
	
	// Parameter for Staff (2)
	private String referenceNumber;
	private String submissionDate;
	
	
	@XmlElement(name = "SumAllTaxByValue")
	public String getSumAllTaxByValue() {
		return sumAllTaxByValue;
	}

	public void setSumAllTaxByValue(String sumAllTaxByValue) {
		this.sumAllTaxByValue = sumAllTaxByValue;
	}
	
	@XmlElement(name = "SumAllTaxByQuantity")
	public String getSumAllTaxByQuantity() {
		return sumAllTaxByQuantity;
	}

	public void setSumAllTaxByQuantity(String sumAllTaxByQuantity) {
		this.sumAllTaxByQuantity = sumAllTaxByQuantity;
	}

	@XmlElement(name = "SumAllTax")
	public String getSumAllTax() {
		return sumAllTax;
	}

	public void setSumAllTax(String sumAllTax) {
		this.sumAllTax = sumAllTax;
	}

	@XmlElement(name = "TaxLessType")
	public String getTaxLessType() {
		return taxLessType;
	}

	public void setTaxLessType(String taxLessType) {
		this.taxLessType = taxLessType;
	}

	@XmlElement(name = "TaxLessFrom")
	public String getTaxLessFrom() {
		return taxLessFrom;
	}

	public void setTaxLessFrom(String taxLessFrom) {
		this.taxLessFrom = taxLessFrom;
	}

	@XmlElement(name = "TaxLessAmount")
	public String getTaxLessAmount() {
		return taxLessAmount;
	}

	public void setTaxLessAmount(String taxLessAmount) {
		this.taxLessAmount = taxLessAmount;
	}

	@XmlElement(name = "TaxDeductionOnBookNo")
	public String getTaxDeductionOnBookNo() {
		return taxDeductionOnBookNo;
	}

	public void setTaxDeductionOnBookNo(String taxDeductionOnBookNo) {
		this.taxDeductionOnBookNo = taxDeductionOnBookNo;
	}

	@XmlElement(name = "TaxDeductionOnBookAmount")
	public String getTaxDeductionOnBookAmount() {
		return taxDeductionOnBookAmount;
	}

	public void setTaxDeductionOnBookAmount(String taxDeductionOnBookAmount) {
		this.taxDeductionOnBookAmount = taxDeductionOnBookAmount;
	}

	@XmlElement(name = "PaymentExciseAmount")
	public String getPaymentExciseAmount() {
		return paymentExciseAmount;
	}

	public void setPaymentExciseAmount(String paymentExciseAmount) {
		this.paymentExciseAmount = paymentExciseAmount;
	}

	@XmlElement(name = "MoiRate")
	public String getPaymentMunicipalPercent() {
		return paymentMunicipalPercent;
	}

	public void setPaymentMunicipalPercent(String paymentMunicipalPercent) {
		this.paymentMunicipalPercent = paymentMunicipalPercent;
	}

	@XmlElement(name = "PaymentMunicipalAmount")
	public String getPaymentMunicipalAmount() {
		return paymentMunicipalAmount;
	}

	public void setPaymentMunicipalAmount(String paymentMunicipalAmount) {
		this.paymentMunicipalAmount = paymentMunicipalAmount;
	}

	@XmlElement(name = "PaymentExciseAndMunicipalTaxAmount")
	public String getPaymentExciseAndMunicipalTaxAmount() {
		return paymentExciseAndMunicipalTaxAmount;
	}

	public void setPaymentExciseAndMunicipalTaxAmount(String paymentExciseAndMunicipalTaxAmount) {
		this.paymentExciseAndMunicipalTaxAmount = paymentExciseAndMunicipalTaxAmount;
	}

	@XmlElement(name = "PaymentOtherAmount")
	public String getPaymentOtherAmount() {
		return paymentOtherAmount;
	}

	public void setPaymentOtherAmount(String paymentOtherAmount) {
		this.paymentOtherAmount = paymentOtherAmount;
	}

	@XmlElement(name = "PaymentNetTaxAmount")
	public String getPaymentNetTaxAmount() {
		return paymentNetTaxAmount;
	}

	public void setPaymentNetTaxAmount(String paymentNetTaxAmount) {
		this.paymentNetTaxAmount = paymentNetTaxAmount;
	}

	@XmlElement(name = "PrintType")
	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	@XmlElement(name = "PaymentFundHealthAmount")
	public String getPaymentFundHealthAmount() {
		return paymentFundHealthAmount;
	}

	public void setPaymentFundHealthAmount(String paymentFundHealthAmount) {
		this.paymentFundHealthAmount = paymentFundHealthAmount;
	}

	@XmlElement(name = "PaymentFundTVAmount")
	public String getPaymentFundTVAmount() {
		return paymentFundTVAmount;
	}

	public void setPaymentFundTVAmount(String paymentFundTVAmount) {
		this.paymentFundTVAmount = paymentFundTVAmount;
	}

	@XmlElement(name = "PaymentFundSportAmount")
	public String getPaymentFundSportAmount() {
		return paymentFundSportAmount;
	}

	public void setPaymentFundSportAmount(String paymentFundSportAmount) {
		this.paymentFundSportAmount = paymentFundSportAmount;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}