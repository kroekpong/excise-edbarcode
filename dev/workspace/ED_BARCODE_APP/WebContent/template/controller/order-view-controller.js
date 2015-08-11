/**
 * 
 */
var module = angular.module('order.view',[]);

module.controller('order.view.controller', function($scope,$rootScope , $location) {
	console.info("order.view.controller")
	$scope.profile = {
		"CompanyName" : "บริษัท สุราสรรพสามิต จำกัด",
		"TaxpayerName" : "สุรากลั่น (สุราชุมชน)",
		"LicenseNo" : "60305817500001",
		"LicenseDate" : "20150115-20150115",
		"CompanyId" : "3031163126",
		"Address" : "เมืองตาก  63000 (06-0215411)",
	};
	
	
});