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
		+'<td >{typeName}</td>'
		+'<td >{companyName}</td>'
		+'<td >{goodsName}</td>'
		+'<td class="right">{degree}</td>'
		+'<td class="right">{size}</td>'
		+'<td class="right">{volume}</td> '
		+'<td class="right">{vat}</td> '
		+'<td class="right">{volumeOld}</td>'
		+'<td class="right">{vatOld}</td>'
		+'<td class="right">{volumedif}</td>'
		+'<td class="right">{vatdif}</td>'
		+'<td class="right">{volumedifpercent}</td>'
		+'<td class="right">{vatdifpercent}</td> </tr>' ,
	summaryRow : '<tr class="tr-summary"> <td class="summary" colspan="6" ><b>รวม</b></td>'
		+'<td class="summary">{sum1}</td>'
		+'<td class="summary">{sum2}</td>'
		+'<td class="summary">{sum3}</td>'
		+'<td class="summary">{sum4}</td>'
		+'<td class="summary">{sum5}</td>'
		+'<td class="summary">{sum6}</td>'
		+'<td class="summary">{sum7}</td>'
		+'<td class="summary">{sum8}</td> </tr>',
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

		var sum1 = 0;
		var sum2 = 0;
		var sum3 = 0;
		var sum4 = 0;
		var sum5 = 0;
		var sum6 = 0;
		var sum7 = 0;
		var sum8 = 0;



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
			col2 = col2.replace('{typeName}' , pval.typeName);
			col2 = col2.replace('{companyName}' , companyName);
			col2 = col2.replace('{goodsName}' , checkNum(goodsName));
			col2 = col2.replace('{degree}' , checkNum(degree));
			col2 = col2.replace('{size}' , checkNum(size));
			col2 = col2.replace('{volume}' , checkNum(pval.volume));
			col2 = col2.replace('{vat}' , checkNum(pval.vat));		
			col2 = col2.replace('{volumeOld}' , checkNum(pval.volumeOld));		
			col2 = col2.replace('{volumedif}' , checkNum(pval.volumedif));		
			col2 = col2.replace('{vatdif}' , checkNum(pval.vatdif));
			col2 = col2.replace('{vatOld}' , checkNum(pval.vatOld));
			col2 = col2.replace('{vatdif}' , checkNum(pval.vatdif));
			col2 = col2.replace('{volumedifpercent}' , checkNum(pval.volumedifpercent));	
			col2 = col2.replace('{vatdifpercent}' , checkNum(pval.vatdifpercent));	
			dataTable += col2;
		
			sum1 += parseNum(pval.volume);
			sum2 += parseNum(pval.vat);
			sum3 += parseNum(pval.volumeOld);
			sum4 += parseNum(pval.vatOld);
			sum5 += parseNum(pval.volumedif);
			sum6 += parseNum(pval.vatdif);
			sum7 += parseNum(pval.volumedifpercent);
			sum8 += parseNum(pval.vatdifpercent);
		});
		
		sumRow = sumRow.replace('{sum1}' , formatNum(0,sum1));		
		sumRow = sumRow.replace('{sum2}' , formatNum(3,sum2));		
		sumRow = sumRow.replace('{sum3}' , formatNum(2,sum3));
		sumRow = sumRow.replace('{sum4}' , formatNum(2,sum4));		
		sumRow = sumRow.replace('{sum5}' , formatNum(2,sum5));		
		sumRow = sumRow.replace('{sum6}' , formatNum(2,sum6));		
		sumRow = sumRow.replace('{sum7}' , formatNum(2,sum7));
		sumRow = sumRow.replace('{sum8}' , formatNum(2,sum8/productList.length));		

		
		dataTable+= sumRow;
		//console.log(dataTable);
		
		$('#tb-data').html(dataTable);
	});
	
}


$(function(){

	//$('#tb-title').html(_pageData.title);
 

});

