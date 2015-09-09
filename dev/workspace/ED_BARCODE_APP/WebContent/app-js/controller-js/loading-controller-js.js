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
	localStorage["versionPrograme"] = $fileUtils.readVersion("version.dat", "1.00B");
	
	localStorage["soaphost"] = "http://124.109.26.20:7001/EDBarcodeWeb/ws/EDBarcodeService"; 
//	localStorage["soaphost"] = "http://192.168.3.196:7001/EDBarcodeWeb/ws/EDBarcodeService"; 
	
	localStorage["CurrentVersionDate"] = (localStorage["CurrentVersionDate"] == undefined)? Date.parse(new Date()) : localStorage["CurrentVersionDate"] ;
	

		// createFilter
	var goodList = JSON.parse(localStorage["GoodsList"]);
	var bandNameSet = new Set();
	var degreeSet = new Set();
	for ( var _i in goodList) {
		var item = goodList[_i];
		bandNameSet.add(item.BrandName);
		degreeSet.add(item.Degree);
	}

	var array1 = [];
	bandNameSet.forEach(function(value) {
		array1.push(value);
	});
	var array2 = [];
	degreeSet.forEach(function(value) {
		array2.push(value);
	});

	// console.log(bandNameSet,degreeSet);
	localStorage["FilterGoods"] = JSON.stringify({
		"BrandName" : array1,
		"Degree" : array2
	});

//	goto loading
	setTimeout(function() {
		window.location="index.html";
	}, 2000);

});