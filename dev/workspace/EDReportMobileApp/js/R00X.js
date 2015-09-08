var _pageData = {};

/** -------------- Constant ---->>>>----------- **/

_pageData.arrType = false;
_pageData.url =  "json/R00X.json";
//_pageData.url =  "http://161.246.3.242:8081/EDBarcodeWeb/json/getR001";
_pageData.month =  "ต.ค.";
_pageData.title =  "<h3>รายงานรายละเอียดสุราแช่ (ในประเทศ) สำนักงานสรรพสามิตพื้นที่กรุงเทพฯ 1 ปี งบประมาณ 2558</h3> ";

_pageData.companyObj = { 
	rowNo: "orderNo",
    companyName: "companyName"
};
	
_pageData.productObj = { 
	productList : "r001DDomainList",
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
	companyRow : '<tr class="tr-company"> <td class="center" rowspan="{size1}" style="vertical-align: top;">{rowNo}</td>'
		+'<td rowspan="{size2}" style="vertical-align: top;">{companyName}</td> </tr>' ,
	productRow : '<tr class="tr-product"> <td >{goodsName}</td>'
		+'<td class="right">{degree}</td>'
		+'<td class="right">{size}</td>'
		+'<td class="right">{price1}</td> '
		+'<td class="right">{quantityBottle}</td>'
		+'<td class="right">{quantityLite}</td> '
		+'<td class="right">{vat}</td> </tr>' ,
	summaryRow : '<tr class="tr-summary"> <td class="summary" colspan="6" ><b>รวม</b></td>'
		+'<td class="summary">{sumBottle}</td>'
		+'<td class="summary">{sumLite}</td>'
		+'<td class="summary">{sumVat}</td> </tr>',
	headerRow : 	'<tr class="header">                    ' 
		+'	<th rowspan="2"> ลำดับ </th>          ' 
		+'	<th rowspan="2">ชื่อผู้ประกอบการ </th>      ' 
		+'	<th rowspan="2">ชื่อสินค้า </th>         ' 
		+'	<th rowspan="2">ดีกรี </th>            ' 
		+'	<th rowspan="2">ขนาด </th>           ' 
		+'	<th rowspan="2">ราคาขายส่งช่วงสุดท้าย </th>  ' 
		+'	<th colspan="3">{month} </th>           ' 
		+'</tr>                                  ' 
		+'<tr class="header">                    ' 
		+'	<th >จำนวน<br>(ขวด) </th>                     ' 
		+'	<th>ปริมาณ<br>(ลิตร)  </th>                      ' 
		+'	<th >ภาษี<br>(บาท)  </th>                       ' 
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
		console.log(_pageData);
		//alert(555);
		//genReport(_pageData.datas);
	}); 
	/*
	 $.ajax({
        type: 'GET',
        dataType: 'jsonp', 
        url:  _pageData.url,
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR); 
        },
        success: function (msg) {
            console.log(msg.responseText);
        }
    });*/
	
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
			col2 = col2.replace('{goodsName}' , checkNum(goodsName));
			col2 = col2.replace('{degree}' , checkNum(degree));
			col2 = col2.replace('{size}' , checkNum(size));
			col2 = col2.replace('{price1}' , checkNum(price1));
			col2 = col2.replace('{price2}' , checkNum(price2));		
			col2 = col2.replace('{quantityBottle}' , checkNum(quantityBottle));		
			col2 = col2.replace('{quantityLite}' , checkNum(quantityLite));		
			col2 = col2.replace('{vat}' , checkNum(vat));		
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

