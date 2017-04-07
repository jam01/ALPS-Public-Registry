package com.jam01.alps.registry.adapter.web.dto;

import com.jam01.alps.registry.domain.profile.Doc;
import com.jam01.alps.registry.domain.profile.Format;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by jam01 on 4/5/17.
 */


public class DocDTO {
	@XmlAttribute
	public Format format;
	@XmlValue
	public String value;

	public static DocDTO mapFrom(Doc doc) {
		DocDTO toReturn = null;
		if (doc != null) {
			toReturn = new DocDTO();
			toReturn.format = doc.getFormat();
			toReturn.value = doc.getValue();
		}

		return toReturn;
	}

	public static Doc mapFrom(DocDTO doc) {
		return new Doc(doc.format, doc.value);
	}
}
