package th.go.excise.edbarcode.report.common.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "SR12011FormReport")
public class SR12011FormReport {
	
	private TaxpayerInfoReport taxpayerInfoReport;
	private List<GoodsEntryReport> goodsListReport;
	private SummaryReport summaryReport;
	
	
	@XmlElement(name = "TaxpayerInfoReport")
	public TaxpayerInfoReport getTaxpayerInfoReport() {
		return taxpayerInfoReport;
	}

	public void setTaxpayerInfoReport(TaxpayerInfoReport taxpayerInfoReport) {
		this.taxpayerInfoReport = taxpayerInfoReport;
	}
	
	@XmlElementWrapper(name = "GoodsListReport")
	@XmlElement(name = "GoodsEntryReport")
	public List<GoodsEntryReport> getGoodsListReport() {
		return goodsListReport;
	}

	public void setGoodsListReport(List<GoodsEntryReport> goodsListReport) {
		this.goodsListReport = goodsListReport;
	}
	
	@XmlElement(name = "SummaryReport")
	public SummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
