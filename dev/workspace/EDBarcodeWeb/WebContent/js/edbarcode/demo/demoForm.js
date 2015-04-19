var count = 1;
var pageSumTaxByValue = 0;
var pageSumTaxByCapacity = 0;

$(document).ready(function(e){
    $("#barcode").keypress(function(e) {
		if (e.which == 13) {
			fetchData();
			$("#barcode").val('');
		}
    });
});

//function getScanType() {
//	var scanType = $("input[type='radio'][name='scanType']:checked").val();
//	return scanType;
//}

function getFlag(data) {
	var flag;
	var tmp = data.split('|');
	if (tmp.length == 1) {
		flag = 'R';
	} else {
		flag = tmp[0];
	}
	return flag;
}

function fetchData() {
	var barcode = $("#barcode").val();
	//console.log('scanValue: ' + barcode);
	var dataArray = barcode.split('||');
	for (var i = 0; i < dataArray.length; i++) {
		var flag = getFlag(dataArray[i]);
		//console.log('flag: ' + flag);
		if ('H' == flag) {
			fetchDataEntrepreneur(dataArray[i]);
		} else if ('D' == flag) {
			fetchDataProduct(dataArray[i]);
		} else if ('S' == flag) {
			fetchDataTaxSummary(dataArray[i]);
		} else if ('R' == flag) {
			fetchDataSumbitOnline(dataArray[i]);
		}
	}
}

//function clearData() {
//	var scanType = getScanType();
//	if ('scanType1' == scanType) {
//		clearDataEntrepreneur();
//	} else if ('scanType3' == scanType) {
//		clearDataTaxSummary();
//	} else if ('scanType4' == scanType) {
//		clearDataEntrepreneur();
//		clearDataTaxSummary();
//	}
//}

function fetchDataEntrepreneur(entrepreneurStr) {
	var header = entrepreneurStr.split('|');
	var param = {
		'licenseNo': header[1]
	};
	$.ajax({
		url: contextPath + "/json/getEntrepreneurInfo",
		type: 'GET',
		data: param,
		async: false,
		cache: false,
		success: function(result, textStatus, jqXHR) {
//			console.log(result);
//			console.log(textStatus);
//			console.log(jqXHR);
			fillDataEntrepreneur(result)
		},
		error: function(jqXHR, textStatus, errorThrown) {
//			console.log(jqXHR);
//			console.log(textStatus);
//			console.log(errorThrown);
		}
	});
}

function fillDataEntrepreneur(ent) {
	$('#licenseNo').val(ent.licenseNo);
	$('#licenseAllowedName').val(ent.licenseAllowedName);
	$('#factoryName').val(ent.factoryName);
	$('#licenseStartDate').val(ent.licenseStartDate);
	$('#licenseEndDate').val(ent.licenseEndDate);
	$('#taxNo').val(ent.taxNo);
	$('#factoryAddress').val(ent.factoryAddress);
}

function clearDataEntrepreneur() {
	$('#licenseNo').val('');
	$('#licenseAllowedName').val('');
	$('#factoryName').val('');
	$('#licenseStartDate').val('');
	$('#licenseEndDate').val('');
	$('#taxNo').val('');
	$('#factoryAddress').val('');
}

function fetchDataTaxSummary(taxSummaryStr) {
	var summarys = taxSummaryStr.split('|');
	var taxSummary = {
		sumTaxByValue: summarys[1],
		sumTaxByCapacity: summarys[2],
		//receipt: summarys[2],
		//reduceTaxProductBaht: summarys[3],
		//reduceTaxByDepBookNoBaht: summarys[4],
		taxByMOI: summarys[3],
		taxByThaiHealth: summarys[4],
		taxByThaiPBS: summarys[5],
		taxByNSDF: summarys[6],
		other: summarys[7]
	};
	fillDataTaxSummary(taxSummary);
}

function fillDataTaxSummary(taxSummary) {
	var sumTaxAlcohol = parseFloat(taxSummary.sumTaxByValue) + parseFloat(taxSummary.sumTaxByCapacity);
	$('#sumTaxProduct1').val(numeral(sumTaxAlcohol).format('0,0.00'));
	$('#sumTaxProduct2').val(numeral(taxSummary.taxByMOI).format('0,0.00'));
	$('#sumTaxProduct3').val(numeral(taxSummary.taxByThaiHealth).format('0,0.00'));
	$('#sumTaxProduct4').val(numeral(taxSummary.taxByThaiPBS).format('0,0.00'));
	$('#sumTaxProduct5').val(numeral(taxSummary.taxByNSDF).format('0,0.00'));
	//$('#reduceTaxProductBaht').val(numeral(0).format('0,0.00'));
	$('#reduceTaxProductBaht').val('');
	$('#receipt').val('');
	$('#remain1').val(numeral(sumTaxAlcohol).format('0,0.00'));
	$('#remain2').val(numeral(taxSummary.taxByMOI).format('0,0.00'));
	$('#remain3').val(numeral(taxSummary.taxByThaiHealth).format('0,0.00'));
	$('#remain4').val(numeral(taxSummary.taxByThaiPBS).format('0,0.00'));
	$('#remain5').val(numeral(taxSummary.taxByNSDF).format('0,0.00'));
	$('#reduceTaxByDepBookNoBaht1').val(numeral(0).format('0,0.00'));
	$('#reduceTaxByDepBookNoBaht2').val(numeral(0).format('0,0.00'));
	$('#reduceTaxByDepBookNoBaht3').val(numeral(0).format('0,0.00'));
	$('#reduceTaxByDepBookNoBaht4').val(numeral(0).format('0,0.00'));
	$('#reduceTaxByDepBookNoBaht5').val(numeral(0).format('0,0.00'));
	$('#sumAllAfterReduce1').val(numeral(sumTaxAlcohol).format('0,0.00'));
	$('#sumAllAfterReduce2').val(numeral(taxSummary.taxByMOI).format('0,0.00'));
	$('#other').val(numeral(taxSummary.other).format('0,0.00'));
	$('#sumFinal2').val(numeral(taxSummary.taxByMOI).format('0,0.00'));
	$('#sumFinal3').val(numeral(taxSummary.taxByThaiHealth).format('0,0.00'));
	$('#sumFinal4').val(numeral(taxSummary.taxByThaiPBS).format('0,0.00'));
	$('#sumFinal5').val(numeral(taxSummary.taxByNSDF).format('0,0.00'));
}

function clearDataTaxSummary() {
	$('#sumTaxProduct1').val('');
	$('#sumTaxProduct2').val('');
	$('#sumTaxProduct3').val('');
	$('#sumTaxProduct4').val('');
	$('#sumTaxProduct5').val('');
	$('#reduceTaxProductBaht').val('');
	$('#receipt').val('');
	$('#remain1').val('');
	$('#remain2').val('');
	$('#remain3').val('');
	$('#remain4').val('');
	$('#remain5').val('');
	$('#reduceTaxByDepBookNoBaht1').val('');
	$('#reduceTaxByDepBookNoBaht2').val('');
	$('#reduceTaxByDepBookNoBaht3').val('');
	$('#reduceTaxByDepBookNoBaht4').val('');
	$('#reduceTaxByDepBookNoBaht5').val('');
	$('#sumAllAfterReduce1').val('');
	$('#sumAllAfterReduce2').val('');
	$('#other').val('');
	$('#sumFinal2').val('');
	$('#sumFinal3').val('');
	$('#sumFinal4').val('');
	$('#sumFinal5').val('');
}

function fetchDataProduct(productStr) {
	var products = productStr.split('|');
	console.log(products);
	
	var alcohol = {
		'productCode': products[1],
		'piece': products[2],
		'sellingPriceByOwner': products[3],
		'sellingPriceByDepartment': products[4],
		'taxByValue' : products[5],
		'taxByCapacity' : products[6],
		'taxByValuePlus' : products[7]
	};
	$.ajax({
		url: contextPath + "/json/getProductInfo",
		type: 'GET',
		data: alcohol,
		async: false,
		cache: false,
		success: function(result, textStatus, jqXHR) {
//			console.log(result);
//			console.log(textStatus);
//			console.log(jqXHR);
//			console.log(alcohol);
			
			alcohol.productGroup = result.productGroup;
			alcohol.productName = result.productName;
			alcohol.degree = result.degree;
			alcohol.size = result.size;
			
			fillDataAlcoholList(alcohol);
		},
		error: function(jqXHR, textStatus, errorThrown) {
//			console.log(jqXHR);
//			console.log(textStatus);
//			console.log(errorThrown);
		}
	});
}

function fillDataAlcoholList(alcohol) {
	$('#alcoholTable > tbody:last').append($('<tr>').attr('id', count));
	var recordNo = $("#alcoholTable > tbody > tr").length - 3;
	$('#alcoholTable > tbody:last > tr:last').append(
		$('<td>')
			.append(recordNo)
			.append($('<img>')
				.attr('src', contextPath + '/images/red_x.png')
				.attr('onclick', 'removeDataAlcoholList(' + count + ')')
            )
	);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.productGroup));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.productName));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.degree).format('0.00')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.size).format('0.000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.piece).format('0,0')));
	var capacityTax = parseFloat(alcohol.size) * parseFloat(alcohol.piece);
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(capacityTax).format('0,0.0000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.sellingPriceByOwner).format('0,0.0000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.sellingPriceByDepartment).format('0,0.0000')));
	var value = parseFloat(alcohol.piece) * parseFloat(alcohol.sellingPriceByOwner);
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(value).format('0,0.0000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.taxByValue).format('0,0.0000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.taxByCapacity).format('0,0.0000')));
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(alcohol.taxByValuePlus).format('0,0.0000')));
	var sumTaxByCapacity = parseFloat(alcohol.taxByCapacity) + parseFloat(alcohol.taxByValuePlus);
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(sumTaxByCapacity).format('0,0.0000')));
	var sumAllByValue = parseFloat(alcohol.piece) * parseFloat(alcohol.taxByValue);
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(sumAllByValue).format('0,0.00')));
	var sumAllByCapacity = parseFloat(alcohol.piece) * parseFloat(sumTaxByCapacity);
	$('#alcoholTable > tbody:last > tr:last').append($('<td align=\'right\'>').append(numeral(sumAllByCapacity).format('0,0.00')));
	$('#alcoholTable > tbody:last > tr:last').append(
		$('<td>')
			.append($('<input>')
				.attr('type', 'text')
				.attr('size', '2')
            )
	);
	count++;
	
	pageSumTaxByValue += sumAllByValue;
	pageSumTaxByCapacity += sumAllByCapacity;
	$("#pageSumTaxByValue").val(numeral(pageSumTaxByValue).format('0,0.00'));
	$("#pageSumTaxByCapacity").val(numeral(pageSumTaxByCapacity).format('0,0.00'));
}

function removeDataAlcoholList(trId) {
	if (confirm('ต้องการลบรายการนี้ ?')) {
		$('#alcoholTable > tbody:last > tr#' + trId).remove();
	}
}

function fetchDataSumbitOnline(referenceCode) {
	var param = {
		'referenceCode': referenceCode
	};
	$.ajax({
		url: contextPath + "/json/getDataFromRefCode",
		type: 'GET',
		data: param,
		async: false,
		cache: false,
		success: function(result, textStatus, jqXHR) {
			fillDataSubmitOnline(result)
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
}

function fillDataSubmitOnline(taxDoc) {
	fillDataEntrepreneur(taxDoc.entrepreneur);
	for (var i = 0; i < taxDoc.procudeList.length; i++) {
		fillDataAlcoholList(taxDoc.procudeList[i]);
	}
	fillDataTaxSummary(taxDoc.summary);
}