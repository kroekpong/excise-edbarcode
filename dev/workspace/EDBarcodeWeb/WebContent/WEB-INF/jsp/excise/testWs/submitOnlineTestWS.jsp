<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="fadeIn active">
	<h3>EDBarcodeWeb SubmitOnline</h3>
	<form action="/EDBarcodeWeb/doControllerSubmitWeb.htm" method="post">

		<table style="height: 500px;">
		
			<tr>
				<td><label >URI</label><input type="text"  id="strurl" name="strurl" value="${strurl}" style="width: 425px;" /></td>
			</tr>
			
			<tr>
				<td><br></td>
			</tr>
			
			<tr>
				<td><input type="submit" /></td>
			</tr>

			<tr>
				<td><br></td>
			</tr>

			<tr>
				<td>Request</td>
				<td>Response</td>
			</tr>

			<tr>
				<td>
					<textarea rows="1" cols="1"
						style="height: 500px; width: 500px;" name="strInput" ><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bar="http://www.excise.go.th/xsd/barcode">
   <soapenv:Header/>
   <soapenv:Body>
      <bar:EbarcodeSubmitOnlineRequest>
         <SubmitOnlineHeader>
            <!--Optional:-->
            <RegistratronId>002310</RegistratronId>
            <CusId>57220138</CusId>
            <CompanyId>885474</CompanyId>
            <CompanyUserId>bac</CompanyUserId>
            <CompanyUserPwd>bac</CompanyUserPwd>
            <TaxpayerId>49L15746</TaxpayerId>
            <ExciseOfficeId>4561</ExciseOfficeId>
            <InternetUniqueId>65256</InternetUniqueId>
            <IpAddress>111.22.33.44</IpAddress>
            <!--Optional:-->
            <SubmissionEmail>my@mail.com</SubmissionEmail>
         </SubmitOnlineHeader>
         <SR12011Info>
            <!--Optional:-->
            <TaxpayerInfo>
               <CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName>
               <Tin>3-1015-1763-7</Tin>
               <LicenseNo>2558/70605817600002</LicenseNo>
               <EffectiveDate>20150101</EffectiveDate>
               <ExpireDate>20151231</ExpireDate>
               <TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName>
               <TaxpayerAddressInfo>
                  <!--Optional:-->
                  <HouseNumber>9/2</HouseNumber>
                  <!--Optional:-->
                  <MooNumber>3</MooNumber>
                  <!--Optional:-->
                  <TrokSoiName/>
                  <!--Optional:-->
                  <StreetName/>
                  <ThambolName>บางโทรัด</ThambolName>
                  <AmphurName>เมืองสมุทรสาคร</AmphurName>
                  <ProvinceName>สมุทรสาคร</ProvinceName>
                  <!--Optional:-->
                  <Postcode>74000</Postcode>
                  <!--Optional:-->
                  <TelNumber>0235558797</TelNumber>
               </TaxpayerAddressInfo>
            </TaxpayerInfo>
            <!--Optional:-->
            <GoodsListInfo>
               <!--Zero or more repetitions:-->
               <GoodsEntryInfo>
                  <ProductCode>70010101#AQk#00#000#026#03#07</ProductCode>
                  <CategoryCode1>70010101</CategoryCode1>
                  <CategoryCode2>AQk</CategoryCode2>
                  <CategoryCode3>00</CategoryCode3>
                  <CategoryCode4>000</CategoryCode4>
                  <CategoryCode5>026</CategoryCode5>
                  <UnitCode>12</UnitCode>
                  <!--Optional:-->
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
            <!--Optional:-->
            <SummaryInfo>
               <SumAllTaxByValue>4930248.50</SumAllTaxByValue>
               <SumAllTaxByQuantity>9398750.00</SumAllTaxByQuantity>
               <SumAllTax>14328998.00</SumAllTax>
               <!--Optional:-->
               <TaxLessType/>
               <!--Optional:-->
               <TaxLessFrom/>
               <!--Optional:-->
               <TaxLessAmount/>
               <!--Optional:-->
               <TaxDeductionOnBookNo/>
               <!--Optional:-->
               <TaxDeductionOnBookAmount/>
               <PaymentExciseAmount>14328998.00</PaymentExciseAmount>
               <PaymentMunicipalAmount>1432899.80</PaymentMunicipalAmount>
               <PaymentFundHealthAmount>315237.96</PaymentFundHealthAmount>
               <PaymentFundTVAmount>236428.47</PaymentFundTVAmount>
               <PaymentFundSportAmount>315237.96</PaymentFundSportAmount>
               <MoiRate>10</MoiRate>
               <PrintType>1</PrintType>
               <PaymentExciseAndMunicipalTaxAmount>15761897.80</PaymentExciseAndMunicipalTaxAmount>
               <!--Optional:-->
               <PaymentOtherAmount/>
               <PaymentNetTaxAmount>15761897.80</PaymentNetTaxAmount>
            </SummaryInfo>
         </SR12011Info>
      </bar:EbarcodeSubmitOnlineRequest>
   </soapenv:Body>
</soapenv:Envelope></textarea>
				</td>

				<td><textarea rows="1" cols="1"
						style="height: 500px; width: 500px;" name="strXML">${strXML}</textarea>
				</td>
			</tr>

		</table>

	</form>
	<form action="/EDBarcodeWeb/menuTestWS.htm" method="GET">
		<input type="submit" value="back" />
	</form>
</div>