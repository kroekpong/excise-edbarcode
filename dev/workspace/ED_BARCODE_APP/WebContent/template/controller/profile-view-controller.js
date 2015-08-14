/**
 * 
 */
var module = angular.module('profile.view',[]);

module.controller('profile.view.controller', function($scope,$rootScope , $location ,$profileService) {
	console.info("profile.view.controller")
	$scope.profile = $profileService.getProfile();
	
	$scope.btnOrderClick = function() {
		$location.path("/view/assignment");
		$rootScope.$broadcast("updateTitle", 1);
	};
	
	
});