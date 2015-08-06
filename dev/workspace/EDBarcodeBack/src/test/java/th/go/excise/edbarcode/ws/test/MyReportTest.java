package th.go.excise.edbarcode.ws.test;

import static org.junit.Assert.fail;
import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;

public class MyReportTest {
	
	public static void main(String[] args) {
		
		String xmlData = "<SR12011FormReport>" +
				"	<TaxpayerInfoReport>" +
				"		<CusId>57220138</CusId>" +
				"		<TaxpayerId>49L15746</TaxpayerId>" +
				"		<Tin>3-1015-1763-7</Tin>" +
				"		<CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName>" +
				"		<LicenseNo>2558/70605817600002</LicenseNo>" +
				"		<EffectiveDate>20150101</EffectiveDate>" +
				"		<ExpireDate>20151231</ExpireDate>" +
				"		<TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName>" +
				"		<TaxpayerAddressReport>" +
				"			<HouseIdNumber></HouseIdNumber>" +
				"			<BuildingName></BuildingName>" +
				"			<RoomNumber></RoomNumber>" +
				"			<FloorNumber></FloorNumber>" +
				"			<VillageName></VillageName>" +
				"			<HouseNumber>9/2</HouseNumber>" +
				"			<MooNumber>3</MooNumber>" +
				"			<TrokSoiName></TrokSoiName>" +
				"			<StreetName></StreetName>" +
				"			<ThambolName>บางโทรัด</ThambolName>" +
				"			<AmphurName>เมืองสมุทรสาคร</AmphurName>" +
				"			<ProvinceName>สมุทรสาคร</ProvinceName>" +
				"			<Postcode>74000</Postcode>" +
				"			<TelNumber></TelNumber>" +
				"			<EmailAddress></EmailAddress>" +
				"		</TaxpayerAddressReport>" +
				"	</TaxpayerInfoReport>" +
				"	<GoodsListReport>" +
				"		<GoodsEntryReport>" +
				"			<SeqNo>1</SeqNo>" +
				"			<ProductTypeCode>70010101#AQk#00#000#026#03#07</ProductTypeCode>" +
				"			<ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc>" +
				"			<GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc>" +
				"			<Degree>12.50</Degree>" +
				"			<GoodsSize>0.750</GoodsSize>" +
				"			<GoodsPiece>6000</GoodsPiece>" +
				"			<GoodsQuantity>4500.0000</GoodsQuantity>" +
				"			<UnitPrice>455.6200</UnitPrice>" +
				"			<DeclarePrice>0.0000</DeclarePrice>" +
				"			<TaxByValue>0.0000</TaxByValue>" +
				"			<TaxByQuantity>168.7500</TaxByQuantity>" +
				"			<TaxByQuantityOver>0.0000</TaxByQuantityOver>" +
				"			<TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver>" +
				"			<NetTaxByValue>0.00</NetTaxByValue>" +
				"			<NetTaxByQuantity>1012500.00</NetTaxByQuantity>" +
				"		</GoodsEntryReport>" +
				"		<GoodsEntryReport>" +
				"			<SeqNo>2</SeqNo>" +
				"			<ProductTypeCode>70010101#AQk#00#000#026#03#08</ProductTypeCode>" +
				"			<ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc>" +
				"			<GoodsDesc>ไวน์ Monsoon Valley White Wine Colombard</GoodsDesc>" +
				"			<Degree>12.00</Degree>" +
				"			<GoodsSize>0.750</GoodsSize>" +
				"			<GoodsPiece>6000</GoodsPiece>" +
				"			<GoodsQuantity>4500.0000</GoodsQuantity>" +
				"			<UnitPrice>455.6200</UnitPrice>" +
				"			<DeclarePrice>0.0000</DeclarePrice>" +
				"			<TaxByValue>0.0000</TaxByValue>" +
				"			<TaxByQuantity>168.7500</TaxByQuantity>" +
				"			<TaxByQuantityOver>0.0000</TaxByQuantityOver>" +
				"			<TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver>" +
				"			<NetTaxByValue>0.00</NetTaxByValue>" +
				"			<NetTaxByQuantity>1012500.00</NetTaxByQuantity>" +
				"		</GoodsEntryReport>" +
				"		<GoodsEntryReport>" +
				"			<SeqNo>3</SeqNo>" +
				"			<ProductTypeCode>70010101#AQk#00#000#026#03#09</ProductTypeCode>" +
				"			<ProductTypeDesc>ไวน์และสปาร์กลิ้งไวน์ที่ทำจากองุ่น</ProductTypeDesc>" +
				"			<GoodsDesc>ไวน์ Monsoon Valley White Wine Shiraz</GoodsDesc>" +
				"			<Degree>13.50</Degree>" +
				"			<GoodsSize>0.750</GoodsSize>" +
				"			<GoodsPiece>5000</GoodsPiece>" +
				"			<GoodsQuantity>3750.0000</GoodsQuantity>" +
				"			<UnitPrice>455.6200</UnitPrice>" +
				"			<DeclarePrice>0.0000</DeclarePrice>" +
				"			<TaxByValue>0.0000</TaxByValue>" +
				"			<TaxByQuantity>168.7500</TaxByQuantity>" +
				"			<TaxByQuantityOver>0.0000</TaxByQuantityOver>" +
				"			<TaxByQuantityWithOver>168.7500</TaxByQuantityWithOver>" +
				"			<NetTaxByValue>0.00</NetTaxByValue>" +
				"			<NetTaxByQuantity>843750.00</NetTaxByQuantity>" +
				"		</GoodsEntryReport>" +
				"	</GoodsListReport>" +
				"	<SummaryReport>" +
				"		<SumAllTaxByValue>4930248.50</SumAllTaxByValue>" +
				"		<SumAllTaxByQuantity>9398750.00</SumAllTaxByQuantity>" +
				"		<SumAllTax>14328998.00</SumAllTax>" +
				"		<TaxLessType></TaxLessType>" +
				"		<TaxLessFrom></TaxLessFrom>" +
				"		<TaxLessAmount></TaxLessAmount>" +
				"		<TaxDeductionOnBookNo></TaxDeductionOnBookNo>" +
				"		<TaxDeductionOnBookAmount></TaxDeductionOnBookAmount>" +
				"		<PaymentExciseAmount>14328998.00</PaymentExciseAmount>" +
				"		<PaymentMunicipalPercent>10</PaymentMunicipalPercent>" +
				"		<PaymentMunicipalAmount>1432899.80</PaymentMunicipalAmount>" +
				"		<PaymentExciseAndMunicipalTaxAmount>15761897.80</PaymentExciseAndMunicipalTaxAmount>" +
				"		<PaymentOtherAmount></PaymentOtherAmount>" +
				"		<PaymentNetTaxAmount>15761897.80</PaymentNetTaxAmount>" +
				"		<PrintType>1</PrintType>" +
				"		<PaymentFundHealthAmount>315237.96</PaymentFundHealthAmount>" +
				"		<PaymentFundTVAmount>236428.47</PaymentFundTVAmount>" +
				"		<PaymentFundSportAmount>315237.96</PaymentFundSportAmount>" +
				"	</SummaryReport>" +
				"</SR12011FormReport>";
		String referenceNumber = "1234555666";
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			byte[] bbb = reportService.generateReport(xmlData, referenceNumber);
			System.out.println(bbb);
		} catch (EDBarcodeReportException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
