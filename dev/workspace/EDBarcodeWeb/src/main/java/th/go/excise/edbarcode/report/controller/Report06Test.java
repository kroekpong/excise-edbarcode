package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R006DDomain;
import th.go.excise.edbarcode.report.domain.R006HDomain;
import th.go.excise.edbarcode.report.domain.R008DDomain;

public class Report06Test {
	
	public static R006HDomain getR006Test(){
		
		R006HDomain returnObj = new R006HDomain();
		
		R006DDomain d1= new R006DDomain();
		d1.setCompanyName("สยามไวน์ซอสซิ่ง");
		d1.setGoodsName("Wine Cooler Cocktail");
		d1.setDegree("5.0");
		d1.setSize("0.275");
		d1.setVat("3000");
		d1.setVatOld("2800");
		d1.setVatdif("200");
		d1.setVatdifpercent("0.00");
		
		R006DDomain d2= new R006DDomain();
		d2.setCompanyName("เสิร์ฟ ออล");
		d2.setGoodsName("Wine Cooler Jus Black");
		d2.setDegree("5.0");
		d2.setSize("0.275");
		d2.setVat("4000");
		d2.setVatOld("3500");
		d2.setVatdif("0.00");
		d2.setVatdifpercent("14.29");
		
		R006DDomain d3= new R006DDomain();
		d3.setCompanyName("สยามไวเนอรี่");
		d3.setGoodsName("Zearch Wine Cooler");
		d3.setDegree("5.0");
		d3.setSize("0.275");
		d3.setVat("1200");
		d3.setVatOld("1200");
		d3.setVatdif("0");
		d3.setVatdifpercent("0.00");
		
		R006DDomain sum = new R006DDomain();
		
		
		List<R006DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		detailList.add(d3);
		
		returnObj.setListR006DDomain(detailList);
		returnObj.setSum(sum);
		return returnObj;
		
	}

}
