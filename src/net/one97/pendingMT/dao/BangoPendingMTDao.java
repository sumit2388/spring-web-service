package net.one97.pendingMT.dao;

import net.one97.pendingMT.bean.BangoUserHistory;
import net.one97.pendingMT.bean.DataBean;
import net.one97.pendingMT.bean.RequestBean;

public interface BangoPendingMTDao {

	public DataBean getMsisdnDetails(RequestBean requestBean);

	public DataBean getParams(String msisdn);

	public void updateParams(String param2, String param3, String msisdn, String packName);

	void insertLog(BangoUserHistory log);

}
