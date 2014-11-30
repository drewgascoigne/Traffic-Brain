package brain.communications;

import brain.main.Brain;

public class Receive implements Runnable 
{
	//global variables for Receive
	private Brain brain;
	public Receive(Brain brain)
	{
		//initialize receive message
		this.brain = brain;
	}
	public void run() 
	{
		receiveMessage();
	}
	/*
	 * Listen indefinitely for a message, if a message is received alert brain
	 */
	public void receiveMessage()
	{
		//insert code for receiving messages here
	}
	
	/*
	 * send message to the brain
	 */
	public void alertBrain(String message)
	{
		//acces the brain class
		brain.receivedMessage(message);
	}
}
