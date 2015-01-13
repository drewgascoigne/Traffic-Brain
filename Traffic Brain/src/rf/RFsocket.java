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
	public void dataReceived(SerialDataEvent event) {
		// System.out.println("Received: " + event.getData());
		String plate_no = event.getData().substring(0, 8);
		dataSend(plate_no+":ACK Hello, drive safe"); // sending ACK
		this.mainListener.actionPerformed(new ActionEvent(this, 0, event
				.getData()));

	}

	public void dataSend(final String message) {
		if(!serial.isOpen()){
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serial.writeln(message);
					sendSucess(message);
				} catch (IllegalStateException ex) {
					sendFailure(ex, message);
				}

			}
		}).start();
	}

	private void sendSucess(String message) {
		System.out.println("\t\tSent: " + message);
	}

	private void sendFailure(IllegalStateException ex, String message) {
		System.err.println("Failed: " + message + " >> ");
		ex.printStackTrace();
	}
}
