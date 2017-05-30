package com.jam01.alps.application.representation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.jam01.alps.domain.Link;

/**
 * Created by jam01 on 4/5/17.
 */
public class LinkRepresentation {
	@JacksonXmlProperty(isAttribute = true)
	public String rel;
	@JacksonXmlProperty(isAttribute = true)
	public String href;

	public static LinkRepresentation mapFrom(Link link) {
		LinkRepresentation toReturn = new LinkRepresentation();
		if (link != null) {
			toReturn.rel = link.getRel().getValue();
			toReturn.href = link.getHref().toExternalForm();
		}

		return toReturn;
	}
}
