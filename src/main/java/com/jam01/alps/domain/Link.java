package com.jam01.alps.domain;

import java.net.URL;

/**
 * Created by jam01 on 4/4/17.
 */

// https://github.com/alps-io/spec/blob/master/draft-02.txt#2.2.8
public class Link {
	public Link(Rel rel, URL href) {
		this.rel = rel;
		this.href = href;
	}

	private final Rel rel;
	private final URL href;

	public Rel getRel() {
		return rel;
	}

	public URL getHref() {
		return href;
	}
}
