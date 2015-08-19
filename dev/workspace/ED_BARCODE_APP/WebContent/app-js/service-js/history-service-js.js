/**
 *  history service
 */
var module = angular.module('history.service', []);
var rw = require("rw");
var path = require('path');
var gui = require('nw.gui');

var HistoryUser = function (){
	this.userHistoryId = "";
	this.historyIdList = [];
};

var HistoryList = function (){
	this.taxpayerName = "";
	this.licenseNo = "";
	this.submitDate = "";
	this.fileName = "";
	this.pdfName = "";
	this.submitType = "";
};

module.service('$historyService', function() {
	this.dirPath = process.cwd();
	this.execPath = path.dirname(process.execPath);
	console.info("$historyService install..", this.dirPath, " : ", this.execPath);
	var hisPath = this.execPath + "\\gen-report\\history";
	var pdfBkk = this.execPath + "\\gen-report\\pdf\\ALL_EXCISE.pdf";
	var hisFileName = hisPath + "\\history.json";
	var currentReadFileContents;
	var jsonHistoryList = [];
	
	this.findHistoryByHisId = function (_HisId , _reload ){
		try {

			currentReadFileContents = rw.readFileSync(hisFileName, "utf8");
			console.log("readCurrentProfile : length", currentReadFileContents.length);
			
			if(jsonHistoryList == undefined || _reload === undefined || _reload === true ){
				jsonHistoryList = JSON.parse(currentReadFileContents);
			}
			
			return findByHisId(_HisId);
		} catch (e) {
			console.error(e.toString());
			return null;
		}
	};
	
	this.save = function(_Profile,_GridList,_submitType) {
		console.info("save",_Profile.CompanyUserPwd);
		var his = this.findHistoryByHisId(_Profile.CompanyUserPwd);

		if(his == null){
			his = new HistoryUser();
			his.userHistoryId = _Profile.CompanyUserPwd;
			jsonHistoryList.push(his);
			console.log("new HistoryUser");
		}
		var idGen = Date.parse(new Date()).toString();
		
		var hisItem = new HistoryList();
		hisItem.taxpayerName = _Profile.TaxpayerName;
		hisItem.licenseNo = _Profile.LicenseNo;
		hisItem.submitDate = Date.parse(new Date());
		hisItem.fileName = idGen.concat(".his");
		hisItem.pdfName = idGen.concat(".pdf");
		hisItem.submitType = _submitType;
		
		his.historyIdList.push(hisItem);
//		console.log(his);
		
		rw.writeFileSync(hisPath + "\\" +hisItem.fileName,JSON.stringify(_GridList), "utf8");
		rw.writeFileSync(hisFileName,JSON.stringify(jsonHistoryList), "utf8");
		//pdf files
		
		rw.writeFileSync(hisPath + "\\" +hisItem.pdfName ,rw.readFileSync(pdfBkk));
		console.info("save ... finish");
	};
	
	function findByHisId(_HisId){
		for(var _i in jsonHistoryList){
			var h = jsonHistoryList[_i];
			if(h.userHistoryId == _HisId ){
				return h;
				break;
			}
		}
		return null;
	}
	
	this.loadLocal = function (_Profile){
		var his = this.findHistoryByHisId(_Profile.CompanyUserPwd);
		if(his == null) return [];
		return his.historyIdList.slice(0).reverse();
	};
	
	this.openHisPdfFile = function(_pdfName) {
		console.info(_pdfName);
		gui.Shell.openItem( hisPath + "\\" + _pdfName );
	};
	
	
	this.openHistoryText = function (_fileName){
		console.info("openHistoryText",_fileName);
		var text = rw.readFileSync(hisPath + "\\" + _fileName , "utf8");
		localStorage["historyItems"]  = text;
		localStorage["historyMod"] = "ON"
	};
	
	
	
	
	
	
	
	
});
