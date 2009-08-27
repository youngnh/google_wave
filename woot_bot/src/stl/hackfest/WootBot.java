package stl.hackfest;

import com.google.wave.api.*;

import java.util.logging.Logger;

public class WootBot extends AbstractRobotServlet {

    private static final Logger log = Logger.getLogger(WootBot.class.getName());

    @Override
    public void processEvents(RobotMessageBundle bundle) {
	Wavelet wavelet = bundle.getWavelet();

	if(bundle.wasSelfAdded()) {
	    log.info("self was added");
	    Blip blip = wavelet.appendBlip();
	    TextView textView = blip.getDocument();
	    textView.append("Woot Bot!");
	}

	for(Event e : bundle.getEvents()) {
	    log.info("event type: " + e.getType().toString());
	    if(e.getType() == EventType.WAVELET_PARTICIPANTS_CHANGED) {
		log.info("wavelet participants changed");
		Blip blip = wavelet.appendBlip();
		TextView textView = blip.getDocument();
		textView.append("Hi, everybody!");
	    }
	}
    }

}