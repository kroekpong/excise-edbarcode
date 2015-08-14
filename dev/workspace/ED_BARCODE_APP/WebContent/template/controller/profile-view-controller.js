/**
 * 
 */
var module = angular.module('profile.view', []);
module.service('$localProfileService', function() {
	console.info("$localProfileService install");
	this.factoryList = JSON.parse(localStorage["GoodsListbyFactorySize"]);

});

module.controller('profile.view.controller', function($scope, $rootScope, $mdDialog, $location, $profileService, $localProfileService) {
	console.info("profile.view.controller")
	$scope.profile = $profileService.getProfile();
	$scope.factoryList = $localProfileService.factoryList;

	$scope.btnOrderClick = function() {
		$location.path("/view/assignment");
		$rootScope.$broadcast("updateTitle", 1);
	};

	$scope.showAdvanced = function(ev) {
		$mdDialog.show({
			controller : DialogController,
			templateUrl : 'template/factory.dialog.view.html',
			parent : angular.element(document.body),
			targetEvent : ev,
			clickOutsideToClose : false
		}).then(function(answer) {
			$scope.status = 'You said the information was "' + answer + '".';
		}, function() {
			$scope.status = 'You cancelled the dialog.';
		});
	};

	function DialogController($scope, $mdDialog, $localProfileService) {
		console.log("DialogController");
		$scope.factoryList = $localProfileService.factoryList;

		$scope.hide = function() {
			$mdDialog.hide();
		};
		$scope.cancel = function() {
			$mdDialog.cancel();
		};
		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};

	}

});