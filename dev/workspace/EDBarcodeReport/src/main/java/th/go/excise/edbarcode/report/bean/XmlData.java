package th.go.excise.edbarcode.report.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XmlData")
public class XmlData {

	private SubmitOnlineHeader submitOnlineHeader;
	private SR12011FormReport sr12011FormReport;

	@XmlElement(name = "SubmitOnlineHeader")
	public SubmitOnlineHeader getSubmitOnlineHeader() {
		return submitOnlineHeader;
	}

	public void setSubmitOnlineHeader(SubmitOnlineHeader submitOnlineHeader) {
		this.submitOnlineHeader = submitOnlineHeader;
	}

	@XmlElement(name = "SR12011Info")
	public SR12011FormReport getSr12011FormReport() {
		return sr12011FormReport;
	}

	public void setSr12011FormReport(SR12011FormReport sr12011FormReport) {
		this.sr12011FormReport = sr12011FormReport;
	}

}
