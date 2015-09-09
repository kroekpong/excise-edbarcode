package th.go.excise.edbarcode.report.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FundEntryReport {
	
	private String fundType;
	private String fundAmt;
	private String fundRate;
	private String creditAmt;
	private String netAmt;

	
	@XmlElement(name = "FundType")
	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	@XmlElement(name = "FundAmt")
	public String getFundAmt() {
		return fundAmt;
	}

	public void setFundAmt(String fundAmt) {
		this.fundAmt = fundAmt;
	}

	@XmlElement(name = "FundRate")
	public String getFundRate() {
		return fundRate;
	}

	public void setFundRate(String fundRate) {
		this.fundRate = fundRate;
	}

	@XmlElement(name = "CreditAmt")
	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	@XmlElement(name = "NetAmt")
	public String getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(String netAmt) {
		this.netAmt = netAmt;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
