package stl.hackfest;

import com.google.wave.api.*;

public class WootItemPrinter {

    private Blip blip;
    private Woot woot;

    public WootItemPrinter(Blip blip, Woot woot) {
	this.blip = blip;
	this.woot = woot;
    }

    public void printItem() {
	TextView textView = blip.getDocument();

	if(woot.isWootoff()) {
	    textView.append("It's a Woot Off!");
	    textView.append("\n\n");
	}

	textView.append("Today's deal is a " + woot.getProduct() + " for " + woot.getPrice());
	textView.append("\n");

	textView.append(woot.getSubtitle());
	textView.append("\n");

	textView.append(woot.getTeaser());
	textView.append("\n");

	Image image = new Image();
	image.setUrl(woot.getSubstandardImage().toString());
	textView.appendElement(image);
	textView.append("\n");

	textView.append("Shipping: " + woot.getShipping());
	textView.append("\n");

	textView.append("Condition: " + woot.getCondition());
	textView.append("\n");

	if(!woot.isSoldout()) {
	    textView.append(woot.getSoldOutPct() + "% sold out");
	} else {
	    textView.append("Completely Sold Out");
	}
	textView.append("\n");	

	textView.append("Get One For Yourself: " + woot.getPurchase().toString());
    }
}