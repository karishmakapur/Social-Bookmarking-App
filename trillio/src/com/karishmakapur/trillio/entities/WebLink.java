package com.karishmakapur.trillio.entities;

public class WebLink extends Bookmark{
	private String url;
	private String host;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "WebLink [url=" + url + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if (url.contains("adultWord") | getTitle().contains("adultWord") || host.contains("adult")) {
			return false;
		}
		return true;
	}

}
