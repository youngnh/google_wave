package stl.hackfest;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.servlet.http.*;

public class WootServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	try {
	    resp.setContentType("text/html");
	    resp.getWriter().println("<html><body>");

	    Map<String,String> items = new Woot().getData();
	    for(String item : items.keySet()) {
		resp.getWriter().println(item + ": " + items.get(item) + "<br/>");
	    }

	    resp.getWriter().println("</body></html>");
	} catch(Exception e) {
	    resp.getWriter().println(e.getClass().getCanonicalName() + ": " + e.getMessage());
	}
    }
}