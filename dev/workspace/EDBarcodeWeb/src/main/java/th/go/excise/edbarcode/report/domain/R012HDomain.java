package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R012HDomain implements Serializable {
	
	private String orderNo;
	
	private List<R012DDomain> listR012DDomain;
	private R012DDomain sum;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<R012DDomain> getListR012DDomain() {
		return listR012DDomain;
	}

	public void setListR012DDomain(List<R012DDomain> listR012DDomain) {
		this.listR012DDomain = listR012DDomain;
	}

	public R012DDomain getSum() {
		return sum;
	}

	public void setSum(R012DDomain sum) {
		this.sum = sum;
	}

	
		

}
