package stl.hackfest;

public class IntWootTag extends WootTag<Integer> {

    public IntWootTag(String property) {
	super(property);
    }

    public Integer convert(String value) {
	return Integer.parseInt(value);
    }
}