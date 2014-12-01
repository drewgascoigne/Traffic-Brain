package brain.main;

public class LightTiming  implements Runnable 
{
	//Global variables
	
	//Time of last light switch
	private long lastLightSwitch;
	//time between switches
	private long lightInterval;
	//length of a green light
	private long greenTimer;
	//length of a red light
	private long redTimer;
	//length of a yellow light
	private long yellowTimer;
	
	/*
	 * Constructor
	 */
	public LightTiming()
	{
		
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
	 * tick of the light program
	 * use this to control the light timings
	 */
	public void run() 
	{
		//loop in here
	}
}
