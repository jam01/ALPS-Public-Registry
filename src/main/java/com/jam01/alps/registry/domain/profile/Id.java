package com.jam01.alps.registry.domain.profile;

/**
 * Created by jam01 on 4/4/17.
 */
public class Id {
	private final String value;

	public Id(String value) {
		this.value = value;
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
