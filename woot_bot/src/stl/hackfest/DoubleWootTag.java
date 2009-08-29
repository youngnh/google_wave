package stl.hackfest;

public class DoubleWootTag extends WootTag<Double> {

    public DoubleWootTag(String property) {
	super(property);
    }

    public Double convert(String value) {
	return Double.parseDouble(value);
    }

}