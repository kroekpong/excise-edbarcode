package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R006HDomain implements Serializable {
	
	private String orderNo;
	
	private List<R006DDomain> listR006DDomain;
	private R006DDomain sum;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<R006DDomain> getListR006DDomain() {
		return listR006DDomain;
	}
	public void setListR006DDomain(List<R006DDomain> listR006DDomain) {
		this.listR006DDomain = listR006DDomain;
	}
	public R006DDomain getSum() {
		return sum;
	}
	public void setSum(R006DDomain sum) {
		this.sum = sum;
	}
	
	
	
	

}
