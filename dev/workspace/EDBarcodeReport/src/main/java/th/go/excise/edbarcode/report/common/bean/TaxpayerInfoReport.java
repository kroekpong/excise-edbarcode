package th.go.excise.edbarcode.report.common.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxpayerInfoReport {
	
	private String cusId;
	private String taxpayerId;
	private String tin;
	private String companyName;
	private String licenseNo;
	private String effectiveDate;
	private String expireDate;
	private String taxpayerName;
	private TaxpayerAddressReport taxpayerAddressReport;
	
	
	@XmlElement(name = "CusId")
	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	@XmlElement(name = "TaxpayerId")
	public String getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	@XmlElement(name = "Tin")
	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@XmlElement(name = "LicenseNo")
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@XmlElement(name = "EffectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@XmlElement(name = "ExpireDate")
	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	@XmlElement(name = "TaxpayerName")
	public String getTaxpayerName() {
		return taxpayerName;
	}

	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	
	@XmlElement(name = "TaxpayerAddressReport")
	public TaxpayerAddressReport getTaxpayerAddressReport() {
		return taxpayerAddressReport;
	}

	public void setTaxpayerAddressReport(TaxpayerAddressReport taxpayerAddressReport) {
		this.taxpayerAddressReport = taxpayerAddressReport;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
