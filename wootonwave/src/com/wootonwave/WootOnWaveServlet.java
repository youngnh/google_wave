package com.wootonwave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

@SuppressWarnings("serial")
public class WootOnWaveServlet extends AbstractRobotServlet {

	@Override
	public void processEvents(RobotMessageBundle bundle) {
		Wavelet wavelet = bundle.getWavelet();

		for (Event event : bundle.getEvents()) {
			Blip eventBlip = event.getBlip();
			
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			log("B4 check. event type: " + event.getType().name() + " event text: " + eventBlip.getDocument().getText() + "\n");

			if (event.getType() == EventType.BLIP_SUBMITTED) {
				log("In check. \n");

				if (!isANewBlip(eventBlip) && (eventBlip.getDocument().getText().contains("woot") || event.getChangedTitle().contains("woot"))) {
					Map<String, String> wootData = readRss();

					for (String key : wootData.keySet()) {
						textView.append(key + ": " + wootData.get(key) + "\n");
					}
				}
			}
		}
	}

	private boolean isANewBlip(Blip eventBlip) {
		return eventBlip.getBlipId().contains("TBD");
	}

	private Map<String, String> readRss() {
		URL url;
		Map<String, String> stuff = null;
		String contents = "";
		try {
			url = new URL("http://www.woot.com/salerss.aspx");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				contents += (line + "\n");
			}

			stuff = new Hashtable<String, String>();
			String wootTag = "<woot:([^ ]*)[^>]*>([^<]*)</woot:.*>";
			Pattern pattern = Pattern.compile(wootTag);
			Matcher m = pattern.matcher(contents);

			String content, tag;
			while (m.find()) {
				tag = m.group(1);
				content = m.group(2);
				stuff.put(tag, content);
			}

		} catch (Exception e) {
			log(e.getMessage());
		}

		log("here is the rss contents: " + contents);

		return stuff;
	}
}
