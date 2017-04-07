package com.jam01.alps.registry.adapter.web.dto;

import com.jam01.alps.registry.domain.profile.Descriptor;
import com.jam01.alps.registry.domain.profile.Ext;
import com.jam01.alps.registry.domain.profile.Id;
import com.jam01.alps.registry.domain.profile.Type;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jam01 on 4/5/17.
 */
public class DescriptorDTO {
	@XmlAttribute
	public String id;
	@XmlAttribute
	public String rt;
	public DocDTO doc;
	@XmlAttribute
	public String name;
	public Ext ext;
	@XmlAttribute
	public String type;
	@XmlAttribute
	public String href;
	public List<DescriptorDTO> descriptor;

	private static DescriptorDTO mappedFrom(Descriptor descriptor) {
		DescriptorDTO toReturn = new DescriptorDTO();
		toReturn.type = descriptor.getType().toString().toLowerCase();
		toReturn.doc = DocDTO.mapFrom(descriptor.getDoc());
		toReturn.name = descriptor.getName();
		toReturn.id = descriptor.getId() != null ? descriptor.getId().getValue() : null;
		toReturn.href = descriptor.getSuperDescriptor() != null ? descriptor.getSuperDescriptor().getLocation().toASCIIString() : null;
		toReturn.rt = descriptor.getRt() != null ? descriptor.getRt().getLocation().toString() : null;
		toReturn.ext = descriptor.getExt();


		if (!descriptor.getDescriptors().isEmpty()) {
			toReturn.descriptor = new ArrayList<>();
			toReturn.descriptor.addAll(descriptor.getDescriptors().stream().map(DescriptorDTO::mappedFrom).collect(Collectors.toList()));
		}

		return toReturn;
	}

	public static List<DescriptorDTO> mappedFrom(List<Descriptor> descriptors) {
		if (descriptors != null)
			return descriptors.stream().map(DescriptorDTO::mappedFrom).collect(Collectors.toList());
		else return null;
	}

	public static DescriptorGraph something(DescriptorDTO dto, DescriptorGraph descriptorGraph) {
		Descriptor toAdd = new Descriptor();
		if (dto.type != null)
			toAdd.setType(Type.valueOf(dto.type));
		if (dto.doc != null)
			toAdd.setDoc(DocDTO.mapFrom(dto.doc));
		if (dto.name != null)
			toAdd.setName(dto.name);
		if (dto.id != null)
			toAdd.setId(new Id(dto.id));




		return null;
	}

	public static List<Descriptor> mapFrom(List<DescriptorDTO> descriptorDTOs) {
//		if (descriptorDTOs != null)
//			return descriptorDTOs.stream().map(DescriptorDTO::mapFrom).collect(Collectors.toList());
		return null;
	}
}
