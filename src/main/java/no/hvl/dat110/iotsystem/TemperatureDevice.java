package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.iotsystem.Common;
import no.hvl.dat110.iotsystem.TemperatureSensor;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		Client sensor = new Client("temperaturesensor", Common.BROKERHOST, Common.BROKERPORT);

		sensor.connect();

		TemperatureSensor tempsensor = new TemperatureSensor();

		for (int i = 0; i < COUNT; i++) {
			int temp = tempsensor.read();
			sensor.publish(Common.TEMPTOPIC, Integer.toString(temp));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Allowed as-is since not modifying exception handling globally
				e.printStackTrace();
			}
		}

		sensor.disconnect();
		System.out.println("Temperature device stopping ...");
	}
}
