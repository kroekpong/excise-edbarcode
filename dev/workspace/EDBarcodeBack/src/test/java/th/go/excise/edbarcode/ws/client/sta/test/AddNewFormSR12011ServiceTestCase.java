package th.go.excise.edbarcode.ws.client.sta.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import local.scc.dev.sta.bac.utils.PasswordEncryptDecrypt;
import local.scc.dev.system.rmi.SccAppServer_Stub;

import org.junit.Test;

import th.go.excise.edbarcode.common.constant.WebServiceConstant;
import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;

public class AddNewFormSR12011ServiceTestCase {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public StaBacRequest createRequestObject() {
		// Prepare Request Header
		th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest wsHeader = new th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest();
		wsHeader.setSourceSystem(WebServiceConstant.STA.SYSTEM_BARCODE);
		wsHeader.setDestinationSystem(WebServiceConstant.STA.SYSTEM_STA_BACK);
		wsHeader.setTransactionCode(WebServiceConstant.STA.TRAN_CODE_ADD_NEW_FORM_SR12011);
		
		Date currentDate = new Date();
		th.go.excise.edbarcode.ws.client.sta.oxm.DataInformation wsDataInformation = new th.go.excise.edbarcode.ws.client.sta.oxm.DataInformation();
		wsDataInformation.setCusId("51175744");
		wsDataInformation.setCompanyId("3031163126");
		wsDataInformation.setCompanyName("ห้างหุ้นส่วนจำกัด ตะวันแดงสุรากลั่น");
		wsDataInformation.setTaxpayerId("49L15746");
		wsDataInformation.setTaxpayerName("ตะวันแดงสุรากลั่น");
		wsDataInformation.setExciseOfficeId("060301");
		wsDataInformation.setFormPeriodBeginDate(DateUtils.wsDateFormat.format(currentDate));
		wsDataInformation.setFormPeriodEndDate(DateUtils.wsDateFormat.format(currentDate));
		wsDataInformation.setFormPeriodResubmissionCode("");
		wsDataInformation.setPaymentExciseAmount("14328998.00");
		wsDataInformation.setPaymentMunicipalAmount("1432899.80");
		wsDataInformation.setPaymentFundHealthAmount("315237.96");
		wsDataInformation.setPaymentFundTVAmount("236428.47");
		wsDataInformation.setPaymentFundSportAmount("315237.96");
		wsDataInformation.setInternetUniqueId("bac");
		
		th.go.excise.edbarcode.ws.client.sta.oxm.BinaryInformation wsBinaryInformation = new th.go.excise.edbarcode.ws.client.sta.oxm.BinaryInformation();
		wsBinaryInformation.setFormDataBinary("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><SR12011FormReport><TaxpayerInfoReport><CusId>57220138</CusId><TaxpayerId>49L15746</TaxpayerId><Tin>3-1015-1763-7</Tin><CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName><LicenseNo>2558/70605817600002</LicenseNo><EffectiveDate>20150101</EffectiveDate><ExpireDate>20151231</ExpireDate><TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName><TaxpayerAddressReport><HouseIdNumber></HouseIdNumber><BuildingName></BuildingName><RoomNumber></RoomNumber><FloorNumber></FloorNumber><VillageName></VillageName><HouseNumber>9/2</HouseNumber><MooNumber>3</MooNumber><TrokSoiName></TrokSoiName><StreetName></StreetName><ThambolName>บางโทรัด</ThambolName><AmphurName>เมืองสมุทรสาคร</AmphurName><ProvinceName>สมุทรสาคร</ProvinceName><Postcode>74000</Postcode><TelNumber></TelNumber><EmailAddress></EmailAddress></TaxpayerAddressReport></TaxpayerInfoReport><GoodsListReport><GoodsEntryReport><SeqNo>1</SeqNo><ProductTypeCode>70010101#AQk#00#000#026#03#07</ProductTypeCode><ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc><GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc><Degree>12.50</Degree><GoodsSize>0.750</GoodsSize><GoodsPiece>6000</GoodsPiece><GoodsQuantity>4500.0000</GoodsQuantity><UnitPrice>455.6200</UnitPrice><DeclarePrice>0.0000</DeclarePrice><TaxByValue>0.0000</TaxByValue><TaxByQuantity>168.7500</TaxByQuantity><TaxByQuantityOver>0.0000</TaxByQuantityOver><TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver><NetTaxByValue>0.00</NetTaxByValue><NetTaxByQuantity>1012500.00</NetTaxByQuantity></GoodsEntryReport><GoodsEntryReport><SeqNo>2</SeqNo><ProductTypeCode>70010101#AQk#00#000#026#03#08</ProductTypeCode><ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc><GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc><Degree>12.00</Degree><GoodsSize>0.750</GoodsSize><GoodsPiece>6000</GoodsPiece><GoodsQuantity>4500.0000</GoodsQuantity><UnitPrice>455.6200</UnitPrice><DeclarePrice>0.0000</DeclarePrice><TaxByValue>0.0000</TaxByValue><TaxByQuantity>168.7500</TaxByQuantity><TaxByQuantityOver>0.0000</TaxByQuantityOver><TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver><NetTaxByValue>0.00</NetTaxByValue><NetTaxByQuantity>1012500.00</NetTaxByQuantity></GoodsEntryReport><GoodsEntryReport><SeqNo>3</SeqNo><ProductTypeCode>70010101#AQk#00#000#026#03#09</ProductTypeCode><ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc><GoodsDesc>ไวน์ Monsoon Valley White Wine Shiraz</GoodsDesc><Degree>13.50</Degree><GoodsSize>0.750</GoodsSize><GoodsPiece>5000</GoodsPiece><GoodsQuantity>3750.0000</GoodsQuantity><UnitPrice>455.6200</UnitPrice><DeclarePrice>0.0000</DeclarePrice><TaxByValue>0.0000</TaxByValue><TaxByQuantity>168.7500</TaxByQuantity><TaxByQuantityOver>0.0000</TaxByQuantityOver><TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver><NetTaxByValue>0.00</NetTaxByValue><NetTaxByQuantity>843750.00</NetTaxByQuantity></GoodsEntryReport></GoodsListReport><SummaryReport><SumAllTaxByValue>4930248.50</SumAllTaxByValue><SumAllTaxByQuantity>9398750.00</SumAllTaxByQuantity><SumAllTax>14328998.00</SumAllTax><TaxLessType></TaxLessType><TaxLessFrom></TaxLessFrom><TaxLessAmount></TaxLessAmount><TaxDeductionOnBookNo></TaxDeductionOnBookNo><TaxDeductionOnBookAmount></TaxDeductionOnBookAmount><PaymentExciseAmount>14328998.00</PaymentExciseAmount><PaymentMunicipalPercent>10</PaymentMunicipalPercent><PaymentMunicipalAmount>1432899.80</PaymentMunicipalAmount><PaymentExciseAndMunicipalTaxAmount>15761897.80</PaymentExciseAndMunicipalTaxAmount><PaymentOtherAmount></PaymentOtherAmount><PaymentNetTaxAmount>15761897.80</PaymentNetTaxAmount><PrintType>1</PrintType><PaymentFundHealthAmount>315237.96</PaymentFundHealthAmount><PaymentFundTVAmount>236428.47</PaymentFundTVAmount><PaymentFundSportAmount>315237.96</PaymentFundSportAmount></SummaryReport></SR12011FormReport>]]>");
		
		th.go.excise.edbarcode.ws.client.sta.oxm.Body wsBody = new th.go.excise.edbarcode.ws.client.sta.oxm.Body();
		wsBody.setDataInformation(wsDataInformation);
		wsBody.setBinaryInformation(wsBinaryInformation);
		
		th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest wsRequest = new th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest();
		wsRequest.setHeader(wsHeader);
		wsRequest.setBody(wsBody);
		
		return wsRequest;
	}
	
	@Test
	public void testCallRmi() {
		
		// Create Request
		StaBacRequest request = createRequestObject();
		
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(StaBacRequest.class).createMarshaller();
			
			StringWriter writer = new StringWriter();
			StringWriter writer2 = new StringWriter();
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "TIS-620");
			jaxbMarshaller.marshal(request, writer);
			
			
			String xmlData = writer.getBuffer().toString();
			System.out.println("RMI Request: " + xmlData);
			writer.close();
			
			//
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(request, writer2);
			System.out.println("RMI Request UTF-8: " + writer2.getBuffer().toString());
			writer.close();
			
			String xmlResult = sendBacRequest(xmlData);
			System.out.println("RMI Response: " + xmlResult);
			
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(StaBacResponse.class).createUnmarshaller();
			
			StringReader reader = new StringReader(xmlResult);
			StaBacResponse response = (StaBacResponse) jaxbUnmarshaller.unmarshal(reader);
			reader.close();
			
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String sendBacRequest(String xmlData) {
		String xmlResult;
		SccAppServer_Stub sccAppServer;
		//String rmiUrl = "rmi://172.17.2.22:21104/SccAppServer";
		String rmiUrl = "rmi://192.168.42.4:1104/SccAppServer";

		try {
			long startTime = System.currentTimeMillis();
			sccAppServer = (SccAppServer_Stub) Naming.lookup(rmiUrl);
			System.out.println(" #### Lookup Result :" + Naming.lookup(rmiUrl));

			xmlResult = sccAppServer.process(xmlData);

			long finishTime = System.currentTimeMillis();
			System.out.println("TIME : " + (finishTime - startTime) + "mesc");
			return xmlResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
