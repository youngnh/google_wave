package stl.hackfest;

public class BooleanWootTag extends WootTag<Boolean> {

    public BooleanWootTag(String property) {
	super(property);
    }

    public Boolean convert(String value) {
	return Boolean.parseBoolean(value.toLowerCase());
    }
}