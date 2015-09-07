/**
 * 
 */
var module = angular.module('hisDraft.view', []);

module.controller('hisDraft.view.controller', function($scope, $rootScope, $soapService, $mdToast, $animate,$mdDialog, $historyService, $profileService) {
	console.info("hisDraft.view.controller");
	var profile = $profileService.getProfile();
	$scope.hisList = $historyService.loadDraftByCurrentUser(profile).slice(0).reverse();
	$scope.profile = {};
	$scope.profile.licenseNo = profile.LicenseNo;
	$scope.TaxpayerNameDes = profile.TaxpayerNameDes;

	console.log("profile.licenseNo",$scope.profile.licenseNo);
	$scope.toastPosition = {
			bottom : true,
			top : false,
			left : false,
			right : true
		};
	
	
	$scope.toStringDate = function(_datemillisec) {
		var d = new Date(_datemillisec);
		return d.toLocaleDateString() + " " + d.toLocaleTimeString();
	};

	$scope.getToastPosition = function() {
		return Object.keys($scope.toastPosition).filter(function(pos) {
			return $scope.toastPosition[pos];
		}).join(' ');
	};

	$scope.removeItem = function (ev){
		$scope.showConfirm(ev, "ยืนยันการทำรายการ ?", function() {
			var removeids = [];
			for(var _i in $scope.hisList){
				var item = $scope.hisList[_i];
				console.log(_i,item.ischeck);
				if(item.ischeck === true){
					removeids.push(item.id);
				}
			}
			
			if(0 === $historyService.removeDraftById(removeids)){
				$scope.hisList = $historyService.loadDraftByCurrentUser(profile).slice(0).reverse();
				$mdToast.show($mdToast.simple().content("ทำรายการเรียบร้อย").position($scope.getToastPosition()).hideDelay(6000));
			}

		});
	}
	
	$scope.openHistory = function(_index){
		$historyService.openDraftMode($scope.hisList[_index]);
		$rootScope.$broadcast("gotoMenuIndex",1);
	}
	
	$scope.showConfirm = function(ev, _title, _fn) {
		// Appending dialog to document.body to cover sidenav in docs app
		var confirm = $mdDialog.confirm().title('แจ้งเตือน').content(_title).ariaLabel('confirm Delete').ok('ตกลง').cancel('ยกเลิก').targetEvent(ev);
		$mdDialog.show(confirm).then(function() {
			// console.log(confirm);
			_fn();
		}, function() {
			console.log("cancle");
		});
	};

});