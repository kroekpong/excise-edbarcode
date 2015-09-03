package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R014DDomain;
import th.go.excise.edbarcode.report.domain.R014HDomain;

public class Report14Test {
	
	public static R014HDomain getR014Test(){
		
		R014HDomain returnObj = new R014HDomain();
		
		returnObj.setCompanyName("หจก. ปิยะสตรี");
		
		
		R014DDomain d1= new R014DDomain();
		d1.setGoodsName("Fox");
		d1.setDegree("5.0");
		d1.setSize("0.625");
		d1.setQuantityBottle("1,200");
		d1.setQuantityLite("750.00");
		d1.setPric1("70.00");
		d1.setVat("11,700.00");
		
		
		
		
		List<R014DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		
		returnObj.setR014DDomainList(detailList);
		
		return returnObj;
		
	}

}
