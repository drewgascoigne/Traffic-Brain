package rf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;

public class RFsocket extends Thread implements SerialDataListener {

	// Main to commence the program
	final Serial serial = SerialFactory.createInstance();
	ActionListener mainListener;
	String inBuffer;
	int bufferSize = 23;
	int bufferIndex = 0;

	public RFsocket(ActionListener actionListener) {
		this.mainListener = actionListener;
		if (!serial.isOpen()) {
			this.start();
		}
	}

	@Override
	public void run() {
		super.run();
		serial.open(Serial.DEFAULT_COM_PORT, 9600);
		serial.addListener(this);
	}

	@Override
	public synchronized void dataReceived(SerialDataEvent event) {
		System.out.println("\t\tReceived: " + event.getData());

		if (bufferIndex == 0){
			System.out.println("\t\tmessage begin");
			inBuffer = event.getData();
			bufferIndex = inBuffer.length();
		}else {
			System.out.println("\t\tmessage concat");

			inBuffer+=event.getData().substring(0, bufferSize - bufferIndex);
			bufferIndex = inBuffer.length();
		}
		
		System.out.println("\t\tchecking buffer"+bufferIndex+" >= " + bufferSize);

		if (bufferIndex >= bufferSize-1){

			bufferIndex =0;
			this.mainListener.actionPerformed(new ActionEvent(this, 0, inBuffer));
			dataSend("<ACKHello car, me is ACK :)>"); // sending ACK
//			dataSend(plate_no + ":ACK drive safe:" + plate_no); 

		}
		
		
//		int length = event.getData().length();
//		if (length < 16){
//			System.out.println("length is invalid");
//			return;
//			}
		
//		String plate_no = event.getData().substring(0, 8);
//		if (!plate_no.equals(event.getData().substring(length - 9, length - 1))) {
//			// plate number at the beginning does not match the plate number at
//			// the end of the message
//			System.out.println("plate number is invalid, can't send message");
//			return;
//		}
//		dataSend(plate_no + ":ACK drive safe:" + plate_no); // sending ACK
		

	}

	public synchronized void dataSend(final String message) {
		System.out.println("sending: " + message);
		if (!serial.isOpen()) {
			return;
		}
		// new Thread(new Runnable() {
		// @Override
		try {
			serial.write("<".getBytes()[0]);
			for (int i = 0; i < message.length(); i++) {
				serial.write(message.getBytes()[i]);
			}
			serial.write(">".getBytes()[0]);
			sendSucess(message);
			Thread.sleep(100);
		} catch (IllegalStateException ex) {
			sendFailure(ex, message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }
		// }).start();
	}

	private void sendSucess(String message) {
		System.out.println("\t\tSent: " + message);
	}

	private void sendFailure(IllegalStateException ex, String message) {
		System.err.println("Failed: " + message + " >> ");
		ex.printStackTrace();
	}
}
