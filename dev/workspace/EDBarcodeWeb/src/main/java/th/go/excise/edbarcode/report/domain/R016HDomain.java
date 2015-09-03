package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R016HDomain implements Serializable {
	
	private String orderNo;
	private String companyName;
	private R016DDomain sum;
	private List<R016DDomain> listR016DDomain;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public R016DDomain getSum() {
		return sum;
	}
	public void setSum(R016DDomain sum) {
		this.sum = sum;
	}
	public List<R016DDomain> getListR016DDomain() {
		return listR016DDomain;
	}
	public void setListR016DDomain(List<R016DDomain> listR016DDomain) {
		this.listR016DDomain = listR016DDomain;
	}

	
		

}
