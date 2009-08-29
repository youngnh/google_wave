package stl.hackfest;

import java.net.*;

public class UrlWootTag extends WootTag<URL> {

    public UrlWootTag(String property) {
	super(property);
    }

    public URL convert(String value) throws Exception {
	return new URL(value);
    }
}