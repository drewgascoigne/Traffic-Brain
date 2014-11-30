package brain.main;

import brain.communications.Receive;
import brain.communications.Send;
import brain.trafficlight.TrafficLight;

public class Brain 
{
	//Used for identifying which messages are being sent to which light
	public static final int[]  TRAFFIC_LIGHT_ID = new int[]{1000,2000, 3000, 4000}; 
	//Array of traffic lights connected to this brain
	public TrafficLight[] trafficLight;
	//communication classes
	public Receive receive;
	public Send send;
	//Main to commence the program
	public static void main(String[] args) 
	{
		new Brain();
	}
	/*
	 * Initialize Brain
	 */
	public Brain()
	{
		//initialize all four traffic lights
		for(int i =0;i<TRAFFIC_LIGHT_ID.length;i++)
		{
			trafficLight[i] = new TrafficLight(TRAFFIC_LIGHT_ID[i]);
		}
		//initialize communication classes
		send = new Send();
		receive = new Receive(this);
	}
	
	/*
	 * Used by receive class to alert the brain and return the contents of the message received
	 */
	public synchronized void receivedMessage(String message)
	{
		//parse the message
		//check validity of message?
		parseMessage(message);
	}
	
	/*
	 * Parses the content of the message to determine which traffic light needs to be alerted
	 */
	private void parseMessage(String message)
	{
		//determine which traffic light to alert
		trafficLight[0/*this index will be set to the information that is gleamed from the message*/].receivedMessage(message);
	}
}
