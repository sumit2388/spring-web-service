package net.one97.pendingMT.bean;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ExtensionData implements Serializable {

	@SerializedName("price")
	@Expose
	private String price;
	@SerializedName("currencyIso3")
	@Expose
	private String currencyIso3;
	@SerializedName("channelType")
	@Expose
	private String channelType;
	@SerializedName("channelSource")
	@Expose
	private String channelSource;
	@SerializedName("campaignKey")
	@Expose
	private String campaignKey;
	@SerializedName("referrer")
	@Expose
	private String referrer;
	private final static long serialVersionUID = 4779214642903012186L;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrencyIso3() {
		return currencyIso3;
	}

	public void setCurrencyIso3(String currencyIso3) {
		this.currencyIso3 = currencyIso3;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	public String getCampaignKey() {
		return campaignKey;
	}

	public void setCampaignKey(String campaignKey) {
		this.campaignKey = campaignKey;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("price", price).append("currencyIso3", currencyIso3)
				.append("channelType", channelType).append("channelSource", channelSource)
				.append("campaignKey", campaignKey).append("referrer", referrer).toString();
	}

}