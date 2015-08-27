/**
 * 
 */
var module = angular.module('profile.view', []);
module.service('$localProfileService', function() {
	console.info("$localProfileService install");
	this.factoryList = JSON.parse(localStorage["GoodsListbyFactorySize"]);
	this.profile = "";

});

module.controller('profile.view.controller', function($scope, $rootScope, $mdDialog, $location, $profileService, $localProfileService) {
	console.info("profile.view.controller");

	$scope.profile = $localProfileService.profile = $profileService.getProfile();
	$scope.factoryList = $localProfileService.factoryList;

	$scope.btnOrderClick = function() {
		$location.path("/view/assignment");
		$rootScope.$broadcast("updateTitle", 1);
	};
	
	$scope.quickMenuClick = function(_index) {
		$rootScope.$broadcast("gotoMenuIndex", _index);
	};

	$scope.showAdvanced = function(ev) {
		$mdDialog.show({
			controller : DialogController,
			templateUrl : 'template/factory.dialog.view.html',
			parent : angular.element(document.body),
			targetEvent : ev,
			clickOutsideToClose : false
		}).then(function(answer) {
			// $scope.status = 'You said the information was "' + answer + '".';
		}, function() {
			// $scope.status = 'You cancelled the dialog.';
		});
	};

	function DialogController($scope, $mdDialog, $localProfileService) {
		console.log("DialogController");
		$scope.factoryList = $localProfileService.factoryList;
		$scope.LicenseNo = $localProfileService.profile.LicenseNo;

		$scope.hide = function() {
			$mdDialog.hide();
		};
		$scope.cancel = function() {
			$mdDialog.cancel();
		};
		$scope.answer = function(answer) {
			if (answer == "OK") {
				localStorage["LicenseNo"] = $scope.LicenseNo;
				console.log("localStorage[\"LicenseNo\"] ", localStorage["LicenseNo"]);
				
//				goto loading
				setTimeout(function() {
					window.location="loading.html";
				}, 1000);
			}
			$mdDialog.hide(answer);
		};

		$scope.getCheck = function(_LicenseNo) {
			return _LicenseNo == $scope.LicenseNo;
		};

		$scope.checkoneonly = function(_LicenseNo) {
			// console.log(_LicenseNo);
			$scope.LicenseNo = _LicenseNo;
		};

	}

});