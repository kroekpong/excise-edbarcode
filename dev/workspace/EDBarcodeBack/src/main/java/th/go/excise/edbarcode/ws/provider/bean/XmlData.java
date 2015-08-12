package th.go.excise.edbarcode.ws.provider.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import th.go.excise.edbarcode.ws.provider.oxm.SR12011Info;
import th.go.excise.edbarcode.ws.provider.oxm.SubmitOnlineHeader;

@XmlRootElement(name = "XmlData")
public class XmlData {
	
	private SubmitOnlineHeader submitOnlineHeader;
	private SR12011Info sr12011Info;
	
	
	@XmlElement(name = "SubmitOnlineHeader")
	public SubmitOnlineHeader getSubmitOnlineHeader() {
		return submitOnlineHeader;
	}

	public void setSubmitOnlineHeader(SubmitOnlineHeader submitOnlineHeader) {
		this.submitOnlineHeader = submitOnlineHeader;
	}
	
	@XmlElement(name = "SR12011Info")
	public SR12011Info getSr12011Info() {
		return sr12011Info;
	}

	public void setSr12011Info(SR12011Info sr12011Info) {
		this.sr12011Info = sr12011Info;
	}
	
}
