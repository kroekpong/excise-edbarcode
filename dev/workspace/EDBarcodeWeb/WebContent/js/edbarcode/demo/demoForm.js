var count = 1;

function getScanType() {
	var scanType = $("input[type='radio'][name='scanType']:checked").val();
	return scanType;
}

function fetchData() {
	var scanType = getScanType();
	if ('scanType1' == scanType) {
		var jsonObj = JSON.parse(testDataEntrepreneur);
		fetchDataEntrepreneur(jsonObj.entrepreneur);
	} else if ('scanType2' == scanType) {
		var jsonObject = JSON.parse(testDataAlcohol);
		fetchDataAlcoholList(jsonObject.alcohol);
	} else if ('scanType3' == scanType) {
		var jsonObj = JSON.parse(testDataTaxSummary);
		fetchDataTaxSummary(jsonObj.taxSummary);
	}
}

function clearData() {
	var scanType = getScanType();
	if ('scanType1' == scanType) {
		clearDataEntrepreneur();
	} else if ('scanType2' == scanType) {
		
	} else if ('scanType3' == scanType) {
		clearDataTaxSummary();
	}
}

function fetchDataEntrepreneur(ent) {
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

function fetchDataTaxSummary(taxSummary) {
	$('#sumTaxProduct1').val(taxSummary.sumTaxProduct1);
	$('#sumTaxProduct2').val(taxSummary.sumTaxProduct2);
	$('#sumTaxProduct3').val(taxSummary.sumTaxProduct3);
	$('#sumTaxProduct4').val(taxSummary.sumTaxProduct4);
	$('#sumTaxProduct5').val(taxSummary.sumTaxProduct5);
	$('#reduceTaxProductBaht').val(taxSummary.reduceTaxProductBaht);
	$('#receipt').val(taxSummary.receipt);
	$('#remain1').val(taxSummary.remain1);
	$('#remain2').val(taxSummary.remain2);
	$('#remain3').val(taxSummary.remain3);
	$('#remain4').val(taxSummary.remain4);
	$('#remain5').val(taxSummary.remain5);
	$('#reduceTaxByDepBookNoBaht1').val(taxSummary.reduceTaxByDepBookNoBaht1);
	$('#reduceTaxByDepBookNoBaht2').val(taxSummary.reduceTaxByDepBookNoBaht2);
	$('#reduceTaxByDepBookNoBaht3').val(taxSummary.reduceTaxByDepBookNoBaht3);
	$('#reduceTaxByDepBookNoBaht4').val(taxSummary.reduceTaxByDepBookNoBaht4);
	$('#reduceTaxByDepBookNoBaht5').val(taxSummary.reduceTaxByDepBookNoBaht5);
	$('#sumAllAfterReduce1').val(taxSummary.sumAllAfterReduce1);
	$('#sumAllAfterReduce2').val(taxSummary.sumAllAfterReduce2);
	$('#other').val(taxSummary.other);
	$('#sumFinal2').val(taxSummary.sumFinal2);
	$('#sumFinal3').val(taxSummary.sumFinal3);
	$('#sumFinal4').val(taxSummary.sumFinal4);
	$('#sumFinal5').val(taxSummary.sumFinal5);
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

function fetchDataAlcoholList(alcohol) {
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
	$('#alcoholTable > tbody:last > tr:last').append($('<td>').append(alcohol.capacityTax));
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
}

function removeDataAlcoholList(trId) {
	if (confirm('ต้องการลบรายการนี้ ?')) {
		$('#alcoholTable > tbody:last > tr#' + trId).remove();
	}
}