package th.go.excise.edbarcode.ws.provider.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocDetail;
import th.go.excise.edbarcode.common.persistence.domain.TmpTaxDocMaster;

import com.baiwa.framework.persistence.dao.AbstractCommonJdbcDao;

@Repository("submitOnlineDao")
public class SubmitOnlineDaoImpl extends AbstractCommonJdbcDao implements SubmitOnlineDao {
	
	private static final Logger logger = LogManager.getLogger();
	
	public int createTempTaxDocMaster(TmpTaxDocMaster master) {
		logger.info("Inside createTempTaxDocMaster()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ed_tmp_tax_doc_master ( ");
		sql.append("   reference_code ");
		sql.append("   , license_no ");
		sql.append("   , sum_tax_by_value ");
		sql.append("   , sum_tax_by_capacity ");
		sql.append("   , receipt ");
		sql.append("   , reduce_tax_product_baht ");
		sql.append("   , reduce_tax_by_dep_book_no_baht ");
		sql.append("   , tax_by_moi ");
		sql.append("   , tax_by_thai_health ");
		sql.append("   , tax_by_thai_pbs ");
		sql.append("   , tax_by_nsdf ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
		
		int key = executeInsert(sql.toString(), new Object[] {
			master.getReferenceCode(),
			master.getLicenseNo(),
			master.getSumTaxByValue(),
			master.getSumTaxByCapacity(),
			master.getReceipt(),
			master.getReduceTaxProductBaht(),
			master.getReduceTaxByDepBookNoBaht(),
			master.getTaxByMOI(),
			master.getTaxByThaiHealth(),
			master.getTaxByThaiPBS(),
			master.getTaxByNSDF()
		}).intValue();
		logger.info("key: " + key);
		
		return key;
	}
	
	public void createTempTaxDocDetail(List<TmpTaxDocDetail> detailList) {
		logger.info("Inside createTempTaxDocDetail()");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ed_tmp_tax_doc_detail ( ");
		sql.append("   ed_tmp_tax_doc_master_id ");
		sql.append("   , product_code ");
		sql.append("   , product_piece ");
		sql.append("   , selling_price_by_owner ");
		sql.append("   , selling_price_by_department ");
		sql.append("   , tax_by_value ");
		sql.append("   , tax_by_capacity ");
		sql.append("   , tax_by_value_plus ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?) ");
		
		for (TmpTaxDocDetail detail : detailList) {
			int updateRecord = jdbcTemplate.update(sql.toString(), new Object[] {
				detail.getTmpTaxDocMasterId(),
				detail.getProductCode(),
				detail.getProductPiece(),
				detail.getSellingPriceByOwner(),
				detail.getSellingPriceByDepartment(),
				detail.getTaxByValue(),
				detail.getTaxByCapacity(),
				detail.getTaxByValuePlus()
			});
			logger.info("updateRecord: " + updateRecord);
		}
	}
	
}
