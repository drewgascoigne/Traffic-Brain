package brain.communications;

import java.util.Date;

import brain.main.Brain;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

//import brain.main.Brain;

public class Receive
{

	//global variables for Receive
	private Brain brain;
	public Receive(Brain brain, Serial serial)
	{
		//initialize receive message
		//this.brain = brain;
		  
		  serial.addListener(new SerialDataListener() {
	            @Override
	            public void dataReceived(SerialDataEvent event) {
	                // print out the data received to the console
	                //alertBrain(""+event.getData());
	            	System.out.println("Received: "+event.getData());
	            }            
	        });
	}

	/*
	 * send message to the brain
	 */
	public void alertBrain(String message)
	{
		brain.receivedMessage(message);
	}

}
