package brain.trafficlight.time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LTimer extends Timer
{
	
	private static final long serialVersionUID = 1L;
	Timer timer;
	LightTiming control; 
	long timerStart;
	long timerEnd;
	public LTimer(LightTiming lt)
	{
		super(0, null);
		control = lt;
		initializeTimer();
	}
	public void stop()
	{
		timerEnd = System.currentTimeMillis();
		super.stop();
	}
	public void start()
	{
		timerStart = System.currentTimeMillis();
		super.start();
	}
	public int getElapsedTime()
	{
		timerEnd = System.currentTimeMillis();
		int elapsed = (int)(timerEnd - timerStart);  
		return elapsed;
	}
	public void initializeTimer()
	{
		setDelay(10000);
		addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent arg0) 
            {
               timer.stop();
               control.endOfLight();
               
            }
		});
	}
	public void resetTimer()
	{
		restart();
	}
	public void addToTimer(int time)
	{
		int timeTempElapsed = getElapsedTime();
		stop();
		int timeTempDelay = getDelay();
		int result = (timeTempDelay - timeTempElapsed) + time;
		setDelay(result);
		start();
	
	}
	public void subtractFromTimer(int time)
	{
		int timeTempElapsed = getElapsedTime();
		stop();
		int timeTempDelay = getDelay();
		int result = (timeTempDelay - timeTempElapsed) - time;
		if(result>0)
		{
			setDelay(result);
			start();
		}
		else
		{
			control.endOfLight();
		}
	
	}
	public void setNewTimer(int time)
	{
		setDelay(time);
		start();
	}
}
