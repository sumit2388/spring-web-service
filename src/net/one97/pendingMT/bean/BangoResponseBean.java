package net.one97.pendingMT.bean;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BangoResponseBean implements Serializable {

	@SerializedName("responseCode")
	@Expose
	private String responseCode;
	@SerializedName("responseMessage")
	@Expose
	private String responseMessage;
	@SerializedName("parameters")
	@Expose
	private Parameters parameters;
	@SerializedName("entitlementId")
	@Expose
	private String entitlementId;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("dateCreated")
	@Expose
	private String dateCreated;
	@SerializedName("dateActivated")
	@Expose
	private String dateActivated;
	@SerializedName("dateEnded")
	@Expose
	private Object dateEnded;
	@SerializedName("dateLastUpdated")
	@Expose
	private String dateLastUpdated;
	@SerializedName("customerIdentifier")
	@Expose
	private String customerIdentifier;
	@SerializedName("merchantAccountKey")
	@Expose
	private String merchantAccountKey;
	@SerializedName("productKey")
	@Expose
	private String productKey;
	@SerializedName("offerKey")
	@Expose
	private String offerKey;
	@SerializedName("activationCode")
	@Expose
	private String activationCode;
	@SerializedName("entitlementDisplayName")
	@Expose
	private String entitlementDisplayName;
	@SerializedName("dateExpiry")
	@Expose
	private String dateExpiry;
	@SerializedName("notificationUrl")
	@Expose
	private String notificationUrl;
	@SerializedName("extensionData")
	@Expose
	private ExtensionData extensionData;
	private final static long serialVersionUID = 7212296828716726655L;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public String getEntitlementId() {
		return entitlementId;
	}

	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(String dateActivated) {
		this.dateActivated = dateActivated;
	}

	public Object getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Object dateEnded) {
		this.dateEnded = dateEnded;
	}

	public String getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(String dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public String getCustomerIdentifier() {
		return customerIdentifier;
	}

	public void setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}

	public String getMerchantAccountKey() {
		return merchantAccountKey;
	}

	public void setMerchantAccountKey(String merchantAccountKey) {
		this.merchantAccountKey = merchantAccountKey;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getOfferKey() {
		return offerKey;
	}

	public void setOfferKey(String offerKey) {
		this.offerKey = offerKey;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getEntitlementDisplayName() {
		return entitlementDisplayName;
	}

	public void setEntitlementDisplayName(String entitlementDisplayName) {
		this.entitlementDisplayName = entitlementDisplayName;
	}

	public String getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(String dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	public ExtensionData getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(ExtensionData extensionData) {
		this.extensionData = extensionData;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("responseCode", responseCode).append("responseMessage", responseMessage)
				.append("parameters", parameters).append("entitlementId", entitlementId).append("status", status)
				.append("dateCreated", dateCreated).append("dateActivated", dateActivated)
				.append("dateEnded", dateEnded).append("dateLastUpdated", dateLastUpdated)
				.append("customerIdentifier", customerIdentifier).append("merchantAccountKey", merchantAccountKey)
				.append("productKey", productKey).append("offerKey", offerKey).append("activationCode", activationCode)
				.append("entitlementDisplayName", entitlementDisplayName).append("dateExpiry", dateExpiry)
				.append("notificationUrl", notificationUrl).append("extensionData", extensionData).toString();
	}

}