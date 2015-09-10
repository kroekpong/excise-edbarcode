/**
 * 
 */
var module = angular.module('order.view', [ 'load.from.file', 'history.service' ]);

var GridItem = function() {
	this.Goods = {};
	this.checkbox = false;
	this.GoodsCount = 1;
	this.PriceAmountTax = 0;
	this.QuantityAmountTax = 0;
	this.DegreeOverTax = 0;

};

module.controller('order.view.controller', function($scope, $rootScope, $location, $productService, $mdDialog, $fileUtils, $timeout, $mdToast, $historyService, $profileService, $soapService) {
	console.info("order.view.controller")
	$scope.historyMode = (localStorage["historyMod"] == "ON") ? true : false;
	$scope.haveSaveDraff = (localStorage["saveDraffItems"] == undefined || localStorage["saveDraffItems"] == "") ? false : true;
	$scope.profile = $profileService.getProfile();
	$scope.roundTaxDate = new Date().toLocaleDateString();

	console.info("historyMode ", $scope.historyMode);
	console.info("haveSaveDraff ", $scope.haveSaveDraff);
	/** search item */
	$scope.userSearch = {};
	$scope.userSearch.BrandName = "";
	$scope.userSearch.Degree = "";
	$scope.DraftMode = false;
	$scope.totalTax = 0;
	$scope.royalTotal = 0;
	$scope.sss = 0;
	$scope.sst = 0;
	$scope.kkt = 0;
	$scope.col13 = 0;
	$scope.onlinesubmitData = {};
	$scope.onlinesubmitData.date = "";
	$scope.sumscope = {};
	$scope.sumscope.taxcol9 = {};
	$scope.sumscope.taxcol9.sss = 0;
	$scope.sumscope.taxcol9.sst = 0;
	$scope.sumscope.taxcol9.kkt = 0;
	$scope.sumscope.taxcol9.sssDisplay = 0;
	$scope.sumscope.taxcol9.sstDisplay = 0;
	$scope.sumscope.taxcol9.kktDisplay = 0;
	/**
	 * อัตราภาษี <MunicipalRateAmount>10</MunicipalRateAmount>
	 * <FundSSSRateAmount>2.0</FundSSSRateAmount> <FundSSTRateAmount>1.5</FundSSTRateAmount>
	 * <FundKKTRateAmount>2.0</FundKKTRateAmount>
	 */
	$scope.MunicipalRateAmountRate = 0;
	$scope.FundSSSRateAmountRate = 0;
	$scope.FundSSTRateAmountRate = 0;
	$scope.FundKKTRateAmountRate = 0;

	if ($scope.historyMode === true) {
		$scope.gridList = JSON.parse(localStorage["historyItems"]);
		var HistoryList = JSON.parse(localStorage["HistoryList"]);
		/** set Tax Rate */
		$scope.MunicipalRateAmountRate = HistoryList.MunicipalRateAmountRate;
		$scope.FundSSSRateAmountRate = HistoryList.FundSSSRateAmountRate;
		$scope.FundSSTRateAmountRate = HistoryList.FundSSTRateAmountRate;
		$scope.FundKKTRateAmountRate = HistoryList.FundKKTRateAmountRate;
		$scope.inputSum8 = HistoryList.inputSum8;
		$scope.sumscope.sumTax8 = HistoryList.sumTax8;
		$scope.inputSum9 = HistoryList.inputSum9;
		$scope.sumscope.sumTax9 = HistoryList.sumTax9;
		$scope.roundTaxDate = new Date(parseInt(HistoryList.submitDate));

		localStorage["historyItems"] = JSON.stringify([]);
		localStorage["HistoryList"] = JSON.stringify({});
		localStorage["historyMod"] = "OFF";
	} else {
		$scope.gridList = [];
	}

	/***************************************************************************
	 * Draft MODE ON
	 **************************************************************************/
	if (localStorage["DraftMode"] == "ON") {
		var DraftData = JSON.parse(localStorage["DraftData"]);

		$scope.MunicipalRateAmountRate = DraftData.MunicipalRateAmountRate;
		$scope.FundSSSRateAmountRate = DraftData.FundSSSRateAmountRate;
		$scope.FundSSTRateAmountRate = DraftData.FundSSTRateAmountRate;
		$scope.FundKKTRateAmountRate = DraftData.FundKKTRateAmountRate;
		$scope.gridList = JSON.parse(DraftData.dataGrid);
		$scope.DraftMode = true;
		$scope.DraftModeId = DraftData.id;
		console.log("DraftMode", $scope.DraftMode, "DraftModeId", $scope.DraftModeId);
		localStorage["DraftMode"] = "OFF";
		localStorage["DraftData"] = "";
	}

	$scope.topProduct = $productService.getTopProduct();
	$scope.searchProduct = $productService.getAllProduct();
	$scope.showDataSearch = $scope.searchProduct.length > 0;
	var productCheck = [];
	var tempSelect = [];
	$scope.canDelete = false;
	console.info("canDelete ", $scope.canDelete);

	$scope.submitType = "offline";
	$scope.step = [ false, true, false, false, false, false ];
	$scope.stepCount = 1;

	// report
	$scope.showReportProgess = false;

	// getsearch
	$scope.toStringDec = function(_number, _decemal) {
		if (_decemal !== undefined) {
			return _number.toFixed(_decemal);
		}
		return _number.toFixed(4);
	}
	$scope.floorNumber = function(_number) {
		return Math.floor(_number);
	}
	
	$scope.floorNumber2POS = function(_number) {
		var str = _number.toFixed(4);
			str = str.substr(0,str.length-2);
		return parseFloat(str).toFixed(2);
	}

	$scope.navigaTor = function(_index) {
		$scope.step = [ false, false, false, false, false, false ];
		$scope.step[_index] = true;
		$scope.stepCount = _index;
		console.info("navigaTor", _index);

		if (_index == 0) {
			$scope.resetProductChecked();
			$rootScope.$broadcast("updateTitleText", "สร้าง สร. 120-11 ► เพิ่ม");
		}
		if (_index == 1) {
			$rootScope.$broadcast("updateTitle", 1);
		}
	};

	$scope.filterItem = JSON.parse(localStorage["FilterGoods"]);
	function createFilterFor(query, type) {
		var lowercaseQuery = angular.lowercase(query);
		return function filterFn(state) {
			if (type === "Degree") {
				return (state.toString().indexOf(lowercaseQuery) >= 0);
			} else {
				var dis = angular.lowercase(state.toString());
				return (dis.indexOf(lowercaseQuery) >= 0);
			}
		};
	}

	$scope.querySearch = function(query, type) {
		console.log(query);
		var data = $scope.filterItem[type];
		var results = query ? data.filter(createFilterFor(query, type)) : data;
		return results;
	};

	// <md-tab label="รายการสุรา แนะนำ">
	// product select

	$scope.getProductChecked = function(_goodId) {
		return productCheck.indexOf(_goodId) > -1;
	};

	$scope.resetProductChecked = function() {
		tempSelect = [];
		productCheck = [];
		$scope.userSearch.BrandName = "";
		$scope.userSearch.Degree = "";
	};

	$scope.productCheckedClick = function(_goodId, product) {
		var findex = productCheck.indexOf(_goodId);
		if (findex === -1) {
			productCheck.push(_goodId);
			tempSelect.push(product);
		} else {
			productCheck.splice(findex, 1);
			tempSelect.splice(findex, 1);
		}

		console.log("_goodId", _goodId, "productCheck", productCheck.length);

	};

	$scope.onAdd = function() {
		for ( var _i in tempSelect) {
			var gl = new GridItem();
			gl.Goods = angular.copy(tempSelect[_i]);
			$scope.gridList.push(gl);
			console.log(gl);
			/**
			 * อัตราภาษี <MunicipalRateAmount>10</MunicipalRateAmount>
			 * <FundSSSRateAmount>2.0</FundSSSRateAmount>
			 * <FundSSTRateAmount>1.5</FundSSTRateAmount>
			 * <FundKKTRateAmount>2.0</FundKKTRateAmount>
			 */
			$scope.MunicipalRateAmountRate = gl.Goods.MunicipalRateAmount;
			$scope.FundSSSRateAmountRate = gl.Goods.FundSSSRateAmount;
			$scope.FundSSTRateAmountRate = gl.Goods.FundSSTRateAmount;
			$scope.FundKKTRateAmountRate = gl.Goods.FundKKTRateAmount;
		}

		$scope.navigaTor(1);
		console.info("onAdd");
	};

	$scope.onDelete = function(ev) {
		$scope.showConfirm(ev, "ยืนยันการทำรายการ ?", function() {
			var del = [];
			for ( var _i in $scope.gridList) {
				var item = $scope.gridList[_i];
				if (item.checkbox) {
					del.push(item);
				}
			}

			for ( var _i in del) {
				var findex = $scope.gridList.indexOf(del[_i]);
				$scope.gridList.splice(findex, 1);
			}

			// check when OK!
			$scope.onBlurCheckDelete(ev);
			$scope.showSimpleToast("ทำรายการเรียบร้อย");

		});
	};

	$scope.onBlurCheckDelete = function(ev) {
		var del = [];
		for ( var _i in $scope.gridList) {
			var item = $scope.gridList[_i];
			if (item.checkbox) {
				del.push(item);
			}
		}

		$scope.canDelete = del.length > 0;
		console.info("onBlurCheckDelete ", $scope.canDelete);
	};

	$scope.showConfirm = function(ev, _title, _fn) {
		// Appending dialog to document.body to cover sidenav in docs app
		var confirm = $mdDialog.confirm().title('แจ้งเตือน').content(_title).ariaLabel('confirm Delete').ok('ตกลง').cancel('ยกเลิก').targetEvent(ev);
		$mdDialog.show(confirm).then(function() {
			// console.log(confirm);
			_fn();
		}, function() {
			console.log("cancle");
		});
	};

	// report
	$scope.toastPosition = {
		bottom : true,
		top : false,
		left : false,
		right : true
	};
	$scope.getToastPosition = function() {
		return Object.keys($scope.toastPosition).filter(function(pos) {
			return $scope.toastPosition[pos];
		}).join(' ');
	};

	$scope.$on("gotoStep", function(event, args) {
		console.info("gotoStep", args);
		$timeout(function() {
			$scope.navigaTor(args);
		}, 100);
	});

	$scope.nextAndGenReport = function() {
		$scope.showReportProgess = true;

		// Gen XML
		$scope.submitOnlineRequest();

		$fileUtils.runGenReport(function(error) {
			if (error == null) {
				$rootScope.$broadcast("gotoStep", 4);
			} else {
				console.log(error);
				$scope.showSimpleToast(error.message);
			}

			$scope.showReportProgess = false;

		});
	};

	$scope.open = function(_index) {
		var item = $fileUtils.pdfItem[_index];
		$fileUtils.open(item);
	};

	$scope.showSimpleToast = function(_msg) {
		$mdToast.show($mdToast.simple().content(_msg).position($scope.getToastPosition()).hideDelay(6000));
	};

	// calc Tax
	$scope.calcPriceAmount = function(_GridItem) {
		// F
		var price = _GridItem.Goods.GoodsPrice;
		if (_GridItem.Goods.DeclarePrice > 0) {
			price = _GridItem.Goods.DeclarePrice;
		}
		
		_GridItem.PriceAmountTax = _GridItem.GoodsCount *_GridItem.Goods.TaxRateByPriceAmount * price / 100
			
		return  _GridItem.Goods.TaxRateByPriceAmount * price / 100;
	};

	$scope.calcQuantityAmount = function(_GridItem) {
		/** "ภาษีปริมาณ(บาท/ลิตร/100)(2.1)" */
		var tax1 = 0;
		if (_GridItem.Goods.Degree <= _GridItem.Goods.DegreeMin) {
			tax1 = (_GridItem.Goods.GoodsSize * 1) * _GridItem.Goods.Degree * _GridItem.Goods.TaxRateByQuantityAmount / 100;

		} else {
			// OVER LOAD
			tax1 = (_GridItem.Goods.GoodsSize * 1) * _GridItem.Goods.DegreeMin * _GridItem.Goods.TaxRateByQuantityAmount / 100;
		}

		/** "ภาษีปริมาณ(บาทต่อลิตร)(2.2)" */
		var tax2 = (_GridItem.Goods.GoodsSize * 1) * _GridItem.Goods.RatePerLitre;
		var max =  Math.max(tax1, tax2);
		_GridItem.QuantityAmountTax = max * _GridItem.GoodsCount;
		return max;

	};

	$scope.calcDegreeOver = function(_GridItem) {
		if (_GridItem.Goods.Degree > _GridItem.Goods.DegreeMin) {
			var diff = _GridItem.Goods.Degree - _GridItem.Goods.DegreeMin;
			return _GridItem.DegreeOverTax = diff * _GridItem.Goods.RateDegreeOver * (_GridItem.Goods.GoodsSize * _GridItem.GoodsCount);
		}

		return 0;
	};

	$scope.sumCalcPriceAmount = function() {
		var sum = 0;
		for ( var _i in $scope.gridList) {
			var item = $scope.gridList[_i];
			sum += item.PriceAmountTax;
		}
		$scope.sumCalcPriceAmountValue = Math.floor(sum);
//		console.log("sumCalcPriceAmountValue", $scope.sumCalcPriceAmountValue);
		$scope.realsumCalcPriceAmountValue = sum;
		return sum;
	};

	$scope.sumCalcQuantityAmount = function() {
		var sum = 0;
		for ( var _i in $scope.gridList) {
			var item = $scope.gridList[_i];
			sum += item.QuantityAmountTax;
		}

		$scope.sumCalcQuantityAmountValue = sum;
		return sum;
	};

	$scope.sumNetTax = function() {
		$scope.totalTax = $scope.sumCalcPriceAmountValue + $scope.sumCalcQuantityAmountValue;
		$scope.difcol7andcol8 = $scope.totalTax - $scope.toNumber($scope.sumscope.sumTax8);
		$scope.col10 = $scope.totalTax - $scope.toNumber($scope.sumscope.sumTax8) - $scope.toNumber($scope.sumscope.sumTax9);
		
		$scope.royalTotal = $scope.col10 * $scope.MunicipalRateAmountRate * 0.01;
		// ปัดเศษ หลักที่สามทิ้ง
		$scope.royalTotal =  parseFloat($scope.floorNumber2POS($scope.royalTotal));
		
		$scope.sss = $scope.difcol7andcol8 * $scope.FundSSSRateAmountRate * 0.01;
		$scope.sst = $scope.difcol7andcol8 * $scope.FundSSTRateAmountRate * 0.01;
		$scope.kkt = $scope.difcol7andcol8 * $scope.FundKKTRateAmountRate * 0.01;
		$scope.col12 = $scope.col10 + $scope.royalTotal;
		$scope.col14 = $scope.col12 + $scope.col13;
		
		// cal หักคืนภาษีสุรา
		var taxcol9 = $scope.toNumber($scope.sumscope.sumTax9);
		if( taxcol9 > 0){
//			console.log("taxcol9",taxcol9);
			$scope.sumscope.taxcol9.sss = taxcol9 * $scope.FundSSSRateAmountRate * 0.01;
			$scope.sumscope.taxcol9.sst = taxcol9 * $scope.FundSSTRateAmountRate * 0.01;
			$scope.sumscope.taxcol9.kkt = taxcol9 * $scope.FundKKTRateAmountRate * 0.01;

			// ปัดเศษ หลักที่สามทิ้ง
			$scope.sumscope.taxcol9.sss =  parseFloat($scope.floorNumber2POS($scope.sumscope.taxcol9.sss));
			$scope.sumscope.taxcol9.sst =  parseFloat($scope.floorNumber2POS($scope.sumscope.taxcol9.sst));
			$scope.sumscope.taxcol9.kkt =  parseFloat($scope.floorNumber2POS($scope.sumscope.taxcol9.kkt));
			
//			console.log("$scope.sumscope.taxcol9.sss" , $scope.sumscope.taxcol9.sss);
//			console.log("$scope.sumscope.taxcol9.sst" , $scope.sumscope.taxcol9.sst);
//			console.log("$scope.sumscope.taxcol9.kkt" , $scope.sumscope.taxcol9.kkt);
			
		}else{
			$scope.sumscope.taxcol9.sss = 0;
			$scope.sumscope.taxcol9.sst = 0;
			$scope.sumscope.taxcol9.kkt = 0;
		}

		// kong toon
		// ปัดเศษ หลักที่สามทิ้ง
		$scope.sumscope.taxcol9.sssDisplay  = parseFloat($scope.floorNumber2POS($scope.sss - $scope.sumscope.taxcol9.sss));
		$scope.sumscope.taxcol9.sstDisplay =  parseFloat($scope.floorNumber2POS($scope.sst - $scope.sumscope.taxcol9.sst));
		$scope.sumscope.taxcol9.kktDisplay  =  parseFloat($scope.floorNumber2POS($scope.kkt - $scope.sumscope.taxcol9.kkt));
//		console.log("$scope.sumscope.taxcol9.sssDisplay",$scope.sumscope.taxcol9.sssDisplay);
		
		return $scope.totalTax;
	};

	// last step
	$scope.doAgain = function() {
		console.info("doAgain ...");
		// $scope.gridList = [];
		// $scope.navigaTor(1);
		window.location.reload(true);
	}

	// history
	$scope.saveHistory = function() {
		if ($scope.submitType === "online") {
			// online
			console.info("submitOnlinefn");
			console.info("onlinesubmitData.date",$scope.onlinesubmitData.date);
			if ($scope.onlinesubmitData.date == "") {
				console.info("$scope.onlinesubmitData.date not have value");
				$scope.showSimpleToast("กรุณากรอก วันที่ยื่นแบบ");
				return;
			}

			$scope.submitOnlinefn();

		} else {
			// OFF line
			$historyService.save($scope.profile, $scope.gridList, $scope.submitType, $scope);
			if ($scope.DraftMode === true) {
				$historyService.clearAllCurrentByUser();
			}
			$scope.navigaTor(5);
		}

	};

	/**
	 * submitOnline * gen report online button
	 */
	
	$scope.buttononlinesubmit = function() {
		console.log("buttononlinesubmit");
		$scope.showpanelOnline = true;
		$scope.onlinesubmitData.date = "";
		$scope.submitType = "online"
	};


	$scope.buttononlinesubmitCancle = function() {
		$scope.showpanelOnline = false;
		$scope.onlinesubmitData.date = "";
		$scope.submitType = "offline"
	};

	$scope.submitOnlinefn = function() {
		$scope.showReportProgess = true;

		var xmlRequest = $scope.submitOnlineRequest();
		var _endpoint = localStorage["soaphost"];

		console.log(xmlRequest.getSOAP());
		$soapService.post(xmlRequest, _endpoint, function(status, xmlDoc, data) {

			if (status == 200) {
				var resStatus = xmlDoc.getVal("SubmitOnlineStatus");
				console.log(status, resStatus);
				if (resStatus == "OK") {
					var referenceNumber = xmlDoc.getVal("ReferenceNumber");
					console.log("referenceNumber", referenceNumber);
					// $scope.navigaTor(5);
					$fileUtils.runGenReportOnline(function(error) {
						if (error == null) {
							$historyService.save($scope.profile, $scope.gridList, $scope.submitType, $scope);
							$scope.showSimpleToast("ทำรายการเรียบร้อย");
							$rootScope.$broadcast("gotoStep", 5);
							$scope.showpanelOnline = false;
						} else {
							console.log(error);
							$scope.showSimpleToast(error.message);
						}

						$scope.showReportProgess = false;
					}, referenceNumber);

				} else {
					$scope.showSimpleToast("error SubmitOnlineStatus status" + resStatus);
					$scope.showReportProgess = false;
				}
			} else {
				$scope.showSimpleToast("กรุณาแช็คการเชื่อมต่ออินเตอร์  error  status" + status);
				$scope.showReportProgess = false;
			}
		})

	};

	$scope.submitOnlineRequest = function() {

		var profile = $scope.profile;
		var addr = profile.AddrBean;
		var userId = "";
		var pws = "";
		var IpAddress = "192.168.1.1";
		var currentDate = "20150908";
		var SubmissionDate = ($scope.onlinesubmitData.date != undefined && $scope.onlinesubmitData.date.length > 9) ? $scope.onlinesubmitData.date.split("/").reverse().join("") : currentDate;
		var EbarcodeSubmitOnlineRequestReQuest = $soapService.getSOAPMessage("EbarcodeSubmitOnlineRequest", "http://www.excise.go.th/xsd/barcode");
		var EbarcodeSubmitOnlineRequest = $soapService.getObject("XmlData");

		var SubmitOnlineHeader = $soapService.getObject("SubmitOnlineHeader");
		SubmitOnlineHeader.push($soapService.getObjectItem("RegistrationId", ""));
		SubmitOnlineHeader.push($soapService.getObjectItem("CusId", profile.CusId));
		SubmitOnlineHeader.push($soapService.getObjectItem("CompanyId", profile.CompanyId));
		SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserId", userId));
		SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserPwd", pws));
		SubmitOnlineHeader.push($soapService.getObjectItem("TaxpayerId", profile.factorys.TaxpayerId));
		SubmitOnlineHeader.push($soapService.getObjectItem("ExciseOfficeId", profile.ExciseOfficeId));
		SubmitOnlineHeader.push($soapService.getObjectItem("InternetUniqueId", profile.InternetUniqueId));
		SubmitOnlineHeader.push($soapService.getObjectItem("IpAddress", IpAddress));
		SubmitOnlineHeader.push($soapService.getObjectItem("SubmissionEmail", profile.factorys.EmailAddress));
		SubmitOnlineHeader.push($soapService.getObjectItem("SubmissionDate", SubmissionDate));
		EbarcodeSubmitOnlineRequest.push(SubmitOnlineHeader);
		EbarcodeSubmitOnlineRequestReQuest.push(SubmitOnlineHeader);

		var SR12011Info = $soapService.getObject("SR12011Info");
		EbarcodeSubmitOnlineRequest.push(SR12011Info);
		EbarcodeSubmitOnlineRequestReQuest.push(SR12011Info);

		var TaxpayerInfo = $soapService.getObject("TaxpayerInfo");
		SR12011Info.push(TaxpayerInfo);
		TaxpayerInfo.push($soapService.getObjectItem("CompanyName", profile.CompanyName));
		TaxpayerInfo.push($soapService.getObjectItem("Tin", profile.CompanyId));
		TaxpayerInfo.push($soapService.getObjectItem("LicenseNo", profile.LicBook + "/" + profile.LicenseNo));
		TaxpayerInfo.push($soapService.getObjectItem("EffectiveDate", profile.factorys.EffectiveDate));
		TaxpayerInfo.push($soapService.getObjectItem("ExpireDate", profile.factorys.ExpireDate));
		TaxpayerInfo.push($soapService.getObjectItem("TaxpayerName", profile.TaxpayerName));
		var TaxpayerAddressInfo = $soapService.getObject("TaxpayerAddressInfo");
		TaxpayerInfo.push(TaxpayerAddressInfo);
		TaxpayerAddressInfo.push($soapService.getObjectItem("HouseNumber", addr.HouseNumber));
		TaxpayerAddressInfo.push($soapService.getObjectItem("MooNumber", addr.MooNumber));
		TaxpayerAddressInfo.push($soapService.getObjectItem("TrokSoiName", addr.TrokSoiName));
		TaxpayerAddressInfo.push($soapService.getObjectItem("StreetName", addr.StreetName));
		TaxpayerAddressInfo.push($soapService.getObjectItem("ThambolName", addr.ThambolName));
		TaxpayerAddressInfo.push($soapService.getObjectItem("AmphurName", addr.AmphurName));
		TaxpayerAddressInfo.push($soapService.getObjectItem("ProvinceName", addr.ProvinceName));
		TaxpayerAddressInfo.push($soapService.getObjectItem("Postcode", addr.Postcode));
		TaxpayerAddressInfo.push($soapService.getObjectItem("TelNumber", addr.TelNumber));

		// loop
		var GoodsListInfo = $soapService.getObject("GoodsListInfo");
		SR12011Info.push(GoodsListInfo);
		var Griditems = $scope.gridList;

		for ( var _i in Griditems) {

			var item = Griditems[_i];
			var index = parseInt(_i) + 1;
			var price = item.Goods.GoodsPrice;
			if (item.Goods.DeclarePrice > 0) {
				price = item.Goods.DeclarePrice;
			}
			var GoodsValue = item.GoodsCount * price;
			var TaxAmount = item.PriceAmountTax + item.QuantityAmountTax;
			var stempType = 0;
			var GoodsEntryInfo = $soapService.getObject("GoodsEntryInfo");
			GoodsListInfo.push(GoodsEntryInfo);

			// 70010101#39K,00,000,055,27#3#0#08##-
			var catCode = item.Goods.GoodsCode.split("#")[1].split(",");

			GoodsEntryInfo.push($soapService.getObjectItem("ProductCode", item.Goods.ProductTypeCode));
			GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode1", catCode[0]));
			GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode2", catCode[1]));
			GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode3", catCode[2]));
			GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode4", catCode[3]));
			GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode5", catCode[4]));
			GoodsEntryInfo.push($soapService.getObjectItem("UnitCode", item.Goods.UnitCode));
			GoodsEntryInfo.push($soapService.getObjectItem("RateFlag", "Q"));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantity", item.Goods.TaxRateByQuantityAmount.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityNumber", item.col45.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityPerUnit", item.Goods.RatePerLitre.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxValue", item.Goods.TaxRateByPriceAmount.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("PriceFlag", item.Goods.PriceFlag));
			GoodsEntryInfo.push($soapService.getObjectItem("InformPrice", item.Goods.GoodsPrice.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("DeclarePrice", item.Goods.DeclarePrice.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("UnitPrice", item.Goods.GoodsPrice.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsNum", item.col45));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsValue", GoodsValue.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxAmount", TaxAmount.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("SeqNo", index));
			GoodsEntryInfo.push($soapService.getObjectItem("ProductTypeDesc", item.Goods.ProductTypeDescriptionText));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsDesc",item.Goods.ProductTypeDescriptionText + " " + item.Goods.BrandName));
			GoodsEntryInfo.push($soapService.getObjectItem("Degree", item.Goods.Degree));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsSize", item.Goods.GoodsSize));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsPiece", item.GoodsCount));
			GoodsEntryInfo.push($soapService.getObjectItem("GoodsQuantity", item.col45.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxByValue", item.col51.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantity", item.col52.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityOver", item.col53.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityWithOver", item.col54.toFixed(4)));
			GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByValue", item.PriceAmountTax.toFixed(2)));
			GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByQuantity", item.QuantityAmountTax.toFixed(2)));

			stempType = (item.Goods.ProductTypeDescriptionText.trim() == "เบียร์") ? 1 : 3;
		}

		// sum

		var SummaryInfo = $soapService.getObject("SummaryInfo");
		SR12011Info.push(SummaryInfo);
		SummaryInfo.push($soapService.getObjectItem("SumAllTaxByValue", $scope.realsumCalcPriceAmountValue.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("SumAllTaxByQuantity", $scope.sumCalcQuantityAmountValue.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("SumAllTax", $scope.totalTax.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("TaxLessFrom", ($scope.sumscope.inputSum8 == undefined) ? "" : $scope.sumscope.inputSum8));
		SummaryInfo.push($soapService.getObjectItem("TaxLessAmount", $scope.toNumber($scope.sumscope.sumTax8).toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookNo", ($scope.sumscope.inputSum9 == undefined) ? "" : $scope.sumscope.inputSum9));
		SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookAmount", $scope.toNumber($scope.sumscope.sumTax9).toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentExciseAmount", $scope.col10.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentMunicipalAmount", $scope.royalTotal.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentFundHealthAmount", $scope.sss.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentFundTVAmount", $scope.sst.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentFundSportAmount", $scope.kkt.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("MoiRate", $scope.MunicipalRateAmountRate.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("MoiTax", $scope.royalTotal.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("SumCreditMoiTax", "0.00"));
		SummaryInfo.push($soapService.getObjectItem("PrintType", stempType));
		SummaryInfo.push($soapService.getObjectItem("PaymentExciseAndMunicipalTaxAmount", $scope.col12.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentOtherAmount", $scope.col13.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("PaymentNetTaxAmount", $scope.col14.toFixed(2)));
		SummaryInfo.push($soapService.getObjectItem("ExciseAmountSubtractTaxLessAmount", ($scope.totalTax-$scope.toNumber($scope.sumscope.sumTax8)).toFixed(2)));

		//kong toon
		var FundListInfo = $soapService.getObject("FundListInfo");
		SR12011Info.push(FundListInfo);
		var FundEntryInfo = [];
		
		
		FundEntryInfo = $soapService.getObject("FundEntryInfo");
		FundEntryInfo.push($soapService.getObjectItem("FundType", "S"));
		FundEntryInfo.push($soapService.getObjectItem("FundAmt", $scope.sss.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("FundRate", $scope.FundSSSRateAmountRate.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("CreditAmt", $scope.sumscope.taxcol9.sss.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("NetAmt", ($scope.sumscope.taxcol9.sssDisplay ).toFixed(2) ));
		FundListInfo.push(FundEntryInfo);
		
		FundEntryInfo = $soapService.getObject("FundEntryInfo");
		FundEntryInfo.push($soapService.getObjectItem("FundType", "T"));
		FundEntryInfo.push($soapService.getObjectItem("FundAmt", $scope.sst.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("FundRate", $scope.FundSSTRateAmountRate.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("CreditAmt", $scope.sumscope.taxcol9.sst.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("NetAmt", ($scope.sumscope.taxcol9.sstDisplay ).toFixed(2) ));
		FundListInfo.push(FundEntryInfo);
		
		FundEntryInfo = $soapService.getObject("FundEntryInfo");
		FundEntryInfo.push($soapService.getObjectItem("FundType", "K"));
		FundEntryInfo.push($soapService.getObjectItem("FundAmt", $scope.kkt.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("FundRate", $scope.FundKKTRateAmountRate.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("CreditAmt", $scope.sumscope.taxcol9.kkt.toFixed(2)));
		FundEntryInfo.push($soapService.getObjectItem("NetAmt", ($scope.sumscope.taxcol9.kktDisplay ).toFixed(2) ));
		FundListInfo.push(FundEntryInfo);
		
		
		var str = EbarcodeSubmitOnlineRequest.getString();
		// writeFile
		var data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + str;
		// data = data.replace("EbarcodeSubmitOnlineRequest",
		// "XmlData").replace("/EbarcodeSubmitOnlineRequest", "/XmlData");
		$fileUtils.writeFileGenReport(data);
		console.log(data);

		return EbarcodeSubmitOnlineRequestReQuest;

	};

	$scope.openHistory = function() {
		$rootScope.$broadcast("gotoMenuIndex", 2);
	}

	// save draff

	$scope.$on("checkSaveDraff", function(event, args) {
		console.info("checkSaveDraff");
		if ($scope.gridList.length > 0) {
			localStorage["saveDraffItems"] = JSON.stringify($scope.gridList);
		}
	});

	$scope.clickLoadDraff = function() {
		if (localStorage["saveDraffItems"] == "undefined" || localStorage["saveDraffItems"] == "") {
			return;
		}

		console.info("clickLoadDraff");
		$scope.gridList = JSON.parse(localStorage["saveDraffItems"]);
		localStorage["saveDraffItems"] = "";
		$scope.haveSaveDraff = false;
	};

	$scope.toNumber = function(_strNumber) {
		if (parseFloat(_strNumber).toString() == "NaN") {
			return 0;
		}
		return parseFloat(_strNumber);
	};

	/***************************************************************************
	 * for รวมภาษี (๘) หัก ค่าภาษีสุรา ... ที่ชำระไว้แล้วจาก (๙) หัก
	 * คืนภาษีสุราตามหนังสือกรมฯ ที่
	 */
	if ($scope.historyMode == false) {
//		$scope.sumTax8 = $scope.toStringDec(0, 2);
//		$scope.sumTax9 = $scope.toStringDec(0, 2);
		$scope.sumscope.sumTax8 = $scope.toStringDec(0, 2);
		$scope.sumscope.sumTax9 = $scope.toStringDec(0, 2);
	}
	
	/***************************************************************************
	 * Save Draft
	 * 
	 **************************************************************************/

	$scope.clickSaveDraft = function(ev) {

		$scope.showConfirm(ev, "ยืนยันการทำรายการ ?", function() {
			if ($scope.DraftMode === true) {
				var draftId = $historyService.savDraft($scope.profile, $scope.gridList, $scope);
				console.info("Save again!!! ID:", draftId);
				var rmId = [];
				rmId.push($scope.DraftModeId);
				$historyService.removeDraftById(rmId);

				$scope.DraftModeId = draftId;
			} else {
				$historyService.clearAllCurrentByUser();
				var draftId = $historyService.savDraft($scope.profile, $scope.gridList, $scope);
				// open draft Mode
				$scope.DraftMode = true;
				$scope.DraftModeId = draftId;
			}
			$mdToast.show($mdToast.simple().content("ทำรายการเรียบร้อย ที่ ฉบับร่าง").position($scope.getToastPosition()).hideDelay(6000));
		});
	}
	
	
	/******
	 * INPUT NUMBER
	 * 
	 * ****/
	$scope.inputNumberOnly = function ( event ){
		var key = event.which || event.keyCode; 
		console.log(key);
	};

});