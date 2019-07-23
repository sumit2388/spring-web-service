package net.one97.pendingMT.bean;

public class DataBean {
	
	private String param2;
	private String param3;
	private String status;
	private String channel;
	private String lastBilledAmount;
	
	private String chargingCode;
	private String gwParams;
	private String url;
	
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChargingCode() {
		return chargingCode;
	}
	public void setChargingCode(String chargingCode) {
		this.chargingCode = chargingCode;
	}
	public String getGwParams() {
		return gwParams;
	}
	public void setGwParams(String gwParams) {
		this.gwParams = gwParams;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getLastBilledAmount() {
		return lastBilledAmount;
	}
	public void setLastBilledAmount(String lastBilledAmount) {
		this.lastBilledAmount = lastBilledAmount;
	}
	@Override
	public String toString() {
		return "DataBean [param2=" + param2 + ", param3=" + param3 + ", status=" + status + ", channel=" + channel
				+ ", lastBilledAmount=" + lastBilledAmount + ", chargingCode=" + chargingCode + ", gwParams=" + gwParams
				+ ", url=" + url + "]";
	}
	
	

}
