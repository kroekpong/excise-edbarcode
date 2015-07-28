package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R001DDomain;
import th.go.excise.edbarcode.report.domain.R001HDomain;

public class ReportTest {
	
	public static R001HDomain getR001Test(){
		
		R001HDomain returnObj = new R001HDomain();
		
		returnObj.setCompanyName(" บริษํท บางกอกพัฒนาไวน์เนอรี่ จำกัด");
		
		
		R001DDomain d1= new R001DDomain();
		d1.setGoodsName("Wine Cooler Cocktail");
		d1.setDegree("5.0");
		d1.setSize("0.275");
		d1.setPric1("140.00");
		d1.setPrice2("");
		d1.setQuantityBottle("1,000");
		d1.setQuantityLite("275.000");
		d1.setVat("4675.00");
		
		
		R001DDomain d2= new R001DDomain();
		d2.setGoodsName("Wine Cooler Jus Black");
		d2.setDegree("5.0");
		d2.setSize("0.275");
		d2.setPric1("140.00");
		d2.setPrice2("");
		d2.setQuantityBottle("1,500");
		d2.setQuantityLite("412.500");
		d2.setVat("7,012.50");
		
		
		
		List<R001DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		
		returnObj.setR001DDomainList(detailList);
		
		return returnObj;
		
	}

}
