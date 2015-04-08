package th.go.excise.edbarcode.common.persistence.domain;

import java.math.BigDecimal;

public class TmpTaxDocDetail {
	
	private int tmpTaxDocDetailId;
	private int tmpTaxDocMasterId;
	private String productCode;
	private int productPiece;
	private BigDecimal sellingPriceByOwner;
	private BigDecimal sellingPriceByDepartment;
	private BigDecimal taxByValue;
	private BigDecimal taxByCapacity;
	private BigDecimal taxByValuePlus;

	public int getTmpTaxDocDetailId() {
		return tmpTaxDocDetailId;
	}

	public void setTmpTaxDocDetailId(int tmpTaxDocDetailId) {
		this.tmpTaxDocDetailId = tmpTaxDocDetailId;
	}

	public int getTmpTaxDocMasterId() {
		return tmpTaxDocMasterId;
	}

	public void setTmpTaxDocMasterId(int tmpTaxDocMasterId) {
		this.tmpTaxDocMasterId = tmpTaxDocMasterId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductPiece() {
		return productPiece;
	}

	public void setProductPiece(int productPiece) {
		this.productPiece = productPiece;
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
