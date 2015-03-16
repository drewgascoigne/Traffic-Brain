package intersection;

import java.awt.Color;

import main.Main;

public class Intersection implements Runnable {
	public static final Color GREEN_LIGHT = Color.GREEN;
	public static final String YELLOW_LIGHT = "yellow";
	public static final String RED_LIGHT = "red";
	private static final long serialVersionUID = 1L;

	TrafficLight north, south, east, west;
	public long timerStart;
	public long timerEnd;
	public boolean lightEnd = false;
	int counter = 0;

	public TrafficLight[] trafficLights;

	public Intersection(TrafficLight[] trafficLights) {
		this.trafficLights = trafficLights;
	}

	public void run() {
		for (int i = 0; i < trafficLights.length; i++) {
			trafficLights[i].newTimer(trafficLights[i].getLightTime());
		}

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while (delta >= 1) {
				ticks++;
				for (int i = 0; i < trafficLights.length; i++) {
					trafficLights[i].tick();
				}
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender) {
				frames++;
				updateLights();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				frames = 0;
				ticks = 0;
			}

		}
	}

	private void updateLights() {
		for(int i = 0; i < trafficLights.length ; i++){
			trafficLights[i].update();
			
		}
		
	}

	public boolean isYellow() {
		for (int i = 0; i < trafficLights.length; i++) {
			if (trafficLights[i].currentLight == Color.YELLOW) {
				return true;
			}
		}
		return false;
	}

	public void restartLights() {
		if (!isYellow()) {
			for (int i = 0; i < trafficLights.length; i++) {
				trafficLights[i].restartTimer();
			}
		} else {
			System.out
					.println("EROR IN RESTART LIGHTS: cannot restart lights when one is yellow");
		}
	}

	public void addToLightTime(long time) {
		if (!isYellow()) {
			for (int i = 0; i < trafficLights.length; i++) {
				trafficLights[i].endTime += time;
			}
		} else {
			System.out
					.println("EROR IN ADD TO LIGHTS TIME: cannot add time to lights when one is yellow");

		}
	}

	public void subtractFromLights(long time) {
		if (!isYellow()) {
			long[] times = new long[4];
			for (int i = 0; i < trafficLights.length; i++) {
				times[i] = trafficLights[i].endTime - time;
				if (times[i] < System.currentTimeMillis()) {
					System.out
							.println("EROR IN SUBTRACT FROM LIGHTS TIME: there is not enough time remaining to subtract further from the lights");
					return;
				}
			}
			for (int i = 0; i < trafficLights.length; i++) {
				trafficLights[i].endTime = times[i];
			}

		} else {
			System.out
					.println("EROR IN SUBTRACT FROM LIGHTS TIME: cannot subtract time from lights when one is yellow");

		}
	}

//	public void lightEnd(TrafficLight li) {
//		if (li.currentLight == Color.GREEN) {
//			// go to yellow light
//			li.currentLight = Color.YELLOW;
//		} else if (li.currentLight == Color.YELLOW) {
//			// go to red light
//			li.currentLight = Color.RED;
//		} else if (li.currentLight == Color.RED) {
//			// go to green light
//			li.currentLight = Color.GREEN;
//		}
//
//		li.newTimer(li.getLightTime());
//	}
}
