<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<form action="testgetsr12011reportsubmit.htm" method="post">
<input type="text" name="strurl" value="${strurl}" style="width: 425px;" />
<div align="left">
<c:if test="${strxml!=null}">
<textarea rows="25" cols="58" name="strxml">
${strxml}
</textarea>
</c:if>
<c:if test="${strxml==null}">
<textarea rows="25" cols="58" name="strxml">
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bar="http://www.excise.go.th/xsd/barcode">
   <soapenv:Header/>
   <soapenv:Body>
      <bar:EbarcodeGetSR12011ReportRequest>
         <DataInformation>
            <ReferenceNumber>225566</ReferenceNumber>
         </DataInformation>
         <BinaryInformation>
            <XmlDataBinary><![CDATA[<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<XmlData>
	<SR12011Info>
		<TaxpayerInfo>
			<CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName>
			<Tin>3-1015-1763-7</Tin>
			<LicenseNo>2558/70605817600002</LicenseNo>
			<EffectiveDate>20150101</EffectiveDate>
			<ExpireDate>20151231</ExpireDate>
			<TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName>
			<TaxpayerAddressInfo>
				<BuildingName/>
				<RoomNumber/>
				<FloorNumber/>
				<VillageName/>
				<HouseNumber>9/2</HouseNumber>
				<MooNumber>3</MooNumber>
				<TrokSoiName/>
				<StreetName/>
				<ThambolName>บางโทรัด</ThambolName>
				<AmphurName>เมืองสมุทรสาคร</AmphurName>
				<ProvinceName>สมุทรสาคร</ProvinceName>
				<Postcode>74000</Postcode>
				<TelNumber>0235558797</TelNumber>
			</TaxpayerAddressInfo>
		</TaxpayerInfo>
		<GoodsListInfo>
			<GoodsEntryInfo>
				<ProductCode>70010101#AQk#00#000#026#03#07</ProductCode>
				<CategoryCode1>70010101</CategoryCode1>
				<CategoryCode2>AQk</CategoryCode2>
				<CategoryCode3>00</CategoryCode3>
				<CategoryCode4>000</CategoryCode4>
				<CategoryCode5>026</CategoryCode5>
				<UnitCode>12</UnitCode>
				<RateFlag>R</RateFlag>
				<TaxQuantity>168.7500</TaxQuantity>
				<TaxQuantityNumber>0</TaxQuantityNumber>
				<TaxQuantityPerUnit>0</TaxQuantityPerUnit>
				<TaxValue>0</TaxValue>
				<PriceFlag>R</PriceFlag>
				<InformPrice>0</InformPrice>
				<DeclarePrice>0</DeclarePrice>
				<UnitPrice>0</UnitPrice>
				<GoodsNum>400</GoodsNum>
				<GoodsValue>12000</GoodsValue>
				<TaxAmount>0</TaxAmount>
				<SeqNo>1</SeqNo>
				<ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc>
				<GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc>
				<Degree>12.50</Degree>
				<GoodsSize>0.750</GoodsSize>
				<GoodsPiece>6000</GoodsPiece>
				<GoodsQuantity>4500.0000</GoodsQuantity>
				<TaxByValue>0.0000</TaxByValue>
				<TaxByQuantity>168.7500</TaxByQuantity>
				<TaxByQuantityOver>0.0000</TaxByQuantityOver>
				<TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver>
				<NetTaxByValue>0.00</NetTaxByValue>
				<NetTaxByQuantity>1012500.00</NetTaxByQuantity>
			</GoodsEntryInfo>
		</GoodsListInfo>
		<SummaryInfo>
			<SumAllTaxByValue>4930248.50</SumAllTaxByValue>
			<SumAllTaxByQuantity>9398750.00</SumAllTaxByQuantity>
			<SumAllTax>14328998.00</SumAllTax>
			<TaxLessType/>
			<TaxLessFrom/>
			<TaxLessAmount>0</TaxLessAmount>
			<TaxDeductionOnBookNo/>
			<TaxDeductionOnBookAmount>0</TaxDeductionOnBookAmount>
			<PaymentExciseAmount>14328998.00</PaymentExciseAmount>
			<PaymentMunicipalAmount>1432899.80</PaymentMunicipalAmount>
			<PaymentFundHealthAmount>315237.96</PaymentFundHealthAmount>
			<PaymentFundTVAmount>236428.47</PaymentFundTVAmount>
			<PaymentFundSportAmount>315237.96</PaymentFundSportAmount>
			<MoiRate>10</MoiRate>
			<PrintType>1</PrintType>
			<RecType>1</RecType>
			<PaymentExciseAndMunicipalTaxAmount>15761897.80</PaymentExciseAndMunicipalTaxAmount>
			<PaymentOtherAmount>0</PaymentOtherAmount>
			<PaymentNetTaxAmount>15761897.80</PaymentNetTaxAmount>
		</SummaryInfo>
	</SR12011Info>
	<SubmitOnlineHeader>
		<RegistratronId>002310</RegistratronId>
		<CusId>57220138</CusId>
		<CompanyId>885474</CompanyId>
		<CompanyUserId>bac</CompanyUserId>
		<CompanyUserPwd>bac</CompanyUserPwd>
		<TaxpayerId>49L15746</TaxpayerId>
		<ExciseOfficeId>4561</ExciseOfficeId>
		<InternetUniqueId>65256</InternetUniqueId>
		<IpAddress>111.22.33.44</IpAddress>
		<SubmissionEmail>my@mail.com</SubmissionEmail>
	</SubmitOnlineHeader>
</XmlData>]]>
            </XmlDataBinary>
         </BinaryInformation>
      </bar:EbarcodeGetSR12011ReportRequest>
   </soapenv:Body>
</soapenv:Envelope>

</textarea>
</c:if>
<textarea rows="25" cols="56" name="strxmlrt">
${strxmlrt}
</textarea>
<br>
<input type="submit" value="Submit">
</form>
</div>