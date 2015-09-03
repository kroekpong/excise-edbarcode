package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;

public class R012DDomain implements Serializable{

	private String typeName;
	private String companyName;
	private String goodsName;
	
	private String size;
	private String vat;
	
	private String sizeold;
	private String vatold;
	
	private String sizedif;
	private String sizedifpercent;
	private String vatdif;
	private String vatdifpercent;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getSizeold() {
		return sizeold;
	}
	public void setSizeold(String sizeold) {
		this.sizeold = sizeold;
	}
	public String getVatold() {
		return vatold;
	}
	public void setVatold(String vatold) {
		this.vatold = vatold;
	}
	public String getSizedif() {
		return sizedif;
	}
	public void setSizedif(String sizedif) {
		this.sizedif = sizedif;
	}
	public String getSizedifpercent() {
		return sizedifpercent;
	}
	public void setSizedifpercent(String sizedifpercent) {
		this.sizedifpercent = sizedifpercent;
	}
	public String getVatdif() {
		return vatdif;
	}
	public void setVatdif(String vatdif) {
		this.vatdif = vatdif;
	}
	public String getVatdifpercent() {
		return vatdifpercent;
	}
	public void setVatdifpercent(String vatdifpercent) {
		this.vatdifpercent = vatdifpercent;
	}
	

	
}
