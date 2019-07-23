package net.one97.pendingMT.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BangoRequestBean implements Serializable {
	@SerializedName("customerIdentifier")
	@Expose
	private String customerIdentifier;
	@SerializedName("merchantAccountKey")
	@Expose
	private String merchantAccountKey;
	@SerializedName("productKey")
	@Expose
	private String productKey;
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
	private final static long serialVersionUID = -727368553727895298L;

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
		return new ToStringBuilder(this).append("customerIdentifier", customerIdentifier)
				.append("merchantAccountKey", merchantAccountKey).append("productKey", productKey)
				.append("entitlementDisplayName", entitlementDisplayName).append("dateExpiry", dateExpiry)
				.append("notificationUrl", notificationUrl).append("extensionData", extensionData).toString();
	}

}
