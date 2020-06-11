package digitalClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class timer extends JFrame implements ItemListener 
{
	JPanel timePanel;
	JPanel jp1;
	JLabel jltime;
    JLabel jl;
    JComboBox<Integer> jcb;
    NumberFormat format;

    public Timer timer;
    public long initial;
    public long ttime2;
    public long time;
    public String ttime;
    public String stoptime;
    public long remaining;
    public int flag;
    Digitaclock dc;
    
    JButton jbt = new JButton("START");
    JButton jbt2 = new JButton("RESET");
    JButton jbt3 = new JButton("STOP");
    JButton jbt4 = new JButton("RESUME");
    JButton colorLabel = new JButton("Color Label");
    JButton colorPanel = new JButton("Color Panel");
    JButton close = new JButton("CLOSE");
    
    public timer(Digitaclock dc)
    {
    	this.dc = dc;
        timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(700, 300));
        timePanel.setBackground(new Color(51,51,51));

        jltime = new JLabel(" ");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jltime.setForeground(Color.GREEN);
        jltime.setBackground(new Color(51,51,51));
        jltime.setOpaque(true);
        jltime.setFont(new Font("Chess", Font.BOLD, 100));

        timePanel.add(jltime);

        jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp1.setBackground(new Color(51,51,51));

        jl = new JLabel("TOTAL TIME (minutes):");
        jl.setFont(new Font("Chess", Font.BOLD, 20));
        jl.setForeground(Color.GREEN);
        jp1.add(jl);

        jcb = new JComboBox<Integer>();
        for (int i = 15; i > 0; i--)
        {
            jcb.addItem(Integer.valueOf(i));
        }
        
        jcb.setSelectedIndex(0);
        ttime = "15";
        
        jp1.add(jcb);
        jp1.add(jbt);
        jp1.add(jbt2);
        jp1.add(jbt3);
        jp1.add(jbt4);
        jp1.add(colorLabel);
        jp1.add(colorPanel);
        jp1.add(close);

        getContentPane().add(jp1, BorderLayout.NORTH);
        getContentPane().add(timePanel, BorderLayout.CENTER);
        getContentPane().add(timePanel, BorderLayout.CENTER);

        Event e = new Event();
        jbt.addActionListener(e);
        jbt2.addActionListener(e);
        jbt3.addActionListener(e);
        jbt4.addActionListener(e);
        colorPanel.addActionListener(e);
        colorLabel.addActionListener(e);
        close.addActionListener(e);
        
        jcb.addItemListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Timer");
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
    }

    // this method will run when user presses the start button
    void updateDisplay(long start) 
    {
        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
    	initial = System.currentTimeMillis();
        	
    	timer.start();
    }

    // code for what happens when user presses the start or reset button
    public class Event implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            String bname = e.getActionCommand();
            
            if (bname.equals("START"))
            {
            	jbt.setEnabled(true);
            	
            	flag = 0;
                updateDisplay(1000);
                
                jbt.setEnabled(false);
                jbt4.setEnabled(false);
            }
            else if(bname.equals("STOP"))
            {
                jbt4.setEnabled(true);

                timer.stop();
                stoptime = jltime.getText();
                String[] st = stoptime.split(":");
                time = (Integer.parseInt(st[0])*60 + Integer.parseInt(st[1]))*1000;
            }
            else if(bname.equals("RESUME"))
            {	
            	flag = 1;
                updateDisplay(time);
            }
            else if(bname.equals("Color Label")) 
            {
            	Color color = JColorChooser.showDialog(timePanel, "Select an Color", Color.black);
            	jltime.setForeground(color);
            }
            else if(bname.equals("Color Panel")) 
            {
            	Color color = JColorChooser.showDialog(timePanel, "Select an Color", Color.black);
            	timePanel.setBackground(color);
            	jp1.setBackground(color);
            	jltime.setBackground(color);
            }
            else if(bname.equals("CLOSE"))
            {
            	setVisible(false);
            }
            else
            {
            	jbt.setEnabled(true);
            	
                jltime.setText("");
                timer.stop();
                remaining = convertTime();
                
                jbt4.setEnabled(false);
            }
        }
    }

    // code that is invoked by swing timer for every second passed
    public class Timeclass implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
        	if(flag == 0)
        		remaining = convertTime();
        	else
        		remaining = time+1000;
            long current = System.currentTimeMillis();
            long elapsed = current - initial;

            remaining -= elapsed;
            
            format = NumberFormat.getNumberInstance();
            format.setMinimumIntegerDigits(2);

            if (remaining < 0)
                remaining = (long) 0;
            
            int minutes = (int) (remaining / 60000);
            int seconds = (int) ((remaining % 60000) / 1000);
            jltime.setText(format.format(minutes) + ":" + format.format(seconds));

            if(remaining == 0)
            {	
            	audio a = new audio(0);
            	int opt = JOptionPane.showConfirmDialog(timePanel,"Time Up");
            	if(opt == JOptionPane.YES_OPTION)
            	{
            		jltime.setText("Time's Up");
                    jltime.setForeground(Color.GREEN);
            	}
                timer.stop();
                a.shut();
            }
        }
    }

    // get the number of minutes chosen by the user and activate convertTime method
    public void itemStateChanged(ItemEvent ie) 
    {
        ttime = (String) jcb.getSelectedItem().toString();
        convertTime();
    }

    // first need to convert no. of minutes from string to long.
    // then need to convert that to milliseconds.
    public long convertTime()
    {
        ttime2 = Long.parseLong(ttime);
        long converted = (ttime2 * 60000) + 1000;
        return converted;
    }
}
