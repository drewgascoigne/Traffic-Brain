package brain.communications;
import java.util.Date;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

public class Send 
{
	//Variables that are needed to send messages go here
	final Serial serial;
	public Send(Serial serial)
	{
		this.serial = serial;
	}
	
	
	public boolean sendMessage(String message)
	{
	
        try
        {

        	System.out.println("Sending serial message: "+message);
            serial.writeln(message);
            return true;
            
        }
        catch(IllegalStateException ex)
        {
            ex.printStackTrace();
            System.out.println("Failed to send serial message");
            return false;
        }
        
  
		
		//if failed to send return false
	}
}
