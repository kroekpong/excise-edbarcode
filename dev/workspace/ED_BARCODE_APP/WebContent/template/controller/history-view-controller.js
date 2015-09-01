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
	console.log("profile.licenseNo",$scope.profile.licenseNo);
	
	$scope.toStringDate = function(_datemillisec) {
		var d = new Date(_datemillisec);
		return d.toLocaleDateString() + " " + d.toLocaleTimeString();
	};

	$scope.openPdf = function(_pdfName) {
		$historyService.openHisPdfFile(_pdfName);
	};
	
	$scope.openHistory = function(_fileName){
		$historyService.openHistoryText(_fileName);
		$rootScope.$broadcast("gotoMenuIndex",1);
	}

});