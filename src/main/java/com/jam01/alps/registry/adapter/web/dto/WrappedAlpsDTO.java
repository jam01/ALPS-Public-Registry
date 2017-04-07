package com.jam01.alps.registry.adapter.web.dto;

/**
 * Created by jam01 on 4/5/17.
 *
 * Currently there's no way to selectively wrap json objects in a root element.
 * See: https://github.com/FasterXML/jackson-annotations/issues/33
 * This class works around that.
 */
public class WrappedAlpsDTO {
	public AlpsDTO alps;

	public WrappedAlpsDTO(AlpsDTO alps) {
		this.alps = alps;
	}
}