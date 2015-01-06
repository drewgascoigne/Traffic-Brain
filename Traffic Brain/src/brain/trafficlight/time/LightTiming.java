package brain.trafficlight.time;

import brain.trafficlight.TrafficLight;

public class LightTiming 
{
	//Global variables
	//finals
	public static final String GREEN_LIGHT = "green";
	public static final String YELLOW_LIGHT = "yellow";
	public static final String RED_LIGHT = "red";

	//Time of last light switch
	private long lastLightSwitch;
	//time between switches
	private long lightInterval;
	//length of a green light
	private long greenTimer = 10000;
	//length of a red light
	private long redTimer = 10000;
	//length of a yellow light
	private long yellowTimer = 4000;
	//light timer id
	private int trafficID;
	//traffic light
	private TrafficLight trafficLight;
	//actual timer
	private LTimer timer;
	//what color the current light is
	private String currentLight;
	/*
	 * Constructor
	 */
	public LightTiming(int trafficID)
	{
		this.trafficID = trafficID;
		timer = new LTimer(this);
	}
	/*Link the traffic light to this timer
	 * 
	 */
	public void addTrafficLight(TrafficLight tl)
	{
		trafficLight=tl;
	}
	/*
	 * If an emergency vehicle is approaching use this method to affect the light timers
	 */
	public void emergencyVehicleApproaching(/*parameter for where the emergency vehicle is approaching*/)
	{
		//affect the lights
	}
	/*
	 * if a pedestrian presses a walk button use this method to affect light timers
	 * parameters: determine where light was pressed and what affect to have on light timers
	 */
	public void pedestrianButton()
	{
		//affect the lights
	}
	/*
	 * indicate a traffic jam at a light
	 * parameter: id to indicate which traffic light has a jam
	 */
	public void trafficJamAtLight(int id)
	{
		//take appropriate action to try and alleviate the jam
	}
	/*
	 * 
	 */
	public void endOfLight()
	{
		switch(currentLight)
		{
			case GREEN_LIGHT:
				//go to yellow light
				currentLight = YELLOW_LIGHT;
				break;
			case YELLOW_LIGHT:
				//go to red light
				currentLight = RED_LIGHT;
				break;
			case RED_LIGHT:
				//go to green light
				currentLight = GREEN_LIGHT;
				break;
			default:
				System.out.println("ERROR: \n\t-Light timing\n\t-got to default in endOfLight()");
				break;
		}
		nextLight();
		
	}
	public void nextLight()
	{
		switch(currentLight)
		{
			case GREEN_LIGHT:
				timer.setNewTimer((int) greenTimer);
				break;
			case YELLOW_LIGHT:
				timer.setNewTimer((int) yellowTimer);
				break;
			case RED_LIGHT:
				timer.setNewTimer((int) redTimer);
				break;
			default:
				System.out.println("ERROR: \n\t-Light timing\n\t-got to default in nextLight()");
				break;
		}
	}
}
