package com.jam01.apr.domain.registry;

/**
 * Created by jam01 on 5/29/17.
 */
public class EntryId {
	final public String id;

	public EntryId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EntryId entryId = (EntryId) o;

		return id.equals(entryId.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
