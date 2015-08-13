/**
 * 
 */
var module = angular.module('updateprogram.view', [ "soap.service" ]);

module.controller('updateprogram.view.controller', function($scope, $rootScope, $soapService, $mdToast, $animate) {
	console.info("updateprogram.view.controller")
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

	$scope.updateDate = new Date().toDateString();
	$scope.version = "1.0012";
	$scope.showProgess = false;

	$scope.btnUpdate = function() {
		$scope.showProgess = true;
		var EbarcodeSyncMasterDataRequest = $soapService.getSOAPMessage("EbarcodeSyncMasterDataRequest", "http://www.excise.go.th/xsd/barcode");

		var InternetUser = $soapService.getObject("InternetUser");
		InternetUser.push($soapService.getObjectItem("CompanyId", "3031163126"));
		InternetUser.push($soapService.getObjectItem("CompanyUserId", "bac"));
		InternetUser.push($soapService.getObjectItem("CompanyUserPwd", "bac"));

		EbarcodeSyncMasterDataRequest.push(InternetUser);

		$soapService.post(EbarcodeSyncMasterDataRequest, "http://124.109.26.20:7001/EDBarcodeWeb/ws/EDBarcodeService", function(status, xmlDoc, data) {
			$scope.showProgess = false;
			$mdToast.show($mdToast.simple().content('สำเร็จ').position($scope.getToastPosition()).hideDelay(3000));
		});

	};

});