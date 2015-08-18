/**
 * 
 */
var module = angular.module('history.view', [ ]);

module.controller('history.view.controller', function($scope, $rootScope, $soapService, $mdToast, $animate,$historyService,$profileService) {
	console.info("history.view.controller");
	
	$scope.hisList = $historyService.loadLocal($profileService.getProfile());

	$scope.toStringDate = function (_datemillisec){
		var d  = new Date(_datemillisec);
		return d.toLocaleDateString() + " " + d.toLocaleTimeString();
	};
	
});