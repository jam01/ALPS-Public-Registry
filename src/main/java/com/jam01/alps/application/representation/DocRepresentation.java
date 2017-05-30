package com.jam01.alps.application.representation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.jam01.alps.domain.Doc;
import com.jam01.alps.domain.Format;


/**
 * Created by jam01 on 4/5/17.
 */

public class DocRepresentation {
	@JacksonXmlProperty(isAttribute = true)
	public String format;
	@JacksonXmlText
	public String value;

	public static DocRepresentation mapFrom(Doc doc) {
		DocRepresentation toReturn = null;
		if (doc != null) {
			toReturn = new DocRepresentation();
			toReturn.format = doc.getFormat().toString().toLowerCase();
			toReturn.value = doc.getValue();
		}
		return toReturn;
	}

	public static Doc mapFrom(DocRepresentation dto) {
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
