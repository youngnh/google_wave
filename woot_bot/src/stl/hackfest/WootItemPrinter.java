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
	
    }
}