package com.jam01.alps.registry.domain.profile;

/**
 * Created by jam01 on 4/4/17.
 */
public class Doc {
	private final Format format;
	private final String value;

	public Doc(String value) {
		this(Format.TEXT, value);
	}

	public Doc(Format format, String value) {
		if (format == null) {
			format = Format.TEXT;
		}
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
