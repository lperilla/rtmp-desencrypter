package org.lperilla.rtmp.desencrypter.bean;

public class ChannelBean {

	private String name;

	private String available;

	private String url;

	private String urlImage;

	private String urlProgramming;

	private String referer;

	private String host;

	private String quality;

	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlProgramming() {
		return urlProgramming;
	}

	public void setUrlProgramming(String urlProgramming) {
		this.urlProgramming = urlProgramming;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ChannelBean [name=" + name + ", available=" + available + ", url=" + url + ", urlImage=" + urlImage + ", urlProgramming=" + urlProgramming + ", referer=" + referer + ", host=" + host
				+ ", quality=" + quality + ", version=" + version + "]";
	}

}
