package th.go.excise.edbarcode.demo.bean;

import java.util.List;

public class ExciseDataFromRefCode {
	
	private EntrepreneurDemo entrepreneur;
	private List<ProductDemo> procudeList;
	private ExciseTaxSummaryDemo summary;

	public EntrepreneurDemo getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(EntrepreneurDemo entrepreneur) {
		this.entrepreneur = entrepreneur;
	}

	public List<ProductDemo> getProcudeList() {
		return procudeList;
	}

	public void setProcudeList(List<ProductDemo> procudeList) {
		this.procudeList = procudeList;
	}

	public ExciseTaxSummaryDemo getSummary() {
		return summary;
	}

	public void setSummary(ExciseTaxSummaryDemo summary) {
		this.summary = summary;
	}
	
}
