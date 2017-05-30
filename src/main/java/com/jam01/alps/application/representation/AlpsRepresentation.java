package com.jam01.alps.application.representation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;


/**
 * Created by jam01 on 4/5/17.
 */

@JsonRootName(value = "alps")
public class AlpsRepresentation {
    @JacksonXmlProperty(isAttribute = true)
    public String version;
    public DocRepresentation doc;
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<DescriptorRepresentation> descriptor;
//    public LinkRepresentation link;
}