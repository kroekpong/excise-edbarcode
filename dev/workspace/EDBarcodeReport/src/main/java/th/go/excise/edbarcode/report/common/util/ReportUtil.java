package th.go.excise.edbarcode.report.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.go.excise.edbarcode.report.common.constant.ReportConstant;

/**
 * @Author: Taechapon Himarat (Su)
 * @Create: September 11, 2013
 */
public class ReportUtil {
	
	private static final Log logger = LogFactory.getLog(ReportUtil.class);
	
	public static InputStream getReportInputStream(String fileName) {
		String inputPath = ReportConstant.rbMyReport.getString("path.input");
		logger.debug("inputFile: " + inputPath + fileName);
		return ClassLoader.getSystemResourceAsStream(inputPath + fileName);
	}
	
	// For Test
	public static String getReportOutputPath(String fileName) throws IOException {
		String outputPath = ReportConstant.rbMyReport.getString("path.output");
		File outputFile = new File(outputPath);
		if (!outputFile.isDirectory()) {
			throw new IOException("Wrong Output Directory");
		}
		outputFile = new File(outputFile.getPath() + File.separator + fileName);
		logger.debug("outputFile: " + outputFile.getAbsolutePath());
		return outputFile.getPath();
	}
	
	public static InputStream getImagePath(String imageName) {
		return ClassLoader.getSystemResourceAsStream("images/" + imageName + "." + ReportConstant.FILE.PNG);
	}
	
}
