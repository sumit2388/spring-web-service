package net.one97.pendingMT.bean;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters implements Serializable {

	@SerializedName("Action")
	@Expose
	private String action;
	@SerializedName("Url")
	@Expose
	private String url;
	private final static long serialVersionUID = 3902865357387810451L;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("action", action).append("url", url).toString();
	}

}