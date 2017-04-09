package com.jam01.alps.application.dto;

import com.jam01.alps.domain.Ext;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

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
}
