package digitalClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.control.ColorPicker;

    public class Digitaclock extends JFrame implements ActionListener, ItemListener
    {
    	JPanel frmDigitalClock;
        static timeDisplay var;
        String timeZone = "Asia/Kolkata";
        String fontUser = "Times New Roman";
        String fontStyle = "BOLD";
        int sizeFont = 15;
        
        int hourFormat = 24;
        JButton timer = new JButton("Timer");
        JButton stopWatch = new JButton("Stop Watch");
        JButton alarmButton = new JButton("Alarm Clock");
        JButton colorLabel = new JButton("Color Label");
        JButton colorPanel = new JButton("Color Panel");
        
        JPanel mainPanel = new JPanel();
        JPanel time = new JPanel();
        JPanel buttons = new JPanel(new FlowLayout());
        JPanel settings = new JPanel(new FlowLayout());
        
        JLabel timeLabel = new JLabel();
        JLabel dayLabel = new JLabel();
        JLabel dateLabel = new JLabel();
        
        JComboBox formatSelect = new JComboBox();
        JComboBox timeZoneCB = new JComboBox();
        JComboBox textFormat = new JComboBox();
        JComboBox textSize = new JComboBox();
        JComboBox textStyle = new JComboBox();
        
        public static void main(String[] args) throws Exception
        {
                Digitaclock window = new Digitaclock();
                var = new timeDisplay(window); 
                var.timeZone = "Asia/Kolkata";
        }

        public Digitaclock() 
        {
        	formatSelect.addItem(24);
        	formatSelect.addItem(12);
        	timeZoneCB.addItem("Asia/Kolkata");
        	timeZoneCB.addItem("Asia/Dubai");
        	timeZoneCB.addItem("Asia/Kathmandu");
        	timeZoneCB.addItem("Asia/Bangkok");
        	timeZoneCB.addItem("America/Detroit");
        	timeZoneCB.addItem("America/Chicago");
        	timeZoneCB.addItem("America/Buenos_Aires");
        	timeZoneCB.addItem("Africa/Cairo");
        	timeZoneCB.addItem("America/Argentina/Rio_Gallegos");
        	timeZoneCB.addItem("Antarctica/Vostok");
        	timeZoneCB.addItem("Asia/Macau");
        	timeZoneCB.addItem("Asia/Muscat");
        	timeZoneCB.addItem("Asia/Qatar");
        	timeZoneCB.addItem("Australia/Sydney");
        	timeZoneCB.addItem("Canada/Central");
        	textFormat.addItem("ChessType");
        	textFormat.addItem("Times New Roman");
        	textFormat.addItem("Arial");
        	textFormat.addItem("Century");
        	textFormat.addItem("Courier New");
        	textFormat.addItem("Lucida Sans");
        	textStyle.addItem("PLAIN");
        	textStyle.addItem("BOLD");
        	textStyle.addItem("ITALIC");
        	for(int i=5 ; i<=30 ; i++)
        		textSize.addItem(i);
        	
        	frmDigitalClock = new JPanel();
    		frmDigitalClock.setBackground(new Color(51, 51, 51));
    		frmDigitalClock.setPreferredSize(new Dimension(700, 300));
    		
    		frmDigitalClock.add(mainPanel);
    		mainPanel.setBounds(10,10,680,200);
    		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
    		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		mainPanel.add(time);
    		mainPanel.add(Box.createVerticalGlue());
    		mainPanel.add(buttons);
    		mainPanel.add(Box.createVerticalGlue());
    		mainPanel.add(settings);
    		mainPanel.setBackground(new Color(51, 51, 51));
    		time.setBackground(new Color(51, 51, 51));
    		buttons.setBackground(new Color(51, 51, 51));
    		settings.setBackground(new Color(51,51,51));
    		
    		time.setLayout(new BoxLayout(time,BoxLayout.Y_AXIS));
    		
    		dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		dayLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		timeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		time.add(dayLabel);
    		time.add(timeLabel);
    		time.add(dateLabel);
    		
    		dayLabel.setForeground(Color.GREEN);
    		timeLabel.setForeground(Color.GREEN);
    		dateLabel.setForeground(Color.GREEN);
    		dayLabel.setFont(new Font("ChessType",Font.PLAIN,15));
    		dateLabel.setFont(new Font("ChessType",Font.PLAIN,15));
    		timeLabel.setFont(new Font("ChessType",Font.PLAIN,30));
    		
    		Dimension d = new Dimension();
    		d.setSize(60, 60);
    		formatSelect.setMaximumSize(d);
    		formatSelect.setMinimumSize(d);
    		formatSelect.setSelectedItem((int)24);
    		timeZoneCB.setMaximumSize(d);
    		timeZoneCB.setMinimumSize(d);
    		timeZoneCB.setSelectedItem((String)"Asia/Kolkata");
    		buttons.add(timer);
    		buttons.add(alarmButton);
    		buttons.add(stopWatch);
    		buttons.add(formatSelect);
    		buttons.add(timeZoneCB);
    		
    		settings.add(colorLabel);
    		settings.add(colorPanel);
    		settings.add(textFormat);
    		settings.add(textSize);
    		settings.add(textStyle);
    		
    		colorLabel.addActionListener(this);
    		colorPanel.addActionListener(this);
    		timer.addActionListener(this);
    		textFormat.addItemListener(this);
    		alarmButton.addActionListener(this);
    		stopWatch.addActionListener(this);
    		formatSelect.addItemListener(this);
    		timeZoneCB.addItemListener(this);
    		textSize.addItemListener(this);
    		textStyle.addItemListener(this);
    		
            getContentPane().add(frmDigitalClock, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Digital Clock");
            pack();
            setLocationByPlatform(true);
            setVisible(true);
        }

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == timer)
			{
				timer var = new timer(this);
				var.setVisible(true);
			}
			else if(e.getSource() == alarmButton)
			{
				alarmClock var = new alarmClock(this,false,0,0,0,new Color(51,51,51),Color.GREEN);
			}
			else if(e.getSource() == stopWatch) 
			{
				stopWatch var = new stopWatch(this);
			}
			else if(e.getSource() == colorPanel)
			{
				Color color = JColorChooser.showDialog(this, "Select an Color", Color.black);
				frmDigitalClock.setBackground(color);
				mainPanel.setBackground(color);
				time.setBackground(color);
				buttons.setBackground(color);
				settings.setBackground(color);
			}
			else if(e.getSource() == colorLabel)
			{
				Color color = JColorChooser.showDialog(this, "Select an Color", Color.black);
				timeLabel.setForeground(color);
				dayLabel.setForeground(color);
				dateLabel.setForeground(color);
			}
		}
		
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == formatSelect) 
			{
				hourFormat = (int)e.getItem();
				var.hourFormat = hourFormat;
			}
			else if(e.getSource() == timeZoneCB)
			{
				timeZone = (String)e.getItem();
				var.timeZone = timeZone;
			}
			else if(e.getSource() == textFormat)
			{
				String tempFont = (String)e.getItem();
				if(fontStyle == "PLAIN")
					timeLabel.setFont(new Font(tempFont,Font.PLAIN,sizeFont));
				else if(fontStyle == "BOLD")
					timeLabel.setFont(new Font(tempFont,Font.BOLD,sizeFont));
				else
					timeLabel.setFont(new Font(tempFont,Font.ITALIC,sizeFont));
				fontUser = tempFont;
			}
			else if(e.getSource() == textSize) 
			{
				int tempSize = (int)e.getItem();
				if(fontStyle == "PLAIN")
					timeLabel.setFont(new Font(fontUser,Font.PLAIN,tempSize));
				else if(fontStyle == "BOLD")
					timeLabel.setFont(new Font(fontUser,Font.BOLD,tempSize));
				else
					timeLabel.setFont(new Font(fontUser,Font.ITALIC,tempSize));
				sizeFont = tempSize;
			}
			else if(e.getSource() == textStyle)
			{
				String tempStyle = (String)e.getItem();
				if(tempStyle == "PLAIN")
					timeLabel.setFont(new Font(fontUser,Font.PLAIN,sizeFont));
				else if(tempStyle == "BOLD")
					timeLabel.setFont(new Font(fontUser,Font.BOLD,sizeFont));
				else
					timeLabel.setFont(new Font(fontUser,Font.ITALIC,sizeFont));
				fontStyle = tempStyle;
			}
		}

		
    }

