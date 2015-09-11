var _pageData = {};

/** -------------- Constant ---->>>>----------- **/

_pageData.arrType = false;
_pageData.url =  _COMMON.HOST + _COMMON.URL.R018 ;
//_pageData.url =  "http://161.246.3.242:8081/EDBarcodeWeb/json/getR001";
_pageData.month =  "ต.ค.";
_pageData.title =  "<h3>ร ายงานสุราขาว แยกตามดีกรี ประจำเดือน {month} เปรียบเทียบปีก่อน</h3> ";

_pageData.companyObj = { 
	rowNo: "orderNo",
    companyName: "companyName"
};
	
_pageData.productObj = { 
	
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
		+'<td rowspan="{size2}" style="vertical-align: top;">{degree}</td> </tr>' ,
	productRow : '<tr class="tr-product" id="ZZZZ" >'
		
		+'<td class="right">{volumespec}</td>'
		+'<td class="right">{volume}</td> '
		+'<td class="right">{tax}</td> '
		+'<td class="right">{volumeold}</td>'
		+'<td class="right">{taxold}</td>'
		+'<td class="right">{volumedif}</td>'
		+'<td class="right">{taxdif}</td>'
		+'<td class="right">{volumedifpercent}</td>'
		+'<td class="right">{taxdifpercent}</td> </tr>' ,
	summaryLastRow : '<tr class="tr-summary"> <td class="summary" colspan="3" ><b>รวม</b></td>'
		+'<td class="summary">{sum1}</td>'
		+'<td class="summary">{sum2}</td>'
		+'<td class="summary">{sum3}</td>'
		+'<td class="summary">{sum4}</td>'
		+'<td class="summary">{sum5}</td>'
		+'<td class="summary">{sum6}</td>'
		+'<td class="summary">{sum7}</td>'
		+'<td class="summary">{sum8}</td> </tr>',
	headerRow : 	'<tr class="header">                    ' 
		+'	<th rowspan="2"> ลำดับ </th>          ' 
		+'	<th colspan="4">{month} </th>           ' 
		+'	<th colspan="2">เดือนนี้ปีก่อน</th>      ' 
		+'	<th colspan="2">แตกต่าง </th>         ' 
		
		+'	<th colspan="2">ร้อยละ </th>  ' 
		
		+'</tr>                                  ' 
		+'<tr class="header">                    ' 
		+'	<th>ดีกรี </th>            ' 
		+'	<th>ขนาดบรรจุ<br>(ลิตร)</th>           ' 
		+'	<th>ปริมาณ<br>(ลิตร)</th>           ' 
		+'	<th>ภาษี<br>(บาท)  </th>                       ' 
		+'	<th>ปริมาณ<br>(ลิตร)</th>           ' 
		+'	<th>ภาษี<br>(บาท)  </th>                       ' 
		+'	<th>ปริมาณ<br>(ลิตร)</th>           ' 
		+'	<th>ภาษี<br>(บาท)  </th>                       ' 
		+'	<th>ปริมาณ<br>(ลิตร)</th>           ' 
		+'	<th>ภาษี<br>(บาท)  </th>                       ' 
		+'</tr>                                  ' 
};

/** ----------<<<  ---- Constant----------------------- **/

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
	var sumRow = _pageData.template.summaryLastRow;	

	var count = 1;

	var sum1 = 0;
	var sum2 = 0;
	var sum3 = 0;
	var sum4 = 0;
	var sum5 = 0;
	var sum6 = 0;
	var sum7 = 0;
	var sum8 = 0;


	hdrRow = hdrRow.replace('{month}' , _pageData.month); 
	dataTable += hdrRow;
	
	$.each( datas[0].degee, function( i, cval ){


		var productList = cval;				
		var size = productList.length+1;
		var rowNo = count++ ;
		var companyName = cval[_pageData.companyObj.companyName] ;		
		var col1 = _pageData.template.companyRow;	 	
		col1 = col1.replace('{size1}' , size);
		col1 = col1.replace('{size2}' , size);
		col1 = col1.replace('{rowNo}' , rowNo);
		col1 = col1.replace('{degree}' , i);				 
		dataTable += col1; 		
	
		$.each( productList, function( j, pval ){	

			console.log(pval);			
			var goodsName = pval[_pageData.productObj.goodsName] ;
			var degree = pval[_pageData.productObj.degree] ;
			var size= pval[_pageData.productObj.size] ;
			var price1= pval[_pageData.productObj.price1] ;
			var price2= pval[_pageData.productObj.price2] ;
			var quantityBottle= pval[_pageData.productObj.quantityBottle] ;
			var quantityLite= pval[_pageData.productObj.quantityLite] ;
			var vat= pval[_pageData.productObj.vat] ;
			
			var col2 = _pageData.template.productRow;
			
			
			col2 = col2.replace('{volumespec}' , checkNum(pval.volumespec));
			col2 = col2.replace('{volume}' , checkNum(pval.volume));
			col2 = col2.replace('{tax}' , checkNum(pval.tax));
			col2 = col2.replace('{volumeold}' , checkNum(pval.volumeold));
			col2 = col2.replace('{taxold}' , checkNum(pval.taxold));
			col2 = col2.replace('{volumedif}' , checkNum(pval.volumedif));
			col2 = col2.replace('{taxdif}' , checkNum(pval.taxdif));
			col2 = col2.replace('{volumedifpercent}' , checkNum(pval.volumedifpercent));
			col2 = col2.replace('{taxdifpercent}' , checkNum(pval.taxdifpercent));		
		
			dataTable += col2;
			
			sum1 += parseNum(pval.volume);
			sum2 += parseNum(pval.tax);
			sum3 += parseNum(pval.volumeold);
			sum4 += parseNum(pval.taxold);
			sum5 += parseNum(pval.volumedif);
			sum6 += parseNum(pval.taxdif);
			sum7 += parseNum(pval.volumedifpercent);
			sum8 += parseNum(pval.taxdifpercent);
			 
		});
		
		//console.log(dataTable);
		
		
	});
		sumRow = sumRow.replace('{sum1}' , formatNum(0,sum1));		
		sumRow = sumRow.replace('{sum2}' , formatNum(0,sum2));
		sumRow = sumRow.replace('{sum3}' , formatNum(0,sum3));
		sumRow = sumRow.replace('{sum4}' , formatNum(0,sum4));
		sumRow = sumRow.replace('{sum5}' , formatNum(0,sum5));
		sumRow = sumRow.replace('{sum6}' , formatNum(0,sum6));
		sumRow = sumRow.replace('{sum7}' , formatNum(2,sum7));
		sumRow = sumRow.replace('{sum8}' , formatNum(2,sum8));
		dataTable+= sumRow;
			
		$('#tb-data').html(dataTable);
	
}


$(function(){

		$('#tb-title').html(_pageData.title.replace('{month}' , _pageData.month));
 

});

