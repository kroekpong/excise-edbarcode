package th.go.excise.edbarcode.ws.provider.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.edbarcode.ws.provider.oxm.Entrepreneur;
import th.go.excise.edbarcode.ws.provider.oxm.Factory;
import th.go.excise.edbarcode.ws.provider.oxm.Product;

import com.baiwa.framework.persistence.dao.AbstractCommonJdbcDao;

@Repository("syncMasterDataDao")
public class SyncMasterDataDaoImpl extends AbstractCommonJdbcDao implements SyncMasterDataDao {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Entrepreneur getEntrepreneur(String taxNo) {
		logger.info("Inside getEntrepreneurData()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ent.tax_no ");
		sql.append("   , ent.license_allowed_name ");
		sql.append("   , fac.factory_name ");
		sql.append("   , fac.licenseNo ");
		sql.append("   , fac.licenseStartDate ");
		sql.append("   , fac.licenseEndDate ");
		sql.append("   , fac.factory_address ");
		sql.append(" FROM ed_entrepreneur ent ");
		sql.append(" INNER JOIN ed_factory fac ON fac.entrepreneur_id = ent.entrepreneur_id ");
		sql.append(" WHERE ent.tax_no = ? ");
		
		logger.debug("taxNo: " + taxNo);
		Entrepreneur result = jdbcTemplate.query(sql.toString(), entrepreneurMapper, new Object[] {
			taxNo
		});
		
		return result;
	}
	
	private static final EntrepreneurMapper entrepreneurMapper = new EntrepreneurMapper();
	private static final class EntrepreneurMapper implements ResultSetExtractor<Entrepreneur> {
		@Override
		public Entrepreneur extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			Entrepreneur entrepreneur = null;
			Factory factory = null;
			
			while (rs.next()) {
				if (entrepreneur != null) {
					entrepreneur = new Entrepreneur();
					entrepreneur.setTaxNo(rs.getString("tax_no"));
					entrepreneur.setLicenseAllowedName(rs.getString("license_allowed_name"));
				}
				factory = new Factory();
				factory.setLicenseNo(rs.getString("license_no"));
				factory.setFactoryName(rs.getString("factory_name"));
				factory.setLicenseStartDate(rs.getString("license_start_date"));
				factory.setLicenseEndDate(rs.getString("license_end_date"));
				factory.setFactoryAddress(rs.getString("factory_address"));
				entrepreneur.getFactoryList().add(factory);
			}
			
			return entrepreneur;
		}
	}

	@Override
	public List<Product> getProductListByEnt(String taxNo) {
		logger.info("Inside getProductListByEnt()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT product.product_id ");
		sql.append("   , product_group_code ");
		sql.append("   , product_group_name ");
		sql.append("   , product_code ");
		sql.append("   , product_name ");
		sql.append("   , brand_major_code ");
		sql.append("   , brand_major_name ");
		sql.append("   , brand_minor_code ");
		sql.append("   , brand_minor_name ");
		sql.append("   , model_code ");
		sql.append("   , model_name ");
		sql.append("   , size_code ");
		sql.append("   , size_name ");
		sql.append("   , unit_code ");
		sql.append("   , unit_name ");
		sql.append("   , degree_code ");
		sql.append("   , degree_name ");
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
		sql.append(" WHERE ent.tax_no = ? ");
		
		logger.debug("taxNo: " + taxNo);
		List<Product> resultList = jdbcTemplate.query(sql.toString(), productMapper, new Object[] {
			taxNo
		});
		logger.debug("size: " + resultList.size());
		
		return null;
	}
	
	private static final ProductMapper productMapper = new ProductMapper();
	private static final class ProductMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product model = new Product();
			//model.setProductId(rs.getInt("product_id"));
			model.setProductGroupCode(rs.getString("product_group_code"));
			model.setProductGroupName(rs.getString("product_group_name"));
			model.setProductCode(rs.getString("product_code"));
			model.setProductName(rs.getString("product_name"));
			model.setBrandMajorCode(rs.getString("brand_major_code"));
			model.setBrandMajorName(rs.getString("brand_major_name"));
			model.setBrandMinorCode(rs.getString("brand_minor_code"));
			model.setBrandMinorName(rs.getString("brand_minor_name"));
			model.setModelCode(rs.getString("model_code"));
			model.setModelName(rs.getString("model_name"));
			model.setSizeCode(rs.getString("size_code"));
			model.setSizeValue(rs.getBigDecimal("size_value"));
			model.setUnitCode(rs.getString("unit_code"));
			model.setUnitName(rs.getString("unit_name"));
			model.setDegreeCode(rs.getString("degree_code"));
			model.setDegreeValue(rs.getBigDecimal("degree_value"));
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
