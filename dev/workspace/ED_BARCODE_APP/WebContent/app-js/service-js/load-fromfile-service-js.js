/**
 * 
 */

var module = angular.module('load.from.file', []);
var path = require('path');
var rw = require('rw');

var Goods = function() {

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
	this.PriceFlag = "";

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
	
	this.setString = function(_prop , _value){
		if(_value == "" || _value === undefined) return;
		this[_prop] = _value.trim();
	}
	this.setNumber = function(_prop , _value){
		if(_value == "" || _value === undefined) return;
		this[_prop] = parseFloat(_value);
	}

};

module.service('$productService', function() {
	console.info("load.from.file", "$productService");

	this.getTopProduct = function() {
		console.info("getTopProduct");
		var GoodsList = [];

		var g = new Goods();

		g.GoodsDescriptionText = "สิงห์ OLD   0.330 4.9000";
		g.BrandName = "สิงห์ OLD";
		g.GoodsSize = parseFloat("0.330");
		g.Degree = parseFloat("4.9000");
		g.DeclarePrice = parseFloat("49.000");
		g.ProductTypeDescriptionText = "เบียร์";

		GoodsList.push(g);

		var g2 = new Goods();

		g2.GoodsDescriptionText = "สิงห์ NEW   0.330 4.9000";
		g2.BrandName = "สิงห์ OLD2";
		g2.GoodsSize = parseFloat("0.330");
		g2.Degree = parseFloat("4.9000");
		g2.DeclarePrice = parseFloat("49.000");
		g2.ProductTypeDescriptionText = "เบียร์";

		GoodsList.push(g2);

		return GoodsList;
	};

});

module.service('$profileService', function() {
	console.info("load.from.file", "$profileService");

	var profile = JSON.parse(localStorage["profile"]);

	this.getProfile = function() {
		var profileUser = {
			"CompanyName" : profile.CompanyName,
			"TaxpayerName" : profile.factorys[0].TaxpayerName  + " [" + profile.factorys[0].LicenseName + "]",
			"LicenseNo" : profile.factorys[0].LicenseNo,
			"LicenseDate" : profile.factorys[0].EffectiveDate + "-" + profile.factorys[0].ExpireDate,
			"CompanyId" : profile.CompanyId,
			"Address" : profile.Address,
		};

//		console.log(profileUser);

		return profileUser;
	};

});

module.service('$fileUtils', function() {
	this.dirPath = process.cwd();
	this.execPath = path.dirname(process.execPath);
	console.info("$fileUtils install..", this.dirPath, " : ", this.execPath);

	this.readCurrentProfile = function() {
		var updatepath = this.execPath + "\\gen-report\\currentProfile";
		var updateFileName = updatepath + "\\currentUser.txt";

		var contents = rw.readFileSync(updateFileName, "utf8");

		console.log("readCurrentProfile : length", contents.length);

		return contents;
	};

});

module.service('$convertDataXml', function() {
	console.info("$convertDataXml install..");

	this.convertXMLtoLocalStorage = function(_dataXml) {

		var pxml = new ParserXml(_dataXml);

		console.info("profile ...");
		var factoryLength = pxml.getSize("TaxpayerName");
		var factorys = [];
		for (var _i = 0; _i < factoryLength; _i++) {
			factorys.push({
				"TaxpayerName" : pxml.getByTagName("TaxpayerName",_i),
				"LicenseNo" : pxml.getByTagName("LicenseNo",_i),
				"LicenseName" : pxml.getByTagName("LicenseName",_i),
				"EffectiveDate" : pxml.getByTagName("EffectiveDate",_i),
				"ExpireDate" : pxml.getByTagName("ExpireDate",_i)
			});
		}

		var profile = {
			"CompanyName" : pxml.getByTagName("CompanyName"),
			"factorys" : factorys,
			"CompanyId" : pxml.getByTagName("CompanyId"),
			"Address" : getXMLtoAddr(pxml),
		};

		 console.log(profile);
		localStorage["profile"] = JSON.stringify(profile);
		
		console.info("GoodsList ...");
		var GoodsListbyFactorySize = [];
		var last = 0;
		for (var _i = 0; _i < factoryLength; _i++) {
			var GoodsL = pxml.countChild("GoodsList",_i);
			GoodsListbyFactorySize.push({
				"factoryIndex" : _i,
				"LicenseNo" : factorys[_i].LicenseNo,
				"start" : last,
				"end" : last+GoodsL
			});
			last = GoodsL;
//			console.log("GoodsList",GoodsL);
		}
		
		console.log("GoodsListbyFactorySize",GoodsListbyFactorySize);
		localStorage["GoodsListbyFactorySize"] = JSON.stringify(GoodsListbyFactorySize);
		
		console.info("load Goods by factory ...");
		var LicenseNo = localStorage["LicenseNo"];
		
		loadGoods(LicenseNo,pxml);

	};
	
	function loadGoods( _LicenseNo, _ParserXml){
		 var GoodsListbyFactorySize = JSON.parse(localStorage["GoodsListbyFactorySize"]);
		 var cGoodsList = "";
		 for(var _i in GoodsListbyFactorySize){
			 cGoodsList = GoodsListbyFactorySize[_i];
			 if(_LicenseNo == "" || _LicenseNo === undefined ||  cGoodsList["LicenseNo"] == _LicenseNo){
				 console.info("end" ,_LicenseNo ,cGoodsList["LicenseNo"] , _i);
				 localStorage["LicenseNo"] = cGoodsList["LicenseNo"] ;
				 break;
			 }
		 }
		 
		 console.log("loadGoods", cGoodsList);
		 var GoodsList = [];
		 for(var _i = cGoodsList["start"] ; _i < cGoodsList["end"] ; _i++){
				 
			 var g = new Goods();
			 g.setString("GoodsCode", _ParserXml.getByTagName("GoodsCode",_i));
			 g.setString("GoodsDescriptionText", _ParserXml.getByTagName("GoodsDescriptionText",_i));
			 g.setString("ProductTypeCode", _ParserXml.getByTagName("ProductTypeCode",_i));
			 g.setString("ProductTypeDescriptionText", _ParserXml.getByTagName("ProductTypeDescriptionText",_i));
			 g.setNumber("GoodsSize", _ParserXml.getByTagName("GoodsSize",_i));
			 g.setString("GoodsSizeUnitDescriptionText", _ParserXml.getByTagName("GoodsSizeUnitDescriptionText",_i));
			 g.setNumber("GoodsPrice", _ParserXml.getByTagName("GoodsPrice",_i));
			 g.setNumber("TaxRateByPriceAmount", _ParserXml.getByTagName("TaxRateByPriceAmount",_i));
			 g.setNumber("TaxRateByQuantityAmount", _ParserXml.getByTagName("TaxRateByQuantityAmount",_i));
			 g.setNumber("FundSSSRateAmount", _ParserXml.getByTagName("FundSSSRateAmount",_i));
			 g.setNumber("Degree", _ParserXml.getByTagName("Degree",_i));
			 g.setString("PriceFlag", _ParserXml.getByTagName("PriceFlag",_i));
			 g.setNumber("DeclarePrice", _ParserXml.getByTagName("DeclarePrice",_i));
			 g.setNumber("UnitCode", _ParserXml.getByTagName("UnitCode",_i));
			 g.setString("GoodsUnitsDescriptionText", _ParserXml.getByTagName("GoodsUnitsDescriptionText",_i));
			 g.setString("IncomeCode", _ParserXml.getByTagName("IncomeCode",_i));
			 g.setString("SeqNo", _ParserXml.getByTagName("SeqNo",_i));
			 g.setString("BrandName", _ParserXml.getByTagName("BrandName",_i));
			 g.setString("SubbrandName", _ParserXml.getByTagName("SubbrandName",_i));
			 g.setString("ModelName", _ParserXml.getByTagName("ModelName",_i));
			 g.setString("InformDate", _ParserXml.getByTagName("InformDate",_i));
			 g.setString("ProductNameEng", _ParserXml.getByTagName("ProductNameEng",_i));
			 g.setNumber("RatePerLitre", _ParserXml.getByTagName("RatePerLitre",_i));
			 g.setNumber("DegreeMin", _ParserXml.getByTagName("DegreeMin",_i));
			 g.setNumber("RateDegreeOver", _ParserXml.getByTagName("RateDegreeOver",_i));
			 g.setNumber("WholesaleMin", _ParserXml.getByTagName("WholesaleMin",_i));
			 g.setNumber("RateWholesaleOver", _ParserXml.getByTagName("RateWholesaleOver",_i));
			 g.setNumber("RatePerLitreMax", _ParserXml.getByTagName("RatePerLitreMax",_i));
			 
//			 console.log(g);
			 
			 GoodsList.push(g);
			 
		 }
		 
		 console.log("GoodsList",GoodsList.length);
		 localStorage["GoodsList"] = JSON.stringify(GoodsList);
	}

});

function getXMLtoAddr(_ParserXml) {
	var str = ""
	str += " " + _ParserXml.getByTagName("ThambolName");
	str += " " + _ParserXml.getByTagName("AmphurName");
	str += " " + _ParserXml.getByTagName("ProvinceName");
	str += " " + _ParserXml.getByTagName("Postcode");
	str += " (" + _ParserXml.getByTagName("TelNumber") + ")";

	return str;
}

function ParserXml(textXml) {
	var xmlDoc;

	this.getXML = function() {
		return xmlDoc;
	}

	if (window.DOMParser) {
		parser = new DOMParser();
		xmlDoc = parser.parseFromString(textXml, "text/xml");
	} else // code for IE
	{
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async = false;
		xmlDoc.loadXML(textXml);
	}
	console.log(xmlDoc);

	this.getByTagName = function(strName, index) {
		var str = "";
		if (xmlDoc.getElementsByTagName(strName).length > 0 && index === undefined) {
			if (xmlDoc.getElementsByTagName(strName)[0].childNodes.length == 1) {
				str = xmlDoc.getElementsByTagName(strName)[0].childNodes[0].nodeValue;
			} else {
				if (xmlDoc.getElementsByTagName(strName)[0].childNodes.length == 0) {
					console.log("empty Value " + strName);
				} else {
					console.log("it root xml tag, Node > 1 :  " + strName);
				}
			}
		} else if (xmlDoc.getElementsByTagName(strName).length > 0 && index < xmlDoc.getElementsByTagName(strName).length) {
			if (xmlDoc.getElementsByTagName(strName)[index].childNodes.length == 1) {
				str = xmlDoc.getElementsByTagName(strName)[index].childNodes[0].nodeValue;
			} else {
				if (xmlDoc.getElementsByTagName(strName)[index].childNodes.length == 0) {
					// console.log("empty Value " + strName);
				} else {
					console.log("it root xml tag, Node > 1 :  " + strName);
				}
			}
		}
		return str;
	};

	this.getVal = function(tagName, index) {
		return this.getByTagName(tagName, index);
	}

	this.getSize = function(strName) {
		var size = 0;
		size = xmlDoc.getElementsByTagName(strName).length;
		return size;
	};

	this.countChild = function(tagName, index) {
		return xmlDoc.getElementsByTagName(tagName)[index].children.length;
	};
}
