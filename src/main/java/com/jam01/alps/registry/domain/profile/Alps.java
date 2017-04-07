package com.jam01.alps.registry.domain.profile;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/4/17.
 */
public class Alps {
	private String version = "1.0";
	private Doc doc;
	private List<Descriptor> descriptor = new ArrayList<>();
	private Link link;

	public static Alps buildGraph(List<Descriptor> descriptor,
								  Map<Descriptor, Descriptor> childRelations,
								  Map<URI, Descriptor> superRelations,
								  Map<URI, List<Descriptor>> rtRelations) {






		return null;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public List<Descriptor> getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(List<Descriptor> descriptor) {
		// Throw all IDs into a HashSet to assert uniqueness
		List<Id> toValidate = descriptor.stream().map(Descriptor::getId).collect(Collectors.toList());
		Set<Id> uniqueIds = new HashSet<>(toValidate);

		if (uniqueIds.size() != toValidate.size()) {
			throw new IllegalArgumentException("IDs are not unique within ALPS profile");
		}

		this.descriptor = descriptor;
	}
}
