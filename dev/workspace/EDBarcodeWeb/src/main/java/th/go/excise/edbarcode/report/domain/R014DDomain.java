package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;

public class R014DDomain implements Serializable{

	
	private String goodsName;
	private String degree;
	private String size;
	private String quantityBottle;
	private String quantityLite;
	private String pric1;
	private String vat;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPric1() {
		return pric1;
	}
	public void setPric1(String pric1) {
		this.pric1 = pric1;
	}
	public String getQuantityBottle() {
		return quantityBottle;
	}
	public void setQuantityBottle(String quantityBottle) {
		this.quantityBottle = quantityBottle;
	}
	public String getQuantityLite() {
		return quantityLite;
	}
	public void setQuantityLite(String quantityLite) {
		this.quantityLite = quantityLite;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	
	
	
}
