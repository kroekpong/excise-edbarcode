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

<table style="width: 90%; border: 0px solid green;">
<tr>
	<td width="70%"></td>
	<td align="right">
		<input type="radio" name="scanType" id="scanType1" value="scanType1" checked="checked"> ผู้ประกอบการ
		<input type="radio" name="scanType" id="scanType2" value="scanType2"> รายการสุรา
		<input type="radio" name="scanType" id="scanType3" value="scanType3"> ผลรวม
	</td>
</tr>
<tr>
	<td></td>
	<td align="right"><input type="text">
		<input type="button" value="Scan" onclick="fetchData()">
		<input type="button" value="Clear" onclick="clearData()">
	</td>
</tr>
</table>

<table style="width: 90%; border: 1px solid green;">
  <tr>
    <td width="20%"><strong>ผู้ประกอบการ</strong></td>
    <td width="30%">&nbsp;</td>
    <td width="20%">เลขประจำตัวผู้เสียภาษีอากร</td>
    <td width="30%"><input id="taxNo" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td><span style="width: 40%;">ชื่อผู้ได้รับอนุญาตทำสุรา</span></td>
    <td colspan="3"><input id="licenseAllowedName" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td>ชื่อโรงงานอุตสาหกรรม</td>
    <td colspan="3"><input id="factoryName" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td>ที่ตั้ง</td>
    <td colspan="3"><input id="factoryAddress" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td>ใบอนุญาตผลิต</td>
    <td><input id="licenseNo" type="text" style="width: 90%;"></td>
    <td>วันที่อนุญาต</td>
    <td><input id="licenseStartDate" type="text" size="10"> - <input id="licenseEndDate" type="text" size="10"></td>
    </tr>
</table>
<br>
<table id="alcoholTable" border="1" style="width: 90%; border: 1px solid green;">
<tbody>
<tr>
    <td rowspan="3">ลำ<br>
      ดับ</td>
    <td rowspan="3"><p align="center">(๓)<br>
      ชนิด<br>
      สุรา</p></td>
    <td colspan="8"><p align="center">(๔)<br>
      รายการสุรา</p></td>
    <td colspan="4"><p align="center">(๕)<br>
      ภาษีสุราต่อภาชนะ</p></td>
    <td colspan="2"><p align="center">(๖)<br>
      รวมภาษีสุรา</p></td>
    <td rowspan="3">เลข<br>
      ที่ถัง<br>
      เบียร์</td>
    </tr>
  <tr>
    <td rowspan="2"><p align="center">(๔.๑)<br>
      ชื่อสุรา</p></td>
    <td rowspan="2">(๔.๒)<br>
ดีกรี</td>
    <td rowspan="2"><p align="center">(๔.๓) <br>
      ขนาดบรรจุ<br>
      (ลิตร)</p></td>
    <td rowspan="2"><p align="center">(๔.๔) <br>
      จำนวน<br>
      ภาชนะ</p></td>
    <td rowspan="2"><p align="center">(๔.๕) <br>
      ปริมาณ<br>
      สุรา <br>
      ที่เสีย<br>
      ภาษี<br>
      (ลิตร)</p></td>
    <td colspan="2"><p align="center">(๔.๖) <br>
      ราคาขายส่ง<br>
      ช่วงสุดท้าย<br>
      ของสุรา <br>
      (บาท/ภาชนะ)<br>
      ไม่รวมภาษี<br>
      มูลค่าเพิ่ม</p></td>
    <td rowspan="2">มูลค่า<br>
      (บาท)</td>
    <td rowspan="2"><p align="center">(๕.๑) <br>
      ภาษี<br>
      ตาม <br>
      มูลค่า<br>
      (บาท/<br>
      ภาชนะ)</p></td>
    <td rowspan="2"><p align="center">(๕.๒) <br>
      ภาษี<br>
      ตาม <br>
      ปริมาณ<br>
      (บาท/<br>
      ภาชนะ)</p></td>
    <td rowspan="2"><p align="center">(๕.๓) <br>
      ภาษี<br>
      ตาม <br>
      ปริมาณ <br>
      กรณี<br>
      ภาษี <br>
      ดีกรีเกิน<br>
      (บาท/<br>
ภาชนะ)</p></td>
    <td rowspan="2"><p align="center">(๕.๔) <br>
      รวมภาษี<br>
      สุรา <br>
      ตาม<br>
      ปริมาณ <br>
      (บาท/<br>
      ภาชนะ)<br>
      (๕.๒+๕.๓)</p></td>
    <td rowspan="2"><p align="center">(๖.๑) <br>
      ตาม<br>
      มูลค่า<br>
      (บาท)
      <br>
      (๔.๔)<br>
      *<br>
(๕.๑)</p></td>
    <td rowspan="2"><p align="center">(๖.๒) <br>
      ตาม<br>
      ปริมาณ<br>
      (บาท)<br>
      (๔.๔)<br>
      *<br>
      (๕.๔)</p></td>
    </tr>
<tr>
    <td>ตามแจ้ง</td>
    <td>ตามประกาศ</td>
</tr>
</tbody>
</table>
<br>
<table border="1" style="width: 90%; border: 1px solid green;">
  <tr>
    <td width="40%">&nbsp;</td>
    <td>สุรา</td>
    <td>มหาดไทย</td>
    <td>กองทุน สสส.</td>
    <td>กองทุน สสท.</td>
    <td>กองทุน กพฬ.</td>
  </tr>
  <tr>
    <td>(๗) รวมภาษีสุรา   (6.1) + (6.2)</td>
    <td><input id="sumTaxProduct1" type="text" style="width: 90%;"></td>
    <td><input id="sumTaxProduct2" type="text" style="width: 90%;"></td>
    <td><input id="sumTaxProduct3" type="text" style="width: 90%;"></td>
    <td><input id="sumTaxProduct4" type="text" style="width: 90%;"></td>
    <td><input id="sumTaxProduct5" type="text" style="width: 90%;"></td>
  </tr>
  <tr>
    <td>(๘) หัก ค่าภาษีสุราที่ชำระไว้แล้ว</td>
    <td><input id="reduceTaxProductBaht" type="text" style="width: 90%;"></td>
    <td>เลขที่ใบเสร็จ</td>
    <td colspan="3"><input id="receipt" type="text" style="width: 90%;"></td>
    </tr>
  <tr>
    <td>คงเหลือ</td>
    <td><input id="remain1" type="text" style="width: 90%;"></td>
    <td><input id="remain2" type="text" style="width: 90%;"></td>
    <td><input id="remain3" type="text" style="width: 90%;"></td>
    <td><input id="remain4" type="text" style="width: 90%;"></td>
    <td><input id="remain5" type="text" style="width: 90%;"></td>
  </tr>
  <tr>
    <td>(๙) หัก คืนภาษี สุรา</td>
    <td><input id="reduceTaxByDepBookNoBaht1" type="text" style="width: 90%;"></td>
    <td><input id="reduceTaxByDepBookNoBaht2" type="text" style="width: 90%;"></td>
    <td><input id="reduceTaxByDepBookNoBaht3" type="text" style="width: 90%;"></td>
    <td><input id="reduceTaxByDepBookNoBaht4" type="text" style="width: 90%;"></td>
    <td><input id="reduceTaxByDepBookNoBaht5" type="text" style="width: 90%;"></td>
  </tr>
  <tr>
    <td>(๑๐) คงเหลือภาษี สุรา</td>
    <td><input id="sumAllAfterReduce1" type="text" style="width: 90%;"></td>
    <td><input id="sumAllAfterReduce2" type="text" style="width: 90%;"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>(๑๓) อื่นๆ</td>
    <td><input id="other" type="text" style="width: 90%;"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>(๑๔) รวมภาษีที่ต้องชำระ</td>
    <td>&nbsp;</td>
    <td><input id="sumFinal2" type="text" style="width: 90%;"></td>
    <td><input id="sumFinal3" type="text" style="width: 90%;"></td>
    <td><input id="sumFinal4" type="text" style="width: 90%;"></td>
    <td><input id="sumFinal5" type="text" style="width: 90%;"></td>
  </tr>
</table>
<p>&nbsp;</p>
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