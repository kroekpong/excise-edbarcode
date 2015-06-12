package th.go.excise.edbarcode.report.common.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GoodsEntry {

//	private String productCode;
//	private String categoryCode1;
//	private String categoryCode2;
//	private String categoryCode3;
//	private String categoryCode4;
//	private String categoryCode5;
//	private String unitCode;
//	private String rateFlag;
//	private String taxQuantity;
//	private String taxQuantityNumber;
//	private String taxQuantityPerUnit;
//	private String taxValue;
//	private String priceFlag;
//	private String informPrice;
	private String declarePrice;
	private String unitPrice;
//	private String goodsNum;
//	private String goodsValue;
//	private String taxAmount;
	private String productTypeDesc;
	private String goodsDesc;
	private String degree;
	private String goodsSize;
	private BigDecimal goodsPiece;
	private BigDecimal goodsQuantity;
	private BigDecimal taxByValue;
	private BigDecimal taxByQuantity;
	private BigDecimal taxByQuantityOver;
	
	@XmlElement(name = "DeclarePrice")
	public String getDeclarePrice() {
		return declarePrice;
	}

	public void setDeclarePrice(String declarePrice) {
		this.declarePrice = declarePrice;
	}
	
	@XmlElement(name = "UnitPrice")
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
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
	public BigDecimal getGoodsPiece() {
		return goodsPiece;
	}

	public void setGoodsPiece(BigDecimal goodsPiece) {
		this.goodsPiece = goodsPiece;
	}

	@XmlElement(name = "GoodsQuantity")
	public BigDecimal getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(BigDecimal goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	@XmlElement(name = "TaxByValue")
	public BigDecimal getTaxByValue() {
		return taxByValue;
	}

	public void setTaxByValue(BigDecimal taxByValue) {
		this.taxByValue = taxByValue;
	}

	@XmlElement(name = "TaxByQuantity")
	public BigDecimal getTaxByQuantity() {
		return taxByQuantity;
	}

	public void setTaxByQuantity(BigDecimal taxByQuantity) {
		this.taxByQuantity = taxByQuantity;
	}

	@XmlElement(name = "TaxByQuantityOver")
	public BigDecimal getTaxByQuantityOver() {
		return taxByQuantityOver;
	}

	public void setTaxByQuantityOver(BigDecimal taxByQuantityOver) {
		this.taxByQuantityOver = taxByQuantityOver;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
