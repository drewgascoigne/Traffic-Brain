package main;

import intersection.Intersection;
import intersection.TrafficLight;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.PiFaceLed;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

import rf.RFsocket;

public class Main extends JFrame implements ActionListener
{
	//Used for identifying which messages are being sent to which light
	public static final int[]  TRAFFIC_LIGHT_ID = new int[]{0,1,2,3}; 
	//Array of traffic lights connected to this brain

	//communication classes
	RFsocket rf;
	
	Intersection intersectionTimer;
	Thread lightTimerThread;
	
	Container c;
	JButton reset;
	JButton addTo;
	JButton subtract;
	JButton sendTestMessage;
	//LightTiming controlNorth,controlSouth,controlEast,controlWest, control;
	JLabel north, south, east, west;
	public static PiFace piface;
	
	
	public static void main(String[] args) 
	{
		new Main();

	}
	/*
	 * Initialize Brain
	 */
	public Main()
	{

		initUI();
//		try {
//			piface = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		TrafficLight[] trafficLights = new TrafficLight[4];
		trafficLights[0] = new TrafficLight(Color.GREEN, 0,north);// north
		trafficLights[1] = new TrafficLight(Color.GREEN, 1,south);// south
		trafficLights[2] = new TrafficLight(Color.RED,2,east);// east
		trafficLights[3] = new TrafficLight(Color.RED, 3,west);// west
		
		//initialize communication classes
//		rf = new RFsocket(this);
				
		
		intersectionTimer = new Intersection(trafficLights);
//		lightTimerThread  = new Thread(lightControlClock);
		intersectionTimer.run();

		
	}
	public void initUI()
	{
		c = new Container();
		reset = new JButton("Reset");
		addTo = new JButton("Add 5 sec");
		subtract = new JButton("Rem 2.5 sec");
		sendTestMessage = new JButton("Send: Test!!Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		north = new JLabel();
		north.setBounds(240,310, 100, 50);
		north.setBackground(Color.BLACK);
		north.setText("not set");
		c.add(north);
		
		south = new JLabel();
		south.setBounds(240,175, 100, 50);
		south.setBackground(Color.BLACK);
		south.setText("not set");
		c.add(south);
		
		east = new JLabel();
		east.setBounds(350,240, 100, 50);
		east.setBackground(Color.BLACK);
		east.setText("not set");
		c.add(east);
		
		west = new JLabel();
		west.setBounds(150,240, 100, 50);
		west.setBackground(Color.BLACK);
		west.setText("not set");
		c.add(west);
		
		reset.setBounds(0,0,100,50);
		reset.addActionListener(this);
		c.add(reset);
		
		addTo.setBounds(100,0,100,50);
		addTo.addActionListener(this);
		c.add(addTo);
		
		subtract.setBounds(200,0,100,50);
		subtract.addActionListener(this);
		c.add(subtract);
		
		sendTestMessage.setBounds(300,0,150,50);
		sendTestMessage.addActionListener(this);
		c.add(sendTestMessage);
		
		setVisible(true);
		add(c);
		setSize(500, 500);
		
	
	}

	
	/*
	 * Parses the content of the message to determine which traffic light needs to be alerted
	 */
	private void parseMessage(String message)
	{
		//determine which traffic light to alert
		//trafficLight[0/*this index will be set to the information that is gleamed from the message*/].receivedMessage(message);
		System.out.println("Received = "+message);
	}
	
//	public void updateLights()
//	{
//		north.setText(""+intersectionTimer.trafficLights[0].getRemainingTime());
//		north.setForeground(intersectionTimer.trafficLights[0].currentLight);
//		
//		
//		south.setText(""+intersectionTimer.trafficLights[1].getRemainingTime());
//		south.setForeground(intersectionTimer.trafficLights[1].currentLight);
//		
//		east.setText(""+intersectionTimer.trafficLights[2].getRemainingTime());
//		east.setForeground(intersectionTimer.trafficLights[2].currentLight);
//		
//		west.setText(""+intersectionTimer.trafficLights[3].getRemainingTime());
//		west.setForeground(intersectionTimer.trafficLights[3].currentLight);
//		
//	}
//	public Color getColor(String light)
//	{
////		if(light == IntersectionTimer.GREEN_LIGHT)
////		{
////			return Color.GREEN;
////		}
////		else
//			if(light == IntersectionTimer.YELLOW_LIGHT)
//		{
//			return Color.YELLOW;	
//		}
//		else if(light == IntersectionTimer.RED_LIGHT)
//		{
//			return Color.RED;	
//		}
//		
//		return Color.BLACK;
//	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(reset))
		{
			intersectionTimer.restartLights();
		}
		else if(e.getSource().equals(addTo))
		{
			intersectionTimer.addToLightTime(5000);
		}
		else if(e.getSource().equals(subtract))
		{
			intersectionTimer.subtractFromLights(2500);
		}
		else if(e.getSource().equals(sendTestMessage))
		{
			rf.dataSend("Test!!Test");
		}else if(e.getSource().equals(rf)){
			parseMessage(e.getActionCommand());
		}
//		else if(e.get){
//			north.setText(""+lightControlClock.trafficLights[0].getRemainingTime());
//			north.setForeground(lightControlClock.trafficLights[0].currentLight);
//			
//			
//			south.setText(""+lightControlClock.trafficLights[1].getRemainingTime());
//			south.setForeground(lightControlClock.trafficLights[1].currentLight);
//			
//			east.setText(""+lightControlClock.trafficLights[2].getRemainingTime());
//			east.setForeground(lightControlClock.trafficLights[2].currentLight);
//			
//			west.setText(""+lightControlClock.trafficLights[3].getRemainingTime());
//			west.setForeground(lightControlClock.trafficLights[3].currentLight);
//			
//		}
		
	}
}

