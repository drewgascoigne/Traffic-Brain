package brain.trafficlight.time;

import java.util.concurrent.TimeUnit;

import brain.trafficlight.TrafficLight;

public class TrafficLightInfo 
{
	public static final String GREEN_LIGHT = "green";
	public static final String YELLOW_LIGHT = "yellow";
	public static final String RED_LIGHT = "red";
	
	public int numberOfLightsAtIntersection;
	public String currentLight;
	public TrafficLight trafficLight;
	
	private long greenTimer = 10000;
	//length of a red light
	private long redTimer = 14000;
	//length of a yellow light
	private long yellowTimer = 4000;
	
	long endTime;
	long startTime;
	long delay;

	LightControlClock master;
	
	long lastSimulatedCar;
	long timeDelay = 0;

	
	public TrafficLightInfo(int nolat, String cl, TrafficLight tid, LightControlClock master)
	{
		numberOfLightsAtIntersection = nolat;
		currentLight =cl;
		trafficLight = tid;
		lastSimulatedCar = System.currentTimeMillis();
		timeDelay = randomWithRange(2000, 6000);
		this.master = master;
	}
	public void tick()
	{
		long temp = System.currentTimeMillis();
		String tempform =String.format("%d : %d", 
			    TimeUnit.MILLISECONDS.toMinutes(temp),
			    TimeUnit.MILLISECONDS.toSeconds(temp) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(temp)));
	//	System.out.println(""+tempform+"; TID: "+trafficID+"; Color = "+currentLight);
		simulatedCar();
		if(temp>endTime)
		{
			master.lightEnd(this);
		}
	}
	public void simulatedCar()
	{
		long time = System.currentTimeMillis();
		if(time>(lastSimulatedCar+timeDelay))
		{
			lastSimulatedCar = time;
			timeDelay = randomWithRange(2000, 20000);
			master.control.simulation.tick();
		}
	}
	public void newTimer(long delay)
	{
		long temp = System.currentTimeMillis();
		String tempform =String.format("%d : %d", 
			    TimeUnit.MILLISECONDS.toMinutes(temp),
			    TimeUnit.MILLISECONDS.toSeconds(temp) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(temp)));
		System.out.println(""+tempform+"; TID: "+trafficLight.getTID()+"; Color = "+currentLight);
		this.delay =delay;
		startTime = temp;
		endTime = delay+startTime;
	}
	public void restartTimer()
	{
		newTimer(delay);
	}
	public long getLightTime()
	{
		//System.out.println("\nCURRENT LIGHT = "+currentLight);
		switch(currentLight)
		{
			case GREEN_LIGHT:
				return greenTimer;

			case YELLOW_LIGHT:
				return yellowTimer;
			
			case RED_LIGHT:
				return redTimer;
		
		}
		return -1;
	}
	public long getRemainingTime()
	{

		long temp = endTime - System.currentTimeMillis();
	
		return temp;
	}
	private long randomWithRange(long min, long max)
	{
	   long range = (max - min) + 1;     
	   return (long)(Math.random() * range) + min;
	}
}
