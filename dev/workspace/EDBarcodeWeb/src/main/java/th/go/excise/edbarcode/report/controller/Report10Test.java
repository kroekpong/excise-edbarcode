package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R001DDomain;
import th.go.excise.edbarcode.report.domain.R001HDomain;
import th.go.excise.edbarcode.report.domain.R010DDomain;
import th.go.excise.edbarcode.report.domain.R010HDomain;

public class Report10Test {
	
	public static R010HDomain getR010Test(){
		
		R010HDomain returnObj = new R010HDomain();
		
		R010DDomain d1= new R010DDomain();
		d1.setCompanyName("สยามไวน์ซอสซิ่ง");
		d1.setTypeName("สุราแช่");
		d1.setGoodsName("Wine Cooler Cocktail");
		d1.setDegree("5.0");
		d1.setSize("0.275");
		d1.setPric1("250");
		d1.setPrice2("0");
		d1.setPricedif("-250");
		d1.setPricedifpercent("-100.00");
		
		R010DDomain d2= new R010DDomain();
		d2.setCompanyName("เสิร์ฟ ออล");
		d2.setTypeName("สุราแช่");
		d2.setGoodsName("Wine Cooler Jus Black");
		d2.setDegree("5.0");
		d2.setSize("0.275");
		d2.setPric1("250");
		d2.setPrice2("0");
		d2.setPricedif("-250");
		d2.setPricedifpercent("-100.00");
		
		R010DDomain d3= new R010DDomain();
		d3.setCompanyName("สยามไวเนอรี่");
		d3.setTypeName("สุราแช่");
		d3.setGoodsName("Zearch Wine Cooler");
		d3.setDegree("5.0");
		d3.setSize("0.275");
		d3.setPric1("250");
		d3.setPrice2("0");
		d3.setPricedif("-250");
		d3.setPricedifpercent("-100.00");
		
		
		List<R010DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		detailList.add(d3);
		
		returnObj.setListR010DDomain(detailList);
		
		return returnObj;
		
	}

}
