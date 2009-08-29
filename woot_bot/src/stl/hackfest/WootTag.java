package stl.hackfest;

public abstract class WootTag<T> {
    
    private String property;

    public WootTag(String property) {
	this.property = property;
    }    

    public abstract T convert(String value) throws Exception;

    public String getProperty() {
	return this.property;
    }
}