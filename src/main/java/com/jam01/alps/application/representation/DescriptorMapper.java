package com.jam01.alps.application.representation;

import com.jam01.alps.domain.Descriptor;
import com.jam01.alps.domain.DescriptorMatrix;
import com.jam01.alps.domain.Id;
import com.jam01.alps.domain.Type;
import com.jam01.alps.domain.exception.ValidationException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/7/17.
 */
public class DescriptorMapper {
	private static DescriptorRepresentation mappedFrom(Descriptor descriptor) {
		DescriptorRepresentation toReturn = new DescriptorRepresentation();
		toReturn.type = descriptor.getType().toString().toLowerCase();
		toReturn.doc = DocRepresentation.mapFrom(descriptor.getDoc());
		toReturn.name = descriptor.getName();
		toReturn.id = descriptor.getId() != null ? descriptor.getId().getValue() : null;
		toReturn.href = descriptor.getSuperDescriptor() != null ? descriptor.getSuperDescriptor().get_Id().toASCIIString() : null;
		toReturn.rt = descriptor.getRt() != null ? descriptor.getRt().get_Id().toString() : null;
		toReturn.ext = descriptor.getExt();


		if (!descriptor.getDescriptors().isEmpty()) {
			toReturn.descriptor = new ArrayList<>();
			toReturn.descriptor.addAll(descriptor.getDescriptors().stream().map(DescriptorMapper::mappedFrom).collect(Collectors.toList()));
		}

		return toReturn;
	}

	public static List<DescriptorRepresentation> mappedFrom(List<Descriptor> descriptors) {
		if (descriptors != null)
			return descriptors.stream().map(DescriptorMapper::mappedFrom).collect(Collectors.toList());
		else return null;
	}

	public static Descriptor mapFrom(DescriptorRepresentation dto) {
		Descriptor toReturn = null;

		if (dto != null) {
			URI uriId;
			Type descType;

			try {
				String uriFragment;
				if (dto.id != null)
					uriFragment = dto.id;
				else
					uriFragment = UUID.randomUUID().toString();
				uriId = new URI(null, null, null, uriFragment);
			} catch (URISyntaxException e) {
				throw new IllegalArgumentException("Cannot convert string to URI");
			}


			if (dto.type == null)
				descType = Type.SEMANTIC;
			else
				try {
					descType = Type.valueOf(dto.type.toUpperCase());
				} catch (IllegalArgumentException e) {
					throw new ValidationException("Invalid type value. See section 2.2.12");
				}

			toReturn = new Descriptor(
					Id.from(dto.id),
					DocRepresentation.mapFrom(dto.doc),
					dto.name,
					descType,
					uriId);
		}

		return toReturn;
	}

	public static List<Descriptor> mapFrom(List<DescriptorRepresentation> descriptorRepresentations) {
		if (descriptorRepresentations != null)
			return descriptorRepresentations.stream().map(DescriptorMapper::mapFrom).collect(Collectors.toList());
		return null;
	}

	public static DescriptorMatrix generateMatrix(DescriptorRepresentation dto) {
		return generateDepthFirstMatrix(dto, new DescriptorMatrix());
	}

	private static DescriptorMatrix generateDepthFirstMatrix(DescriptorRepresentation dto, DescriptorMatrix matrix) {
		// Add current node to matrix
		Descriptor toAdd = mapFrom(dto);
		matrix.addDescriptor(toAdd);
		// Add relations to matrix if they exist
		if (dto.href != null)
			matrix.addSuperRelation(toAdd, escapedFragment(dto.href));
		if (dto.rt != null)
			matrix.addRtRelation(toAdd, escapedFragment(dto.rt));

		// If node has no children set it as lastRoot
		if (dto.descriptor == null) {
			matrix.setLastRoot(toAdd.get_Id());
			return matrix;
		}

		if (!dto.descriptor.isEmpty()) {
			Iterator<DescriptorRepresentation> children = dto.descriptor.iterator();
			while (true) {
				if (children.hasNext()) {
					// Recursively fill matrix
					generateDepthFirstMatrix(children.next(), matrix);
					// lastRoot represents a child
					matrix.addDescriptorRelaToLastRoot(toAdd);
				} else {
					// If node has no more children set it as lastRoot
					matrix.setLastRoot(toAdd.get_Id());
					break;
				}
			}
		}

		return matrix;
	}

	private static URI escapedFragment(String uri) {
		URI toReturn;
		try {
			if (uri.charAt(0) == '#')
				toReturn = new URI(null, null, uri.substring(1));
			else {
				URL url = new URL(uri);
				toReturn = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			}
		} catch (URISyntaxException | MalformedURLException e) {
			throw new ValidationException(uri + " is not valid URI. See section 2.2.6", e);
		}

		if (toReturn.getFragment() == null)
			throw new ValidationException("The URL MUST contain a fragment per Section 2.2.7.2 referencing the related 'descriptor'.");

		return toReturn;
	}
}