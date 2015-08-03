//package th.go.excise.edbarcode.demo.dao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocDetail;
//import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocMaster;
//import th.go.excise.edbarcode.demo.bean.EntrepreneurDemo;
//import th.go.excise.edbarcode.demo.bean.ProductDemo;
//
//import com.baiwa.framework.persistence.dao.AbstractCommonJdbcDao;
//
//@Repository("exciseDemoDao")
//public class ExciseDemoDaoImpl extends AbstractCommonJdbcDao implements ExciseDemoDao {
//	
//	private static final Logger logger = LogManager.getLogger();
//	
//	@Override
//	public EntrepreneurDemo getEntrepreneurDetail(String licenseNo) {
//		logger.info("Inside getEntrepreneurDetail()");
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT f.license_no ");
//		sql.append("   , e.license_allowed_name ");
//		sql.append("   , f.factory_name ");
//		sql.append("   , f.license_start_date ");
//		sql.append("   , f.license_end_date ");
//		sql.append("   , e.tax_no ");
//		sql.append("   , f.factory_address ");
//		sql.append(" FROM ed_entrepreneur e ");
//		sql.append(" INNER JOIN ed_factory f ON f.entrepreneur_id = e.entrepreneur_id ");
//		sql.append(" WHERE f.license_no = ? ");
//		
//		EntrepreneurDemo result = jdbcTemplate.queryForObject(sql.toString(), entrepreneurMapper, new Object[] {
//			licenseNo
//		});
//		
//		return result;
//	}
//	
//	private static final EntrepreneurMapper entrepreneurMapper = new EntrepreneurMapper();
//	private static final class EntrepreneurMapper implements RowMapper<EntrepreneurDemo> {
//		@Override
//		public EntrepreneurDemo mapRow(ResultSet rs, int rowNum) throws SQLException {
//			EntrepreneurDemo model = new EntrepreneurDemo();
//			model.setLicenseNo(rs.getString("license_no"));
//			model.setLicenseAllowedName(rs.getString("license_allowed_name"));
//			model.setFactoryName(rs.getString("factory_name"));
//			model.setLicenseStartDate(rs.getString("license_start_date"));
//			model.setLicenseEndDate(rs.getString("license_end_date"));
//			model.setTaxNo(rs.getString("tax_no"));
//			model.setFactoryAddress(rs.getString("factory_address"));
//			return model;
//		}
//	}
//
//	@Override
//	public ProductDemo getProductDetail(String productCode) {
//		logger.info("Inside getProductDetail()");
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT product_code ");
//		sql.append("   , product_name ");
//		sql.append("   , degree_value ");
//		sql.append("   , size_value ");
//		sql.append(" FROM ed_product product ");
//		sql.append(" WHERE product_code = ? ");
//		
//		logger.debug("productCode: " + productCode);
//		ProductDemo result = jdbcTemplate.queryForObject(sql.toString(), productMapper, new Object[] {
//			productCode
//		});
//		
//		return result;
//	}
//	
//	private static final ProductMapper productMapper = new ProductMapper();
//	private static final class ProductMapper implements RowMapper<ProductDemo> {
//		@Override
//		public ProductDemo mapRow(ResultSet rs, int rowNum) throws SQLException {
//			ProductDemo model = new ProductDemo();
//			model.setProductGroup(rs.getString("product_code"));
//			model.setProductName(rs.getString("product_name"));
//			model.setDegree(rs.getBigDecimal("degree"));
//			model.setSize(rs.getBigDecimal("size"));
//			return model;
//		}
//	}
//
//	@Override
//	public TmpTaxDocMaster getTmpTaxDocMaster(String referenceCode) {
//		logger.info("Inside getTmpTaxDocMaster()");
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT reference_code ");
//		sql.append("   , license_no ");
//		sql.append("   , sum_tax_by_value ");
//		sql.append("   , sum_tax_by_capacity ");
//		sql.append("   , receipt ");
//		sql.append("   , reduce_tax_product_baht ");
//		sql.append("   , reduce_tax_by_dep_book_no_baht ");
//		sql.append("   , tax_by_moi ");
//		sql.append("   , tax_by_thai_health ");
//		sql.append("   , tax_by_thai_pbs ");
//		sql.append("   , tax_by_nsdf ");
//		sql.append(" FROM ed_tmp_tax_doc_master ");
//		sql.append(" WHERE reference_code = ? ");
//		
//		TmpTaxDocMaster result = jdbcTemplate.queryForObject(sql.toString(), tmpMasterMapper, new Object[] {
//			referenceCode
//		});
//		
//		return result;
//	}
//	
//	private static final TmpMasterMapper tmpMasterMapper = new TmpMasterMapper();
//	private static final class TmpMasterMapper implements RowMapper<TmpTaxDocMaster> {
//		@Override
//		public TmpTaxDocMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
//			TmpTaxDocMaster model = new TmpTaxDocMaster();
//			model.setReferenceCode(rs.getString("reference_code"));
//			model.setLicenseNo(rs.getString("license_no"));
//			model.setSumTaxByValue(rs.getBigDecimal("sum_tax_by_value"));
//			model.setSumTaxByCapacity(rs.getBigDecimal("sum_tax_by_capacity"));
//			model.setReceipt(rs.getString("receipt"));
//			model.setReduceTaxProductBaht(rs.getBigDecimal("reduce_tax_product_baht"));
//			model.setReduceTaxByDepBookNoBaht(rs.getBigDecimal("reduce_tax_by_dep_book_no_baht"));
//			model.setTaxByMOI(rs.getBigDecimal("tax_by_moi"));
//			model.setTaxByThaiHealth(rs.getBigDecimal("tax_by_thai_health"));
//			model.setTaxByThaiPBS(rs.getBigDecimal("tax_by_thai_pbs"));
//			model.setTaxByNSDF(rs.getBigDecimal("tax_by_nsdf"));
//			return model;
//		}
//	}
//
//	@Override
//	public List<TmpTaxDocDetail> getTmpTaxDocDetail(String referenceCode) {
//		logger.info("Inside getTmpTaxDocDetail()");
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT detail.product_code ");
//		sql.append("   , detail.product_piece ");
//		sql.append("   , detail.selling_price_by_owner ");
//		sql.append("   , detail.selling_price_by_department ");
//		sql.append("   , detail.tax_by_value ");
//		sql.append("   , detail.tax_by_capacity ");
//		sql.append("   , detail.tax_by_value_plus ");
//		sql.append(" FROM ed_tmp_tax_doc_detail detail ");
//		sql.append(" INNER JOIN ed_tmp_tax_doc_master master ");
//		sql.append("   ON master.ed_tmp_tax_doc_master_id = detail.ed_tmp_tax_doc_master_id ");
//		sql.append(" WHERE master.reference_code = ? ");
//		
//		List<TmpTaxDocDetail> resultList = jdbcTemplate.query(sql.toString(), tmpDetailMapper, new Object[] {
//			referenceCode
//		});
//		logger.debug("size: " + resultList.size());
//		
//		return resultList;
//	}
//	
//	private static final TmpDetailMapper tmpDetailMapper = new TmpDetailMapper();
//	private static final class TmpDetailMapper implements RowMapper<TmpTaxDocDetail> {
//		@Override
//		public TmpTaxDocDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
//			TmpTaxDocDetail model = new TmpTaxDocDetail();
//			model.setProductCode(rs.getString("product_code"));
//			model.setProductPiece(rs.getInt("product_piece"));
//			model.setSellingPriceByOwner(rs.getBigDecimal("selling_price_by_owner"));
//			model.setSellingPriceByDepartment(rs.getBigDecimal("selling_price_by_department"));
//			model.setTaxByValue(rs.getBigDecimal("tax_by_value"));
//			model.setTaxByCapacity(rs.getBigDecimal("tax_by_capacity"));
//			model.setTaxByValuePlus(rs.getBigDecimal("tax_by_value_plus"));
//			return model;
//		}
//	}
//
//}
