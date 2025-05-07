package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		Client display = new Client("display", Common.BROKERHOST, Common.BROKERPORT);

		display.connect();
		display.createTopic(Common.TEMPTOPIC);
		display.subscribe(Common.TEMPTOPIC);

		for (int i = 0; i < COUNT; i++) {
			Message msg = display.receive();

			if (msg instanceof PublishMsg) {
				String temp = ((PublishMsg) msg).getMessage();
				System.out.println("DISPLAY: " + temp);
			}
		}

		display.unsubscribe(Common.TEMPTOPIC);
		display.disconnect();

		System.out.println("Display stopping ...");
	}
}
