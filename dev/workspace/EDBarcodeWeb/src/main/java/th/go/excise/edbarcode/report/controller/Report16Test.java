package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R001DDomain;
import th.go.excise.edbarcode.report.domain.R001HDomain;
import th.go.excise.edbarcode.report.domain.R016DDomain;
import th.go.excise.edbarcode.report.domain.R016HDomain;

public class Report16Test {
	
	public static R016HDomain getR016Test(){
		
		R016HDomain returnObj = new R016HDomain();
		
		returnObj.setCompanyName(" บริษํท บางกอกพัฒนาไวน์เนอรี่ จำกัด");
		
		
		R016DDomain d1= new R016DDomain();
		d1.setGoodsName("Wine Cooler Cocktail");
		d1.setDegree("5.0");
		d1.setSize("0.275");
		d1.setPric1("140.00");
		d1.setQuantityBottle("1,000");
		d1.setQuantityLite("275.000");
		d1.setVat("4,675.00");
		
		
		R016DDomain d2= new R016DDomain();
		d2.setGoodsName("Wine Cooler Jus Black");
		d2.setDegree("5.0");
		d2.setSize("0.275");
		d2.setPric1("140.00");
		d2.setQuantityBottle("15,000");
		d2.setQuantityLite("412.500");
		d2.setVat("7,012.50");
		
		R016DDomain d3= new R016DDomain();
		d3.setGoodsName("Wine Cooler Jus Black");
		d3.setDegree("5.0");
		d3.setSize("0.750");
		d3.setPric1(" ");
		d3.setQuantityBottle(" ");
		d3.setQuantityLite(" ");
		d3.setVat(" ");
		
		R016DDomain d4= new R016DDomain();
		d4.setGoodsName("Wine Cooler Jus Red");
		d4.setDegree("5.0");
		d4.setSize("0.275");
		d4.setPric1("140.00");
		d4.setQuantityBottle("15,000");
		d4.setQuantityLite("412.500");
		d4.setVat("7,012.50");
		
		
		List<R016DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		detailList.add(d3);
		detailList.add(d4);
		
		R016DDomain sum = new R016DDomain();
		sum.setQuantityBottle("31,000");
		sum.setQuantityLite("1,100");
		sum.setVat("18,700.00");
		
		returnObj.setSum(sum);
		returnObj.setListR016DDomain(detailList);
		
		return returnObj;
		
	}

}
