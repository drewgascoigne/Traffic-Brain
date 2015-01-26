package simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import brain.main.Brain;

public class VehicleStub 
{
	public static final String MESSAGE = "Hello traffic, this is car";
	Brain brain;
	ArrayList<String> plateNumbers = new ArrayList<String>();
	int counter = 0;
	
	public VehicleStub(Brain b)
	{

//		public RFsocket(ActionListener actionListener) {
//			this.mainListener = actionListener;
		this.brain = b;
		
	}
	public void tick()
	{
		int length = String.valueOf(counter).length();
		String resultString = "";
		for(int i = 0;i<(8-length);i++)
		{
			resultString = resultString+"0";
		}
		resultString = resultString+counter;
		resultString = resultString+MESSAGE+resultString;
		
		plateNumbers.add(resultString);
		counter++;
		System.out.println("Simulating car: "+resultString);
		this.brain.actionPerformed(new ActionEvent(this, 0, resultString));

		//brain.rf.dataReceivedSimulation(resultString);
		
	}
	
	
}
