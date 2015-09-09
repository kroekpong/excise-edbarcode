var _pageData = {};

/** -------------- Constant ---->>>>----------- **/

_pageData.arrType = false;
_pageData.url =  "json/R008.json";
//_pageData.url =  "http://161.246.3.242:8081/EDBarcodeWeb/json/getR001";
_pageData.month =  "ต.ค.";
_pageData.title =  "<h3>รายงานตารางชื่อสุรา 10 Top</h3> ";

_pageData.companyObj = { 
	rowNo: "orderNo",
    companyName: "companyName"
};
	
_pageData.productObj = { 
	productList : "listR008DDomain",
	goodsName: "goodsName",
	degree: "degree",
	size: "size",
	price1: "pric1",
	price2: "price2",
	quantityBottle: "quantityBottle",
	quantityLite: "quantityLite",
	vat: "vat"
};
	
_pageData.template = {
	companyRow : ' ' ,
	productRow : '<tr class="tr-product">' 
		+'<td >{rowNum}</td>'
		+'<td >{companyName}</td>'
		+'<td >{goodsName}</td>'
		+'<td class="right">{degree}</td>'
		+'<td class="right">{size}</td>'
		+'<td class="right">{vat}</td> '
		+'<td class="right">{vatOld}</td>'
		+'<td class="right">{vatdif}</td> '
		+'<td class="right">{vatdifpercent}</td> </tr>' ,
	summaryRow : '<tr class="tr-summary"> <td class="summary" colspan="6" ><b>รวม</b></td>'
		+'<td class="summary">{sumBottle}</td>'
		+'<td class="summary">{sumLite}</td>'
		+'<td class="summary">{sumVat}</td> </tr>',
	headerRow : '<tr class="header">'
		+	'<th rowspan="2" style="width:49px">ลำดับ</strong></th>'
		+	'<th rowspan="2" style="width:164px">ชื่อสุรา</th>'
		+	'<th rowspan="2" style="width:138px">บริษัท</th>'
		+	'<th colspan="5" style="width:428px">ต.ค. 2557</th>'
		+	'<th colspan="2" style="width:88px">เดือนนี้ ปีก่อน</th>'
		+	'<th colspan="2" style="width:90px">แตกต่าง</th>'
		+	'<th colspan="2" style="width:84px">ร้อยละ</th>'
		+'</tr>'
		+'<tr class="header">'
		+	'<th style="width:164px">ชนิดสุรา</th>'
		+	'<th style="width:88px">ดีกรี</th>'
		+	'<th style="width:88px">ขนาดบรรจุ(ลิตร)</th>'
		+	'<th style="width:88px">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th style="width:88px">ภาษี<br/>(บาท)</th>'
		+	'<th style="width:88px">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th style="width:88px">ภาษี<br/>(บาท)</th>'
		+	'<th style="width:88px">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th style="width:88px">ภาษี<br/>(บาท)</th>'
		+	'<th style="width:88px">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th style="width:88px">ภาษี<br/>(บาท)</th>'
		+'</tr> '
};

/** ----------<<<  ---- Constant----------------------- **/

function parseNum(val){  
	//val = val.replace(/[^\d\.\-eE+]/g, "");
	return ($.trim(val) == '')? 0 : parseFloat(val);
}

function formatNum(digits,val){
	return val.toFixed(digits).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function checkNum(val){
	return ($.trim(val) == '')? '-' : val;
}

function getData(){  	

	$.getJSON( _pageData.url , function( data ) {
		if(_pageData.arrType){
			_pageData.datas = data // IF return 
		}else{
			_pageData.datas = [data] // IF return 1 obj
		}
		//console.log(_pageData.datas);
		genReport(_pageData.datas);
	}); 
	
	
}

function genReport(datas){  

	var dataTable = "";		
	
	var hdrRow = _pageData.template.headerRow; 
	
	
	hdrRow = hdrRow.replace('{month}' , _pageData.month); 
	dataTable += hdrRow;
	
	$.each( datas, function( i, cval ){
		var productList = cval[_pageData.productObj.productList];				
		var size = productList.length+1;
		var rowNo = cval[_pageData.companyObj.rowNo] ;
		
		var col1 = _pageData.template.companyRow;	 	
		col1 = col1.replace('{size1}' , size);
		col1 = col1.replace('{size2}' , size);
		col1 = col1.replace('{rowNo}' , rowNo);
				 
		dataTable += col1; 		
	
		var sumRow = _pageData.template.summaryRow;	
		var sumBottle = 0;
		var sumLite = 0;
		var sumVat = 0;
		$.each( productList, function( j, pval ){
				
			var companyName = pval.companyName ;		
			var goodsName = pval[_pageData.productObj.goodsName] ;
			var degree = pval[_pageData.productObj.degree] ;
			var size= pval[_pageData.productObj.size] ;
			var price1= pval[_pageData.productObj.price1] ;
			var price2= pval[_pageData.productObj.price2] ;
			var quantityBottle= pval[_pageData.productObj.quantityBottle] ;
			var quantityLite= pval[_pageData.productObj.quantityLite] ;
			var vat= pval[_pageData.productObj.vat] ;
			var vatOld= pval.vatOld ;
			var vatdif= pval.vatdif ;
			var vatdifpercent= pval.vatdifpercent ;
			
			var col2 = _pageData.template.productRow;
			col2 = col2.replace('{rowNum}' , (j+1));
			col2 = col2.replace('{companyName}' , companyName);
			col2 = col2.replace('{goodsName}' , checkNum(goodsName));
			col2 = col2.replace('{degree}' , checkNum(degree));
			col2 = col2.replace('{size}' , checkNum(size));
			col2 = col2.replace('{price1}' , checkNum(price1));
			col2 = col2.replace('{price2}' , checkNum(price2));		
			col2 = col2.replace('{quantityBottle}' , checkNum(quantityBottle));		
			col2 = col2.replace('{quantityLite}' , checkNum(quantityLite));		
			col2 = col2.replace('{vat}' , checkNum(vat));
			col2 = col2.replace('{vatOld}' , checkNum(vatOld));
			col2 = col2.replace('{vatdif}' , checkNum(vatdif));
			col2 = col2.replace('{vatdifpercent}' , checkNum(vatdifpercent));		
			dataTable += col2;
			
			sumBottle += parseNum(quantityBottle);
			sumLite += parseNum(quantityLite);
			sumVat += parseNum(vat);
			 
		});
		
		sumRow = sumRow.replace('{sumBottle}' , formatNum(0,sumBottle));		
		sumRow = sumRow.replace('{sumLite}' , formatNum(3,sumLite));		
		sumRow = sumRow.replace('{sumVat}' , formatNum(2,sumVat));		
		
		dataTable+= sumRow;
		//console.log(dataTable);
		
		$('#tb-data').html(dataTable);
	});
	
}


$(function(){

	//$('#tb-title').html(_pageData.title);
 

});

