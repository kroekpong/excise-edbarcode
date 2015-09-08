/**
 * 
 */
var module = angular.module('loadingApp', [ 'ngMaterial','load.from.file']);

module.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('blue' , {
	    	 'default': '700'
	    })
	    .accentPalette('orange');
});



module.controller('loadingAppCtrl', function($scope, $location , $fileUtils , $convertDataXml) {
	console.info('loadingAppCtrl');

	var xml = $fileUtils.readCurrentProfile();
	$convertDataXml.convertXMLtoLocalStorage(xml);
	
	//versionPrograme
	localStorage["versionPrograme"] = "1.00";
	localStorage["soaphost"] = "http://124.109.26.20:7001/EDBarcodeWeb/ws/EDBarcodeService"; 
//	localStorage["soaphost"] = "http://192.168.3.196:7001/EDBarcodeWeb/ws/EDBarcodeService"; 
	
	localStorage["CurrentVersionDate"] = (localStorage["CurrentVersionDate"] == undefined)? Date.parse(new Date()) : localStorage["CurrentVersionDate"] ;
	
//	goto loading
	setTimeout(function() {
		window.location="index.html";
	}, 2000);

});