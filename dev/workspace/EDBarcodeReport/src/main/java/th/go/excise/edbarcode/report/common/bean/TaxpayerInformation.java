package th.go.excise.edbarcode.report.common.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxpayerInformation {

	private String registratiodId;
	private String customerId;
	private String tin;
	private String pin_Nit_Id;
	private String companyName;
	private TaxpayerAddress taxpayerAddress;
	private LicenseInfo licenseInfo;

	@XmlElement(name = "RegistrationId")
	public String getRegistratiodId() {
		return registratiodId;
	}

	public void setRegistratiodId(String registratiodId) {
		this.registratiodId = registratiodId;
	}

	@XmlElement(name = "CustomerId")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@XmlElement(name = "Tin")
	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	@XmlElement(name = "Pin_Nit_Id")
	public String getPin_Nit_Id() {
		return pin_Nit_Id;
	}

	public void setPin_Nit_Id(String pin_Nit_Id) {
		this.pin_Nit_Id = pin_Nit_Id;
	}

	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@XmlElement(name = "TaxpayerAddress", type = TaxpayerAddress.class)
	public TaxpayerAddress getTaxpayerAddress() {
		return taxpayerAddress;
	}

	public void setTaxpayerAddress(TaxpayerAddress taxpayerAddress) {
		this.taxpayerAddress = taxpayerAddress;
	}

	@XmlElement(name = "LicenseInfo")
	public LicenseInfo getLicenseInfo() {
		return licenseInfo;
	}

	public void setLicenseInfo(LicenseInfo licenseInfo) {
		this.licenseInfo = licenseInfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
