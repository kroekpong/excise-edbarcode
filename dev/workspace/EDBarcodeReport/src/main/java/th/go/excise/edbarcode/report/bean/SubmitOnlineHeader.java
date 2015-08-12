package th.go.excise.edbarcode.report.bean;

import javax.xml.bind.annotation.XmlElement;

public class SubmitOnlineHeader {

	private String cusId;
	private String taxpayerId;

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

}
