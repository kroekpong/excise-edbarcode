package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R001HDomain implements Serializable {
	
	private String orderNo;
	private String companyName;
	
	private List<R001DDomain> r001DDomainList;

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

	public List<R001DDomain> getR001DDomainList() {
		return r001DDomainList;
	}

	public void setR001DDomainList(List<R001DDomain> r001dDomainList) {
		r001DDomainList = r001dDomainList;
	}
	
	

}
