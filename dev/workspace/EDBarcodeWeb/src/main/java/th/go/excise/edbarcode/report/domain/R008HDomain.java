package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R008HDomain implements Serializable {
	
	private String orderNo;
	
	private List<R008DDomain> listR008DDomain;
	private R008DDomain sum;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<R008DDomain> getListR008DDomain() {
		return listR008DDomain;
	}
	public void setListR008DDomain(List<R008DDomain> listR008DDomain) {
		this.listR008DDomain = listR008DDomain;
	}
	public R008DDomain getSum() {
		return sum;
	}
	public void setSum(R008DDomain sum) {
		this.sum = sum;
	}
	
	

}
