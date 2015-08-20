package th.go.excise.edbarcode.common.constant;

public class WebServiceConstant {
	
	public static final String NAMESPACE_URI = "http://www.excise.go.th/xsd/barcode";
	
	public static final class STA {
		// System
		public static final String SYSTEM_STA_BACK = "STABAC";
		public static final String SYSTEM_BARCODE = "BARCODE";
		
		// Transaction Code
		public static final String TRAN_CODE_GET_LICENSE_AND_GOODS_INFO = "GetLicenseNGoodsInfo";
		public static final String TRAN_CODE_ADD_NEW_FORM_SR12011 = "AddNewFormSR12011";
	}
	
	public static final class PCC {
		public static final String SYSTEM_ID = "007";
		public static final String SERVICE_ID = "007BAR0001";
		public static final String FORM_CODE = "สร12011";
		public static final String PAY_TYPE = "3";
		public static final String RECORD_TYPE = "1";
	}
	
	public static final class STATUS_CODE {
		public static final String OK = "OK";
		public static final String ERROR = "ERROR";
	}
	
	public static final class STATUS_DESC {
		public static final String SUCCESS = "Success";
	}
	
}
