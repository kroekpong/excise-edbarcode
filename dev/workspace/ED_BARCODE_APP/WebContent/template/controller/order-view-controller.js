/**
 * 
 */
var module = angular.module('order.view', [ 'load.from.file' ]);

var GridItem = function() {
	this.Goods = {};
	this.checkbox = false;
	this.GoodsCount = 1;

};

module.controller('order.view.controller', function($scope, $rootScope, $location, $productService,$mdDialog) {
	console.info("order.view.controller")

	$scope.topProduct = $productService.getTopProduct();
	$scope.searchProduct = $productService.getAllProduct();
	$scope.showDataSearch = $scope.searchProduct.length > 0;
	$scope.gridList = [];
	var productCheck = [];
	var tempSelect = [];

	$scope.submitType = "offline";
	$scope.step = [ false, true, false, false, false, false, ];
	$scope.stepCount = 1;

	// getsearch
	$scope.toStringDec = function(_number) {
		return _number.toFixed(4);
	}

	$scope.navigaTor = function(_index) {
		$scope.step = [ false, false, false, false, false, false, ];
		$scope.step[_index] = true;
		$scope.stepCount = _index;
		console.info("navigaTor", _index);

		if (_index == 0) {
			productCheck = [];
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

	$scope.resetProductChecked = function(_goodId) {
		tempSelect = productCheck = [];
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
	
	$scope.onDelete = function (ev){
		$scope.showConfirm(ev,"ยืนยันการทำรายการ ?",function(){
			var del = [];
			for(var _i in $scope.gridList ){
				var item = $scope.gridList [_i];
				if(item.checkbox){
					del.push(item);
				}
			}
			
			for(var _i in del){
				var findex= $scope.gridList.indexOf(del[_i]);
				$scope.gridList.splice(findex, 1);
			}
			
		});
	};
	
	$scope.showConfirm = function(ev,_title,_fn) {
		    // Appending dialog to document.body to cover sidenav in docs app
		    var confirm = $mdDialog.confirm()
		          .title('แจ้งเตือน')
		          .content(_title)
		          .ariaLabel('confirm Delete')
		          .ok('ตกลง')
		          .cancel('ยกเลิก')
		          .targetEvent(ev);
		    $mdDialog.show(confirm).then(function() {
//		      console.log(confirm);
		      _fn();
		    }, function() {
		    	console.log("cancle");
		    });
		  };

});