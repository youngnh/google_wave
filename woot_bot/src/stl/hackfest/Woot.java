package stl.hackfest;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.servlet.http.*;

public class Woot {

    private Map<String,String> data;

    public Woot() throws IOException {
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
	    this.data = processContents(contents);
	} catch(MalformedURLException e) {
	    // swallow b/c if we screwed up the URL...
	}
    }

    private Map<String,String> processContents(String contents) {
	Map<String,String> stuff = new Hashtable<String,String>();
	String wootTag = "<woot:([^ ]*)[^>]*>([^<]*)</woot:.*>";
	Pattern pattern = Pattern.compile(wootTag);
	Matcher m = pattern.matcher(contents);

	String content, tag;
	while(m.find()) {
	    tag = m.group(1);
	    content = m.group(2);
	    stuff.put(tag,content);
	}

	return stuff;
    }

    public Map<String,String> getData() {
	return this.data;
    }
}