/**
 * 
 */
var DateDiff = {

	inDays : function(d1, d2) {
		var t2 = d2.getTime();
		var t1 = d1.getTime();

		return parseInt((t2 - t1) / (24 * 3600 * 1000));
	},

	inWeeks : function(d1, d2) {
		var t2 = d2.getTime();
		var t1 = d1.getTime();

		return parseInt((t2 - t1) / (24 * 3600 * 1000 * 7));
	},

	inMonths : function(d1, d2) {
		var d1Y = d1.getFullYear();
		var d2Y = d2.getFullYear();
		var d1M = d1.getMonth();
		var d2M = d2.getMonth();

		return (d2M + 12 * d2Y) - (d1M + 12 * d1Y);
	},

	inYears : function(d1, d2) {
		return d2.getFullYear() - d1.getFullYear();
	}
}

var module = angular.module('updateprogram.view', [ "soap.service" ]);

module.controller('updateprogram.view.controller', function($scope, $rootScope, $soapService, $mdToast, $animate) {
	console.info("updateprogram.view.controller");
	var tempDate = (localStorage["CurrentVersionDate"] == "undefined") ? "" : new Date(parseInt(localStorage["CurrentVersionDate"]));
	$scope.isCurrentVersion = false;
	$scope.WarningText = "กรุณากดปรับปรุงข้อมูลล่าสุดข้อมูลล่าสุด";
	$scope.updateDate = 'ไม่ได้ระบุ';
	$scope.versionPrograme =  localStorage["versionPrograme"];
	
	if (tempDate != "") {
		var dateDiffCount = 0;
		dateDiffCount = DateDiff.inDays(new Date(), tempDate)
		if (dateDiffCount == 0) {
			$scope.isCurrentVersion = true;
			$scope.WarningText = "";
		} else {
			$scope.WarningText =  "ปรับปรุงข้อมูลล่าสุดเมื่อ " + Math.abs(dateDiffCount) + " วันที่แล้ว " + $scope.WarningText;
		}

		$scope.updateDate = tempDate.toLocaleDateString();
	}
	console.log("isCurrentVersion", $scope.isCurrentVersion);

	$scope.toastPosition = {
		bottom : false,
		top : true,
		left : false,
		right : true
	};
	$scope.getToastPosition = function() {
		return Object.keys($scope.toastPosition).filter(function(pos) {
			return $scope.toastPosition[pos];
		}).join(' ');
	};

	$scope.showProgess = false;

	$scope.btnUpdate = function() {
		$scope.showProgess = true;
		var EbarcodeSyncMasterDataRequest = $soapService.getSOAPMessage("EbarcodeSyncMasterDataRequest", "http://www.excise.go.th/xsd/barcode");

		var InternetUser = $soapService.getObject("InternetUser");
		InternetUser.push($soapService.getObjectItem("CompanyId", localStorage["CompanyId"]));
		var pwd = localStorage["CompanyUserPwd"].split("#");
		InternetUser.push($soapService.getObjectItem("CompanyUserId", pwd[0]));
		InternetUser.push($soapService.getObjectItem("CompanyUserPwd", pwd[1]));

		EbarcodeSyncMasterDataRequest.push(InternetUser);

		$soapService.post(EbarcodeSyncMasterDataRequest, localStorage["soaphost"], function(status, xmlDoc, data) {
			$scope.showProgess = false;
			console.log(status,resStatus);
			if (status == 200) {
				var resStatus = xmlDoc.getVal("SyncMasterDataStatus");
				if(resStatus == "OK"){
					$soapService.writeUpdateFile(data);
					localStorage["CurrentVersionDate"] = Date.parse(new Date());
					$mdToast.show($mdToast.simple().content('สำเร็จ').position($scope.getToastPosition()).hideDelay(3000));
					//reload Local Data
					window.location = "loading.html";
				}else{
					$mdToast.show($mdToast.simple().content('ไม่สำเร็จ Error Code ' + status).position($scope.getToastPosition()).hideDelay(8000));
				}
			}else if(status == 0){
				$mdToast.show($mdToast.simple().content("ไม่สามารถเชื่อมต่อ อินเตอร์เน็ตได้").position($scope.getToastPosition()).hideDelay(8000));
			}else{
				$mdToast.show($mdToast.simple().content('ไม่สำเร็จ Error Code ' + status).position($scope.getToastPosition()).hideDelay(8000));
			}
		});

	};

});