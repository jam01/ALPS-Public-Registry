package com.jam01.alps.registry.domain.profile;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/4/17.
 */
public class Alps {
	private String version;
	private Doc doc;
	private List<Descriptor> roots = new ArrayList<>();
	private Map<URI, Descriptor> descriptorMap = new HashMap<>();
	private Link link;

	public Alps(String version, Doc doc, List<DescriptorMatrix> graphs) {
		this.version = version;
		this.doc = doc;

		if (graphs != null) {
			// All the relations
			Set<Map.Entry<URI, List<URI>>> childRelations = new HashSet<>();
			Set<Map.Entry<URI, URI>> superDescRelations = new HashSet<>();
			Set<Map.Entry<URI, URI>> returnRelations = new HashSet<>();

			for (DescriptorMatrix graph : graphs) {
				descriptorMap.putAll(graph.getDescriptorMap());
				childRelations.addAll(graph.getChildRelations());
				superDescRelations.addAll(graph.getSuperRelations());
				returnRelations.addAll(graph.getRtRelations());
			}

			for (Map.Entry<URI, URI> superRel : superDescRelations) {
				Descriptor superDescriptor = descriptorMap.get(superRel.getValue());
				if (superDescriptor == null)
					superDescriptor = new Descriptor(null, null, null, null, superRel.getValue());
				descriptorMap.get(superRel.getKey()).setSuperDescriptor(superDescriptor);
			}

			for (Map.Entry<URI, URI> returnRel : returnRelations) {
				descriptorMap.get(returnRel.getKey()).setRt(descriptorMap.get(returnRel.getValue()));
			}

			for (Map.Entry<URI, List<URI>> childRel : childRelations) {
				for (URI targetDesc : childRel.getValue()) {
					descriptorMap.get(childRel.getKey()).addDescriptor(descriptorMap.get(targetDesc));
				}
			}

			// Add all roots
			roots.addAll(graphs.stream().map(DescriptorMatrix::getLastRoot).map(rootURI -> descriptorMap.get(rootURI)).collect(Collectors.toList()));
		}

	}

	public Link getLink() {
		return link;
	}

	public String getVersion() {
		return version;
	}

	public Doc getDoc() {
		return doc;
	}

	public List<Descriptor> getRoots() {
		return roots;
	}

	public void setRoots(List<Descriptor> roots) {
		this.roots = roots;
	}
}
