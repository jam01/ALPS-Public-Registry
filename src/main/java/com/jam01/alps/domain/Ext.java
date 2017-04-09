package com.jam01.alps.domain;

import java.net.URL;

/**
 * Created by jam01 on 4/4/17.
 */
public class Ext {

	private final Id id;
	private URL href;
	private String value;

	public Ext(Id id) {
		this.id = id;
	}

	public Id getId() {
		return id;
	}

	public URL getHref() {
		return href;
	}

	public void setHref(URL href) {
		this.href = href;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
