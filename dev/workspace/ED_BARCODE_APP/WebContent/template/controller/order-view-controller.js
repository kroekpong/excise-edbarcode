/**
 * 
 */
var module = angular.module('order.view',['load.from.file']);

module.controller('order.view.controller', function($scope,$rootScope , $location,$productService) {
	console.info("order.view.controller")
	
	$scope.topProduct = $productService.getTopProduct();
	$scope.search = {};
	$scope.search.GoodsDescriptionTextList = [{
		svalue : "002",
		slabel : "สิงห์ OLD 0.630 4.9000"
	},
	{
		svalue : "003",
		slabel : "เบียร์ไฮเนเก้น(ขวด) 0.640 0.630 4.5000"
	}
	];
	
	
	$scope.search.GoodsSizeList = [{
		svalue : "001",
		slabel : "4.9"
	},
	{
		svalue : "002",
		slabel : "4.5"
	}
	];
	
	
});