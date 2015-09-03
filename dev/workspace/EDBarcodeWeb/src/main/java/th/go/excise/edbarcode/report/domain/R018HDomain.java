package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class R018HDomain implements Serializable {
	
	private String month;
	private Map<String,List<R018DDomain>> degee;
	private R018DDomain sum;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Map<String, List<R018DDomain>> getDegee() {
		return degee;
	}
	public void setDegee(Map<String, List<R018DDomain>> degee) {
		this.degee = degee;
	}
	public R018DDomain getSum() {
		return sum;
	}
	public void setSum(R018DDomain sum) {
		this.sum = sum;
	}
	
	
}
