package th.go.excise.edbarcode.report.common.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GoodsEntryReport {

	private String seqNo;
	private String ProductTypeCode;
	private String productTypeDesc;
	private String goodsDesc;
	private String degree;
	private String goodsSize;
	private String goodsPiece;
	private String goodsQuantity;
	private String unitPrice;
	private String declarePrice;
	private String taxByValue;
	private String taxByQuantity;
	private String taxByQuantityOver;
	private String taxByQuantityWithOver;
	private String netTaxByValue;
	private String netTaxByQuantity;
	
	
	
	@XmlElement(name = "SeqNo")
	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	@XmlElement(name = "ProductTypeCode")
	public String getProductTypeCode() {
		return ProductTypeCode;
	}

	public void setProductTypeCode(String productTypeCode) {
		ProductTypeCode = productTypeCode;
	}

	@XmlElement(name = "ProductTypeDesc")
	public String getProductTypeDesc() {
		return productTypeDesc;
	}

	public void setProductTypeDesc(String productTypeDesc) {
		this.productTypeDesc = productTypeDesc;
	}

	@XmlElement(name = "GoodsDesc")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	@XmlElement(name = "Degree")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@XmlElement(name = "GoodsSize")
	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	@XmlElement(name = "GoodsPiece")
	public String getGoodsPiece() {
		return goodsPiece;
	}

	public void setGoodsPiece(String goodsPiece) {
		this.goodsPiece = goodsPiece;
	}

	@XmlElement(name = "GoodsQuantity")
	public String getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	
	@XmlElement(name = "UnitPrice")
	public String getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@XmlElement(name = "DeclarePrice")
	public String getDeclarePrice() {
		return declarePrice;
	}

	public void setDeclarePrice(String declarePrice) {
		this.declarePrice = declarePrice;
	}

	@XmlElement(name = "TaxByValue")
	public String getTaxByValue() {
		return taxByValue;
	}

	public void setTaxByValue(String taxByValue) {
		this.taxByValue = taxByValue;
	}

	@XmlElement(name = "TaxByQuantity")
	public String getTaxByQuantity() {
		return taxByQuantity;
	}

	public void setTaxByQuantity(String taxByQuantity) {
		this.taxByQuantity = taxByQuantity;
	}

	@XmlElement(name = "TaxByQuantityOver")
	public String getTaxByQuantityOver() {
		return taxByQuantityOver;
	}

	public void setTaxByQuantityOver(String taxByQuantityOver) {
		this.taxByQuantityOver = taxByQuantityOver;
	}

	@XmlElement(name = "TaxByQuantityWithOver")
	public String getTaxByQuantityWithOver() {
		return taxByQuantityWithOver;
	}

	public void setTaxByQuantityWithOver(String taxByQuantityWithOver) {
		this.taxByQuantityWithOver = taxByQuantityWithOver;
	}
	
	@XmlElement(name = "NetTaxByValue")
	public String getNetTaxByValue() {
		return netTaxByValue;
	}

	public void setNetTaxByValue(String netTaxByValue) {
		this.netTaxByValue = netTaxByValue;
	}
	
	@XmlElement(name = "NetTaxByQuantity")
	public String getNetTaxByQuantity() {
		return netTaxByQuantity;
	}

	public void setNetTaxByQuantity(String netTaxByQuantity) {
		this.netTaxByQuantity = netTaxByQuantity;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
