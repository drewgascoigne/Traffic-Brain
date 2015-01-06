package brain.trafficlight;

import java.util.ArrayList;

import brain.trafficlight.time.LightTiming;
import brain.violation.Violations;

public class TrafficLight 
{
	//id of this traffic light
	int id;
	//list of violations this traffic light has received
	//possibly the buffer to be added to the cloud?
	ArrayList<Violations> violations = new ArrayList<Violations>();
	private LightTiming timing;
	/*
	 * initialize traffic light
	 */
	public TrafficLight(int id)
	{
		this.id = id;
	}
	/*
	 * link the timer to this traffic light
	 */
	public void addTimer(LightTiming lt)
	{
		timing = lt;
	}
	/*
	 * Add a violation to the violations array list 
	 * this will be used when the traffic light receives messages from the car
	 */
	public void addViolation(String type, int trafficId, String licensePlate, String date, String description)
	{
		//determine type of violation and add that type
	}
	/*
	 * Used by the brain to alert a traffic light that a car has sent it a message
	 */
	public void receivedMessage(String message)
	{
		//deal with the contents of the message and respond accordingly
	}
	
	
}
