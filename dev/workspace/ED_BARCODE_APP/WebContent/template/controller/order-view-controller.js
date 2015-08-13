/**
 * 
 */
var module = angular.module('order.view',['load.from.file']);

var GridItem = function(){
	this.Goods = {};
	this.checkbox = false;
	this.GoodsCount = 1;
	
};



module.controller('order.view.controller', function($scope,$rootScope , $location,$productService) {
	console.info("order.view.controller")
	
	$scope.topProduct = $productService.getTopProduct();
	$scope.searchProduct =  $productService.getTopProduct();
	$scope.search = {};
	$scope.showDataSearch = $scope.searchProduct.length > 0;
	$scope.gridList = [];
	
	$scope.submitType = "offline";
	$scope.step = [false,true,false,false,false,false,];
	$scope.stepCount = 1;
	
	
	var gl = new GridItem();
		gl.Goods = $scope.topProduct[0];
	$scope.gridList.push(gl);
	gl = new GridItem();
	gl.Goods = $scope.topProduct[1];
	$scope.gridList.push(gl);
	
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
	
	
	$scope.toStringDec = function( _number ){
		return _number.toFixed(4);
	}
	
	$scope.navigaTor = function (_index){
		$scope.step = [false,false,false,false,false,false,];
		$scope.step[_index] = true;
		$scope.stepCount = _index;
		console.info("navigaTor" , _index);
	};
	
	
	
	
	
	
	
	
	
});