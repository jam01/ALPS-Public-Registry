package com.jam01.alps.registry.adapter.web.dto;

import com.jam01.alps.registry.domain.profile.Alps;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */

//@JsonRootName("alps")
@XmlRootElement(name = "alps")
public class AlpsDTO {

	@XmlAttribute
	public String version;
	public DocDTO doc;
	public List<DescriptorDTO> descriptor;
	public LinkDTO link;

	public static AlpsDTO mapFrom(Alps alps) {
		AlpsDTO toReturn = new AlpsDTO();
		toReturn.version = alps.getVersion();
		toReturn.doc = DocDTO.mapFrom(alps.getDoc());
		toReturn.descriptor = DescriptorDTO.mappedFrom(alps.getDescriptor());
		toReturn.link = LinkDTO.mapFrom(alps.getLink());

		return toReturn;
	}

	public static Alps mapFrom(AlpsDTO alpsDTO) {
		Alps toReturn = new Alps();
		toReturn.setVersion(alpsDTO.version);
		toReturn.setDoc(DocDTO.mapFrom(alpsDTO.doc));

		DescriptorGraph descriptorGraph = new DescriptorGraph();
		for (DescriptorDTO childDto : alpsDTO.descriptor) {
			DescriptorDTO.something(childDto, descriptorGraph);
		}



//		toReturn.setDescriptor(DescriptorDTO.mapFrom(alpsDTO.descriptor));
//		try {
//			toReturn.setLink(new Link(new Rel(alpsDTO.link.rel), new URL(alpsDTO.link.href)));
//		} catch (MalformedURLException e) {
//			throw new IllegalArgumentException(e);
//		}

		return toReturn;
	}
}