package test;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class main extends JFrame implements ActionListener
{
	Container c = new Container();
	JButton reset = new JButton("Reset");
	LightTiming control;
	public static void main(String[] args) 
	{
	
		new main();
		
	}
	
	public main()
	{
		control = new LightTiming(1);
		control.currentLight = control.GREEN_LIGHT;
		control.timer.setNewTimer();
		long temp = System.currentTimeMillis();
		
		
		reset.setBounds(0,0,100,50);
		reset.addActionListener(this);
		c.add(reset);
		setVisible(true);
		add(c);
		setSize(500, 500);
	
	}

	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(reset))
		{
			control.restartLight();
		}
		
	}

}
