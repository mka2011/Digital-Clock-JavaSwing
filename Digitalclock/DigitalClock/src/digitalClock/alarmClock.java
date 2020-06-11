package digitalClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class alarmClock extends JFrame implements ItemListener, ActionListener, Runnable{
	
	JPanel aClock;
	Digitaclock dc;
	JButton start = new JButton("Start");
	JButton cancel = new JButton("cancel");
	JButton close = new JButton("close");
	JButton colorLabel = new JButton("Color Label");
    JButton colorPanel = new JButton("Color Panel");
	
	JPanel mainPanel = new JPanel(); 
	JPanel display = new JPanel();
	JPanel Buttons = new JPanel(new FlowLayout());
	JPanel remTimePanel = new JPanel(new FlowLayout());
	JPanel settings = new JPanel(new FlowLayout());
	JPanel shitPanel = new JPanel(new FlowLayout());
	
	JComboBox hour = new JComboBox();
	JComboBox minute = new JComboBox();
	JComboBox second = new JComboBox();
	JComboBox ampmSelect = new JComboBox();
	JLabel timeRemLabel = new JLabel();
	
	private int hourVal;
	private int minVal;
	private int secVal;
	private int snoozeVal;
	private int format;
	private int ampm = 0;
	boolean bool;
	boolean cancelFlag = false;
	boolean interruptFlag = false;
	
	public alarmClock(Digitaclock dc, boolean bool, int hourVal, int minVal, int secVal,Color bg,Color text){
		this.dc = dc;
		this.format = dc.hourFormat;
		for(int i = 0 ; i < 60 ; i++) {
			if(i < format)
				hour.addItem(i);
			
			minute.addItem(i);
			second.addItem(i);
		}
		this.hour.setSelectedItem((int)hourVal);
		this.minute.setSelectedItem((int)minVal);
		this.second.setSelectedItem((int)secVal);
		ampmSelect.addItem("AM"); 
		ampmSelect.addItem("PM");
		
		aClock = new JPanel();
		aClock.setBackground(bg);
		aClock.setPreferredSize(new Dimension(700, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(aClock, BorderLayout.CENTER);
        setTitle("Alarm Clock");
        pack();
        setLocationByPlatform(true);
        setVisible(true);
		
		aClock.add(mainPanel);
		mainPanel.setBounds(10,10,680,200);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		display.setLayout(new BoxLayout(display,BoxLayout.X_AXIS));
		
		mainPanel.setBackground(bg);
		display.setBackground(bg);
		Buttons.setBackground(bg);
		remTimePanel.setBackground(bg);
		settings.setBackground(bg);
		shitPanel.setBackground(bg);
		
		mainPanel.add(display);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(shitPanel);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(Buttons);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(remTimePanel);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(settings);
		
		display.add(hour);
		display.add(Box.createHorizontalStrut(10));
		display.add(minute);
		display.add(Box.createHorizontalStrut(10));
		display.add(second);
		display.add(Box.createHorizontalStrut(10));
		display.add(ampmSelect);
		
		
		hour.setForeground(Color.BLACK);
		hour.setAlignmentX(Component.CENTER_ALIGNMENT);
		Dimension d = new Dimension();
		d.setSize(60, 60);
		hour.setMaximumSize(d);
		hour.setMinimumSize(d);
		this.hourVal = hourVal;
		
		minute.setForeground(Color.BLACK);
		minute.setAlignmentX(Component.CENTER_ALIGNMENT);
		minute.setMaximumSize(d);
		minute.setMinimumSize(d);
		this.minVal = minVal;
		
		second.setForeground(Color.BLACK);
		second.setAlignmentX(Component.CENTER_ALIGNMENT);
		second.setMaximumSize(d);
		second.setMinimumSize(d);
		this.secVal = secVal;
		
		ampmSelect.setForeground(Color.BLACK);
		ampmSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		ampmSelect.setMaximumSize(d);
		ampmSelect.setMinimumSize(d);
		if(format == 12)
			this.ampm = 0;
		else
		{
			ampmSelect.setVisible(false);
		}
		
		Buttons.add(start);
		Buttons.add(cancel);
		Buttons.add(close);
		cancel.setVisible(false);
		cancel.disable();
		
		remTimePanel.add(timeRemLabel);
		timeRemLabel.setVisible(false);
		timeRemLabel.setForeground(text);
		timeRemLabel.setBackground(new Color(51,51,51));
		
		hour.addItemListener(this);
		minute.addItemListener(this);
		second.addItemListener(this);
		start.addActionListener(this);
		cancel.addActionListener(this);
		close.addActionListener(this);
		ampmSelect.addItemListener(this);
		colorLabel.addActionListener(this);
		colorPanel.addActionListener(this);
		this.bool = bool;
		
		settings.add(colorLabel);
		settings.add(colorPanel);
		
	}
	
	public alarmClock(Color bg, Color text) {
		this.aClock.setBackground(bg);
		this.mainPanel.setBackground(bg);
		this.remTimePanel.setBackground(bg);
		this.Buttons.setBackground(bg);
		this.settings.setBackground(bg);
		this.shitPanel.setBackground(bg);
		this.display.setBackground(bg);
		this.timeRemLabel.setForeground(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			bool = true;
			if(interruptFlag)
				this.run();
			else
				new Thread(this).start();
						
		}
		else if(e.getSource() == cancel)
		{	
			alarmClock ac = new alarmClock(dc,false,0,0,0,aClock.getBackground(),timeRemLabel.getForeground());
			setVisible(false);
		}
		else if(e.getSource() == close)
		{
			setVisible(false);
		}
		else if(e.getSource() == colorPanel)
		{
			Color color = JColorChooser.showDialog(this, "Select an Color", Color.black);
			aClock.setBackground(color);
			mainPanel.setBackground(color);
			remTimePanel.setBackground(color);
			Buttons.setBackground(color);
			settings.setBackground(color);
			shitPanel.setBackground(color);
			display.setBackground(color);
		}
		else if(e.getSource() == colorLabel)
		{
			Color color = JColorChooser.showDialog(this, "Select an Color", Color.black);
			timeRemLabel.setForeground(color);
		}
	}
	
	public void run() {
		start.setVisible(false);
		start.disable();
		hour.setEnabled(false);
		minute.setEnabled(false);
		second.setEnabled(false);
		cancel.setVisible(true);
		cancel.enable();
		timeRemLabel.setVisible(true);
		timeRemLabel.setText("");
		Date dateCheck = new Date();
		String setTime = "";
		if(dateCheck.getHours()>hourVal)
			setTime = 24+hourVal+":"+minVal+":"+secVal;
		else if(dateCheck.getHours() == hourVal)
		{
			if(dateCheck.getMinutes() > minVal)
				setTime = 24+hourVal+":"+minVal+":"+secVal;
			else if(dateCheck.getMinutes() == minVal)
				{
					if(dateCheck.getSeconds()>secVal)
						setTime = 24+hourVal+":"+minVal+":"+secVal;
					else
						setTime = hourVal+":"+minVal+":"+secVal;
				}
			else
				setTime = hourVal+":"+minVal+":"+secVal;
		}
		else
			setTime = hourVal+":"+minVal+":"+secVal;
		while(bool)
		{
			Date date = new Date();
			if(hourVal == date.getHours() && minVal == date.getMinutes() && secVal == date.getSeconds())
				bool = false;
			SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
			String sysTime = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			try 
			{
				Date date1 = form.parse(sysTime);
				Date date2 = form.parse(setTime);
				long remTime = date2.getTime() - date1.getTime();
				long remSec = remTime/1000;
				long remMin = remSec/60;
				long remHour = remMin/60;
				remSec %= 60;
				remMin %= 60;
				timeRemLabel.setText("Time Remaining -> "+remHour+" : "+remMin + " : " + remSec);
			} 
			catch (ParseException e1) 
			{
				e1.printStackTrace();
			}
			
			try 
			{
					Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.getMessage();
			}
		}
		if(cancelFlag == true)
			timeRemLabel.setText("Alarm Cancelled");
		else 
		{
				Date dateHelper = new Date();
				audio a = new audio(dateHelper.getDay());
				timeRemLabel.setText("TIME UP");
				int opt = JOptionPane.showConfirmDialog(aClock,"Would you like to snooze");
				if(opt == JOptionPane.YES_OPTION)
				{
					bool = true;
					timeRemLabel.setVisible(true);
					timeRemLabel.setText("");
					minVal = minVal + 1;
					if(minVal >= 60)
					{
						minVal %= 60;
						hourVal = (hourVal+1)%24;
					}
					alarmClock ac = new alarmClock(dc,bool,hourVal,minVal,secVal,aClock.getBackground(),timeRemLabel.getForeground());
					setVisible(false);
					new Thread(ac).start();
				}
				a.shut();
		}
		cancel.setText("New");
	}
	
	public static void main(String args[])
	{
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == hour) {
			hourVal = ((int)e.getItem());
			if(format == 12 && ampm == 1)
					hourVal += 12;
			
		}
		if(e.getSource() == minute)
			minVal = (int)e.getItem();
		 
		if(e.getSource() == second)
			secVal = (int)e.getItem();
		
		if(e.getSource() == ampmSelect)
		{
			if((String)e.getItem() == "AM")
				ampm = 0;
			else
				ampm = 1;

		}
		
	}
	
	
}