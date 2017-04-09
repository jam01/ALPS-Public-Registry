package com.jam01.alps.application.dto;

import com.jam01.alps.domain.Doc;
import com.jam01.alps.domain.Format;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by jam01 on 4/5/17.
 */

public class DocDTO {
	@XmlAttribute
	public String format;
	@XmlValue
	public String value;

	public static DocDTO mapFrom(Doc doc) {
		DocDTO toReturn = null;
		if (doc != null) {
			toReturn = new DocDTO();
			toReturn.format = doc.getFormat().toString().toLowerCase();
			toReturn.value = doc.getValue();
		}
		return toReturn;
	}

	public static Doc mapFrom(DocDTO dto) {
		Doc toReturn = null;

		if (dto != null) {
			Format format;

			if (dto.format == null)
				format = Format.TEXT;
			else
				try {
					format = Format.valueOf(dto.format);
				} catch (IllegalArgumentException e) {
					// Unrecognized format, treated as plain text per Section 2.2.5
					format = Format.TEXT;
				}

			toReturn = new Doc(format, dto.value);
		}

		return toReturn;
	}
}
