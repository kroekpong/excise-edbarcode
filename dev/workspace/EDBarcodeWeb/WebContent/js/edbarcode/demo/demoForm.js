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

function getScanType() {
	var scanType = $("input[type='radio'][name='scanType']:checked").val();
	return scanType;
}

function fetchData() {
	var scanType = getScanType();
	console.log('scanType: ' + scanType);
	console.log('scanValue: ' + $("#barcode").val());
	if ('scanType1' == scanType) {
		fetchDataEntrepreneur($("#barcode").val());
	} else if ('scanType2' == scanType) {
		fetchDataProduct($("#barcode").val());
	} else if ('scanType3' == scanType) {
		fetchDataTaxSummary($("#barcode").val());
	} else if ('scanType4' == scanType) {
		fetchDataSumbitOnline($("#barcode").val());
	}
}

function clearData() {
	var scanType = getScanType();
	if ('scanType1' == scanType) {
		clearDataEntrepreneur();
	} else if ('scanType3' == scanType) {
		clearDataTaxSummary();
	} else if ('scanType4' == scanType) {
		clearDataEntrepreneur();
		clearDataTaxSummary();
	}
}

function fetchDataEntrepreneur(licenseNo) {
	var param = {
		'licenseNo': licenseNo
	};
	$.ajax({
		url: contextPath + "/json/getEntrepreneurInfo",
		type: 'GET',
		data: param,
		async: false,
		cache: false,
		success: function(result, textStatus, jqXHR) {
			console.log(result);
			console.log(textStatus);
			console.log(jqXHR);
			fillDataEntrepreneur(result)
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
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
		sumTaxByValue: summarys[0],
		sumTaxByCapacity: summarys[1],
		receipt: summarys[2],
		reduceTaxProductBaht: summarys[3],
		reduceTaxByDepBookNoBaht: summarys[4],
		taxByMOI: summarys[5],
		taxByThaiHealth: summarys[6],
		taxByThaiPBS: summarys[7],
		taxByNSDF: summarys[8]
	};
	fillDataTaxSummary(taxSummary);
}

function fillDataTaxSummary(taxSummary) {
	var sumTaxAlcohol = parseFloat(taxSummary.sumTaxByValue) + parseFloat(taxSummary.sumTaxByCapacity);
	$('#sumTaxProduct1').val(sumTaxAlcohol);
	$('#sumTaxProduct2').val(taxSummary.taxByMOI);
	$('#sumTaxProduct3').val(taxSummary.taxByThaiHealth);
	$('#sumTaxProduct4').val(taxSummary.taxByThaiPBS);
	$('#sumTaxProduct5').val(taxSummary.taxByNSDF);
	$('#reduceTaxProductBaht').val(0);
	$('#receipt').val('');
	$('#remain1').val(sumTaxAlcohol);
	$('#remain2').val(taxSummary.taxByMOI);
	$('#remain3').val(taxSummary.taxByThaiHealth);
	$('#remain4').val(taxSummary.taxByThaiPBS);
	$('#remain5').val(taxSummary.taxByNSDF);
	$('#reduceTaxByDepBookNoBaht1').val(0);
	$('#reduceTaxByDepBookNoBaht2').val(0);
	$('#reduceTaxByDepBookNoBaht3').val(0);
	$('#reduceTaxByDepBookNoBaht4').val(0);
	$('#reduceTaxByDepBookNoBaht5').val(0);
	$('#sumAllAfterReduce1').val(sumTaxAlcohol);
	$('#sumAllAfterReduce2').val(taxSummary.taxByMOI);
	$('#other').val(0);
	$('#sumFinal2').val(taxSummary.taxByMOI);
	$('#sumFinal3').val(taxSummary.taxByThaiHealth);
	$('#sumFinal4').val(taxSummary.taxByThaiPBS);
	$('#sumFinal5').val(taxSummary.taxByNSDF);
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
		'productCode': products[0],
		'piece': products[1],
		'sellingPriceByOwner': products[2],
		'sellingPriceByDepartment': products[3],
		'taxByValue' : products[4],
		'taxByCapacity' : products[5],
		'taxByValuePlus' : products[6]
	};
	$.ajax({
		url: contextPath + "/json/getProductInfo",
		type: 'GET',
		data: alcohol,
		async: false,
		cache: false,
		success: function(result, textStatus, jqXHR) {
			console.log(result);
			console.log(textStatus);
			console.log(jqXHR);
			console.log(alcohol);
			
			alcohol.productGroup = result.productGroup;
			alcohol.productName = result.productName;
			alcohol.degree = result.degree;
			alcohol.size = result.size;
			
			fillDataAlcoholList(alcohol);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
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
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.degree));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.size));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.piece));
	var capacityTax = parseFloat(alcohol.size) * parseFloat(alcohol.piece);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(capacityTax));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.sellingPriceByOwner));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.sellingPriceByDepartment));
	var value = parseFloat(alcohol.piece) * parseFloat(alcohol.sellingPriceByOwner);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(value));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.taxByValue));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.taxByCapacity));
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.taxByValuePlus));
	var sumTaxByCapacity = parseFloat(alcohol.taxByCapacity) + parseFloat(alcohol.taxByValuePlus);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(sumTaxByCapacity));
	var sumAllByValue = parseFloat(alcohol.piece) * parseFloat(alcohol.taxByValue);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(sumAllByValue));
	var sumAllByCapacity = parseFloat(alcohol.piece) * parseFloat(sumTaxByCapacity);
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(sumAllByCapacity));
	$('#alcoholTable > tbody:last > tr:last').append(
		$('<td>')
			.append($('<input>')
				.attr('type', 'text')
				.attr('size', '2')
            )
	);
	count++;
	
	pageSumTaxByValue += parseFloat(alcohol.taxByValue);
	pageSumTaxByCapacity += parseFloat(alcohol.taxByCapacity);
	$("#pageSumTaxByValue").val(pageSumTaxByValue);
	$("#pageSumTaxByCapacity").val(pageSumTaxByCapacity);
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