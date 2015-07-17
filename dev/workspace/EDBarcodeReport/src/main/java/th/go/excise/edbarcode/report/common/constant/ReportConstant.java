package th.go.excise.edbarcode.report.common.constant;

import java.util.ResourceBundle;

public class ReportConstant {
	
	public static final ResourceBundle rbMyReport = ResourceBundle.getBundle("report-config");
	
	public static final String CURRENT_PATH = System.getProperty("user.dir");
	
	public static final class FILE {
		public static final String JASPER = "jasper";
		public static final String JRXML  = "jrxml";
		public static final String PNG    = "png";
		public static final String PDF    = "pdf";
	}
	
	public static final class REPORT {
		public static final String SR120_11 = "SR120-11";
		public static final String SSS1_01 = "SSS1_01";
		public static final String SST1_01 = "SST1_01";
		public static final String KKT1_01 = "KKT1_01";
		public static final String[] NAME_LIST = {SR120_11, SSS1_01, SST1_01, KKT1_01};
	}
	
	public static final class MODE {
		public static final String ALL = "A";
		public static final String SEPERATE = "S";
	}
	
	public static final class REPORT_STATUS {
		public static final int SUCCESS = 1;
		public static final int FAILED = 0;
	}
	
}
