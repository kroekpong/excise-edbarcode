var _pageData = {};

/** -------------- Constant ---->>>>----------- **/

_pageData.arrType = false;
_pageData.url =  "json/R010.json";
//_pageData.url =  "http://161.246.3.242:8081/EDBarcodeWeb/json/getR001";
_pageData.month =  "ต.ค.";
_pageData.title =  "<h3>รายงานตารางราคาขายส่งช่วงสุดท้าย ประจำเดือน </h3> ";

_pageData.companyObj = { 
	rowNo: "orderNo",
    companyName: "companyName"
};
	
_pageData.productObj = { 
	productList : "listR010DDomain",
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
	productRow : '<tr class="tr-product"><td >{rowNum}</td > '
		+'<td >{companyName}</td>'
		+'<td >{typeName}</td>'
		+'<td >{goodsName}</td>'
		+'<td class="right">{degree}</td>'
		+'<td class="right">{size}</td>'
		+'<td class="right">{price1}</td> '
		+'<td class="right">{price2}</td>'
		+'<td class="right">{pricedif}</td> '
		+'<td class="right">{pricedifpercent}</td> </tr>' ,
	summaryRow : ' ',
	headerRow : '<tr class="header">                    ' 
		+'	<th rowspan="2"> ลำดับ </th>          ' 
		+'	<th rowspan="2">บริษัท </th>      ' 
		+'	<th rowspan="2">ชนิดสุรา </th>         ' 
		+'	<th rowspan="2">ชื่อสุรา </th>         ' 
		+'	<th rowspan="2">ดีกรี </th>            ' 
		+'	<th rowspan="2">ขนาดบรรจุ<br>(ลิตร)</th>           ' 
		+'	<th colspan="2">ราคาขายส่งช่วงสุดท้าย <br>(บาท/ภาชนะ)ไม่รวมภาษีมูลค่าเพิ่ม</th>  '  
		+'	<th rowspan="2">แตกต่าง </th>         ' 
		+'	<th rowspan="2">ร้อยละ </th>            ' 
		+'</tr>                                  ' 
		+'<tr class="header">                    ' 
		+'	<th >ตามแจ้ง</th> ' 
		+'	<th>ตามประกาศ</th>  ' 
		+'</tr>                                  ' 
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
		console.log(_pageData);
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
		var sumBottle = 0;
		var sumLite = 0;
		var sumVat = 0;
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
			col2 = col2.replace('{rowNum}' , (j+1));
			col2 = col2.replace('{typeName}' , pval.typeName);
			col2 = col2.replace('{companyName}' , pval.companyName);
			col2 = col2.replace('{goodsName}' , checkNum(goodsName));
			col2 = col2.replace('{degree}' , checkNum(degree));
			col2 = col2.replace('{size}' , checkNum(size));
			col2 = col2.replace('{price1}' , checkNum(price1));
			col2 = col2.replace('{price2}' , checkNum(price2));			
			col2 = col2.replace('{pricedif}' , checkNum(pval.pricedif));		
			col2 = col2.replace('{pricedifpercent}' , checkNum(pval.pricedifpercent));		
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

