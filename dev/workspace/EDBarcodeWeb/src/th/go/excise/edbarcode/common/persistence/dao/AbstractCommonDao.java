package th.go.excise.edbarcode.common.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AbstractCommonDao {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public Long executeInsert(final String sql, final Object[] params) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					int i = 1;
					for (Object obj : params) {
						if (obj instanceof String) {
							ps.setString(i, (String) obj);
						} else if (obj instanceof Integer) {
							ps.setInt(i, (Integer) obj);
						} else if (obj instanceof Long) {
							ps.setLong(i, (Long) obj);
						} else if (obj instanceof Float) {
							ps.setFloat(i, (Float) obj);
						} else if (obj instanceof Double) {
							ps.setDouble(i, (Double) obj);
						} else if (obj instanceof BigDecimal) {
							ps.setBigDecimal(i, (BigDecimal) obj);
						} else if (obj instanceof Date) {
							ps.setDate(i, (Date) obj);
						} else if (obj instanceof Time) {
							ps.setTime(i, (Time) obj);
						} else if (obj instanceof Timestamp) {
							ps.setTimestamp(i, (Timestamp) obj);
						} else if (obj instanceof Boolean) {
							ps.setBoolean(i, (Boolean) obj);
						} else if (obj instanceof Object) {
							ps.setObject(i, obj);
						} else if (obj == null) {
							ps.setNull(i, Types.NULL);
						}
						i++;
					}
					return ps;
				}
			}, keyHolder);
		Long newId = keyHolder.getKey().longValue();
		return newId;
	}
}
