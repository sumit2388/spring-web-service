package net.one97.pendingMT.bean;

import java.util.Map;

public class RequestBean {
	private String msisdn;
	private String packName;
	private String channel;
	private String shortCode;
	private String operator;
	private String circle;
	private String userType;
	
	public RequestBean(Map<String, String> allRequestParams) {
		msisdn = (allRequestParams.containsKey("msisdn")) ? allRequestParams.get("msisdn") : null;
		packName = (allRequestParams.containsKey("packName")) ? allRequestParams.get("packName") : null;
		channel = (allRequestParams.containsKey("channel")) ? allRequestParams.get("channel") : null;
		shortCode = (allRequestParams.containsKey("shortCode")) ? allRequestParams.get("shortCode") : null;
		operator = (allRequestParams.containsKey("operator")) ? allRequestParams.get("operator") : null;
		circle = (allRequestParams.containsKey("circle")) ? allRequestParams.get("circle") : null;
		userType=((allRequestParams.containsKey("userType")) ? allRequestParams.get("userType") : null);
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "RequestBean [msisdn=" + msisdn + ", packName=" + packName + ", channel=" + channel + ", shortCode="
				+ shortCode + ", operator=" + operator + ", circle=" + circle + ", userType=" + userType + "]";
	}
	


}
