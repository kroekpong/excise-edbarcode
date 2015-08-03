package th.go.excise.edbarcode.demo.bean;

import java.math.BigDecimal;

public class ProductDemo {
	
	private String productGroup;
	private String productName;
	private BigDecimal degree;
	private BigDecimal size;
	
	private String productCode;
	private BigDecimal piece;
	private BigDecimal sellingPriceByOwner;
	private BigDecimal sellingPriceByDepartment;
	private BigDecimal taxByValue;
	private BigDecimal taxByCapacity;
	private BigDecimal taxByValuePlus;


	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getDegree() {
		return degree;
	}

	public void setDegree(BigDecimal degree) {
		this.degree = degree;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPiece() {
		return piece;
	}

	public void setPiece(BigDecimal piece) {
		this.piece = piece;
	}

	public BigDecimal getSellingPriceByOwner() {
		return sellingPriceByOwner;
	}

	public void setSellingPriceByOwner(BigDecimal sellingPriceByOwner) {
		this.sellingPriceByOwner = sellingPriceByOwner;
	}

	public BigDecimal getSellingPriceByDepartment() {
		return sellingPriceByDepartment;
	}

	public void setSellingPriceByDepartment(BigDecimal sellingPriceByDepartment) {
		this.sellingPriceByDepartment = sellingPriceByDepartment;
	}

	public BigDecimal getTaxByValue() {
		return taxByValue;
	}

	public void setTaxByValue(BigDecimal taxByValue) {
		this.taxByValue = taxByValue;
	}

	public BigDecimal getTaxByCapacity() {
		return taxByCapacity;
	}

	public void setTaxByCapacity(BigDecimal taxByCapacity) {
		this.taxByCapacity = taxByCapacity;
	}

	public BigDecimal getTaxByValuePlus() {
		return taxByValuePlus;
	}

	public void setTaxByValuePlus(BigDecimal taxByValuePlus) {
		this.taxByValuePlus = taxByValuePlus;
	}
	
}
