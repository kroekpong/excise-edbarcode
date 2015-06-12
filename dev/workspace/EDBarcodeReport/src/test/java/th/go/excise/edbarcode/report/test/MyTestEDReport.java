package th.go.excise.edbarcode.report.test;

import th.go.excise.edbarcode.report.common.exception.EDBarcodeReportException;
import th.go.excise.edbarcode.report.service.EDBarcodeReportService;
import th.go.excise.edbarcode.report.service.EDBarcodeReportServiceImpl;

public class MyTestEDReport {
	
	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
//		sb.append(" <?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
		sb.append(" <SR12011Form> ");
		sb.append(" 	<TaxpayerInformation> ");
		sb.append(" 		<Tin>3-1015-1763-7</Tin> ");
		sb.append(" 		<CompanyName>บริษัท สยามไวเนอรี่ จำกัด</CompanyName> ");
		sb.append(" 		<TaxpayerAddress> ");
		sb.append(" 			<TaxpayerName>บริษัท สยามไวเนอรี่ จำกัด</TaxpayerName> ");
		sb.append(" 			<HouseIdNumber></HouseIdNumber> ");
		sb.append(" 			<BuildingName></BuildingName> ");
		sb.append(" 			<RoomNumber></RoomNumber> ");
		sb.append(" 			<FloorNumber></FloorNumber> ");
		sb.append(" 			<VillageName></VillageName> ");
		sb.append(" 			<HouseNumber>9/2</HouseNumber> ");
		sb.append(" 			<MooNumber>3</MooNumber> ");
		sb.append(" 			<TrokSoiName></TrokSoiName> ");
		sb.append(" 			<StreetName></StreetName> ");
		sb.append(" 			<ThambolName>บางโทรัด</ThambolName> ");
		sb.append(" 			<AmphurName>เมืองสมุทรสาคร</AmphurName> ");
		sb.append(" 			<ProvinceName>สมุทรสาคร</ProvinceName> ");
		sb.append(" 			<Postcode>74000</Postcode> ");
		sb.append(" 			<TelNumber></TelNumber> ");
		sb.append(" 			<EmailAddress></EmailAddress> ");
		sb.append(" 		</TaxpayerAddress> ");
		sb.append(" 		<LicenseInfo> ");
		sb.append(" 			<LicenseNo>2558/70605817600002</LicenseNo> ");
		sb.append(" 			<EffectiveDate>20150101</EffectiveDate> ");
		sb.append(" 			<ExpireDate>20151231</ExpireDate> ");
		sb.append(" 		</LicenseInfo> ");
		sb.append(" 	</TaxpayerInformation> ");
		sb.append(" 	<GoodsList> ");
		sb.append(" 		<GoodsEntry> ");
		sb.append(" 			<ProductTypeDesc>สุราแช่อื่นๆ</ProductTypeDesc> ");
		sb.append(" 			<GoodsDesc>สุราแช่ผลไม้ Berri Estates Five Star White</GoodsDesc> ");
		sb.append(" 			<Degree>12.50</Degree> ");
		sb.append(" 			<GoodsSize>3.000</GoodsSize> ");
		sb.append(" 			<GoodsPiece>10000</GoodsPiece> ");
		sb.append(" 			<GoodsQuantity>30000.0000</GoodsQuantity> ");
		sb.append(" 			<UnitPrice>454.8900</UnitPrice> ");
		sb.append(" 			<DeclarePrice>0.0000</DeclarePrice> ");
		sb.append(" 			<TaxByValue>22.7445</TaxByValue> ");
		sb.append(" 			<TaxByQuantity>30.0000</TaxByQuantity> ");
		sb.append(" 			<TaxByQuantityOver>0.0000</TaxByQuantityOver> ");
		sb.append(" 		</GoodsEntry> ");
		sb.append(" 		<GoodsEntry> ");
		sb.append(" 			<ProductTypeDesc>สุราแช่อื่นๆ</ProductTypeDesc> ");
		sb.append(" 			<GoodsDesc>สุราแช่ผลไม้ Fincade Malpica Smooth Red</GoodsDesc> ");
		sb.append(" 			<Degree>13.50</Degree> ");
		sb.append(" 			<GoodsSize>0.750</GoodsSize> ");
		sb.append(" 			<GoodsPiece>5000</GoodsPiece> ");
		sb.append(" 			<GoodsQuantity>3750.0000</GoodsQuantity> ");
		sb.append(" 			<UnitPrice>209.5700</UnitPrice> ");
		sb.append(" 			<DeclarePrice>0.0000</DeclarePrice> ");
		sb.append(" 			<TaxByValue>10.4785</TaxByValue> ");
		sb.append(" 			<TaxByQuantity>30.0000</TaxByQuantity> ");
		sb.append(" 			<TaxByQuantityOver>0.0000</TaxByQuantityOver> ");
		sb.append(" 		</GoodsEntry> ");
		sb.append(" 		<GoodsEntry> ");
		sb.append(" 			<ProductTypeDesc>สุราแช่อื่นๆ</ProductTypeDesc> ");
		sb.append(" 			<GoodsDesc>สุราแช่ผลไม้ Fincade Malpica Fresh White</GoodsDesc> ");
		sb.append(" 			<Degree>12.50</Degree> ");
		sb.append(" 			<GoodsSize>0.750 </GoodsSize> ");
		sb.append(" 			<GoodsPiece>6000</GoodsPiece> ");
		sb.append(" 			<GoodsQuantity>4500.0000</GoodsQuantity> ");
		sb.append(" 			<UnitPrice>209.5700</UnitPrice> ");
		sb.append(" 			<DeclarePrice>0.0000</DeclarePrice> ");
		sb.append(" 			<TaxByValue>10.4785</TaxByValue> ");
		sb.append(" 			<TaxByQuantity>30.0000</TaxByQuantity> ");
		sb.append(" 			<TaxByQuantityOver>0.0000</TaxByQuantityOver> ");
		sb.append(" 		</GoodsEntry> ");
		sb.append(" 	</GoodsList> ");
		sb.append(" </SR12011Form> ");
		
		EDBarcodeReportService reportService = new EDBarcodeReportServiceImpl();
		try {
			String outputFile = "C://iReport//TEST//EXCISE.pdf";
			reportService.generateReport(sb.toString(), outputFile);
		} catch (EDBarcodeReportException e) {
			e.printStackTrace();
		}
		
//		System.out.println(ClassLoader.getSystemResourceAsStream("jrxml/SR120-11.jrxml"));
	}
	
}
