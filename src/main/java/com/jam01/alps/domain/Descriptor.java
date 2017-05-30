package com.jam01.alps.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jam01 on 4/4/17.
 */
public class Descriptor {
	private final Id id;
	private final Doc doc;
	private final String name;
	private final Type type;
	private final URI _Id;
	private Descriptor rt;
	private Descriptor superDescriptor;
	private Ext ext;
	private List<Descriptor> descriptors = new ArrayList<>();

//	public Descriptor(Id id, Doc doc, String name) {
//		this(id, doc, name, Type.SEMANTIC, new URI(null, null, id));
//	}

	public Descriptor(Id id, Doc doc, String name, Type type, URI uri) {
		this.id = id;
		this.doc = doc;
		this.name = name;
		this.type = type;
		this._Id = uri;
	}

	public URI get_Id() {
		return _Id;
	}

	public Descriptor getSuperDescriptor() {
		return superDescriptor;
	}

	public void setSuperDescriptor(Descriptor superDescriptor) {
		this.superDescriptor = superDescriptor;
	}

	public Id getId() {
		return id;
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

	public String getName() {
		return name;
	}

	public Ext getExt() {
		return ext;
	}
//
//	public void setExt(Ext ext) {
//		this.ext = ext;
//	}

	public Type getType() {
		return type;
	}

	public List<Descriptor> getDescriptors() {
		return descriptors;
	}

	public void addDescriptor(Descriptor descriptor) {
		descriptors.add(descriptor);
	}
}
