package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R008DDomain;
import th.go.excise.edbarcode.report.domain.R008HDomain;

public class Report08Test {
	
	public static R008HDomain getR008Test(){
		
		R008HDomain returnObj = new R008HDomain();
		
		R008DDomain d1= new R008DDomain();
		d1.setCompanyName("สยามไวน์ซอสซิ่ง");
		d1.setTypeName("สุราแช่");
		d1.setGoodsName("Wine Cooler Cocktail");
		d1.setDegree("5.0");
		d1.setSize("0.275");
		d1.setVolume("3000");
		d1.setVat("1500");
		d1.setVolumeOld("2800");
		d1.setVatOld("1400");
		d1.setVolumedif("200");
		d1.setVatdif("100");
		d1.setVolumedifpercent("7.14");
		d1.setVatdifpercent("7.14");
		
		R008DDomain d2= new R008DDomain();
		d2.setCompanyName("เสิร์ฟ ออล");
		d2.setTypeName("สุราแช่");
		d2.setGoodsName("Wine Cooler Jus Black");
		d2.setDegree("5.0");
		d2.setSize("0.275");
		d2.setVolume("4000");
		d2.setVat("2000");
		d2.setVolumeOld("3500");
		d2.setVatOld("1750");
		d2.setVolumedif("500");
		d2.setVatdif("250");
		d2.setVolumedifpercent("14.29");
		d2.setVatdifpercent("14.29");
		
		R008DDomain d3= new R008DDomain();
		d3.setCompanyName("สยามไวเนอรี่");
		d3.setTypeName("สุราแช่");
		d3.setGoodsName("Zearch Wine Cooler");
		d3.setDegree("5.0");
		d3.setSize("0.275");
		d3.setVolume("1200");
		d3.setVat("1110");
		d3.setVolumeOld("1200");
		d3.setVatOld("1110");
		d3.setVolumedif("0");
		d3.setVatdif("0");
		d3.setVolumedifpercent("0.00");
		d3.setVatdifpercent("0.00");
		
		R008DDomain sum = new R008DDomain();
		
		
		List<R008DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		
		returnObj.setListR008DDomain(detailList);
		returnObj.setSum(sum);
		return returnObj;
		
	}

}
