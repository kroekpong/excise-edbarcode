/**
 * 
 */
var module = angular.module('indexApp', [ 'ngMaterial', "ui.router" ,"profile.view","order.view" ,"updateprogram.view","history.view"]);

module.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('light-blue' , {
	    	 'default': '600'
	    })
	    .accentPalette('orange');
});

module.config(function($stateProvider, $urlRouterProvider) {
	//
	// For any unmatched url, redirect to /state1
	$urlRouterProvider.otherwise("/view/assignment_ind");
	
	// profile
	$stateProvider.state('profile', {
		url : "/view/assignment_ind",
		templateUrl : "template/profile.view.html",
		controller : "profile.view.controller"
	});
	
	// order
	$stateProvider.state('order', {
		url : "/view/assignment",
		templateUrl : "template/order.view.html",
		controller : "order.view.controller"
	});
	// update
	$stateProvider.state('update', {
		url : "/view/inbox",
		templateUrl : "template/updateprogram.view.html",
		controller : "updateprogram.view.controller"
	});
	// history
	$stateProvider.state('history', {
		url : "/view/history",
		templateUrl : "template/history.view.html",
		controller : "history.view.controller"
	});
});

module.controller('AppCtrl', function($scope,$rootScope, $mdSidenav, $mdUtil, $location) {

	console.log('AppCtrl');
	$scope.userLogin = localStorage["CompanyUserPwd"].split("#")[0];

	function buildToggler(navID) {
		var debounceFn = $mdUtil.debounce(function() {
			$mdSidenav(navID).toggle().then(function() {
				console.info("toggle " + navID + " is done");
			});
		}, 200);
		return debounceFn;
	}

	$scope.toggleLeft = buildToggler('left');
	$scope.settings = [ {
		"iconName" : "assignment_ind",
		"label" : "หน้าแรก"
	}, {
		"iconName" : "assignment",
		"label" : "สร้าง สร. 120-11"
	}, {
		"iconName" : "history",
		"label" : "ประวัติการทำรายการ"
	}, {
		"iconName" : "inbox",
		"label" : "อัพเดทโปรแกรม"
	}
//	,{
//		"iconName" : "settings",
//		"label" : "ตั้งค่า"
//	}, {
//		"iconName" : "notifications",
//		"label" : "เกียวกับโปรแกรม"
//	} 
	];

	$scope.toolbarTitle = "หน้าแรก ED Barcode";

	$scope.navigateTo = function(_iconName, _event) {
		$rootScope.$broadcast("checkSaveDraff");
		$scope.toolbarTitle = _iconName.label;
//		console.info(_iconName);
		$location.path("/view/" + _iconName.iconName);
		$mdSidenav('left').close();
	};
	
	
	$scope.$on("gotoMenuIndex", function(event, args) {
		$scope.toolbarTitle = $scope.settings[args].label;
		$location.path("/view/" + $scope.settings[args].iconName);
	});
	
	$scope.$on("updateTitle", function(event, args) {
		$scope.toolbarTitle = $scope.settings[args].label;
	});
	
	$scope.$on("updateTitleText", function(event, args) {
		$scope.toolbarTitle = args;
	});

});