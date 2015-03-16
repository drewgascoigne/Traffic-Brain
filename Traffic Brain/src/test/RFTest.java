package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import rf.*;

public class RFTest {
	public static void main(String[] args) {
		System.out.println("Testing started");
		
		final RFsocket rf = new RFsocket(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Full message Received: " + e.getActionCommand()+"\t"+ ((String)e.getActionCommand()).equals("Hello traffic,me is car"));
			}

		});
		
//		while(true){
//			rf.dataSend("BPFX 876:0123456789ABCDEF:BPFX 876");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					for (int i = 0; i < 2; i++) {
//						rf.dataSend("xAx Green,5 xAx");
//						rf.dataSend("xBx Green,5 xBx");
//						rf.dataSend("xCx Red,5 xCx");
//						rf.dataSend("xDx Red,5 xDx");
//					}
//					
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					for (int i = 0; i < 2; i++) {
//						rf.dataSend("xAx Red,5 xAx");
//						rf.dataSend("xBx Red,5 xBx");
//						rf.dataSend("xCx Green,5 xCx");
//						rf.dataSend("xDx Green,5 xDx");
//					}
//					
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		}); //.start();

	}

}
