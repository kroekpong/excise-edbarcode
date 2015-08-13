/**
 * 
 */

var module = angular.module('load.from.file',[]);

var Goods = function(){
	
	this.GoodsCode = "";
	this.GoodsDescriptionText = "";
	this.ProductTypeDescriptionText = "";

	this.GoodsSize = 0;
	this.GoodsSizeUnitDescriptionText = "";
	this.GoodsPrice = 0;
	this.TaxRateByPriceAmount = 0;
	this.TaxRateByQuantityAmount = 0;
	this.FundSSSRateAmount = 0;
	this.FundSSTRateAmount = 0;

	this.Degree = 0;
	this.PriceFlag = "P";

	this.DeclarePrice = 0;
	this.UnitCode = 0;
	this.GoodsUnitsDescriptionText = "";

	this.IncomeCode = "";
	this.SeqNo = 1;
	this.BrandName = "";
	this.SubbrandName = "";
	this.ModelName = "";
	this.InformDate = "";
	this.ProductNameEng = "";

	this.RatePerLitre = 0;
	this.DegreeMin = 0;
	this.RateDegreeOver = 0;
	
	this.WholesaleMin = 0;
	this.RateWholesaleOver = 0;
	this.RatePerLitreMax = 0;
	
};


module.service('$productService', function(){
	console.info("load.from.file","$productService");
	
	
	this.getTopProduct = function() {
		console.info("getTopProduct");
		var GoodsList = [];
		
		var g = new Goods();
		
		g.GoodsDescriptionText = "สิงห์ OLD   0.330 4.9000";
		g.BrandName = "สิงห์ OLD";
		g.GoodsSize =  parseFloat("0.330");
		g.Degree = parseFloat("4.9000");
		g.DeclarePrice = parseFloat("49.000");
		g.ProductTypeDescriptionText = "เบียร์";
		
		GoodsList.push(g);
		
		var g2 = new Goods();
		
		g2.GoodsDescriptionText = "สิงห์ NEW   0.330 4.9000";
		g2.BrandName = "สิงห์ OLD2";
		g2.GoodsSize =  parseFloat("0.330");
		g2.Degree = parseFloat("4.9000");
		g2.DeclarePrice = parseFloat("49.000");
		g2.ProductTypeDescriptionText = "เบียร์";
		
		GoodsList.push(g2);
		
		return GoodsList;
	};
	
	
	
	
});