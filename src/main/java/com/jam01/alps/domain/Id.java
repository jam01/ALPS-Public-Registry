package com.jam01.alps.domain;

/**
 * Created by jam01 on 4/4/17.
 */
public class Id {
	private final String value;

	private Id(String id) {
		this.value = id;
	}

	public static Id from(String id) {
		if (id == null || id.isEmpty())
			return null;
		return new Id(id);
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Id id = (Id) o;

		return value.equals(id.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
