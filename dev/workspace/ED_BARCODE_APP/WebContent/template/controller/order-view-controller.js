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

		//Gen XML
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
		$historyService.save($profileService.getProfile(), $scope.gridList, $scope.submitType);
		$scope.navigaTor(5);
	};

	/**
	 * submitOnline * gen report
	 * 
	 */
	
	$scope.submitOnlineRequest = function() {
		
		var profile = $profileService.getProfile();
		var addr = profile.AddrBean;
		var userId = "";
		var pws = "";
		var IpAddress = "192.168.1.1";
		
		var EbarcodeSubmitOnlineRequest = $soapService.getObject("EbarcodeSubmitOnlineRequest");
			var SubmitOnlineHeader = $soapService.getObject("SubmitOnlineHeader");
				SubmitOnlineHeader.push($soapService.getObjectItem("RegistrationId",""));
				SubmitOnlineHeader.push($soapService.getObjectItem("CusId",profile.CusId));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyId",profile.CompanyId));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserId",userId));
				SubmitOnlineHeader.push($soapService.getObjectItem("CompanyUserPwd",pws));
				SubmitOnlineHeader.push($soapService.getObjectItem("TaxpayerId",profile.factorys.TaxpayerId));
				SubmitOnlineHeader.push($soapService.getObjectItem("ExciseOfficeId",profile.factorys.ExciseOfficeId));
				SubmitOnlineHeader.push($soapService.getObjectItem("InternetUniqueId",profile.InternetUniqueId));
				SubmitOnlineHeader.push($soapService.getObjectItem("IpAddress",IpAddress));
				SubmitOnlineHeader.push($soapService.getObjectItem("SubmissionEmail",profile.factorys.EmailAddress));
				EbarcodeSubmitOnlineRequest.push(SubmitOnlineHeader);
				
		var SR12011Info = $soapService.getObject("SR12011Info");
			EbarcodeSubmitOnlineRequest.push(SR12011Info);
			var TaxpayerInfo = $soapService.getObject("TaxpayerInfo");
			SR12011Info.push(TaxpayerInfo);
			TaxpayerInfo.push($soapService.getObjectItem("CompanyName",profile.CompanyName));
			TaxpayerInfo.push($soapService.getObjectItem("Tin",profile.CompanyId));
			TaxpayerInfo.push($soapService.getObjectItem("LicenseNo",profile.LicenseNo));
			TaxpayerInfo.push($soapService.getObjectItem("EffectiveDate",profile.factorys.EffectiveDate));
			TaxpayerInfo.push($soapService.getObjectItem("ExpireDate",profile.factorys.ExpireDate));
			TaxpayerInfo.push($soapService.getObjectItem("TaxpayerName",profile.TaxpayerName));
			var TaxpayerAddressInfo = $soapService.getObject("TaxpayerAddressInfo");
				TaxpayerInfo.push(TaxpayerAddressInfo);
				TaxpayerAddressInfo.push($soapService.getObjectItem("HouseNumber",addr.HouseNumber));
				TaxpayerAddressInfo.push($soapService.getObjectItem("MooNumber",addr.MooNumber));
				TaxpayerAddressInfo.push($soapService.getObjectItem("TrokSoiName",addr.TrokSoiName));
				TaxpayerAddressInfo.push($soapService.getObjectItem("StreetName",addr.StreetName));
				TaxpayerAddressInfo.push($soapService.getObjectItem("ThambolName",addr.ThambolName));
				TaxpayerAddressInfo.push($soapService.getObjectItem("AmphurName",addr.AmphurName));
				TaxpayerAddressInfo.push($soapService.getObjectItem("ProvinceName",addr.ProvinceName));
				TaxpayerAddressInfo.push($soapService.getObjectItem("Postcode",addr.Postcode));
				TaxpayerAddressInfo.push($soapService.getObjectItem("TelNumber",addr.TelNumber));

			
				//loop
			var GoodsListInfo = $soapService.getObject("GoodsListInfo");
			SR12011Info.push(GoodsListInfo);
			var Griditems = $scope.gridList;
			
			for(var _i in Griditems){
				
				var item = Griditems[_i];	
				var index = parseInt(_i) + 1;
				var price = item.Goods.GoodsPrice;
				if (item.Goods.PriceFlag == "P") {
					price = item.Goods.DeclarePrice;
				}
				var GoodsValue = item.GoodsCount * price;
				var TaxAmount = item.PriceAmountTax + item.QuantityAmountTax;
				var MoiRate = 10;
				var stempType = 0;
				var GoodsEntryInfo = $soapService.getObject("GoodsEntryInfo");
					GoodsListInfo.push(GoodsEntryInfo);
					GoodsEntryInfo.push($soapService.getObjectItem("ProductCode",item.Goods.ProductTypeCode));
					GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode1",item.Goods.GoodsCode));
					GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode2",item.Goods.GoodsCode));
					GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode3",item.Goods.GoodsCode));
					GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode4",item.Goods.GoodsCode));
					GoodsEntryInfo.push($soapService.getObjectItem("CategoryCode5",item.Goods.GoodsCode));
					GoodsEntryInfo.push($soapService.getObjectItem("UnitCode",item.Goods.UnitCode));
					GoodsEntryInfo.push($soapService.getObjectItem("RateFlag",""));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantity",item.Goods.TaxRateByQuantityAmount));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityNumber",item.col45));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxQuantityPerUnit",item.Goods.RatePerLitre));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxValue",item.Goods.TaxRateByPriceAmount));
					GoodsEntryInfo.push($soapService.getObjectItem("PriceFlag",item.Goods.PriceFlag));
					GoodsEntryInfo.push($soapService.getObjectItem("InformPrice",item.Goods.GoodsPrice));
					GoodsEntryInfo.push($soapService.getObjectItem("DeclarePrice",item.Goods.DeclarePrice));
					GoodsEntryInfo.push($soapService.getObjectItem("UnitPrice", item.Goods.GoodsPrice));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsNum",item.col45));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsValue",GoodsValue));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxAmount",TaxAmount));
					GoodsEntryInfo.push($soapService.getObjectItem("SeqNo", index ));
					GoodsEntryInfo.push($soapService.getObjectItem("ProductTypeDesc",item.Goods.ProductTypeDescriptionText));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsDesc",item.Goods.GoodsDescriptionText));
					GoodsEntryInfo.push($soapService.getObjectItem("Degree",item.Goods.Degree));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsSize",item.Goods.GoodsSize));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsPiece",item.GoodsCount));
					GoodsEntryInfo.push($soapService.getObjectItem("GoodsQuantity",item.col45));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxByValue",item.col51));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantity",item.col52));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityOver",item.col53));
					GoodsEntryInfo.push($soapService.getObjectItem("TaxByQuantityWithOver",item.col54));
					GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByValue",item.PriceAmountTax));
					GoodsEntryInfo.push($soapService.getObjectItem("NetTaxByQuantity",item.QuantityAmountTax));
					
					MoiRate = item.Goods.MunicipalRateAmount;
					stempType = (item.Goods.ProductTypeDescriptionText.trim() == "เบียร์")? 2 : 1;
			}	
				
				//sum
			var TaxDeductionOnBookAmount = "";
			var TaxDeductionOnBookAmount = 0;
			var PaymentFundHealthAmount = $scope.totalTax * 0.02;
			var PaymentFundTVAmount = $scope.totalTax * 0.015;
			var PaymentFundSportAmount =  $scope.totalTax * 0.02;
			var Amount = ($scope.totalTax+$scope.royalTotal).toFixed(2);
			
			var SummaryInfo = $soapService.getObject("SummaryInfo");
			SR12011Info.push(SummaryInfo);
			SummaryInfo.push($soapService.getObjectItem("SumAllTaxByValue",$scope.sumCalcPriceAmountValue));
			SummaryInfo.push($soapService.getObjectItem("SumAllTaxByQuantity",$scope.sumCalcQuantityAmountValue));
			SummaryInfo.push($soapService.getObjectItem("SumAllTax",$scope.totalTax));
			SummaryInfo.push($soapService.getObjectItem("TaxLessType",$scope.totalTax));
			SummaryInfo.push($soapService.getObjectItem("TaxLessFrom",$scope.totalTax));
			SummaryInfo.push($soapService.getObjectItem("TaxLessAmount",$scope.totalTax));
			SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookNo",TaxDeductionOnBookAmount));
			SummaryInfo.push($soapService.getObjectItem("TaxDeductionOnBookAmount",TaxDeductionOnBookAmount));
			SummaryInfo.push($soapService.getObjectItem("PaymentExciseAmount",$scope.totalTax));
			SummaryInfo.push($soapService.getObjectItem("PaymentMunicipalAmount",$scope.royalTotal));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundHealthAmount",PaymentFundHealthAmount.toFixed(2)));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundTVAmount",PaymentFundTVAmount.toFixed(2)));
			SummaryInfo.push($soapService.getObjectItem("PaymentFundSportAmount",PaymentFundSportAmount.toFixed(2)));
			SummaryInfo.push($soapService.getObjectItem("MoiRate",MoiRate));
			SummaryInfo.push($soapService.getObjectItem("PrintType",stempType));
			SummaryInfo.push($soapService.getObjectItem("PaymentExciseAndMunicipalTaxAmount",Amount));
			SummaryInfo.push($soapService.getObjectItem("PaymentOtherAmount",Amount));
			SummaryInfo.push($soapService.getObjectItem("PaymentNetTaxAmount",Amount));
			
			
			
				
			
		
			var str =	EbarcodeSubmitOnlineRequest.getString();
			//writeFile
			var data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + str;
			data = data.replace("EbarcodeSubmitOnlineRequest", "XmlData").replace("/EbarcodeSubmitOnlineRequest", "/XmlData");
			$fileUtils.writeFileGenReport(data);
			console.log(data);
			
			return str;
		
	};

});