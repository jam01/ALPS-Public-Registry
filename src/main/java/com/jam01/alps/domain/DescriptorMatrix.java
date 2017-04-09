package com.jam01.alps.domain;

import com.jam01.alps.domain.exception.ValidationException;

import java.net.URI;
import java.util.*;

/**
 * Created by jam01 on 4/7/17.
 */
public class DescriptorMatrix {
	private URI lastRoot;
	private Map<URI, Descriptor> descriptorMap = new HashMap<>();
	private Map<URI, List<URI>> childRelations = new HashMap<>();
	private Map<URI, URI> superRelations = new HashMap<>();
	private Map<URI, URI> rtRelations = new HashMap<>();

	public void addDescriptor(Descriptor toAdd) {
		if (descriptorMap.containsKey(toAdd.get_Id()))
			throw new ValidationException("IDs MUST be unique within ALPS profile per Section 2.2.7");
		descriptorMap.put(toAdd.get_Id(), toAdd);
	}

	public void addSuperRelation(Descriptor from, URI to) {
		superRelations.put(from.get_Id(), to);
	}

	public void addRtRelation(Descriptor from, URI to) {
		rtRelations.put(from.get_Id(), to);
	}

	public void addDescriptorRelaToLastRoot(Descriptor from) {
		List<URI> fromDescRelations = childRelations.get(from.get_Id());
		if (fromDescRelations == null)
			fromDescRelations = new ArrayList<>();
		fromDescRelations.add(lastRoot);
		childRelations.put(from.get_Id(), fromDescRelations);
	}

	public Map<URI, Descriptor> getDescriptorMap() {
		return descriptorMap;
	}

	public Set<Map.Entry<URI, List<URI>>> getChildRelations() {
		return childRelations.entrySet();
	}

	public Set<Map.Entry<URI, URI>> getSuperRelations() {
		return superRelations.entrySet();
	}

	public Set<Map.Entry<URI, URI>> getRtRelations() {
		return rtRelations.entrySet();
	}

	public URI getLastRoot() {
		return lastRoot;
	}

	public void setLastRoot(URI lastRoot) {
		this.lastRoot = lastRoot;
	}
}
