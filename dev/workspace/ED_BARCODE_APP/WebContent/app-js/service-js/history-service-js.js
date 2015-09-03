/**
 * history service
 */
var module = angular.module('history.service', []);
var rw = require("rw");
var path = require('path');
var gui = require('nw.gui');

var HistoryUser = function() {
	this.userHistoryId = "";
	this.historyIdList = [];
};

var HistoryList = function() {
	this.taxpayerName = "";
	this.licenseNo = "";
	this.submitDate = "";
	this.fileName = "";
	this.pdfName = "";
	this.submitType = "";
	this.inputSum8 = "";
	this.sumTax8 = "";
	this.inputSum9 = "";
	this.sumTax9 = "";
	this.FundSSSRateAmountRate = 0;
	this.FundSSTRateAmountRate = 0;
	this.FundKKTRateAmountRate = 0;
	this.MunicipalRateAmountRate = 0;
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

	this.findHistoryByHisId = function(_HisId, _reload) {
		try {

			currentReadFileContents = rw.readFileSync(hisFileName, "utf8");
			console.log("readCurrentProfile : length", currentReadFileContents.length);

			if (jsonHistoryList == undefined || _reload === undefined || _reload === true) {
				jsonHistoryList = JSON.parse(currentReadFileContents);
			}

			return findByHisId(_HisId);
		} catch (e) {
			console.error(e.toString());
			return null;
		}
	};

	this.save = function(_Profile, _GridList, _submitType, _scope) {
		console.info("save", _Profile.CompanyUserPwd);
		var his = this.findHistoryByHisId(_Profile.CompanyUserPwd);

		if (his == null) {
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
		hisItem.inputSum8 = _scope.inputSum8;
		hisItem.sumTax8 = _scope.sumTax8;
		hisItem.inputSum9 = _scope.inputSum9;
		hisItem.sumTax9 = _scope.sumTax9;

		hisItem.FundKKTRateAmountRate = _scope.FundKKTRateAmountRate;
		hisItem.FundSSTRateAmountRate = _scope.FundSSTRateAmountRate;
		hisItem.FundSSSRateAmountRate = _scope.FundSSSRateAmountRate;
		hisItem.MunicipalRateAmountRate = _scope.MunicipalRateAmountRate;

		his.historyIdList.push(hisItem);
		// console.log(his);
		// console.log(_GridList);
		rw.writeFileSync(hisPath + "\\" + hisItem.fileName, JSON.stringify(_GridList), "utf8");
		rw.writeFileSync(hisFileName, JSON.stringify(jsonHistoryList), "utf8");
		// pdf files

		rw.writeFileSync(hisPath + "\\" + hisItem.pdfName, rw.readFileSync(pdfBkk));
		console.info("save ... finish");
	};

	function findByHisId(_HisId) {
		for ( var _i in jsonHistoryList) {
			var h = jsonHistoryList[_i];
			if (h.userHistoryId == _HisId) {
				return h;
				break;
			}
		}
		return null;
	}

	this.loadLocal = function(_Profile) {
		var his = this.findHistoryByHisId(_Profile.CompanyUserPwd);
		if (his == null)
			return [];
		return his.historyIdList.slice(0).reverse();
	};

	this.openHisPdfFile = function(_pdfName) {
		console.info(_pdfName);
		gui.Shell.openItem(hisPath + "\\" + _pdfName);
	};

	this.openHistoryText = function(_fileName, _HistoryList) {
		console.info("openHistoryText", _fileName);
		var text = rw.readFileSync(hisPath + "\\" + _fileName, "utf8");
		localStorage["historyItems"] = text;
		localStorage["HistoryList"] = JSON.stringify(_HistoryList);
		localStorage["historyMod"] = "ON"
	};

	/**
	 * hisDraft save Draft
	 */

	var pathDraft = this.execPath + "\\gen-report\\draft";
	var Draft = function() {
		this.id = "";
		this.userId = "";
		this.draftName = "";
		this.taxpayerName = "";
		this.licenseNo = "";
		this.submitDate = Date.parse(new Date());
		this.dataGrid = "";
		this.FundSSSRateAmountRate = 0;
		this.FundSSTRateAmountRate = 0;
		this.FundKKTRateAmountRate = 0;
		this.MunicipalRateAmountRate = 0;
	};

	this.loadDraftFromFile = function() {
		var draftList = [];
		try {
			var text = rw.readFileSync(pathDraft + "\\draft.json", "utf8");
			draftList = JSON.parse(text);
		} catch (e) {
			console.error("Fild not found", e.toString());
			draftList = [];
		}
		return draftList;
	};

	this.removeDraftById = function(_ids) {
		if (_ids.length == 0) {
			console.info("_ids .length == 0 ");
			return -1;
		}

		console.info("removeDraftById");
		var drafts = this.loadDraftFromFile();

		for ( var _i in _ids) {
			var pid = _ids[_i];
			for ( var _j in drafts) {
				var d = drafts[_j];
				if (d.id == pid) {
					drafts.splice(_j, 1);
					break;
				}
			}
		}

		console.log("drafts", drafts.length);
		rw.writeFileSync(pathDraft + "\\draft.json", JSON.stringify(drafts), "utf8");
		
		return 0;
	};

	this.loadDraftByCurrentUser = function() {
		var currentId = btoa(localStorage["CompanyUserPwd"]);
		var drafts = this.loadDraftFromFile();
		var myDrafts = [];
		for ( var _index in drafts) {
			var d = drafts[_index];
			if (d.userId === currentId) {
				myDrafts.push(d);
			}
		}

		return myDrafts;
	};

	this.openDraftMode = function(_Draft) {
		localStorage["DraftMode"] = "ON";
		localStorage["DraftData"] = JSON.stringify(_Draft);
		console.info("DraftMode", localStorage["DraftMode"]);

	};

	this.savDraft = function(_profile, _gridItem, _scope) {

		if (_gridItem.length == 0) {
			console.info("savDraft", _profile.CompanyUserPwd, "Grid no data");
			return;
		}

		console.info("savDraft", _profile.CompanyUserPwd);
		var drafts = this.loadDraftFromFile();

		var newDraft = new Draft();
		newDraft.id = Math.floor(Math.random() * 10000000000000);
		newDraft.userId = _profile.CompanyUserPwd;
		newDraft.taxpayerName = _profile.TaxpayerName;
		newDraft.licenseNo = _profile.LicenseNo;

		newDraft.dataGrid = JSON.stringify(_gridItem);

		newDraft.FundSSSRateAmountRate = _scope.FundSSSRateAmountRate;
		newDraft.FundSSTRateAmountRate = _scope.FundSSTRateAmountRate;
		newDraft.FundKKTRateAmountRate = _scope.FundKKTRateAmountRate;
		newDraft.MunicipalRateAmountRate = _scope.MunicipalRateAmountRate;

		drafts.push(newDraft);
		rw.writeFileSync(pathDraft + "\\draft.json", JSON.stringify(drafts), "utf8");
		console.info("savDraft ... finish");
	}

});
