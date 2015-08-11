/**
 * 
 */
var module = angular.module('indexApp', [ 'ngMaterial', "ui.router" ,"profile.view","order.view" ]);


module.config(function($stateProvider, $urlRouterProvider) {
	//
	// For any unmatched url, redirect to /state1
//	$urlRouterProvider.otherwise("/view/assignment_ind");
	//
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
});

module.controller('AppCtrl', function($scope, $mdSidenav, $mdUtil, $location) {

	console.log('AppCtrl');

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
		"label" : "ข้อมูลโดยรวม"
	}, {
		"iconName" : "assignment",
		"label" : "สร้าง สร. 120-11"
	}, {
		"iconName" : "history",
		"label" : "ประวัติการทำรายการ"
	}, {
		"iconName" : "inbox",
		"label" : "อัพเดทโปรแกรม"
	}, {
		"iconName" : "settings",
		"label" : "ตั้งค่า"
	}, {
		"iconName" : "notifications",
		"label" : "เกียวกับโปรแกรม"
	} ];

	$scope.toolbarTitle = "หน้าแรก ED Barcode";

	$scope.navigateTo = function(_iconName, _event) {
		$scope.toolbarTitle = _iconName.label;
//		console.info(_iconName);
		$location.path("/view/" + _iconName.iconName);
		$mdSidenav('left').close();
	};

	$scope.$on("updateTitle", function(event, args) {
		$scope.toolbarTitle = $scope.settings[args].label;
	});

});