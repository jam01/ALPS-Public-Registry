package com.jam01.alps.registry.domain.profile;

import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */
public class Profile {
	public Profile(String title, List<String> tags, Alps alps) {
		this.title = title;
		this.tags = tags;
		this.alps = alps;
	}

	String id;
	private String title;
	private List<String> tags;
	private Alps alps;
	public Profile(String title) {
		this(title, null);
	}

	public Profile(String title, Alps alps) {
		this.title = title;
		this.alps = alps;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Alps getAlps() {
		return alps;
	}

	public void setAlps(Alps alps) {
		this.alps = alps;
	}
}
