package com.jam01.alps.registry.domain.profile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/4/17.
 */
public class Descriptor {
	private Id id;
	// See section 2.2.11
	private Descriptor rt;
	// See section 2.2.3
	private Descriptor superDescriptor;
	private Doc doc;
	private String name;
	private Ext ext;
	// See section 2.2.12
	private Type type = Type.SEMANTIC;
	private List<Descriptor> descriptors = new ArrayList<>();
	private URI location;

	public Descriptor(URI location) {
		this.location = location;
	}

	public Descriptor() {
	}

	public Descriptor(Id id, Type type) {
		setId(id);
		this.type = type;
	}

	public Descriptor(Id id) {
		setId(id);
	}

	public URI getLocation() {
		return location;
	}

	public void setLocation(URI location) {
		this.location = location;
	}

	public Descriptor getSuperDescriptor() {
		return superDescriptor;
	}

	public void setSuperDescriptor(Descriptor superDescriptor) {
		this.superDescriptor = superDescriptor;
	}

	public void setResolvedDescriptor(Descriptor superDescriptor) {
		if (!this.superDescriptor.location.equals(superDescriptor.getLocation()))
			throw new IllegalArgumentException("Descriptors have wrong locations");
		this.superDescriptor = superDescriptor;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
		try {
			location = new URI("#" + id.getValue());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("#" + id.getValue() + " Is not a valid argument to form a URI");
		}
	}

	public Descriptor getRt() {
		return rt;
	}

	public void setRt(Descriptor rt) {
		this.rt = rt;
	}

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ext getExt() {
		return ext;
	}

	public void setExt(Ext ext) {
		this.ext = ext;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Descriptor> getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(List<Descriptor> descriptors) {
		this.descriptors = descriptors;
	}

	public void addDescriptor(Descriptor toAdd) {
		if (descriptors == null) descriptors = new ArrayList<>();
		descriptors.add(toAdd);
	}

	public List<Id> getIdsRecursively() {
		List<Id> toReturn = new ArrayList<>();
		if (getId() != null)
			toReturn.add(getId());
		if (getDescriptors() != null)
			toReturn.addAll(getDescriptors().stream().map(Descriptor::getId).collect(Collectors.toList()));

		return toReturn;
	}
}
