/**
 * 
 */
var module = angular.module('profile.view',[]);

module.controller('profile.view.controller', function($scope,$rootScope , $location) {
	console.info("profile.view.controller")
	$scope.profile = {
		"CompanyName" : "บริษัท สุราสรรพสามิต จำกัด",
		"TaxpayerName" : "สุรากลั่น (สุราชุมชน)",
		"LicenseNo" : "60305817500001",
		"LicenseDate" : "20150115-20150115",
		"CompanyId" : "3031163126",
		"Address" : "เมืองตาก  63000 (06-0215411)",
	};
	
	$scope.btnOrderClick = function() {
		$location.path("/view/assignment");
		$rootScope.$broadcast("updateTitle", 1);
	};
	
	
});