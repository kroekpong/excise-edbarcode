package th.go.excise.edbarcode.report.domain;

import java.io.Serializable;
import java.util.List;

public class R010HDomain implements Serializable {
	
	private String orderNo;
	
	private List<R010DDomain> listR010DDomain;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<R010DDomain> getListR010DDomain() {
		return listR010DDomain;
	}

	public void setListR010DDomain(List<R010DDomain> listR010DDomain) {
		this.listR010DDomain = listR010DDomain;
	}

	

}
