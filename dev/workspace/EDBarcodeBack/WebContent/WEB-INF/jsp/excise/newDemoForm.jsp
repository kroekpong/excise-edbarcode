<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<script type="text/javascript" src='<c:url value="/js/edbarcode/demo/demoForm.js"/>'></script>
<script type="text/javascript">
var testDataEntrepreneur = '{"entrepreneur":{"licenseNo":"2558/456789ABCD","licenseAllowedName":"บริษัท สยามไวเนอรี่ จำกัด","factoryName":"บริษัท สยามไวเนอรี่ จำกัด","licenseStartDate":"01/01/2558","licenseEndDate":"31/12/2558","taxNo":"3-1015-1763-7","factoryAddress":"9/2 หมู่ 3 ต.บางโทรัด อ.เมืองสมุทรสาคร จ.สมุทรสาคร 74000","productList":[]}}';
var testDataAlcohol = '{"alcohol":{"productGroup":"สุราแช่อื่นๆ","productName":"สุราแช่ผลไม้ MONT CLAIR BIN 9","degree":"13.00","size":"0.750","piece":"1500","capacityTax":"11250.0000","sellingPriceByOwner":"209.5800","sellingPriceByDepartment":"0.0000","taxByValue":"10.4790","taxByCapacity":"7.5000","taxByValuePlus":"0.0000"}}';
var testDataTaxSummary = '{"taxSummary":{"sumTaxProduct1":"71,326.00","sumTaxProduct2":"7,132.60","sumTaxProduct3":"1,426.52","sumTaxProduct4":"1,069.89","sumTaxProduct5":"1,426.52","reduceTaxProductBaht":"0.00","receipt":"","remain1":"71,326.00","remain2":"7,132.60","remain3":"1,426.52","remain4":"1,069.89","remain5":"1,426.52","reduceTaxByDepBookNoBaht1":"0.00","reduceTaxByDepBookNoBaht2":"0.00","reduceTaxByDepBookNoBaht3":"0.00","reduceTaxByDepBookNoBaht4":"0.00","reduceTaxByDepBookNoBaht5":"0.00","sumAllAfterReduce1":"71,326.00","sumAllAfterReduce2":"7,132.60","other":"0.00","sumFinal2":"78,458.60","sumFinal3":"1,426.52","sumFinal4":"1,069.89","sumFinal5":"1,426.52"}}';
</script>

<div class="fadeIn active">        

<form action="#" method="post" >
<h4 class="stag-sans-section-heading" align="center">สร. 120-11</h4>

<table style="width: 90%; border: 0px solid black;">
<tr>
	<td width="60%"></td>
	<td align="right">
		Barcode: <input type="text" id="barcode" style="width: 70%;">
<!-- 		<input type="button" value="Scan" onclick="fetchData()"> -->
<!-- 		<input type="button" value="Clear" onclick="clearData()"> -->
	</td>
</tr>
</table>

<table style="width: 90%; border: 1px solid glay;">
  <tr>
    <td width="20%"><strong>ผู้ประกอบการ</strong></td>
    <td width="25%">&nbsp;</td>
    <td width="20%">เลขประจำตัวผู้เสียภาษีอากร</td>
    <td width="30%"><input id="taxNo" type="text" style="width: 100%;"></td>
    </tr>
  <tr>
    <td><span style="width: 40%;">ชื่อผู้ได้รับอนุญาตทำสุรา</span></td>
    <td colspan="3"><input id="licenseAllowedName" type="text" style="width: 100%;"></td>
    </tr>
  <tr>
    <td>ชื่อโรงงานอุตสาหกรรม</td>
    <td colspan="3"><input id="factoryName" type="text" style="width: 100%;"></td>
    </tr>
  <tr>
    <td>ที่ตั้ง</td>
    <td colspan="3"><input id="factoryAddress" type="text" style="width: 100%;"></td>
    </tr>
  <tr>
    <td>ใบอนุญาตผลิต</td>
    <td><input id="licenseNo" type="text" style="width: 90%;"></td>
    <td style="width: 10%;">วันที่อนุญาต</td>
    <td><input id="licenseStartDate" type="text" size="10"> - <input id="licenseEndDate" type="text" size="10"></td>
    </tr>
</table>
<br>
<table id="alcoholTable" border="1" style="width: 90%; border: 1px solid glay;">
<tbody>
<tr>
    <td rowspan="3" bgcolor="#E0E0E0" align="center">ลำ<br>
      ดับ</td>
    <td rowspan="3" bgcolor="#E0E0E0"><p align="center">(๓)<br>
      ชนิด<br>
      สุรา</p></td>
    <td colspan="8" bgcolor="#E0E0E0"><p align="center">(๔)<br>
      รายการสุรา</p></td>
    <td colspan="4" bgcolor="#E0E0E0"><p align="center">(๕)<br>
      ภาษีสุราต่อภาชนะ</p></td>
    <td colspan="2" bgcolor="#E0E0E0"><p align="center">(๖)<br>
      รวมภาษีสุรา</p></td>
    <td rowspan="3" bgcolor="#E0E0E0" align="center">เลข<br>
      ที่ถัง<br>
      เบียร์</td>
    </tr>
  <tr>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๔.๑)<br>
      ชื่อสุรา</p></td>
    <td rowspan="2" bgcolor="#E0E0E0">(๔.๒)<br>
ดีกรี</td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๔.๓) <br>
      ขนาดบรรจุ<br>
      (ลิตร)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๔.๔) <br>
      จำนวน<br>
      ภาชนะ</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๔.๕) <br>
      ปริมาณ<br>
      สุรา <br>
      ที่เสีย<br>
      ภาษี<br>
      (ลิตร)</p></td>
    <td colspan="2" bgcolor="#E0E0E0"><p align="center">(๔.๖) <br>
      ราคาขายส่ง<br>
      ช่วงสุดท้าย<br>
      ของสุรา <br>
      (บาท/ภาชนะ)<br>
      ไม่รวมภาษี<br>
      มูลค่าเพิ่ม</p></td>
    <td rowspan="2" bgcolor="#E0E0E0" align="center">มูลค่า<br>
      (บาท)</td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๕.๑) <br>
      ภาษี<br>
      ตาม <br>
      มูลค่า<br>
      (บาท/<br>
      ภาชนะ)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๕.๒) <br>
      ภาษี<br>
      ตาม <br>
      ปริมาณ<br>
      (บาท/<br>
      ภาชนะ)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๕.๓) <br>
      ภาษี<br>
      ตาม <br>
      ปริมาณ <br>
      กรณี<br>
      ภาษี <br>
      ดีกรีเกิน<br>
      (บาท/<br>
ภาชนะ)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๕.๔) <br>
      รวมภาษี<br>
      สุรา <br>
      ตาม<br>
      ปริมาณ <br>
      (บาท/<br>
      ภาชนะ)<br>
      (๕.๒+๕.๓)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๖.๑) <br>
      ตาม<br>
      มูลค่า<br>
      (บาท)
      <br>
      (๔.๔)<br>
      *<br>
(๕.๑)</p></td>
    <td rowspan="2" bgcolor="#E0E0E0"><p align="center">(๖.๒) <br>
      ตาม<br>
      ปริมาณ<br>
      (บาท)<br>
      (๔.๔)<br>
      *<br>
      (๕.๔)</p></td>
    </tr>
<tr>
    <td bgcolor="#E0E0E0" align="center">ตามแจ้ง</td>
    <td bgcolor="#E0E0E0" align="center">ตามประกาศ</td>
</tr>
</tbody>
</table>
<br>
<br>
<table id="alcoholSummaryTable" border="0" style="width: 90%;" border: 1px solid glay;">
  <tbody>
    <tr>
      <td align="right" bgcolor="#E0E0E0">รวม</td>
      <td width="80" align="center" bgcolor="#E0E0E0">ตามมูลค่า</td>
      <td width="150" bgcolor="#E0E0E0"><p align="center">
        <input type="text" id="pageSumTaxByValue" style="width: 90%; text-align: right;">
      </p></td>
      <td width="80" align="center" bgcolor="#E0E0E0">ตามปริมาณ</td>
      <td width="150" bgcolor="#E0E0E0"><p align="center">
        <input id="pageSumTaxByCapacity" type="text" style="width: 90%; text-align: right;">
      </p></td>
      <td width="5%" bgcolor="#E0E0E0"><p align="center">&nbsp;</p></td>
      </tr>
    </tbody>
</table>
<br>
<table border="1" style="width: 90%; border: 1px solid glay;">
  <tr>
    <td width="25%" bgcolor="#E0E0E0">&nbsp;</td>
    <td width="15%" bgcolor="#E0E0E0" align="center">สุรา</td>
    <td width="15%" bgcolor="#E0E0E0" align="center">มหาดไทย</td>
    <td width="15%" bgcolor="#E0E0E0" align="center">กองทุน สสส.</td>
    <td width="15%" bgcolor="#E0E0E0" align="center">กองทุน สสท.</td>
    <td width="15%" bgcolor="#E0E0E0" align="center">กองทุน กพฬ.</td>
  </tr>
  <tr>
    <td>&nbsp;(๗) รวมภาษีสุรา   (6.1) + (6.2)</td>
    <td align="center"><input id="sumTaxProduct1" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumTaxProduct2" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumTaxProduct3" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumTaxProduct4" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumTaxProduct5" type="text" style="width: 90%; text-align: right;"></td>
  </tr>
  <tr>
    <td>&nbsp;(๘) หัก ค่าภาษีสุราที่ชำระไว้แล้ว</td>
    <td align="center"><input id="reduceTaxProductBaht" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center">เลขที่ใบเสร็จ</td>
    <td colspan="3" align="center"><input id="receipt" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td>&nbsp;คงเหลือ</td>
    <td align="center"><input id="remain1" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="remain2" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="remain3" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="remain4" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="remain5" type="text" style="width: 90%; text-align: right;"></td>
  </tr>
  <tr>
    <td>&nbsp;(๙) หัก คืนภาษี สุรา</td>
    <td align="center"><input id="reduceTaxByDepBookNoBaht1" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="reduceTaxByDepBookNoBaht2" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="reduceTaxByDepBookNoBaht3" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="reduceTaxByDepBookNoBaht4" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="reduceTaxByDepBookNoBaht5" type="text" style="width: 90%; text-align: right;"></td>
  </tr>
  <tr>
    <td>&nbsp;(๑๐) คงเหลือภาษี สุรา</td>
    <td align="center"><input id="sumAllAfterReduce1" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumAllAfterReduce2" type="text" style="width: 90%; text-align: right;"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;(๑๓) อื่นๆ</td>
    <td align="center"><input id="other" type="text" style="width: 90%; text-align: right;"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;(๑๔) รวมภาษีที่ต้องชำระ</td>
    <td>&nbsp;</td>
    <td align="center"><input id="sumFinal2" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumFinal3" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumFinal4" type="text" style="width: 90%; text-align: right;"></td>
    <td align="center"><input id="sumFinal5" type="text" style="width: 90%; text-align: right;"></td>
  </tr>
</table>
<p>&nbsp;</p>
</form>
</div>