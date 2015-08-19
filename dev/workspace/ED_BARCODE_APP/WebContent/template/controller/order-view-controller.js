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

module.controller('order.view.controller', function($scope, $rootScope, $location, $productService, $mdDialog, $fileUtils, $timeout,
		$mdToast, $historyService, $profileService, $soapService) {
	console.info("order.view.controller")

	$scope.topProduct = $productService.getTopProduct();
	$scope.searchProduct = $productService.getAllProduct();
	$scope.showDataSearch = $scope.searchProduct.length > 0;
	$scope.gridList = [];
	var productCheck = [];
	var tempSelect = [];

	$scope.submitType = "offline";
	$scope.step = [ false, true, false, false, false, false, false ];
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

	$scope.navigaTor = function(_index) {
		$scope.step = [ false, false, false, false, false, false, ];
		$scope.step[_index] = true;
		$scope.stepCount = _index;
		console.info("navigaTor", _index);

		if (_index == 0) {
			$scope.resetProductChecked();
		}
	};

	function createFilterFor(query, type) {
		var lowercaseQuery = angular.lowercase(query);
		return function filterFn(state) {
			if (type === "Degree") {
				return (state.Degree.toString().indexOf(lowercaseQuery) === 0);
			} else {
				var dis = angular.lowercase(state.GoodsDescriptionText);
				// console.log(lowercaseQuery,state.GoodsDescriptionText);
				return (dis.indexOf(lowercaseQuery) === 0);
			}
		};
	}

	$scope.querySearch = function(query, type) {
		console.log(query);
		var results = query ? $scope.searchProduct.filter(createFilterFor(query, type)) : $scope.searchProduct;
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
			gl.Goods = tempSelect[_i];
			$scope.gridList.push(gl);
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

		});
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
		bottom : false,
		top : true,
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
		if (_GridItem.Goods.PriceFlag == "P") {
			price = _GridItem.Goods.DeclarePrice;
		}
		return _GridItem.PriceAmountTax = (_GridItem.Goods.GoodsSize * _GridItem.GoodsCount) * _GridItem.Goods.TaxRateByPriceAmount * price / 100;
	};

	$scope.calcQuantityAmount = function(_GridItem) {
		/** "ภาษีปริมาณ(บาท/ลิตร/100)(2.1)" */
		var tax1 = 0;
		if (_GridItem.Goods.Degree <= _GridItem.Goods.DegreeMin) {
			tax1 = (_GridItem.Goods.GoodsSize * _GridItem.GoodsCount) * _GridItem.Goods.Degree * _GridItem.Goods.TaxRateByQuantityAmount / 100;

		} else {
			// OVER LOAD
			tax1 = (_GridItem.Goods.GoodsSize * _GridItem.GoodsCount) * _GridItem.Goods.DegreeMin * _GridItem.Goods.TaxRateByQuantityAmount / 100;
		}

		/** "ภาษีปริมาณ(บาทต่อลิตร)(2.2)" */
		var tax2 = (_GridItem.Goods.GoodsSize * _GridItem.GoodsCount) * _GridItem.Goods.RatePerLitre;
		return _GridItem.QuantityAmountTax = Math.max(tax1, tax2);

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
		$scope.sumCalcPriceAmountValue = sum;
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

	// last step
	$scope.doAgain = function() {
		console.info("doAgain ...");
		$scope.gridList = [];
		$scope.navigaTor(1);
	}

	// history
	$scope.saveHistory = function() {
//		$historyService.save($profileService.getProfile(), $scope.gridList, $scope.submitType);
//		$scope.navigaTor(5);
		$scope.submitOnlineRequest();
	};

	/**
	 * submitOnline * gen report
	 * 
	 */
	
	$scope.submitOnlineRequest = function() {
		var EbarcodeSubmitOnlineRequest = $soapService.getObject("EbarcodeSubmitOnlineRequest");
			var SubmitOnlineHeader = $soapService.getObject("SubmitOnlineHeader");
				SubmitOnlineHeader.push($soapService.getObjectItem("RegistrationId","RegistrationId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("CusId","CusId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyId","CompanyId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserId","CompanyUserId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserPwd","CompanyUserPwd"));
				SubmitOnlineHeader.push($soapService.getObjectItem("TaxpayerId","TaxpayerId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("ExciseOfficeId","ExciseOfficeId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("InternetUniqueId","InternetUniqueId"));
				SubmitOnlineHeader.push($soapService.getObjectItem("IpAddress","IpAddress"));
				SubmitOnlineHeader.push($soapService.getObjectItem("SubmissionEmail","SubmissionEmail"));
				EbarcodeSubmitOnlineRequest.push(SubmitOnlineHeader);
				
		var SR12011Info = $soapService.getObject("SR12011Info");
			EbarcodeSubmitOnlineRequest.push(SR12011Info);
			var TaxpayerInfo = $soapService.getObject("TaxpayerInfo");
			SR12011Info.push(TaxpayerInfo);
			TaxpayerInfo.push($soapService.getObjectItem("CompanyName","CompanyName"));
			TaxpayerInfo.push($soapService.getObjectItem("Tin","Tin"));
			TaxpayerInfo.push($soapService.getObjectItem("LicenseNo","LicenseNo"));
			TaxpayerInfo.push($soapService.getObjectItem("EffectiveDate","EffectiveDate"));
			TaxpayerInfo.push($soapService.getObjectItem("ExpireDate","ExpireDate"));
			TaxpayerInfo.push($soapService.getObjectItem("TaxpayerName","TaxpayerName"));
			var TaxpayerAddressInfo = $soapService.getObject("TaxpayerAddressInfo");
				TaxpayerInfo.push(TaxpayerAddressInfo);
				TaxpayerAddressInfo.push($soapService.getObjectItem("HouseNumber","HouseNumber"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("MooNumber","MooNumber"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("TrokSoiName","TrokSoiName"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("StreetName","StreetName"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("ThambolName","ThambolName"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("AmphurName","AmphurName"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("ProvinceName","ProvinceName"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("Postcode","Postcode"));
				TaxpayerAddressInfo.push($soapService.getObjectItem("TelNumber","TelNumber"));

			
				//loop
			var GoodsListInfo = $soapService.getObject("GoodsListInfo");
			SR12011Info.push(GoodsListInfo);
			var GoodsEntryInfo = $soapService.getObject("GoodsEntryInfo");
				GoodsListInfo.push(GoodsEntryInfo);
				GoodsEntryInfo.push($soapService.getObjectItem("ProductCode","ProductCode"));
				GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode1","CategoryCode1"));
				GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode2","CategoryCode2"));
				GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode3","CategoryCode3"));
				GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode4","CategoryCode4"));
				GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode5","CategoryCode5"));
				GoodsEntryInfo.push($soapService.getObjectItem("UnitCode","UnitCode"));
				GoodsEntryInfo.push($soapService.getObjectItem("RateFlag","RateFlag"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantity","TaxQuantity"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityNumber","TaxQuantityNumber"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityPerUnit","TaxQuantityPerUnit"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxValue","TaxValue"));
				GoodsEntryInfo.push($soapService.getObjectItem("PriceFlag","PriceFlag"));
				GoodsEntryInfo.push($soapService.getObjectItem("InformPrice","InformPrice"));
				GoodsEntryInfo.push($soapService.getObjectItem("DeclarePrice","DeclarePrice"));
				GoodsEntryInfo.push($soapService.getObjectItem("UnitPrice","UnitPrice"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsNum","GoodsNum"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsValue","GoodsValue"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxAmount","TaxAmount"));
				GoodsEntryInfo.push($soapService.getObjectItem("SeqNo","SeqNo"));
				GoodsEntryInfo.push($soapService.getObjectItem("ProductTypeDesc","ProductTypeDesc"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsDesc","GoodsDesc"));
				GoodsEntryInfo.push($soapService.getObjectItem("Degree","Degree"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsSize","GoodsSize"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsPiece","GoodsPiece"));
				GoodsEntryInfo.push($soapService.getObjectItem("GoodsQuantity","GoodsQuantity"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxByValue","TaxByValue"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantity","TaxByQuantity"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityOver","TaxByQuantityOver"));
				GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityWithOver","TaxByQuantityWithOver"));
				GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByValue","NetTaxByValue"));
				GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByQuantity","NetTaxByQuantity"));

				
				//sum
			var SummaryInfo = $soapService.getObject("SummaryInfo");
			SR12011Info.push(SummaryInfo);
			SummaryInfo.push($soapService.getObjectItem("SumAllTaxByValue","SumAllTaxByValue"));
			SummaryInfo.push($soapService.getObjectItem("SumAllTaxByQuantity","SumAllTaxByQuantity"));
			SummaryInfo.push($soapService.getObjectItem("SumAllTax","SumAllTax"));
			SummaryInfo.push($soapService.getObjectItem("TaxLessType","TaxLessType"));
			SummaryInfo.push($soapService.getObjectItem("TaxLessFrom","TaxLessFrom"));
			SummaryInfo.push($soapService.getObjectItem("TaxLessAmount","TaxLessAmount"));
			SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookNo","TaxDeductionOnBookNo"));
			SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookAmount","TaxDeductionOnBookAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentExciseAmount","PaymentExciseAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentMunicipalAmount","PaymentMunicipalAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundHealthAmount","PaymentFundHealthAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundTVAmount","PaymentFundTVAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundSportAmount","PaymentFundSportAmount"));
			SummaryInfo.push($soapService.getObjectItem("MoiRate","MoiRate"));
			SummaryInfo.push($soapService.getObjectItem("PrintType","PrintType"));
			SummaryInfo.push($soapService.getObjectItem("PaymentExciseAndMunicipalTaxAmount","PaymentExciseAndMunicipalTaxAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentOtherAmount","PaymentOtherAmount"));
			SummaryInfo.push($soapService.getObjectItem("PaymentNetTaxAmount","PaymentNetTaxAmount"));
			
			
			
				
			
		
			var str =	EbarcodeSubmitOnlineRequest.getString();
//			console.log(str);
		
	};

});