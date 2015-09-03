package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R014HDomain implements Serializable {
	
	private String orderNo;
	private String companyName;
	
	private List<R014DDomain> r014DDomainList;

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

	public List<R014DDomain> getR014DDomainList() {
		return r014DDomainList;
	}

	public void setR014DDomainList(List<R014DDomain> r014dDomainList) {
		r014DDomainList = r014dDomainList;
	}

	
	

}
