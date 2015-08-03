package th.go.excise.edbarcode.common.util;

import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {

	// Date format for web service
	public static final String WS_DATE_FORMAT = "yyyyMMdd";
	
	public static final FastDateFormat wsDateFormat = FastDateFormat.getInstance(WS_DATE_FORMAT, Locale.US);

}
