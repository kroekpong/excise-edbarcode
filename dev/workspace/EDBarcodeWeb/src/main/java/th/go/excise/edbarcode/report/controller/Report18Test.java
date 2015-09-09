package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import th.go.excise.edbarcode.report.domain.R018DDomain;
import th.go.excise.edbarcode.report.domain.R018HDomain;

public class Report18Test {
	
	public static R018HDomain getR018Test(){
		
		R018HDomain returnObj = new R018HDomain();
		
		returnObj.setMonth("ต.ค. 2557");
		
		
		R018DDomain d1= new R018DDomain();
		d1.setVolumespec("0.333");
		d1.setVolume("300");
		d1.setTax("150");
		d1.setVolumeold("280");
		d1.setTaxold("140");
		d1.setVolumedif("20");
		d1.setTaxdif("10");
		d1.setVolumedifpercent("7.14");
		d1.setTaxdifpercent("7.14");
		
		R018DDomain d2= new R018DDomain();
		d2.setVolumespec("0.625");
		d2.setVolume("400");
		d2.setTax("200");
		d2.setVolumeold("350");
		d2.setTaxold("175");
		d2.setVolumedif("50");
		d2.setTaxdif("25");
		d2.setVolumedifpercent("14.29");
		d2.setTaxdifpercent("14.29");
		
		R018DDomain d21= new R018DDomain();
		d21.setVolumespec("0.333");
		d21.setVolume("200");
		d21.setTax("110");
		d21.setVolumeold("200");
		d21.setTaxold("110");
		d21.setVolumedif("0");
		d21.setTaxdif("0");
		d21.setVolumedifpercent("0.00");
		d21.setTaxdifpercent("0.00");
		
		R018DDomain d22= new R018DDomain();
		d22.setVolumespec("0.625");
		d22.setVolume("250");
		d22.setTax("125");
		d22.setVolumeold("250");
		d22.setTaxold("125");
		d22.setVolumedif("0");
		d22.setTaxdif("0");
		d22.setVolumedifpercent("0.00");
		d22.setTaxdifpercent("0.00");
		
		R018DDomain d31= new R018DDomain();
		d31.setVolumespec("0.333");
		d31.setVolume("500");
		d31.setTax("250");
		d31.setVolumeold("520");
		d31.setTaxold("260");
		d31.setVolumedif("-20");
		d31.setTaxdif("-10");
		d31.setVolumedifpercent("-3.85");
		d31.setTaxdifpercent("-3.85");
		
		R018DDomain d32= new R018DDomain();
		d32.setVolumespec("0.625");
		d32.setVolume("350");
		d32.setTax("175");
		d32.setVolumeold("360");
		d32.setTaxold("190");
		d32.setVolumedif("-10");
		d32.setTaxdif("-15");
		d32.setVolumedifpercent("-2.78");
		d32.setTaxdifpercent("-7.89");
		
		R018DDomain d41= new R018DDomain();
		d41.setVolumespec("0.333");
		d41.setVolume("600");
		d41.setTax("300");
		d41.setVolumeold("550");
		d41.setTaxold("275");
		d41.setVolumedif("50");
		d41.setTaxdif("25");
		d41.setVolumedifpercent("9.09");
		d41.setTaxdifpercent("9.09");
		
		R018DDomain d42= new R018DDomain();
		d42.setVolumespec("0.625");
		d42.setVolume("340");
		d42.setTax("170");
		d42.setVolumeold("340");
		d42.setTaxold("170");
		d42.setVolumedif("0");
		d42.setTaxdif("0");
		d42.setVolumedifpercent("0.00");
		d42.setTaxdifpercent("0.00");
		
		
		List<R018DDomain> detailList1=  new ArrayList();
		List<R018DDomain> detailList2 = new ArrayList();
		List<R018DDomain> detailList3 = new ArrayList();
		List<R018DDomain> detailList4 = new ArrayList();
		
		detailList1.add(d1);
		detailList1.add(d2);
		
		detailList2.add(d21);
		detailList2.add(d22);
		
		detailList3.add(d31);
		detailList3.add(d32);
		
		detailList4.add(d41);
		detailList4.add(d42);
		
		Map<String,List<R018DDomain>> degee = new HashMap();
		
		degee.put("28", detailList1);
		degee.put("30", detailList2);
		degee.put("35", detailList3);
		degee.put("40", detailList4);
		
		R018DDomain sum = new R018DDomain();

		sum.setVolume("2940");
		sum.setTax("1480");
		sum.setVolumeold("2850");
		sum.setTaxold("1445");
		sum.setVolumedif("90");
		sum.setTaxdif("35");
		sum.setVolumedifpercent("23.90");
		sum.setTaxdifpercent("18.78");
		
		returnObj.setDegee(degee);
		returnObj.setSum(sum);
		
		return returnObj;
		
	}
}
