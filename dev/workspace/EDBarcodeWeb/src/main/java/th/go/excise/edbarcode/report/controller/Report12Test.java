package th.go.excise.edbarcode.report.controller;

import java.util.ArrayList;
import java.util.List;

import th.go.excise.edbarcode.report.domain.R001DDomain;
import th.go.excise.edbarcode.report.domain.R001HDomain;
import th.go.excise.edbarcode.report.domain.R012DDomain;
import th.go.excise.edbarcode.report.domain.R012HDomain;

public class Report12Test {
	
	public static R012HDomain getR012Test(){
		
		R012HDomain returnObj = new R012HDomain();
		
		R012DDomain d1 = new R012DDomain();
		d1.setTypeName("สุราแช่");
		d1.setCompanyName("หจก.ปิยะสตรี");
		d1.setGoodsName("สรุาแช่\"Fox\"");
		d1.setSize("750.000");
		d1.setVat("11,700.00");
		d1.setSizeold("-");
		d1.setVatold("-");
		d1.setSizedif("750.00");
		d1.setSizedifpercent("100.00");
		d1.setVatdif("11,700.00");
		d1.setVatdifpercent("100.00");
		
		R012DDomain d2 = new R012DDomain();
		d2.setTypeName("สุรากลั่น");
		d2.setCompanyName("หจก. วินัยการค้า");
		d2.setGoodsName("สรุากลั่น\"สามรวง\"");
		d2.setSize("187.500");
		d2.setVat("10,340.64");
		d2.setSizeold("-");
		d2.setVatold("-");
		d2.setSizedif("187.500");
		d2.setSizedifpercent("100.00");
		d2.setVatdif("10,340.64");
		d2.setVatdifpercent("100.00");
		
		R012DDomain d3 = new R012DDomain();
		d3.setTypeName("สุรากลั่น");
		d3.setCompanyName("หจก. ไพโรจน์การค้า");
		d3.setGoodsName("สรุากลั่น\"สามขวด\"");
		d3.setSize("187.500");
		d3.setVat("10,340.64");
		d3.setSizeold("-");
		d3.setVatold("-");
		d3.setSizedif("187.500");
		d3.setSizedifpercent("100.00");
		d3.setVatdif("10,340.64");
		d3.setVatdifpercent("100.00");
		
		R012DDomain d4 = new R012DDomain();
		d4.setTypeName("สุรากลั่น");
		d4.setCompanyName("หจก.ศรีภาณุ");
		d4.setGoodsName("สุรากลั่น\"หัวอินทรีย์\"");
		d4.setSize("250.00");
		d4.setVat("13,787.52");
		d4.setSizeold("-");
		d4.setVatold("-");
		d4.setSizedif("250.000");
		d4.setSizedifpercent("100.00");
		d4.setVatdif("13,787.52");
		d4.setVatdifpercent("100.00");
		
		R012DDomain d5 = new R012DDomain();
		d5.setTypeName("สุรากลั่น");
		d5.setCompanyName("หจก.เดชาพลการค้า");
		d5.setGoodsName("สุรากลั่น\"กระรอกเผือก\"");
		d5.setSize("125.000");
		d5.setVat("6,893.76");
		d5.setSizeold("-");
		d5.setVatold("-");
		d5.setSizedif("125.000");
		d5.setSizedifpercent("100.00");
		d5.setVatdif("6,893.76");
		d5.setVatdifpercent("100.00");
		
		R012DDomain sum = new R012DDomain();
		sum.setSize("1,500.00");
		sum.setVat("53,062.560");
		sum.setSizeold("-");
		sum.setVatold("-");
		sum.setSizedif("1,500.00");
		sum.setSizedifpercent("100.00");
		sum.setVatdif("53,062.560");
		sum.setVatdifpercent("100.00");
		
		List<R012DDomain> detailList = new ArrayList();
		
		detailList.add(d1);
		detailList.add(d2);
		detailList.add(d3);
		detailList.add(d4);
		detailList.add(d5);
		
		returnObj.setListR012DDomain(detailList);
		returnObj.setSum(sum);
		
		return returnObj;
		
	}

}
