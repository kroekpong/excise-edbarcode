package th.go.excise.edbarcode.ws.client.barcode.submitonline.test;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineRequest;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.oxm.EbarcodeSubmitOnlineResponse;
import th.go.excise.edbarcode.ws.client.barcode.submitonline.service.SubmitOnlineBackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:WebContent/WEB-INF/config/application-config.xml"
})
public class SubmitOnlineBackServiceTestCase {
	
	private static final Logger logger = LogManager.getLogger(SubmitOnlineBackServiceTestCase.class);
	
	private static final String XML_PATH = "th/go/excise/edbarcode/ws/client/barcode/submitonline/xml/";
	
	@Autowired
	private SubmitOnlineBackService submitOnlineBackService;
	
	private SOAPMessage getSoapMessage(String xmlFileName) throws SOAPException, IOException {
		InputStream xmlFile = ClassLoader.getSystemResourceAsStream(XML_PATH + xmlFileName);
		System.out.println(xmlFile);
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlFile);
		xmlFile.close();
		
		return soapMessage;
	}

	//@Test
	public void testCallSubmitOnlineWebService() {
		
		EbarcodeSubmitOnlineRequest request = null;
		
		try {
			SOAPMessage soapMessage = getSoapMessage("example.xml");
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSubmitOnlineRequest.class).createUnmarshaller();
			
			request = (EbarcodeSubmitOnlineRequest) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			System.out.println(request);
			
			EbarcodeSubmitOnlineResponse response = submitOnlineBackService.doService(request);
			System.out.println(response);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}

	}
	@Test
	public void testUnmarshallFromXmlString() {
		
		EbarcodeSubmitOnlineRequest request = null;
		
		String xmlString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bar=\"http://www.excise.go.th/xsd/barcode\">" +
				"   <soapenv:Header/>" +
				"   <soapenv:Body>" +
				"      <bar:EbarcodeSubmitOnlineRequest>" +
				"         <SubmitOnlineHeader>" +
				"            <RegistratronId>002310</RegistratronId>" +
				"            <CusId>57220138</CusId>" +
				"            <CompanyId>885474</CompanyId>" +
				"            <CompanyUserId>bac</CompanyUserId>" +
				"            <CompanyUserPwd>bac</CompanyUserPwd>" +
				"            <TaxpayerId>49L15746</TaxpayerId>" +
				"            <ExciseOfficeId>4561</ExciseOfficeId>" +
				"            <InternetUniqueId>65256</InternetUniqueId>" +
				"            <IpAddress>111.22.33.44</IpAddress>" +
				"            <!--Optional:-->" +
				"            <SubmissionEmail>my@mail.com</SubmissionEmail>" +
				"         </SubmitOnlineHeader>" +
				"         <SR12011Info>" +
				"            <!--Optional:-->" +
				"            <TaxpayerInfo>" +
				"               <CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName>" +
				"               <Tin>3-1015-1763-7</Tin>" +
				"               <LicenseNo>2558/70605817600002</LicenseNo>" +
				"               <EffectiveDate>20150101</EffectiveDate>" +
				"               <ExpireDate>20151231</ExpireDate>" +
				"               <TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName>" +
				"               <TaxpayerAddressInfo>" +
				"                  <!--Optional:-->" +
				"                  <BuildingName></BuildingName>" +
				"                  <!--Optional:-->" +
				"                  <RoomNumber></RoomNumber>" +
				"                  <!--Optional:-->" +
				"                  <FloorNumber></FloorNumber>" +
				"                  <!--Optional:-->" +
				"                  <VillageName></VillageName>" +
				"                  <!--Optional:-->" +
				"                  <HouseNumber>9/2</HouseNumber>" +
				"                  <!--Optional:-->" +
				"                  <MooNumber>3</MooNumber>" +
				"                  <!--Optional:-->" +
				"                  <TrokSoiName></TrokSoiName>" +
				"                  <!--Optional:-->" +
				"                  <StreetName></StreetName>" +
				"                  <ThambolName>บางโทรัด</ThambolName>" +
				"                  <AmphurName>เมืองสมุทรสาคร</AmphurName>" +
				"                  <ProvinceName>สมุทรสาคร</ProvinceName>" +
				"                  <!--Optional:-->" +
				"                  <Postcode>74000</Postcode>" +
				"                  <!--Optional:-->" +
				"                  <TelNumber>0235558797</TelNumber>" +
				"               </TaxpayerAddressInfo>" +
				"            </TaxpayerInfo>" +
				"            <!--Optional:-->" +
				"            <GoodsListInfo>" +
				"               <!--Zero or more repetitions:-->" +
				"               <GoodsEntryInfo>" +
				"                  <ProductCode>70010101#AQk#00#000#026#03#07</ProductCode>" +
				"                  <CategoryCode1>70010101</CategoryCode1>" +
				"                  <CategoryCode2>AQk</CategoryCode2>" +
				"                  <CategoryCode3>00</CategoryCode3>" +
				"                  <CategoryCode4>000</CategoryCode4>" +
				"                  <CategoryCode5>026</CategoryCode5>" +
				"                  <UnitCode>12</UnitCode>" +
				"                  <RateFlag>R</RateFlag>" +
				"                  <TaxQuantity>168.7500</TaxQuantity>" +
				"                  <TaxQuantityNumber>0</TaxQuantityNumber>" +
				"                  <TaxQuantityPerUnit>0</TaxQuantityPerUnit>" +
				"                  <TaxValue>0</TaxValue>" +
				"                  <PriceFlag>R</PriceFlag>" +
				"                  <InformPrice>0</InformPrice>" +
				"                  <DeclarePrice>0</DeclarePrice>" +
				"                  <UnitPrice>0</UnitPrice>" +
				"                  <GoodsNum>400</GoodsNum>" +
				"                  <GoodsValue>12000</GoodsValue>" +
				"                  <TaxAmount>0</TaxAmount>" +
				"                  <SeqNo>1</SeqNo>" +
				"                  <ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc>" +
				"                  <GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc>" +
				"                  <Degree>12.50</Degree>" +
				"                  <GoodsSize>0.750</GoodsSize>" +
				"                  <GoodsPiece>6000</GoodsPiece>" +
				"                  <GoodsQuantity>4500.0000</GoodsQuantity>" +
				"                  <TaxByValue>0.0000</TaxByValue>" +
				"                  <TaxByQuantity>168.7500</TaxByQuantity>" +
				"                  <TaxByQuantityOver>0.0000</TaxByQuantityOver>" +
				"                  <TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver>" +
				"                  <NetTaxByValue>0.00</NetTaxByValue>" +
				"                  <NetTaxByQuantity>1012500.00</NetTaxByQuantity>" +
				"               </GoodsEntryInfo>" +
				"            </GoodsListInfo>" +
				"            <!--Optional:-->" +
				"            <SummaryInfo>" +
				"               <SumAllTaxByValue>4930248.50</SumAllTaxByValue>" +
				"               <SumAllTaxByQuantity>9398750.00</SumAllTaxByQuantity>" +
				"               <SumAllTax>14328998.00</SumAllTax>" +
				"               <!--Optional:-->" +
				"               <TaxLessType></TaxLessType>" +
				"               <!--Optional:-->" +
				"               <TaxLessFrom></TaxLessFrom>" +
				"               <!--Optional:-->" +
				"               <TaxLessAmount></TaxLessAmount>" +
				"               <!--Optional:-->" +
				"               <TaxDeductionOnBookNo></TaxDeductionOnBookNo>" +
				"               <!--Optional:-->" +
				"               <TaxDeductionOnBookAmount></TaxDeductionOnBookAmount>" +
				"               <PaymentExciseAmount>14328998.00</PaymentExciseAmount>" +
				"               <PaymentMunicipalAmount>1432899.80</PaymentMunicipalAmount>" +
				"               <PaymentFundHealthAmount>315237.96</PaymentFundHealthAmount>" +
				"               <PaymentFundTVAmount>236428.47</PaymentFundTVAmount>" +
				"               <PaymentFundSportAmount>315237.96</PaymentFundSportAmount>" +
				"               <MoiRate>10</MoiRate>" +
				"               <PrintType>1</PrintType>" +
				"               <RecType>1</RecType>" +
				"               <PaymentExciseAndMunicipalTaxAmount>15761897.80</PaymentExciseAndMunicipalTaxAmount>" +
				"               <!--Optional:-->" +
				"               <PaymentOtherAmount></PaymentOtherAmount>" +
				"               <PaymentNetTaxAmount>15761897.80</PaymentNetTaxAmount>" +
				"            </SummaryInfo>" +
				"         </SR12011Info>" +
				"      </bar:EbarcodeSubmitOnlineRequest>" +
				"   </soapenv:Body>" +
				"</soapenv:Envelope>";
		
		try {
			InputStream xmlInputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			System.out.println(xmlInputStream);
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), xmlInputStream);
			xmlInputStream.close();
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(EbarcodeSubmitOnlineRequest.class).createUnmarshaller();
			
			request = (EbarcodeSubmitOnlineRequest) unmarshaller.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			System.out.println(request);
			
//			EbarcodeSubmitOnlineResponse response = submitOnlineBackService.doService(request);
//			System.out.println(response);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		
	}

}
