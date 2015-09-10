var _pageData = {};

/** -------------- Constant ---->>>>----------- **/

_pageData.arrType = false;
_pageData.url =  "json/R012.json";
//_pageData.url =  "http://161.246.3.242:8081/EDBarcodeWeb/json/getR001";
_pageData.month =  "ต.ค.";
_pageData.title =  "<h3>ตารางแสดงผลการเสียภาษีสรรพสามิตของผู้เสียภาษีแต่ละรายสำหรับสินค้าและบริการแต่ละประเภทในแต่ละเดือน โดยเปรียบเทียบกับเดือนเดียวกันปีที่แล้ว สุราในประเทศ ประจำเดือน กันยายน ปีงบประมาณ 2557</h3> ";

_pageData.companyObj = { 
	rowNo: "orderNo",
    companyName: "companyName"
};
	
_pageData.productObj = { 
	productList : "listR012DDomain",
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
	productRow : '<tr class="tr-product"> <td >{rowNum}</td>'
		+'<td class="right">{typeName}</td>'
		+'<td class="right">{companyName}</td>'
		+'<td class="right">{goodsName1}<br>"{goodsName2}"</td>'
		+'<td class="right">{size}</td>'
		+'<td class="right">{vat}</td>'
		+'<td class="right">{sizeold}</td>'
		+'<td class="right">{vatold}</td> '
		+'<td class="right">{sizedif}</td>'
		+'<td class="right">{sizedifpercent}</td> '
		+'<td class="right">{vatdif}</td> '
		+'<td class="right">{vatdifpercent}</td> </tr>' ,
	summaryRow : '<tr class="tr-summary"> <td class="summary" colspan="4" ><b>รวม</b></td>'
		+'<td class="summary">{sum1}</td>'
		+'<td class="summary">{sum2}</td>'
		+'<td class="summary">{sum3}</td>'
		+'<td class="summary">{sum4}</td>'
		+'<td class="summary">{sum5}</td>'
		+'<td class="summary">{sum6}</td>'
		+'<td class="summary">{sum7}</td>'
		+'<td class="summary">{sum8}</td> </tr>',
	headerRow : '<tr class="header">                    ' 
		+'	<th rowspan="3"> ลำดับ </th>          ' 
		+'	<th rowspan="3">ชนิดสุรา </th>         ' 
		+'	<th rowspan="3">ชื่อผู้ประกอบการ </th>      ' 
		+'	<th rowspan="3">ชื่อสุรา </th>         ' 
		+'	<th colspan="2">เดือนนี้ </th>         ' 
		+'	<th colspan="2">เดือนนี้ปีที่แล้ว</th>         ' 
		+'	<th colspan="4">เปรียบเทียยบกับปีที่แล้ว (+ เพิ่ม, - ลด)</th>         ' 
		+'</tr>                                  ' 
		+'<tr class="header">                    ' 
		+	'<th rowspan="2">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th rowspan="2">ภาษี<br/>(บาท)</th>'
		+	'<th rowspan="2">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th rowspan="2">ภาษี<br/>(บาท)</th>'
		+	'<th colspan="2">ปริมาณ<br/>(ลิตร)</th>'
		+	'<th colspan="2">ภาษี<br/>(บาท)</th>'
		+'</tr>                                  ' 
		+'<tr class="header">'

		+	'<th>จำนวน</th>'		
		+	'<th>ร้อยละ</th>'
		+	'<th>จำนวน</th>'
		+	'<th>ร้อยละ</th>'
		+'</tr>                                  ' 
};

/** ----------<<<  ---- Constant----------------------- **/

function parseNum(val){  
	val = val.replace(/[^\d\.\-eE+]/g, "");
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
		var companyName = cval[_pageData.companyObj.companyName] ;		
		var col1 = _pageData.template.companyRow;	 	
		col1 = col1.replace('{size1}' , size);
		col1 = col1.replace('{size2}' , size);
		col1 = col1.replace('{rowNo}' , rowNo);
		col1 = col1.replace('{companyName}' , companyName);				 
		dataTable += col1; 		
	
		var sumRow = _pageData.template.summaryRow;	
		var sum1 = 0;
		var sum2 = 0;
		var sum3 = 0;
		var sum4 = 0;
		var sum5 = 0;
		var sum6 = 0;
		var sum7 = 0;
		var sum8 = 0;

		$.each( productList, function( j, pval ){				
			var goodsName = pval[_pageData.productObj.goodsName] ;
			var degree = pval[_pageData.productObj.degree] ;
			var size= pval[_pageData.productObj.size] ;
			var price1= pval[_pageData.productObj.price1] ;
			var price2= pval[_pageData.productObj.price2] ;
			var quantityBottle= pval[_pageData.productObj.quantityBottle] ;
			var quantityLite= pval[_pageData.productObj.quantityLite] ;
			var vat= pval[_pageData.productObj.vat] ;
			
			var col2 = _pageData.template.productRow;
			col2 = col2.replace('{rowNum}' , j+1 );
			col2 = col2.replace('{goodsName1}' , checkNum(pval.goodsName1));
			col2 = col2.replace('{typeName}' , checkNum(pval.typeName));
			col2 = col2.replace('{companyName}' , checkNum(pval.companyName));
			col2 = col2.replace('{goodsName2}' , checkNum(pval.goodsName2));
			col2 = col2.replace('{size}' , checkNum(pval.size));		
			col2 = col2.replace('{vat}' , checkNum(pval.vat));		
			col2 = col2.replace('{sizeold}' , checkNum(pval.sizeold));	
			col2 = col2.replace('{vatold}' , checkNum(pval.vatold));	
			col2 = col2.replace('{sizedif}' , checkNum(pval.sizedif));	
			col2 = col2.replace('{sizedifpercent}' , checkNum(pval.sizedifpercent));	
			col2 = col2.replace('{vatdif}' , checkNum(pval.vatdif));		
			col2 = col2.replace('{vatdifpercent}' , checkNum(pval.vatdifpercent));		
			dataTable += col2;
			
			sum1 += parseNum(pval.size);
			sum2 += parseNum(pval.vat);
			sum3 += parseNum(pval.sizeold)||0;
			sum4 += parseNum(pval.vatold)||0;
			sum5 += parseNum(pval.sizedif);
			sum6 += parseNum(pval.sizedifpercent);
			sum7 += parseNum(pval.vatdif);
			sum8 += parseNum(pval.vatdifpercent);

		});
		
		sumRow = sumRow.replace('{sum1}' , formatNum(2,sum1));		
		sumRow = sumRow.replace('{sum2}' , formatNum(3,sum2));		
		sumRow = sumRow.replace('{sum3}' , formatNum(2,checkNum(sum3)));	
		sumRow = sumRow.replace('{sum4}' , formatNum(2,checkNum(sum4)));	
		sumRow = sumRow.replace('{sum5}' , formatNum(2,sum5));	
		sumRow = sumRow.replace('{sum6}' , formatNum(2,sum6/productList.length));	
		sumRow = sumRow.replace('{sum7}' , formatNum(2,sum7));	
		sumRow = sumRow.replace('{sum8}' , formatNum(2,sum8/productList.length));		
		
		dataTable+= sumRow;
		//console.log(dataTable);
		
		$('#tb-data').html(dataTable);
	});
	
}


$(function(){

	$('#tb-title').html(_pageData.title);
 

});

