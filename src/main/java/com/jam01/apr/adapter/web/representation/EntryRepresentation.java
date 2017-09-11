package com.jam01.apr.adapter.web.representation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.jam01.apr.domain.registry.Entry;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */

@JsonRootName(value = "entry")
public class EntryRepresentation extends ResourceSupport {
	public List<String> keywords;
	public String title;

	public static EntryRepresentation mappedFrom(Entry entry) {
		EntryRepresentation toReturn = null;
		if (entry != null) {
			toReturn = new EntryRepresentation();
			toReturn.keywords = entry.getKeywords();
			toReturn.title = entry.getTitle();
		}

		return toReturn;
	}

	public static Entry mapFrom(EntryRepresentation representation) {
		return new Entry(representation.title, representation.keywords);
	}
}
