package th.go.excise.edbarcode.report.common.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "SR12011Form")
public class SR12011Form {

	private TaxpayerInformation taxpayerInformation;
	private List<GoodsEntry> goodsList;

	@XmlElement(name = "TaxpayerInformation")
	public TaxpayerInformation getTaxpayerInformation() {
		return taxpayerInformation;
	}

	public void setTaxpayerInformation(TaxpayerInformation taxpayerInformation) {
		this.taxpayerInformation = taxpayerInformation;
	}
	
	@XmlElementWrapper(name = "GoodsList")
	@XmlElement(name = "GoodsEntry")
	public List<GoodsEntry> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsEntry> goodsList) {
		this.goodsList = goodsList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
