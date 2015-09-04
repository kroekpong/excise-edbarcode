/**
 * 
 */

var module = angular.module('load.from.file', []);
var path = require('path');
var rw = require('rw');
var exec = require('child_process').exec;
var gui = require('nw.gui');

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
	this.FundKKTRateAmount = 0;

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
	
	this.MunicipalRateAmount = 0;
//	this.GoodId = Date.now();
	
	this.genId = function(){
		this.GoodId = Math.floor(Math.random()*10000000000000);
//		console.log("this.GoodId" , this.GoodId );
	}
	
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
	var GoodsList = JSON.parse(localStorage["GoodsList"]);
	var list = GoodsList.slice(0).slice(0, 10);
	allproduct =GoodsList.slice(0);
	
	this.getTopProduct = function() {
		console.info("getTopProduct");
		return list;
	};
	
	this.getAllProduct = function(){
		return allproduct; 
	}
	
	this.getSearchCriteria = function (){
		var GoodsDescriptionTextList = new Set();
		var Degree = new Set();
		for(var _i in allproduct){
			var p = allproduct[_i];
			GoodsDescriptionTextList.add(p.GoodsDescriptionText);
			Degree.add(p.Degree);
		}
		
		console.log("GoodsDescriptionTextList",GoodsDescriptionTextList.size);
		console.log("Degree",Degree.size);
		
		var set1 = [];
		GoodsDescriptionTextList.forEach(function( _value){
			set1.push({
				"display" : _value,
				"value" : _value
			});
		});
		var set2 = [];
		Degree.forEach(function( _value){
			set2.push({
				"display" : _value,
				"value" : _value
			});
		});
		
		return {
			"GoodsDescriptionTextList" : set1,
			"Degree" : set2
		};
	};

});

module.service('$profileService', function() {
	console.info("load.from.file", "$profileService");

	var profiles = JSON.parse(localStorage["profile"]);
	var LicenseNo = localStorage["LicenseNo"];
	var index = 0;
	for(var _i in profiles.factorys){
		if( profiles.factorys[_i]["LicenseNo"] == LicenseNo){
			index = _i;
			console.info("profile",LicenseNo);
			break;
		}
	}

	this.getProfile = function() {
		var profileUser = {
			"CompanyName" : profiles.CompanyName,
			"TaxpayerName" : profiles.factorys[index].TaxpayerName ,
			"TaxpayerNameDes" : profiles.factorys[index].TaxpayerName  + " [" + profiles.factorys[index].LicenseName + "]",
			"LicenseNo" : profiles.factorys[index].LicenseNo,
			"LicBook" : profiles.factorys[index].LicBook ,
			"LicenseNoAndLicBook" : profiles.factorys[index].LicBook + "/" + profiles.factorys[index].LicenseNo,
			"LicenseDate" : profiles.factorys[index].EffectiveDate + "-" + profiles.factorys[index].ExpireDate,
			"CompanyId" : profiles.CompanyId,
			"Address" : profiles.Address,
			"CompanyUserPwd" : profiles.CompanyUserPwd,
			"CusId" : profiles.CusId,
			"AddrBean" : profiles.AddrBean,
			"InternetUniqueId" : profiles.InternetUniqueId,
			"ExciseOfficeId" : profiles.ExciseOfficeId,
			"factorys" : profiles.factorys[index]
		};

//		console.log(profileUser);

		return profileUser;
	};

});

module.service('$fileUtils', function() {
	this.dirPath = process.cwd();
	this.execPath = path.dirname(process.execPath);
	console.info("$fileUtils install..", this.dirPath, " : ", this.execPath);
	var updatepath = this.execPath + "\\gen-report\\currentProfile";
	var updateFileName = updatepath + "\\currentUser.txt";
	var reportPath = this.execPath + "\\gen-report";
	this.pdfItem = [
	       		reportPath + "\\pdf\\SR120-11.pdf",
	       		reportPath + "\\pdf\\SSS1_01.pdf",
	       		reportPath + "\\pdf\\SST1_01.pdf",
	       		reportPath + "\\pdf\\KKT1_01.pdf",
	       		reportPath + "\\pdf\\ALL_EXCISE.pdf"
	       	];
	
	this.readCurrentProfile = function() {

		var contents = rw.readFileSync(updateFileName, "utf8");
		console.log("readCurrentProfile : length", contents.length);
		return contents;
	};
	
	this.runGenReport = function ( _fnCallback ){
		execute("\"" + reportPath + "\\run_report.bat\" \"" + reportPath + "\"" ,_fnCallback);
	};
	
	this.runGenReportOnline = function ( _fnCallback , _reffNo ){
		execute("\"" + reportPath + "\\run_report_online.bat\" \"" + reportPath + "\" " + _reffNo,_fnCallback);
	};
	
	this.writeFileGenReport = function (_data){
		rw.writeFileSync(reportPath + "\\genReportPdf.xml",_data,"utf8" );
	};
	
	function execute(command, callback){
		console.info("command",command);
	    exec(command, function(error, stdout, stderr){ callback(error, stdout); });
	};
	
	this.open = function ( _url ){
		console.info("open", _url);
		gui.Shell.openItem(_url);
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
				"TaxpayerId" : pxml.getByTagName("TaxpayerId",_i),
				"LicenseNo" : pxml.getByTagName("LicenseNo",_i),
				"LicBook" : pxml.getByTagName("LicBook",_i),
				"LicenseName" : pxml.getByTagName("LicenseName",_i),
				"EffectiveDate" : pxml.getByTagName("EffectiveDate",_i),
				"ExpireDate" : pxml.getByTagName("ExpireDate",_i),
//				"ExciseOfficeId" : pxml.getByTagName("ExciseOfficeId",_i),
				"EmailAddress" : pxml.getByTagName("EmailAddress",_i)
			});
		}
		
		var addrBean = {
				"HouseNumber" : pxml.getByTagName("HouseIdNumber"),
				"MooNumber" : pxml.getByTagName("MooNumber"),
				"TrokSoiName" : pxml.getByTagName("TrokSoiName"),
				"StreetName" : pxml.getByTagName("StreetName"),
				"ThambolName" : pxml.getByTagName("ThambolName"),
				"AmphurName" : pxml.getByTagName("AmphurName"),
				"ProvinceName" : pxml.getByTagName("ProvinceName"),
				"Postcode" : pxml.getByTagName("Postcode"),
				"TelNumber" : pxml.getByTagName("TelNumber")
		};
		var profile = {
			"CompanyName" : pxml.getByTagName("CompanyName"),
			"factorys" : factorys,
			"CompanyId" : pxml.getByTagName("CompanyId"),
			"Address" : getXMLtoAddr(pxml),
			"AddrBean" : addrBean,
			"CompanyUserPwd" : pxml.getByTagName("CompanyUserPwd"),
			"InternetUniqueId" : pxml.getByTagName("InternetUniqueId"),
			"ExciseOfficeId" : pxml.getByTagName("ExciseOfficeId"),
			"CusId" : pxml.getByTagName("CusId")
		};

//		 console.log(profile);
		localStorage["profile"] = JSON.stringify(profile);
//		save to memory
		localStorage["CompanyId"] = profile.CompanyId;
		localStorage["CompanyUserPwd"] = atob(profile.CompanyUserPwd);
		
		console.info("GoodsList ...");
		var GoodsListbyFactorySize = [];
		var last = 0;
		for (var _i = 0; _i < factoryLength; _i++) {
			var GoodsL = pxml.countChild("GoodsList",_i);
			GoodsListbyFactorySize.push({
				"factoryIndex" : _i,
				"LicenseNo" : factorys[_i].LicenseNo,
				"factorys" : factorys[_i],
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
			 g.setString("UnitCode", _ParserXml.getByTagName("UnitCode",_i));
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
			 g.setNumber("MunicipalRateAmount", _ParserXml.getByTagName("MunicipalRateAmount",_i));
			 g.setNumber("FundSSTRateAmount", _ParserXml.getByTagName("FundSSTRateAmount",_i));
			 g.setNumber("FundKKTRateAmount", _ParserXml.getByTagName("FundKKTRateAmount",_i));
			 g.genId();
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
