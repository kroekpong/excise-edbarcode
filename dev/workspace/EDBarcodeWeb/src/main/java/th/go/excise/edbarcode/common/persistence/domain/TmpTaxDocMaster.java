package th.go.excise.edbarcode.common.persistence.domain;

import java.math.BigDecimal;

public class TmpTaxDocMaster {
	
	private int tmpTaxDocMasterId;
	private String referenceCode;
	private String licenseNo;
	private BigDecimal sumTaxByValue;
	private BigDecimal sumTaxByCapacity;
	private String receipt;
	private BigDecimal reduceTaxProductBaht;
	private BigDecimal reduceTaxByDepBookNoBaht;
	private BigDecimal taxByMOI;
	private BigDecimal taxByThaiHealth;
	private BigDecimal taxByThaiPBS;
	private BigDecimal taxByNSDF;
	
	public int getTmpTaxDocMasterId() {
		return tmpTaxDocMasterId;
	}
	
	public void setTmpTaxDocMasterId(int tmpTaxDocMasterId) {
		this.tmpTaxDocMasterId = tmpTaxDocMasterId;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public BigDecimal getSumTaxByValue() {
		return sumTaxByValue;
	}

	public void setSumTaxByValue(BigDecimal sumTaxByValue) {
		this.sumTaxByValue = sumTaxByValue;
	}

	public BigDecimal getSumTaxByCapacity() {
		return sumTaxByCapacity;
	}

	public void setSumTaxByCapacity(BigDecimal sumTaxByCapacity) {
		this.sumTaxByCapacity = sumTaxByCapacity;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public BigDecimal getReduceTaxProductBaht() {
		return reduceTaxProductBaht;
	}

	public void setReduceTaxProductBaht(BigDecimal reduceTaxProductBaht) {
		this.reduceTaxProductBaht = reduceTaxProductBaht;
	}

	public BigDecimal getReduceTaxByDepBookNoBaht() {
		return reduceTaxByDepBookNoBaht;
	}

	public void setReduceTaxByDepBookNoBaht(BigDecimal reduceTaxByDepBookNoBaht) {
		this.reduceTaxByDepBookNoBaht = reduceTaxByDepBookNoBaht;
	}

	public BigDecimal getTaxByMOI() {
		return taxByMOI;
	}

	public void setTaxByMOI(BigDecimal taxByMOI) {
		this.taxByMOI = taxByMOI;
	}

	public BigDecimal getTaxByThaiHealth() {
		return taxByThaiHealth;
	}

	public void setTaxByThaiHealth(BigDecimal taxByThaiHealth) {
		this.taxByThaiHealth = taxByThaiHealth;
	}

	public BigDecimal getTaxByThaiPBS() {
		return taxByThaiPBS;
	}

	public void setTaxByThaiPBS(BigDecimal taxByThaiPBS) {
		this.taxByThaiPBS = taxByThaiPBS;
	}

	public BigDecimal getTaxByNSDF() {
		return taxByNSDF;
	}

	public void setTaxByNSDF(BigDecimal taxByNSDF) {
		this.taxByNSDF = taxByNSDF;
	}
	
}
