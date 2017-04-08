package com.jam01.alps.registry.adapter.web.dto;

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
}