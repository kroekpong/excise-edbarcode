package th.go.excise.edbarcode.ws.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.edbarcode.common.dao.AbstractCommonDao;
import th.go.excise.edbarcode.ws.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.oxm.Product;

@Repository("syncMasterDataDao")
public class SyncMasterDataDaoImpl extends AbstractCommonDao implements SyncMasterDataDao {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Entrepreneur getEntrepreneur(String licenseNo) {
		logger.info("Inside getEntrepreneurData()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT entrepreneur_id ");
		sql.append("   , license_no ");
		sql.append("   , license_allowed_name ");
		sql.append("   , factory_name ");
		sql.append("   , license_start_date ");
		sql.append("   , license_end_date ");
		sql.append("   , tax_no ");
		sql.append("   , factory_address ");
		sql.append(" FROM ed_entrepreneur ");
		sql.append(" WHERE license_no = ? ");
		
		logger.debug("licenseNo: " + licenseNo);
		Entrepreneur result = jdbcTemplate.queryForObject(sql.toString(), entrepreneurMapper, new Object[] {
			licenseNo
		});
		
		return result;
	}
	
	private static final EntrepreneurMapper entrepreneurMapper = new EntrepreneurMapper();
	private static final class EntrepreneurMapper implements RowMapper<Entrepreneur> {
		@Override
		public Entrepreneur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Entrepreneur model = new Entrepreneur();
			//model.setEntrepreneurId(rs.getInt("entrepreneur_id"));
			model.setLicenseNo(rs.getString("license_no"));
			model.setLicenseAllowedName(rs.getString("license_allowed_name"));
			model.setFactoryName(rs.getString("factory_name"));
			model.setLicenseStartDate(rs.getString("license_start_date"));
			model.setLicenseEndDate(rs.getString("license_end_date"));
			model.setTaxNo(rs.getString("tax_no"));
			model.setFactoryAddress(rs.getString("factory_address"));
			return model;
		}

	}

	@Override
	public List<Product> getProductListByEnt(String licenseNo) {
		logger.info("Inside getProductListByEnt()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT product.product_id ");
		sql.append("   , product_code ");
		sql.append("   , product_group ");
		sql.append("   , product_name ");
		sql.append("   , brand_major ");
		sql.append("   , brand_minor ");
		sql.append("   , model ");
		sql.append("   , size ");
		sql.append("   , unit ");
		sql.append("   , degree ");
		sql.append("   , tax_by_value ");
		sql.append("   , tax_by_capacity ");
		sql.append("   , tax_by_liter ");
		sql.append("   , lowest_degree_no_tax ");
		sql.append("   , tax_plus_by_degree ");
		sql.append("   , lowest_selling_price_no_tax ");
		sql.append("   , tax_plus_by_selling_price ");
		sql.append("   , announce_price_date ");
		sql.append("   , announce_price_value ");
		sql.append(" FROM ed_product product ");
		sql.append(" INNER JOIN ed_entrepreneur_product_mapping mapping ON mapping.product_id = product.product_id ");
		sql.append(" INNER JOIN ed_entrepreneur ent ON ent.entrepreneur_id = mapping.entrepreneur_id ");
		sql.append(" WHERE ent.license_no = ? ");
		
		logger.debug("licenseNo: " + licenseNo);
		List<Product> resultList = jdbcTemplate.query(sql.toString(), productMapper, new Object[] {
			licenseNo
		});
		logger.debug("size: " + resultList.size());
		
		return resultList;
	}
	
	private static final ProductMapper productMapper = new ProductMapper();
	private static final class ProductMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product model = new Product();
			//model.setProductId(rs.getInt("product_id"));
			model.setProductCode(rs.getString("product_code"));
			model.setProductGroup(rs.getString("product_group"));
			model.setProductName(rs.getString("product_name"));
			model.setBrandMajor(rs.getString("brand_major"));
			model.setBrandMinor(rs.getString("brand_minor"));
			model.setModel(rs.getString("model"));
			model.setSize(rs.getBigDecimal("size"));
			model.setUnit(rs.getString("unit"));
			model.setDegree(rs.getBigDecimal("degree"));
			model.setTaxByValue(rs.getBigDecimal("tax_by_value"));
			model.setTaxByCapacity(rs.getBigDecimal("tax_by_capacity"));
			model.setTaxByLiter(rs.getBigDecimal("tax_by_liter"));
			model.setLowestDegreeNoTax(rs.getBigDecimal("lowest_degree_no_tax"));
			model.setTaxPlusByDegree(rs.getBigDecimal("tax_plus_by_degree"));
			model.setLowestSellingPriceNoTax(rs.getBigDecimal("lowest_selling_price_no_tax"));
			model.setTaxPlusBySellingPrice(rs.getBigDecimal("tax_plus_by_selling_price"));
			model.setAnnouncePriceDate(rs.getString("announce_price_date"));
			model.setAnnouncePriceValue(rs.getBigDecimal("announce_price_value"));
			return model;
		}

	}

}
