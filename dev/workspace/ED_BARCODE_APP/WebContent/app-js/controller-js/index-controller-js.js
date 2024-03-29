/**
 * 
 */
var module = angular.module('indexApp', [ 'ngMaterial', "ui.router" ,"profile.view","order.view" ,"updateprogram.view","history.view","hisDraft.view"]);

module.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('blue-grey' , {
	    	 'default': '400'
	    })
	    .accentPalette('orange');
});

module.directive("datepicker",function(){
	console.log("datepicker");
	  return {
	    restrict:"A",
	    link:function(scope,el,attr){
			      el.datepicker({
			    	  minDate: 0,
			    	  dateFormat : 'dd/mm/yy'
			      });
	    }
	  };
});

module.directive("inputnumberonly",function(){
	console.log("inputnumberonly");
	return {
		restrict:"A",
		link:function(scope,element,attr){
			
			element.on('keypress', function(event) {
//				console.log("event",event);
//				console.log("selector",this.value);
				
				var key = event.which || event.keyCode; 
//				console.log("keypress",key);
				var isNumber = false;
				if(key >=48 && key <=57){
					isNumber = true;
				}
//				if(key >=96 && key <=105){
//					isNumber = true;
//				}
				if(key == 46){
					var haveDot = (this.value.indexOf(".") > 0);
					if(!haveDot)
						isNumber = true;
				}
				if(!isNumber){
					event.preventDefault();
				}
		      });
		}
	};
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
	// hisDraft
	$stateProvider.state('hisDraft', {
		url : "/view/notifications",
		templateUrl : "template/hisDraft.view.html",
		controller : "hisDraft.view.controller"
	});
});

module.controller('AppCtrl', function($scope,$rootScope, $mdSidenav, $mdUtil, $location , $state) {

	console.log('AppCtrl');
	$scope.userLogin = localStorage["CompanyUserPwd"].split("#")[0];
	$scope.updateProgramDate = (localStorage["CurrentVersionDate"] == "undefined") ? "" : new Date(parseInt(localStorage["CurrentVersionDate"])).toLocaleDateString();
	
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
		"label" : "สร้าง สร. ๑๒๐-๑๑"
	}, {
		"iconName" : "history",
		"label" : "ประวัติการทำรายการ"
	}, {
		"iconName" : "inbox",
		"label" : "ปรับปรุงข้อมูลสินค้าและอัตราภาษี"
	}, {
		"iconName" : "notifications",
		"label" : "ฉบับร่าง"
	} 
//	,{
//		"iconName" : "settings",
//		"label" : "ตั้งค่า"
//	}, {
//		"iconName" : "notifications",
//		"label" : "เกียวกับโปรแกรม"
//	} 
	];

	$scope.toolbarTitle = $scope.settings[0].label;
	
	$scope.versionPrograme =  localStorage["versionPrograme"];

	$scope.navigateTo = function(_iconName, _event) {
		$rootScope.$broadcast("checkSaveDraff");
		$scope.toolbarTitle = _iconName.label;
//		console.info(_iconName);
		$location.path("/view/" + _iconName.iconName);
		$mdSidenav('left').close();
	};
	
	$scope.gotoHome = function (){
		$scope.$broadcast("gotoMenuIndex",0);
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

	/**
	 * Name when refresh
	 * */
//	console.info("$state.current.name", $location.path());
		for(var _i in $scope.settings){
			var menu = $scope.settings[_i];
			if($location.path().indexOf( menu.iconName) > -1){
				console.log("f index ",menu.label);
				$scope.toolbarTitle = menu.label;
				break;
			}
		}
});












