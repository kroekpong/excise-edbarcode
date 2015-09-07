/**
 * 
 */
var module = angular.module('history.view', []);

module.controller('history.view.controller', function($scope, $rootScope, $soapService, $mdToast, $animate, $historyService, $profileService) {
	console.info("history.view.controller");
	var profile = $profileService.getProfile();
	$scope.hisList = $historyService.loadLocal(profile);
	$scope.profile = {};
	$scope.profile.licenseNo = profile.LicenseNo;
	$scope.TaxpayerNameDes = profile.TaxpayerNameDes;
	console.log("profile.licenseNo",$scope.profile.licenseNo);
	
	$scope.toStringDate = function(_datemillisec) {
		var d = new Date(_datemillisec);
		return d.toLocaleDateString() + " " + d.toLocaleTimeString();
	};

	$scope.openPdf = function(_pdfName) {
		$historyService.openHisPdfFile(_pdfName);
	};
	
	$scope.openHistory = function(_fileName,_index){
		$historyService.openHistoryText(_fileName,$scope.hisList[_index]);
		$rootScope.$broadcast("gotoMenuIndex",1);
	}

});