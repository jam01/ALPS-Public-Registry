package com.jam01.apr.domain.registry;

import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */
public class Entry {
	private EntryId id;
	private String title;
	private List<String> tags;

	public Entry(String title, List<String> tags) {
		this.title = title;
		this.tags = tags;
	}

	public EntryId getId() {
		return id;
	}

	public void setId(EntryId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
