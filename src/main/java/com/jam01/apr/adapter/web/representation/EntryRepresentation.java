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
	public List<String> tags;
	public String title;
//	public String id;

	public static EntryRepresentation mappedFrom(Entry entry) {
		EntryRepresentation toReturn = new EntryRepresentation();
		if (entry != null) {
			toReturn.tags = entry.getTags();
			toReturn.title = entry.getTitle();
//			toReturn.id = entry.getId().id;
		}

		return toReturn;
	}

	public static Entry mapFrom(EntryRepresentation representation) {
		return new Entry(representation.title, representation.tags);
	}
}
