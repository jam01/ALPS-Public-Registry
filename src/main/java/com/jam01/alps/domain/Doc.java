package com.jam01.alps.domain;

/**
 * Created by jam01 on 4/4/17.
 */
public class Doc {
	private final Format format;
	private final String value;

	public Doc(Format format, String value) {
		this.format = format;
		this.value = value;
	}

	public Format getFormat() {
		return format;
	}

	public String getValue() {
		return value;
	}

}
