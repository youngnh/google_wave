package stl.hackfest;

import com.google.wave.api.*;

public class WootBot extends AbstractRobotServlet {

    @Override
    public void processEvents(RobotMessageBundle bundle) {
	Wavelet wavelet = bundle.getWavelet();

	if(bundle.wasSelfAdded()) {
	    Blip blip = wavelet.appendBlip();
	    TextView textView = blip.getDocument();
	    textView.append("I'm alive!");
	}

	for(Event e : bundle.getEvents()) {
	    if(e.getType() == EventType.WAVELET_PARTICIPANTS_CHANGED) {
		Blip blip = wavelet.appendBlip();
		TextView textView = blip.getDocument();
		textView.append("Hi, everybody!");
	    }
	}
    }

}