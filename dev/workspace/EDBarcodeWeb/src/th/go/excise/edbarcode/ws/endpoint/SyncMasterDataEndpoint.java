package th.go.excise.edbarcode.ws.endpoint;

import java.math.BigDecimal;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import th.go.excise.edbarcode.ws.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.oxm.Product;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataRequest;
import th.go.excise.edbarcode.ws.oxm.SyncMasterDataResponse;

@Endpoint
public class SyncMasterDataEndpoint {
	
	private static final Logger logger = LogManager.getLogger();
	
	@PayloadRoot(localPart = "syncMasterDataRequest", namespace = "http://www.excise.go.th/xsd/barcode")
	public SyncMasterDataResponse doEndpoint(@RequestPayload SyncMasterDataRequest syncMasterDataRequest) throws DatatypeConfigurationException {
		logger.info(" In doEndpoint syncMasterDataRequest");
		
		String licenseNo = syncMasterDataRequest.getLicenseNo();
		logger.info(" licenseNo:" + licenseNo);
		
		SyncMasterDataResponse response = new SyncMasterDataResponse();
		
//		if ("001".equals(licenseNo)) {
			Entrepreneur entrepreneur = new Entrepreneur();
			entrepreneur.setLicenseNo(licenseNo);
			entrepreneur.setLicenseAllowedName("บริษัท สยามไวเนอรี่ จำกัด");
			entrepreneur.setFactoryName("บริษัท สยามไวเนอรี่ จำกัด");
			entrepreneur.setLicenseStartDate("01/01/2558");
			entrepreneur.setLicenseEndDate("31/12/2558");
			entrepreneur.setTaxNo("3-1015-1763-7");
			entrepreneur.setFactoryAddress("9/2 หมู่ 3 ต.บางโทรัด อ.เมืองสมุทรสาคร จ.สมุทรสาคร 74000");
			
			Product p1 = new Product();
			p1.setProductGroup("สุราแช่อื่นๆ");
			p1.setProductCode("P001");
			p1.setProductName("สุราแช่ 1");
			p1.setBrandMajor("แบรนด์หลัก");
			p1.setBrandMinor("แบรนด์รอง");
			p1.setModel("แบบหรือรุ่น");
			p1.setSize(BigDecimal.ONE);
			p1.setUnit("หน่วยสินค้า");
			p1.setDegree(BigDecimal.ONE);
			p1.setTaxByValue(new BigDecimal("48"));
			p1.setTaxByCapacity(new BigDecimal("155"));
			p1.setTaxByLiter(new BigDecimal("8"));
			p1.setLowestDegreeNoTax(new BigDecimal("7"));
			p1.setTaxPlusByDegree(new BigDecimal("3"));
			p1.setLowestSellingPriceNoTax(new BigDecimal("0"));
			p1.setTaxPlusBySellingPrice(new BigDecimal("0"));
			p1.setAnnouncePriceDate("30/03/2558");
			p1.setAnnouncePriceValue(new BigDecimal("0"));
			
			Product p2 = new Product();
			p2.setProductGroup("สุราแช่อื่นๆ");
			p2.setProductCode("xxxbbb002");
			p2.setProductName("สุราแช่ 2");
			p2.setBrandMajor("แบรนด์หลัก");
			p2.setBrandMinor("แบรนด์รอง");
			p2.setModel("แบบหรือรุ่น");
			p2.setSize(BigDecimal.TEN);
			p2.setUnit("หน่วยสินค้า");
			p2.setDegree(BigDecimal.TEN);
			p2.setTaxByValue(new BigDecimal("49"));
			p2.setTaxByCapacity(new BigDecimal("156"));
			p2.setTaxByLiter(new BigDecimal("9"));
			p2.setLowestDegreeNoTax(new BigDecimal("8"));
			p2.setTaxPlusByDegree(new BigDecimal("4"));
			p2.setLowestSellingPriceNoTax(new BigDecimal("0"));
			p2.setTaxPlusBySellingPrice(new BigDecimal("0"));
			p2.setAnnouncePriceDate("30/03/2558");
			p2.setAnnouncePriceValue(new BigDecimal("0"));
			
			entrepreneur.getProductList().add(p1);
			entrepreneur.getProductList().add(p2);
			
			response.setStatus("0");
			response.setDescription("Success");
			response.setEntrepreneur(entrepreneur);
//		} else if ("002".equals(licenseNo)) {
//			
//		} else {
//			response.setStatus("1");
//			response.setDescription("Invalid License No.");
//		}
		
		return response;
	}
	
}