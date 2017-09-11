package com.jam01.apr.domain.registry;

import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */
public class Entry {
	private EntryId id;
	private String title;
	private List<String> keywords;

	public Entry(String title, List<String> keywords) {
		this.title = title;
		this.keywords = keywords;
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

	public List<String> getKeywords() {
		return keywords;
	}
}
