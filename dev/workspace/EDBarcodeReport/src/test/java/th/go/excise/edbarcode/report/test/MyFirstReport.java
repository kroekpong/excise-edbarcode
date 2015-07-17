package th.go.excise.edbarcode.report.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.go.excise.edbarcode.report.common.bean.Product;
import th.go.excise.edbarcode.report.common.util.ReportUtil;

public class MyFirstReport {
	
	public static void main(String[] args) throws Exception {
		
		long start = System.currentTimeMillis();
		
		String jrxmlFile  = ReportUtil.getReportFile("MyFirstReport.jrxml");
		String outputFile = ReportUtil.getReportOutputPath("MyFirstReport.pdf");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("my_param", "Hello World");
		map.put("my_qr_code", "31");
		
		List<Product> productList = new ArrayList<Product>();
		Product product = null;
		for (int i = 0; i < 10; i++) {
			product = new Product();
			product.setProductGroup("G" + (i + 1));
			product.setProductName("Product" + (i + 1));
			productList.add(product);
		}
		
		JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFile);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(productList, true));
		JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(productList, true));
		JasperPrint jasperPrint3 = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(productList, true));

		JRPdfExporter exporter = new JRPdfExporter();

//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint1));
		
		
		ExporterInputItem item1 = new SimpleExporterInputItem(jasperPrint1);
		ExporterInputItem item2 = new SimpleExporterInputItem(jasperPrint2);
		ExporterInputItem item3 = new SimpleExporterInputItem(jasperPrint3);
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		exporter.setExporterInput(new SimpleExporterInput(items));
		
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		exporter.exportReport();
		
		System.out.println("Export Finished");
		System.out.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
	
}
