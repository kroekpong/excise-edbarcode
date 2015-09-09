package th.go.excise.edbarcode.report.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "SR12011Info")
public class SR12011FormReport {
	
	private TaxpayerInfoReport taxpayerInfoReport;
	private List<GoodsEntryReport> goodsListReport;
	private SummaryReport summaryReport;
	private List<FundEntryReport> fundListReport;
	
	// Internal Data
	private String formId;
	
	@XmlElement(name = "TaxpayerInfo")
	public TaxpayerInfoReport getTaxpayerInfoReport() {
		return taxpayerInfoReport;
	}

	public void setTaxpayerInfoReport(TaxpayerInfoReport taxpayerInfoReport) {
		this.taxpayerInfoReport = taxpayerInfoReport;
	}
	
	@XmlElementWrapper(name = "GoodsListInfo")
	@XmlElement(name = "GoodsEntryInfo")
	public List<GoodsEntryReport> getGoodsListReport() {
		return goodsListReport;
	}

	public void setGoodsListReport(List<GoodsEntryReport> goodsListReport) {
		this.goodsListReport = goodsListReport;
	}
	
	@XmlElement(name = "SummaryInfo")
	public SummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}
	
	@XmlElementWrapper(name = "FundListInfo")
	@XmlElement(name = "FundEntryInfo")
	public List<FundEntryReport> getFundListReport() {
		return fundListReport;
	}

	public void setFundListReport(List<FundEntryReport> fundListReport) {
		this.fundListReport = fundListReport;
	}
	
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
