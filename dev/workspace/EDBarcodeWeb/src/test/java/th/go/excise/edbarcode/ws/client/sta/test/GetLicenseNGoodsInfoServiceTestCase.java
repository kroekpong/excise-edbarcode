package th.go.excise.edbarcode.ws.client.sta.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import local.scc.dev.sta.bac.utils.PasswordEncryptDecrypt;

import org.junit.Test;

import th.go.excise.edbarcode.common.util.DateUtils;
import th.go.excise.edbarcode.ws.client.sta.oxm.Body;
import th.go.excise.edbarcode.ws.client.sta.oxm.HeaderRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.InternetUser;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacRequest;
import th.go.excise.edbarcode.ws.client.sta.oxm.StaBacResponse;
import th.go.excise.edbarcode.ws.client.sta.service.GetLicenseNGoodsInfoService;
import th.go.excise.edbarcode.ws.client.sta.service.GetLicenseNGoodsInfoServiceImpl;

public class GetLicenseNGoodsInfoServiceTestCase {
	
	private StaBacRequest createRequestObject() {
		PasswordEncryptDecrypt staPasswordUtil = new PasswordEncryptDecrypt();
		Date currentDate = new Date();
		
		String companyId = "3031163126";
		String userId = "bac";
		String password = "bac";
		
		// Create Header Request
		HeaderRequest header = new HeaderRequest();
		header.setSourceSystem("BARCODE");
		header.setDestinationSystem("STABAC");
		header.setTransactionCode("GetLicenseNGoodsInfo");
		
		// Create Body Request
		InternetUser internetUser = new InternetUser();
		internetUser.setCompanyId(companyId);
		internetUser.setCompanyUserId(userId);
		internetUser.setCompanyUserPwd(staPasswordUtil.encrypt(userId, password));
		internetUser.setBeginDate(DateUtils.wsDateFormat.format(currentDate));
		internetUser.setEndDate(DateUtils.wsDateFormat.format(currentDate));
		Body body = new Body();
		body.setInternetUser(internetUser);
		
		StaBacRequest request = new StaBacRequest();
		request.setHeader(header);
		request.setBody(body);
		
		return request;
	}
	
	@Test
	public void testEncryptPassword() {
		System.out.println("Case: testEncryptPassword");
		
		PasswordEncryptDecrypt staPasswordUtil = new PasswordEncryptDecrypt();
		
		String userId = "bac";
		String password = "bac";
		String passwordEnceypt = "YmFjI2JhYw==";
		
		System.out.println(staPasswordUtil.encrypt(userId, password));
		assertEquals(passwordEnceypt, staPasswordUtil.encrypt(userId, password));
	}
	
	@Test
	public void testDecryptPassword() {
		System.out.println("Case: testDecryptPassword");
		
		PasswordEncryptDecrypt staPasswordUtil = new PasswordEncryptDecrypt();
		
		String password = "bac";
		String passwordEnceypt = "YmFjI2JhYw==";
		
		System.out.println(staPasswordUtil.decrypt(passwordEnceypt));
		assertEquals(password, staPasswordUtil.decrypt(passwordEnceypt));
	}
	
	@Test
	public void testCreateRequest() {
		System.out.println("Case: testCreateRequest");
		
		StaBacRequest request = createRequestObject();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StaBacRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			StringWriter writer = new StringWriter();
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "TIS-620");
			
			jaxbMarshaller.marshal(request, writer);
			
			System.out.println(writer.getBuffer().toString());
			writer.close();
			
		} catch (JAXBException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testTransferXmlResponseToObject() {
		System.out.println("Case: testTransferXmlResponseToObject");
		
		String inputXml =
			"<ns2:StaBacResponse xmlns:ns2=\"http://www.example.org/StaBacSchema\">" +
			"	<Header>" +
			"		<SourceSystem>STABAC</SourceSystem>" +
			"		<DestinationSystem>STA</DestinationSystem>" +
			"		<ResultCode>OK</ResultCode>" +
			"	</Header>" +
			"	<Body>" +
			"		<Company>" +
			"			<CusId>51175744</CusId>" +
			"			<CompanyId>3031163126</CompanyId>" +
			"			<CompanyTitleCode>0013</CompanyTitleCode>" +
			"			<CompanyTitleName>เธ&#xfffd;.</CompanyTitleName>" +
			"			<CompanyName>เธ&#xfffd;เธฃเธดเธฉเธฑเธ&#xfffd; เธชเธธเธฃเธฒเธชเธฃเธฃเธ&#xfffd;เธชเธฒเธกเธดเธ&#xfffd; เธ&#xfffd;เธณเธ&#xfffd;เธฑเธ&#xfffd; </CompanyName>" +
			"			<CompanyBranchCode>0</CompanyBranchCode>" +
			"			<ExciseOfficeId>060102</ExciseOfficeId>" +
			"			<CompanyUserPwd>YmFjI2JhYw==</CompanyUserPwd>" +
			"			<CompanyAddress>" +
			"				<HouseIdNumber/>" +
			"				<BuildingName/>" +
			"				<RoomNumber/>" +
			"				<FloorNumber/>" +
			"				<VillageName/>" +
			"				<HouseNumber>157</HouseNumber>" +
			"				<MooNumber>7</MooNumber>" +
			"				<TrokSoiName>-</TrokSoiName>" +
			"				<StreetName>-</StreetName>" +
			"				<ThambolCode>630114</ThambolCode>" +
			"				<ThambolName>เธงเธฑเธ&#xfffd;เธ&#xfffd;เธฃเธฐเธ&#xfffd;เธ&#xfffd;</ThambolName>" +
			"				<AmphurCode>6301</AmphurCode>" +
			"				<AmphurName>เน&#xfffd;เธกเธทเธญเธ&#xfffd;เธ&#xfffd;เธฒเธ&#xfffd;</AmphurName>" +
			"				<ProvinceCode>63</ProvinceCode>" +
			"				<ProvinceName>เธ&#xfffd;เธฒเธ&#xfffd;</ProvinceName>" +
			"				<Postcode>63000</Postcode>" +
			"				<TelNumber>06-0215411</TelNumber>" +
			"				<EmailAddress/>" +
			"			</CompanyAddress>" +
			"			<TaxpayerList>" +
			"				<TaxpayerDetail>" +
			"					<TaxpayerId>49L15746</TaxpayerId>" +
			"					<TaxpayerName>เธ&#xfffd;เธฐเธงเธฑเธ&#xfffd;เน&#xfffd;เธ&#xfffd;เธ&#xfffd;เธชเธธเธฃเธฒเธ&#xfffd;เธฅเธฑเน&#xfffd;เธ&#xfffd;</TaxpayerName>" +
			"					<TaxpayerAddress>" +
			"						<HouseIdNumber/>" +
			"						<BuildingName/>" +
			"						<RoomNumber/>" +
			"						<FloorNumber/>" +
			"						<VillageName/>" +
			"						<HouseNumber/>" +
			"						<MooNumber>7</MooNumber>" +
			"						<TrokSoiName>-</TrokSoiName>" +
			"						<StreetName>0026</StreetName>" +
			"						<ThambolCode>630114</ThambolCode>" +
			"						<Postcode>63000</Postcode>" +
			"						<TelNumber>06-0215411</TelNumber>" +
			"						<EmailAddress/>" +
			"					</TaxpayerAddress>" +
			"					<LicenseList>" +
			"						<LicenseInfo>" +
			"							<LicenseNo>60305817500001</LicenseNo>" +
			"							<LicenseType>เธช.1/72</LicenseType>" +
			"							<LicenseCode>18</LicenseCode>" +
			"							<LicenseName>เธชเธธเธฃเธฒเธ&#xfffd;เธฅเธฑเน&#xfffd;เธ&#xfffd; (เธชเธธเธฃเธฒเธ&#xfffd;เธธเธกเธ&#xfffd;เธ&#xfffd;)</LicenseName>" +
			"							<EffectiveDate>20150115</EffectiveDate>" +
			"							<ExpireDate>20151231</ExpireDate>" +
			"							<LicBook>2558</LicBook>" +
			"							<LicDate>20150115</LicDate>" +
			"							<DocNo>255800000144</DocNo>" +
			"							<DocType>05</DocType>" +
			"							<LineNo>2</LineNo>" +
			"						</LicenseInfo>" +
			"					</LicenseList>" +
			"					<GoodsList>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#001,00,000,008,97#1#0#00##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD   0.330 4.9000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.330</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText/>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.9000</Degree>" +
			"							<PriceFlag>P</PriceFlag>" +
			"							<UnitCode>00</UnitCode>" +
			"							<GoodsUnitsDescriptionText/>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>1</SeqNo>" +
			"							<BrandName>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#001,00,000,011,97#2#0#00##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD   0.630 4.9000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.630</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText/>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.9000</Degree>" +
			"							<PriceFlag>P</PriceFlag>" +
			"							<UnitCode>00</UnitCode>" +
			"							<GoodsUnitsDescriptionText/>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>2</SeqNo>" +
			"							<BrandName>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#004,00,000,011,99#3#0#07##-</GoodsCode>" +
			"							<GoodsDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;เน&#xfffd;เธฎเน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;(เธ&#xfffd;เธงเธ&#xfffd;) 0.640   0.630 4.5000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.630</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsSizeUnitDescriptionText>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.5000</Degree>" +
			"							<PriceFlag>P</PriceFlag>" +
			"							<UnitCode>07</UnitCode>" +
			"							<GoodsUnitsDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsUnitsDescriptionText>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>3</SeqNo>" +
			"							<BrandName>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;เน&#xfffd;เธฎเน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;(เธ&#xfffd;เธงเธ&#xfffd;) 0.640</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#011,00,000,010,BC#4#0#07##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธญเธฑเธกเธชเน&#xfffd;เธ&#xfffd;เธฅ   0.625 4.3000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.625</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsSizeUnitDescriptionText>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.3000</Degree>" +
			"							<PriceFlag>P</PriceFlag>" +
			"							<UnitCode>07</UnitCode>" +
			"							<GoodsUnitsDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsUnitsDescriptionText>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>4</SeqNo>" +
			"							<BrandName>เธญเธฑเธกเธชเน&#xfffd;เธ&#xfffd;เธฅ</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#073,00,000,040,AY#5#0#08##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธ&#xfffd;เธญเธขเธซเธฅเธงเธ&#xfffd;   0.072 3.8000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.072</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText>เธ&#xfffd;เธฃเธฐเธ&#xfffd;เน&#xfffd;เธญเธ&#xfffd;</GoodsSizeUnitDescriptionText>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>3.8000</Degree>" +
			"							<PriceFlag>P</PriceFlag>" +
			"							<UnitCode>08</UnitCode>" +
			"							<GoodsUnitsDescriptionText>เธ&#xfffd;เธฃเธฐเธ&#xfffd;เน&#xfffd;เธญเธ&#xfffd;</GoodsUnitsDescriptionText>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>5</SeqNo>" +
			"							<BrandName>เธ&#xfffd;เธญเธขเธซเธฅเธงเธ&#xfffd;</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"					</GoodsList>" +
			"				</TaxpayerDetail>" +
			"				<TaxpayerDetail>" +
			"					<TaxpayerId>53B00128</TaxpayerId>" +
			"					<TaxpayerName>เธ&#xfffd;เธฐเธงเธฑเธ&#xfffd;เน&#xfffd;เธ&#xfffd;เธ&#xfffd;เธชเธธเธฃเธฒเธ&#xfffd;เธฅเธฑเน&#xfffd;เธ&#xfffd;</TaxpayerName>" +
			"					<TaxpayerAddress>" +
			"						<HouseIdNumber/>" +
			"						<BuildingName/>" +
			"						<RoomNumber/>" +
			"						<FloorNumber/>" +
			"						<VillageName/>" +
			"						<HouseNumber/>" +
			"						<MooNumber>7</MooNumber>" +
			"						<TrokSoiName>-</TrokSoiName>" +
			"						<StreetName>0026</StreetName>" +
			"						<ThambolCode>650801</ThambolCode>" +
			"						<Postcode>65130</Postcode>" +
			"						<TelNumber>06-0215411</TelNumber>" +
			"						<EmailAddress/>" +
			"					</TaxpayerAddress>" +
			"					<LicenseList>" +
			"						<LicenseInfo>" +
			"							<LicenseNo>70605817400002</LicenseNo>" +
			"							<LicenseType>เธช.1/72</LicenseType>" +
			"							<LicenseCode>13</LicenseCode>" +
			"							<LicenseName>เธชเธธเธฃเธฒเธ&#xfffd;เธฅเธฑเน&#xfffd;เธ&#xfffd; (เน&#xfffd;เธฃเธ&#xfffd;เน&#xfffd;เธซเธ&#xfffd;เน&#xfffd;)</LicenseName>" +
			"							<EffectiveDate>20150113</EffectiveDate>" +
			"							<ExpireDate>20151231</ExpireDate>" +
			"							<LicBook>2558</LicBook>" +
			"							<LicDate>20150113</LicDate>" +
			"							<DocNo>255800000142</DocNo>" +
			"							<DocType>05</DocType>" +
			"							<LineNo>4</LineNo>" +
			"						</LicenseInfo>" +
			"					</LicenseList>" +
			"					<GoodsList>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#001,00,000,011,97#1#0#00##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD   0.630 4.9000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.630</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText/>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.9000</Degree>" +
			"							<PriceFlag>F</PriceFlag>" +
			"							<UnitCode>00</UnitCode>" +
			"							<GoodsUnitsDescriptionText/>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>1</SeqNo>" +
			"							<BrandName>เธชเธดเธ&#xfffd;เธซเน&#xfffd; OLD</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#004,00,000,011,99#2#0#07##-</GoodsCode>" +
			"							<GoodsDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;เน&#xfffd;เธฎเน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;(เธ&#xfffd;เธงเธ&#xfffd;) 0.640   0.630 4.5000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.630</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsSizeUnitDescriptionText>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.5000</Degree>" +
			"							<PriceFlag>F</PriceFlag>" +
			"							<UnitCode>07</UnitCode>" +
			"							<GoodsUnitsDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsUnitsDescriptionText>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>2</SeqNo>" +
			"							<BrandName>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;เน&#xfffd;เธฎเน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;เน&#xfffd;เธ&#xfffd;(เธ&#xfffd;เธงเธ&#xfffd;) 0.640</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"						<Goods>" +
			"							<GoodsCode>70010101#011,00,000,010,BC#3#0#07##-</GoodsCode>" +
			"							<GoodsDescriptionText>เธญเธฑเธกเธชเน&#xfffd;เธ&#xfffd;เธฅ   0.625 4.3000</GoodsDescriptionText>" +
			"							<ProductTypeCode>70010101</ProductTypeCode>" +
			"							<ProductTypeDescriptionText>เน&#xfffd;เธ&#xfffd;เธตเธขเธฃเน&#xfffd;</ProductTypeDescriptionText>" +
			"							<GoodsSize>0.625</GoodsSize>" +
			"							<GoodsSizeUnitDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsSizeUnitDescriptionText>" +
			"							<GoodsPrice>0.0</GoodsPrice>" +
			"							<TaxRateByPriceAmount>0.0</TaxRateByPriceAmount>" +
			"							<TaxRateByQuantityAmount>0.0</TaxRateByQuantityAmount>" +
			"							<FundSSSRateAmount>0.0</FundSSSRateAmount>" +
			"							<FundSSTRateAmount>0.0</FundSSTRateAmount>" +
			"							<Degree>4.3000</Degree>" +
			"							<PriceFlag>F</PriceFlag>" +
			"							<UnitCode>07</UnitCode>" +
			"							<GoodsUnitsDescriptionText>เธ&#xfffd;เธงเธ&#xfffd;</GoodsUnitsDescriptionText>" +
			"							<IncomeCode>204020</IncomeCode>" +
			"							<SeqNo>3</SeqNo>" +
			"							<BrandName>เธญเธฑเธกเธชเน&#xfffd;เธ&#xfffd;เธฅ</BrandName>" +
			"							<SubbrandName/>" +
			"							<ModelName/>" +
			"							<InformDate/>" +
			"							<ProductNameEng> </ProductNameEng>" +
			"							<RatePerLitre>0.0</RatePerLitre>" +
			"							<DegreeMin>0.0</DegreeMin>" +
			"							<RateDegreeOver>0.0</RateDegreeOver>" +
			"							<WholesaleMin>0.0</WholesaleMin>" +
			"							<RateWholesaleOver>0.0</RateWholesaleOver>" +
			"							<RatePerLitreMax>0.0</RatePerLitreMax>" +
			"						</Goods>" +
			"					</GoodsList>" +
			"				</TaxpayerDetail>" +
			"			</TaxpayerList>" +
			"		</Company>" +
			"	</Body>" +
			"</ns2:StaBacResponse>";
		
		try {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(StaBacResponse.class).createUnmarshaller();
			
			StringReader reader = new StringReader(inputXml);
			StaBacResponse response = (StaBacResponse) jaxbUnmarshaller.unmarshal(reader);
			reader.close();
			
			System.out.println("Object: " + response.toString());

		} catch (JAXBException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testCallRmi() {
		System.out.println("Case: testCallRmi");
		
		GetLicenseNGoodsInfoService service = new GetLicenseNGoodsInfoServiceImpl();
		
		StaBacRequest request = createRequestObject();
		
		StaBacResponse response = service.doService(request);
		System.out.println("Object: " + response.toString());
	}

}
