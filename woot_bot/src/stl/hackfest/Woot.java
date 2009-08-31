package stl.hackfest;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import javax.servlet.http.*;
import org.apache.commons.beanutils.*;

public class Woot {

    private static final Logger log = Logger.getLogger(Woot.class.getName());

    private Map<String,WootTag<?>> propMap;

    private String shipping; // "shipping" ex: "$5 shipping"
    private URL discussion; // "discussionurl"
    private URL standardImage; // "standardimage"
    private String condition; // "condition"
    private URL purchase; // "purchaseurl"
    private URL substandardImage; // "substandardimage"
    private String teaser; // "teaser"
    private double soldOutPct; // "soldoutpercentage"
    private boolean soldout; // "soldout" ex: "False"
    private String price; // "price" ex: "$119.99"
    private int comments; // "comments"
    private URL detailImage; // "detailimage"
    private URL thumbnailImage; // "thumbnailimage"
    private URL blog; // "blogurl"
    private String subtitle; // "subtitle"
    private String product; // "product"
    private boolean wootoff; // "wootoff" ex: "False"

    public Woot() throws IOException {
	propMap = new Hashtable<String,WootTag<?>>();
	propMap.put("shipping", new StringWootTag("shipping"));
	propMap.put("discussionurl", new UrlWootTag("discussion"));
	propMap.put("standardimage", new UrlWootTag("standardImage"));
	propMap.put("condition", new StringWootTag("condition"));
	propMap.put("purchaseurl", new UrlWootTag("purchase"));
	propMap.put("substandardimage", new UrlWootTag("substandardImage"));
	propMap.put("teaser", new StringWootTag("teaser"));
	propMap.put("soldoutpercentage", new DoubleWootTag("soldOutPct"));
	propMap.put("soldout", new BooleanWootTag("soldout"));
	propMap.put("price", new StringWootTag("price"));
	propMap.put("comments", new IntWootTag("comments"));
	propMap.put("detailimage", new UrlWootTag("detailImage"));
	propMap.put("thumbnailimage", new UrlWootTag("thumbnailImage"));
	propMap.put("blogurl", new UrlWootTag("blog"));
	propMap.put("subtitle", new StringWootTag("subtitle"));
	propMap.put("product", new StringWootTag("product"));
	propMap.put("wootoff", new BooleanWootTag("wootoff"));

	refresh();
    }

    public void refresh() throws IOException {
	try {
	    URL url = new URL("http://www.woot.com/salerss.aspx");
	    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    String line;
	    String contents = "";
	    while((line = reader.readLine()) != null) {
		contents += (line + "\n");
	    }
	    processContents(contents);
	} catch(MalformedURLException e) {
	    // swallow b/c if we screwed up the URL...
	}
    }

    private void processContents(String contents) {
	String wootTag = "<woot:([^ ]*)[^>]*>([^<]*)</woot:.*>";
	Pattern pattern = Pattern.compile(wootTag);
	Matcher m = pattern.matcher(contents);

	String content, tag;
	while(m.find()) {
	    tag = m.group(1);
	    content = m.group(2);
	    setProperty(tag, content);
	}
    }

    private void setProperty(String tag, String content) {
	try {
	    WootTag wootTag = propMap.get(tag);
	    BeanUtils.setProperty(this, wootTag.getProperty(), wootTag.convert(content));
	} catch(Exception e) {
	    e.printStackTrace();
	    log.severe("Something bad happened setting a Woot object property: " + e.getMessage());
	}
    }

    public String getShipping() {
	return this.shipping;
    }

    public void setShipping(String shipping) {
	this.shipping = shipping;
    }

    public URL getDiscussion() {
	return this.discussion;
    }

    public void setDiscussion(URL discussion) {
	this.discussion = discussion;
    }

    public URL getStandardImage() {
	return this.standardImage;
    }

    public void setStandardImage(URL standardImage) {
	this.standardImage = standardImage;
    }

    public String getCondition() {
	return this.condition;
    }

    public void setCondition(String condition) {
	this.condition = condition;
    }

    public URL getPurchase() {
	return this.purchase;
    }

    public void setPurchase(URL purchase) {
	this.purchase = purchase;
    }

    public URL getSubstandardImage() {
	return this.substandardImage;
    }

    public void setSubstandardImage(URL substandardImage) {
	this.substandardImage = substandardImage;
    }

    public String getTeaser() {
	return this.teaser;
    }

    public void setTeaser(String teaser) {
	this.teaser = teaser;
    }

    public double getSoldOutPct() {
	return this.soldOutPct;
    }

    public void setSoldOutPct(double soldOutPct) {
	this.soldOutPct = soldOutPct;
    }

    public boolean isSoldout() {
	return this.soldout;
    }

    public void setSoldout(boolean soldout) {
	this.soldout = soldout;
    }

    public String getPrice() {
	return this.price;
    }

    public void setPrice(String price) {
	this.price = price;
    }

    public int getComments() {
	return this.comments;
    }

    public void setComments(int comments) {
	this.comments = comments;
    }

    public URL getDetailImage() {
	return this.detailImage;
    }

    public void setDetailImage(URL detailImage) {
	this.detailImage = detailImage;
    }

    public URL getThumbnailImage() {
	return this.thumbnailImage;
    }

    public void setThumbnailImage(URL thumbnailImage) {
	this.thumbnailImage = thumbnailImage;
    }

    public URL getBlog() {
	return this.blog;
    }

    public void setBlog(URL blog) {
	this.blog = blog;
    }

    public String getSubtitle() {
	return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
	this.subtitle = subtitle;
    }

    public String getProduct() {
	return this.product;
    }

    public void setProduct(String product) {
	this.product = product;
    }

    public boolean isWootoff() {
	return this.wootoff;
    }

    public void setWootoff(boolean wootoff) {
	this.wootoff = wootoff;
    }
}