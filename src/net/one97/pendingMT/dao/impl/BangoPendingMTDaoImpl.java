package net.one97.pendingMT.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.one97.pendingMT.bean.BangoUserHistory;
import net.one97.pendingMT.bean.DataBean;
import net.one97.pendingMT.bean.RequestBean;
import net.one97.pendingMT.dao.BangoPendingMTDao;

@Repository
public class BangoPendingMTDaoImpl implements BangoPendingMTDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BangoPendingMTDaoImpl.class);

	public JdbcTemplate getJdbcTemplateForFetch() {
		return jdbcTemplate;
	}

	public void setJdbcTemplateForFetch(JdbcTemplate jdbcTemplateForFetch) {
		this.jdbcTemplate = jdbcTemplateForFetch;
	}

	@Override
	public DataBean getMsisdnDetails(RequestBean bean) {
		logger.info(bean.getMsisdn() + " _[GETTING MSISDN DETAILS]");
		DataBean dataBean = null;
		String getPackDetailQuery = "select param2,param3,status,channel ,last_billed_amount from sm_user_registration_dubango where msisdn='"
				+ bean.getMsisdn() + "'  and pack_name='" + bean.getPackName() + "' ";
		logger.info(bean.getMsisdn() + "_GET MSISDN DETAIL QUERY - " + getPackDetailQuery);
		try {
			dataBean = (DataBean) jdbcTemplate.queryForObject(getPackDetailQuery, new Object[] {},
					new RowMapper<Object>() {
						@Override
						public DataBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							DataBean msisdnDetail = new DataBean();
							msisdnDetail.setParam2(resultSet.getString("param2"));
							msisdnDetail.setParam3(resultSet.getString("param3"));
							msisdnDetail.setStatus(resultSet.getString("status"));
							msisdnDetail.setChannel(resultSet.getString("channel"));
							msisdnDetail.setLastBilledAmount(resultSet.getString("last_billed_amount"));
							return msisdnDetail;
						}
					});
		} catch (Exception e) {
			logger.error(bean.getMsisdn() + " _[DataAccessException occured]  : " + e.getMessage());
		}
		return dataBean;
	}

	@Override
	public DataBean getParams(String msisdn) {
		logger.info(msisdn + " _[Getting gwParams ,url and charging code DETAILS]");
		DataBean dataBean = null;
		String query = "select charging_code,url,params from be_product_billing_config pb, be_product_gateway_config pg where pg.config_id= pb.gateway_config_id and billing_mode='fresh' and gateway_config_id=1 limit 1 ";
		logger.info(msisdn + "GetParams QUERY - " + query);
		try {
			dataBean = (DataBean) jdbcTemplate.queryForObject(query, new Object[] {}, new RowMapper<Object>() {
				@Override
				public DataBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					DataBean params = new DataBean();
					params.setChargingCode(resultSet.getString("charging_code"));
					params.setGwParams(resultSet.getString("params"));
					params.setUrl(resultSet.getString("url"));
					return params;
				}
			});
		} catch (Exception e) {
			logger.error(msisdn + " _[DataAccessException occured]  : " + e.getMessage());
		}
		return dataBean;
	}

	@Override
	public void updateParams(String param2, String param3, String msisdn, String packName) {
		String query = "UPDATE sm_user_registration_dubango SET param2 = ? , param3 = ? where msisdn = ? and pack_name = ? and status=2 ";
		jdbcTemplate.update(query, param2, param3,msisdn,packName);
	}
	
	@Override
	public void insertLog(BangoUserHistory log) {
		logger.error(log.getMsisdn() + " Inserting logs "+log);
		jdbcTemplate.update( "INSERT INTO bango_user_history (msisdn,customer_id,ent_id,source_app,usertype,returning,plan_id,response_code,response_desc,"
				+ "request_type,request_status,remarks) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",log.getMsisdn(),log.getCustomer_id(),log.getEnt_id(),log.getSource_app(),log.getUsertype(),log.getReturning(),log.getPlan_id(),log.getResponse_code(),log.getResponse_desc(),
				log.getRequest_type(),log.getRequest_status(),log.getRemarks()				
			);
		logger.error(log.getMsisdn() + " logs inserted");
	}

}
