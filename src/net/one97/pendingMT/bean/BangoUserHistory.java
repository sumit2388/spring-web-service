package net.one97.pendingMT.bean;

public class BangoUserHistory {
	
	private	String msisdn;
	private	String customer_id;
	private	String ent_id ;  
	private	String source_app="pendingMT";
	private	String usertype ;
	private	Integer returning ; 
	private	String plan_id;
	private	String response_code;
	private	String response_desc;
	private	String request_type;
	private	String request_status;
	private Double billing_amount;
	private String remarks;
	
	public BangoUserHistory() {

	}

	
	public BangoUserHistory(String msisdn, String customer_id, String ent_id, String usertype,
			Integer returning, String plan_id, String response_code, String response_desc, String request_type,
			String request_status, Double billing_amount, String remarks) {
		super();
		this.msisdn = msisdn;
		this.customer_id = customer_id;
		this.ent_id = ent_id;
		this.usertype = usertype;
		this.returning = returning;
		this.plan_id = plan_id;
		this.response_code = response_code;
		this.response_desc = response_desc;
		this.request_type = request_type;
		this.request_status = request_status;
		this.billing_amount = billing_amount;
		this.remarks = remarks;
	}


	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(String ent_id) {
		this.ent_id = ent_id;
	}

	public String getSource_app() {
		return source_app;
	}

	public void setSource_app(String source_app) {
		this.source_app = source_app;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getReturning() {
		return returning;
	}

	public void setReturning(Integer returning) {
		this.returning = returning;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_desc() {
		return response_desc;
	}

	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public Double getBilling_amount() {
		return billing_amount;
	}

	public void setBilling_amount(Double billing_amount) {
		this.billing_amount = billing_amount;
	}
	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "BangoUserHistory [msisdn=" + msisdn + ", customer_id=" + customer_id + ", ent_id=" + ent_id
				+ ", source_app=" + source_app + ", usertype=" + usertype + ", returning=" + returning + ", plan_id="
				+ plan_id + ", response_code=" + response_code + ", response_desc=" + response_desc + ", request_type="
				+ request_type + ", request_status=" + request_status + ", billing_amount=" + billing_amount
				+ ", remarks=" + remarks + "]";
	}
	

}
