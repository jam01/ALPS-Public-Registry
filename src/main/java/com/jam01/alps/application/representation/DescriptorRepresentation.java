package com.jam01.alps.application.representation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.jam01.alps.domain.Ext;

import java.util.List;

/**
 * Created by jam01 on 4/5/17.
 */
public class DescriptorRepresentation {
    @JacksonXmlProperty(isAttribute = true)
    public String id;
    @JacksonXmlProperty(isAttribute = true)
    public String rt;
    public DocRepresentation doc;
    @JacksonXmlProperty(isAttribute = true)
    public String name;
    public Ext ext;
    @JacksonXmlProperty(isAttribute = true)
    public String type;
    @JacksonXmlProperty(isAttribute = true)
    public String href;
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<DescriptorRepresentation> descriptor;
}
