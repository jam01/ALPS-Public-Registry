package com.jam01.alps.application.dto;

import com.jam01.alps.domain.Link;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by jam01 on 4/5/17.
 */
public class LinkDTO {
	@XmlAttribute
	public String rel;
	@XmlAttribute
	public String href;

	public static LinkDTO mapFrom(Link link) {
		LinkDTO toReturn = new LinkDTO();
		if (link != null) {
			toReturn.rel = link.getRel().getValue();
			toReturn.href = link.getHref().toExternalForm();
		}

		return toReturn;
	}
}
