<!DOCTYPE html><%@ page pageEncoding="UTF-8"%>

<script>
var testData = '{"entrepreneur":{"licenseNo":"2558/456789ABCD","licenseAllowedName":"บริษัท สยามไวเนอรี่ จำกัด","factoryName":"บริษัท สยามไวเนอรี่ จำกัด","licenseStartDate":"01/01/2558","licenseEndDate":"31/12/2558","taxNo":"3-1015-1763-7","factoryAddress":"9/2 หมู่ 3 ต.บางโทรัด อ.เมืองสมุทรสาคร จ.สมุทรสาคร 74000","productList":[]}}';
function fetchData() {
	var jsonObj = JSON.parse(testData);
	document.getElementById('licenseNo').value = jsonObj.entrepreneur.licenseNo;
	document.getElementById('licenseAllowedName').value = jsonObj.entrepreneur.licenseAllowedName;
	document.getElementById('factoryName').value = jsonObj.entrepreneur.factoryName;
	document.getElementById('licenseStartDate').value = jsonObj.entrepreneur.licenseStartDate;
	document.getElementById('licenseEndDate').value = jsonObj.entrepreneur.licenseEndDate;
	document.getElementById('taxNo').value = jsonObj.entrepreneur.taxNo;
	document.getElementById('factoryAddress').value = jsonObj.entrepreneur.factoryAddress;
}
</script>

<div class="fadeIn active">        

<form action="#" method="post" >
<h4 class="stag-sans-section-heading" align="center">สร. 120-11</h4>

<table style="width: 90%; border: 0px solid green;">
<tr>
	<td width="70%"></td>
	<td align="right">
		<input type="text">
		<input type="button" value="Scan" onclick="fetchData()">
	</td>
</tr>
</table>

<table style="width: 90%; border: 1px solid green;">
<tr>
    <td width="50%" valign="top"><strong>ผู้ประกอบการ</strong>
    	<table style="width: 100%; border: 0px">
        <tr>
        	<td style="width: 60%;">ชื่อผู้ได้รับอนุญาตทำสุรา</td>
        	<td><input id="licenseAllowedName" type="text" ></td>
        </tr>
        <tr>
            <td>ชื่อโรงงานอุตสาหกรรม</td>
            <td><input id="factoryName" type="text"></td>
        </tr>
        <tr>
            <td>ใบอนุญาตทำสุราเลขที่</td>
            <td><input id="licenseNo" type="text"></td>
        </tr>
        <tr>
            <td>ลงวันที่</td>
            <td><input id="licenseStartDate" type="text"> - <input id="licenseEndDate" type="text"></td>
        </tr>
        <tr>
            <td>เลขประจำตัว</td>
            <td><input id="taxNo" type="text"></td>
        </tr>
        <tr>
            <td>ที่อยู่</td>
            <td><input id="factoryAddress" type="text"></td>
        </tr>
        </table>
    </td>
<td width="50%" valign="top"><strong>เจ้าหน้าที่</strong></td>
</tr>
</table>
<br>
<br>

<table style="width: 90%;">
<tr>
	<td><a href="#" class="button secondary with-chevron temp lg-free js-subscribe-link">คำนวนภาษี</a></td>
	<td><a href="#" class="button secondary with-chevron temp lg-free js-subscribe-link">Submit</a></td>
</tr>
</table>

</form>
</div>