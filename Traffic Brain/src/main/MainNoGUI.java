package main;

import intersection.Intersection;
import intersection.TrafficLight;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

import rf.RFsocket;


public class MainNoGUI implements ActionListener {
	// Used for identifying which messages are being sent to which light
	public static final int[] TRAFFIC_LIGHT_ID = new int[] { 0, 1, 2, 3 };
	// Array of traffic lights connected to this brain

	// communication classes
	RFsocket rf;

	Intersection intersectionTimer;
	Thread lightTimerThread;
	Container c;
	public static PiFace piface;

	public static void main(String[] args) {
		new MainNoGUI();
	}

	/*
	 * Initialize Brain
	 */
	public MainNoGUI() {

		 try {
		 piface = new PiFaceDevice(Spi.CHANNEL_0);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }

		TrafficLight[] trafficLights = new TrafficLight[4];
		trafficLights[0] = new TrafficLight(Color.GREEN, 0);// north
		trafficLights[1] = new TrafficLight(Color.GREEN, 1);// south
		trafficLights[2] = new TrafficLight(Color.RED, 2);// east
		trafficLights[3] = new TrafficLight(Color.RED, 3);// west

		// initialize communication classes
		// rf = new RFsocket(this);

		intersectionTimer = new Intersection(trafficLights);
//		intersectionTimer.run();
		
		RFsocket rf = new RFsocket(this);

	}

	/*
	 * Parses the content of the message to determine which traffic light needs
	 * to be alerted
	 */
	private void parseMessage(String message) {
		// determine which traffic light to alert
		// trafficLight[0/*this index will be set to the information that is
		// gleamed from the message*/].receivedMessage(message);
		System.out.println("Received = " + message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Car just passed>> modify the car count");
		
	}

}
