package brain.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import rf.RFsocket;
import simulation.VehicleStub;
import brain.trafficlight.TrafficLight;
import brain.trafficlight.time.LightControlClock;

public class Brain implements ActionListener
{
	//Used for identifying which messages are being sent to which light
	public static final int[]  TRAFFIC_LIGHT_ID = new int[]{0,1,2,3}; 
	//Array of traffic lights connected to this brain
	public TrafficLight[] trafficLight = new TrafficLight[TRAFFIC_LIGHT_ID.length];
	//timing class for the lights
	//communication classes
	public RFsocket rf;
	
	LightControlClock lightTimer;
	Thread lightTimerThread;
	
	public VehicleStub simulation = new VehicleStub(this);
//	Container c = new Container();
//	JButton reset = new JButton("Reset");
//	JButton addTo = new JButton("Add 5 sec");
//	JButton subtract = new JButton("Rem 2.5 sec");
//	JButton sendTestMessage = new JButton("Send: Test!!Test");
//	LightTiming controlNorth,controlSouth,controlEast,controlWest, control;
//	JLabel north, south, east, west;	
	
	
	public static void main(String[] args) 
	{
		new Brain();
	}
	/*
	 * Initialize Brain
	 */
	public Brain()
	{

		//initialize communication classes
		rf = new RFsocket(this);
		
		//initialize all four traffic lights
		for(int i =0;i<TRAFFIC_LIGHT_ID.length;i++)
		{
			trafficLight[i] = new TrafficLight(TRAFFIC_LIGHT_ID[i]);
		}

		//link light timers and traffic lights together
		/*for(int i =0;i<TRAFFIC_LIGHT_ID.length;i++)
		{
		//	trafficLight[i].addTimer(lightTimer[i]);
		//	lightTimer[i].addTrafficLight(trafficLight[i]);
		}*/

		
	//	initUI();
		
		lightTimer = new LightControlClock(this);
		lightTimerThread  = new Thread(lightTimer);
		lightTimer.run();
		
	}
//	public void initUI()
//	{
//		north = new JLabel();
//		north.setBounds(240,310, 100, 50);
//		north.setBackground(Color.BLACK);
//		north.setText("not set");
//		c.add(north);
//		
//		south = new JLabel();
//		south.setBounds(240,175, 100, 50);
//		south.setBackground(Color.BLACK);
//		south.setText("not set");
//		c.add(south);
//		
//		east = new JLabel();
//		east.setBounds(350,240, 100, 50);
//		east.setBackground(Color.BLACK);
//		east.setText("not set");
//		c.add(east);
//		
//		west = new JLabel();
//		west.setBounds(150,240, 100, 50);
//		west.setBackground(Color.BLACK);
//		west.setText("not set");
//		c.add(west);
//		
//		reset.setBounds(0,0,100,50);
//		reset.addActionListener(this);
//		c.add(reset);
//		
//		addTo.setBounds(100,0,100,50);
//		addTo.addActionListener(this);
//		c.add(addTo);
//		
//		subtract.setBounds(200,0,100,50);
//		subtract.addActionListener(this);
//		c.add(subtract);
//		
//		sendTestMessage.setBounds(300,0,150,50);
//		sendTestMessage.addActionListener(this);
//		c.add(sendTestMessage);
//		
//		setVisible(true);
//		add(c);
//		setSize(500, 500);
//		
//	
//	}

	
	/*
	 * Parses the content of the message to determine which traffic light needs to be alerted
	 */
	private void parseMessage(String message)
	{
		//determine which traffic light to alert
		//trafficLight[0/*this index will be set to the information that is gleamed from the message*/].receivedMessage(message);
		System.out.println("Received = "+message);
		//rf.(message);
		//<[1|R|10][2|R|10][3|G|6][4|G|6]>
		String temp = "<["+lightTimer.trafficLight[0].trafficLight+"|"+lightTimer.trafficLight[1].currentLight+"|"+lightTimer.trafficLight[1];
		rf.broadcast();
	}
	
	public void updateLights()
	{

//		north.setText(lightTimer.trafficLight[0].currentLight+"; "+lightTimer.trafficLight[0].getRemainingTime());
//		north.setForeground(getColor(lightTimer.trafficLight[0].currentLight));
//		
//		south.setText(lightTimer.trafficLight[1].currentLight+"; "+lightTimer.trafficLight[1].getRemainingTime());
//		south.setForeground(getColor(lightTimer.trafficLight[1].currentLight));
//		
//		east.setText(lightTimer.trafficLight[2].currentLight+"; "+lightTimer.trafficLight[2].getRemainingTime());
//		east.setForeground(getColor(lightTimer.trafficLight[2].currentLight));
//		
//		west.setText(lightTimer.trafficLight[3].currentLight+"; "+lightTimer.trafficLight[3].getRemainingTime());
//		west.setForeground(getColor(lightTimer.trafficLight[3].currentLight));
//		
	}
//	public Color getColor(String light)
//	{
//		if(light == LightControlClock.GREEN_LIGHT)
//		{
//			return Color.GREEN;
//		}
//		else if(light == LightControlClock.YELLOW_LIGHT)
//		{
//			return Color.YELLOW;	
//		}
//		else if(light == LightControlClock.RED_LIGHT)
//		{
//			return Color.RED;	
//		}
//		
//		return Color.BLACK;
//	}
	
	public void actionPerformed(ActionEvent e)
	{
//		if(e.getSource().equals(reset))
//		{
//			lightTimer.restartLights();
//		}
//		else if(e.getSource().equals(addTo))
//		{
//			lightTimer.addToLightTime(5000);
//		}
//		else if(e.getSource().equals(subtract))
//		{
//			lightTimer.subtractFromLights(2500);
//		}
//		else 
//		if(e.getSource().equals(sendTestMessage))
//		{
//			rf.dataSend("Test!!Test");
//		}
		if(e.getSource().equals(rf))
		{
			parseMessage(e.getActionCommand());
		}
		else if(e.getSource().equals(simulation))
		{
			parseMessage(e.getActionCommand());
		}
		
	}
}

