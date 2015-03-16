package intersection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import com.pi4j.device.piface.PiFaceLed;

import main.Main;
import main.MainNoGUI;
import violation.Violations;

public class TrafficLight {

	public Color currentLight = null;
	public int id;
	private long greenTimer = 10000;
	private long redTimer = 14000; // length of a red light
	private long yellowTimer = 4000; // length of a yellow light

	public long endTime;
	long startTime;
	long delay;

	JLabel gui;

	// id of this traffic light
	// list of violations this traffic light has received
	// possibly the buffer to be added to the cloud?
	ArrayList<Violations> violations = new ArrayList<Violations>();

	Intersection master;

	public TrafficLight(Color color, int id, JLabel gui) {
		this.id = id;
		this.gui = gui;
		switchLightTo(color);

	}

	public TrafficLight(Color color, int id) {
		currentLight = color;
		this.id = id;
	}

	public void tick() {
		long temp = System.currentTimeMillis();
		String tempform = String.format(
				"%d : %d",
				TimeUnit.MILLISECONDS.toMinutes(temp),
				TimeUnit.MILLISECONDS.toSeconds(temp)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(temp)));
		// System.out.println(""+tempform+"; TID: "+trafficID+"; Color = "+currentLight);
		if (temp > endTime) {
			// master.lightEnd(this);
			if (currentLight == Color.GREEN) {
				// go to yellow light
				switchLightTo(Color.YELLOW);

			} else if (currentLight == Color.YELLOW) {
				// go to red light
				switchLightTo(Color.RED);
				if (id == 0) {
					MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).pulse(
							getLightTime());
				}
			} else if (currentLight == Color.RED) {
				// go to green light
				switchLightTo(Color.GREEN);
				if (id == 0) {
					MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).pulse(
							getLightTime());
				}
			}

			newTimer(getLightTime());
		}
	}

	public void switchLightTo(Color light) {
		currentLight = light;

		if (id == 0) { // traffic light 0 LEDS
			if (light == Color.GREEN) {
//				MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).on();
				MainNoGUI.piface.getLed(PiFaceLed.LED6.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).off();
			} else if (light == Color.YELLOW) { // traffic light 1 go to north
//				MainNoGUI.piface.getLed(PiFaceLed.LED6.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED6.getIndex()).on();
				MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).off();
			} else if (light == Color.RED) {
//				MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED6.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).on();
			}
			
		}else if (id == 2) { // traffic light 2 switch LED to green
			if (light == Color.GREEN) {
//				MainNoGUI.piface.getLed(PiFaceLed.LED5.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED2.getIndex()).on();
				MainNoGUI.piface.getLed(PiFaceLed.LED3.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED4.getIndex()).off();
			} else if (light == Color.YELLOW) { // traffic light 1 go to north
//				MainNoGUI.piface.getLed(PiFaceLed.LED6.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED2.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED3.getIndex()).on();
				MainNoGUI.piface.getLed(PiFaceLed.LED4.getIndex()).off();
			} else if (light == Color.RED) {
//				MainNoGUI.piface.getLed(PiFaceLed.LED7.getIndex()).pulse(
//						getLightTime());
				MainNoGUI.piface.getLed(PiFaceLed.LED2.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED3.getIndex()).off();
				MainNoGUI.piface.getLed(PiFaceLed.LED4.getIndex()).on();
			}
		}

	}

	public void newTimer(long delay) {
		long temp = System.currentTimeMillis();
		String tempform = String.format(
				"%d : %d",
				TimeUnit.MILLISECONDS.toMinutes(temp),
				TimeUnit.MILLISECONDS.toSeconds(temp)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(temp)));
		System.out.println("" + tempform + "; TID: " + id + "; Color = "
				+ currentLight.toString());
		this.delay = delay;
		startTime = temp;
		endTime = delay + startTime;
	}

	public void restartTimer() {
		newTimer(delay);
	}

	public long getLightTime() {
		// System.out.println("\nCURRENT LIGHT = "+currentLight);
		if (currentLight == Color.GREEN) {
			return greenTimer;
		} else if (currentLight == Color.YELLOW) {
			return yellowTimer;
		} else if (currentLight == Color.RED) {
			return redTimer;
		}
		return -1;
	}

	public long getRemainingTime() {

		long temp = endTime - System.currentTimeMillis();

		return temp;
	}

	public int getID() {
		return id;
	}

	/*
	 * link the timer to this traffic light
	 */
	/*
	 * public void addTimer(LightTiming lt) { timing = lt; }
	 */
	/*
	 * Add a violation to the violations array list this will be used when the
	 * traffic light receives messages from the car
	 */
	public void addViolation(String type, int trafficId, String licensePlate,
			String date, String description) {
		// determine type of violation and add that type
	}

	/*
	 * Used by the brain to alert a traffic light that a car has sent it a
	 * message
	 */
	public void receivedMessage(String message) {
		// deal with the contents of the message and respond accordingly
	}

	public void update() {
		if (gui == null)
			return;
		gui.setText("" + getRemainingTime());
		gui.setForeground(currentLight);
	}
}
