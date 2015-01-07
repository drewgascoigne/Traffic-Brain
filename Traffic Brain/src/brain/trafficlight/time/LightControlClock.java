package brain.trafficlight.time;

import brain.main.Brain;

public class LightControlClock implements Runnable
{
	public static final String GREEN_LIGHT = "green";
	public static final String YELLOW_LIGHT = "yellow";
	public static final String RED_LIGHT = "red";
	private static final long serialVersionUID = 1L;
	
	TrafficLightInfo north, south, east, west; 
	public long timerStart;
	public long timerEnd;
	public boolean lightEnd = false;
	int counter = 0;
	Brain control;
	
	public TrafficLightInfo[] trafficLight;
	public LightControlClock(Brain m)
	{
		trafficLight = new TrafficLightInfo[4];
		trafficLight[0] = new TrafficLightInfo( 4,GREEN_LIGHT,  m.trafficLight[0], this);//north
		trafficLight[1] = new TrafficLightInfo( 4,GREEN_LIGHT,  m.trafficLight[1], this);//south
		trafficLight[2] = new TrafficLightInfo( 4, RED_LIGHT,  m.trafficLight[2], this);//east
		trafficLight[3] = new TrafficLightInfo( 4,RED_LIGHT, m.trafficLight[3], this);//west
		control = m;
	}
	public void run() 
	{
		for(int i =0;i<trafficLight.length;i++)
		{
			trafficLight[i].newTimer(trafficLight[i].getLightTime());
		}

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while(true)
		{
			long now = System.nanoTime();
			delta+=(now -lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while(delta>=1)
			{
				ticks++;
				for(int i =0;i<trafficLight.length;i++)
				{
					trafficLight[i].tick();
				}
				delta-=1;	
				shouldRender = true;
			}
			if(shouldRender)
			{
				frames++;
				control.updateLights();	
			}
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer +=1000;
				frames = 0;
				ticks = 0;
			}
		
			
		}
	}
	public boolean isYellow()
	{
		for(int i =0;i<trafficLight.length;i++)
		{
			if(trafficLight[i].currentLight == YELLOW_LIGHT)
			{
				return true;
			}
		}
		return false;
	}
	public void restartLights()
	{
		if(!isYellow())
		{
			for(int i =0;i<trafficLight.length;i++)
			{
				trafficLight[i].restartTimer();
			}
		}
		else
		{
			System.out.println("EROR IN RESTART LIGHTS: cannot restart lights when one is yellow");
		}
	}
	public void addToLightTime(long time)
	{
		if(!isYellow())
		{
			for(int i =0;i<trafficLight.length;i++)
			{
				trafficLight[i].endTime+=time;
			}
		}
		else
		{
			System.out.println("EROR IN ADD TO LIGHTS TIME: cannot add time to lights when one is yellow");

		}
	}
	public void subtractFromLights(long time)
	{
		if(!isYellow())
		{
			long[] times = new long[4];
			for(int i =0;i<trafficLight.length;i++)
			{
				times[i] = trafficLight[i].endTime-time;
				if(times[i]<System.currentTimeMillis())
				{
					System.out.println("EROR IN SUBTRACT FROM LIGHTS TIME: there is not enough time remaining to subtract further from the lights");
					return;
				}
				
				
			}
			for(int i =0;i<trafficLight.length;i++)
			{
				trafficLight[i].endTime = times[i];
			}

		}
		else
		{
			System.out.println("EROR IN SUBTRACT FROM LIGHTS TIME: cannot subtract time from lights when one is yellow");

		}
	}
	public void lightEnd(TrafficLightInfo li)
	{
		switch(li.currentLight)
		{
			case GREEN_LIGHT:
				//go to yellow light
				li.currentLight = YELLOW_LIGHT;
				break;
			case YELLOW_LIGHT:
				//go to red light
				li.currentLight = RED_LIGHT;
				break;
			case RED_LIGHT:
				//go to green light
				li.currentLight = GREEN_LIGHT;
				break;
		}
		li.newTimer(li.getLightTime());
	}

}
