package net.one97.pendingMT.bean;

public class HttpResponse {
	private int statusCode;
	private String responseBody;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HttpResponse [statusCode=").append(statusCode).append(", responseBody=").append(responseBody)
				.append("]");
		return builder.toString();
	}

}
