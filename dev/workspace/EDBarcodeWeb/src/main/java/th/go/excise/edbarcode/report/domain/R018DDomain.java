package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;

public class R018DDomain implements Serializable{

	
	private String volumespec;
	private String volume;
	private String tax;
	private String volumeold;
	private String taxold;
	private String volumedif;
	private String taxdif;
	private String volumedifpercent;
	private String taxdifpercent;
	public String getVolumespec() {
		return volumespec;
	}
	public void setVolumespec(String volumespec) {
		this.volumespec = volumespec;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getVolumeold() {
		return volumeold;
	}
	public void setVolumeold(String volumeold) {
		this.volumeold = volumeold;
	}
	public String getTaxold() {
		return taxold;
	}
	public void setTaxold(String taxold) {
		this.taxold = taxold;
	}
	public String getVolumedif() {
		return volumedif;
	}
	public void setVolumedif(String volumedif) {
		this.volumedif = volumedif;
	}
	public String getTaxdif() {
		return taxdif;
	}
	public void setTaxdif(String taxdif) {
		this.taxdif = taxdif;
	}
	public String getVolumedifpercent() {
		return volumedifpercent;
	}
	public void setVolumedifpercent(String volumedifpercent) {
		this.volumedifpercent = volumedifpercent;
	}
	public String getTaxdifpercent() {
		return taxdifpercent;
	}
	public void setTaxdifpercent(String taxdifpercent) {
		this.taxdifpercent = taxdifpercent;
	}
	
	
}
