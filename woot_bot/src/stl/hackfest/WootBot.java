package stl.hackfest;

import com.google.wave.api.*;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class WootBot extends AbstractRobotServlet {

    private static final Logger log = Logger.getLogger(WootBot.class.getName());

    @Override
    public void processEvents(RobotMessageBundle bundle) {
	log.warning("Currently at version: " + getVersion());
	
	Wavelet wavelet = bundle.getWavelet();

	if(bundle.wasSelfAdded()) {
	    log.warning("self was added");
	    Blip blip = wavelet.appendBlip();
	    TextView textView = blip.getDocument();
	    textView.append("Woot Bot!");
	}

	listRegisteredReceivedEvents(bundle, EventType.DOCUMENT_CHANGED);

	List<Event> submittedEvents = bundle.getBlipSubmittedEvents();
	for(Event event : submittedEvents) {
	    Blip blip = event.getBlip();
	    TextView textView = blip.getDocument();
	    log.warning("Blip submitted! " + blip.getBlipId());
	    // you may want to check this: textView.getText().contains("woot") 
	    // or !responses.contains(blip.getBlipId())
	    addDealsToWavelet(wavelet);
	}
    }

    private void listRegisteredReceivedEvents(RobotMessageBundle bundle, EventType...except) {
	List<EventType> exceptions = Arrays.asList(except);

	for(Event event : bundle.getEvents()) {
	    for(EventType eventType : EventType.values()) {
		if(event.getType() == eventType && !exceptions.contains(event.getType())) {
		    log.warning("### Event of type: " + eventType.toString());
		}
	    }
	}
    }


    private void addDealsToWavelet(Wavelet wavelet) {
	try {
	    Woot woot = new Woot();

	    log.warning("Adding deals to wavelet! Today is a " + woot.getProduct());

	    Blip blip = wavelet.appendBlip();
	    log.warning("Blip added! " + blip.getBlipId());

	    new WootItemPrinter(blip, woot).printItem();
	} catch(IOException e) {
	    e.printStackTrace();
	    log.severe("Something went horribly wrong: " + e.getMessage());
	}

    }

}