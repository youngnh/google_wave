package stl.hackfest;

public class StringWootTag extends WootTag<String> {

    public StringWootTag(String property) {
	super(property);
    }

    public String convert(String value) {
	return value;
    }

}