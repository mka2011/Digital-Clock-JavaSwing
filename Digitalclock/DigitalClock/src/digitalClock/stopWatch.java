package digitalClock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class stopWatch extends JFrame {

        public Timer timer;
        public int delay = 10;
        public ActionListener b = new ButtonListener();
        
    	public int csec;
        public int minute;
        public int second;
        
        public JLabel watch;
        public JLabel disp;
                
        public JPanel dispPanel;
        public JPanel watchPanel;
        Digitaclock dc;
        
        public JButton reset;
        public JButton lap;
        public JButton start;
        public JButton stop;
        public JButton close;
        public JButton colorLabel = new JButton("Text Color");
        public JButton colorPanel = new JButton("Background Color");

		public stopWatch(Digitaclock dc) 
        {
			this.dc = dc;
            watchPanel = new JPanel();
            watchPanel.setBackground(new Color(51,51,51));
            watchPanel.setPreferredSize(new Dimension(700,600));
            
            dispPanel = new JPanel();
            dispPanel.setBackground(new Color(51,51,51));
            disp = new JLabel();
            watchPanel.setPreferredSize(new Dimension(700,600));
            
            disp.setForeground(Color.GREEN);
            disp.setFont(new Font("Chess", Font.PLAIN, 36));
            
            watch = new JLabel("00:00:00", JLabel.CENTER);
            watch.setForeground(Color.GREEN);
            watch.setFont(new Font("Chess", Font.PLAIN, 36));
            
            watchPanel.add(watch);
            dispPanel.add(disp);
            add(watchPanel);
            add(dispPanel);            
            
            reset = new JButton("reset");
            lap = new JButton("lap");
            start = new JButton("start");
            stop = new JButton("stop");
            close = new JButton("Close");
            
            start.addActionListener(b);
            stop.addActionListener(b);
            reset.addActionListener(b);
            lap.addActionListener(b);
            close.addActionListener(b);
            colorLabel.addActionListener(b);
            colorPanel.addActionListener(b);
            
            watchPanel.add(start);
            watchPanel.add(stop);
            watchPanel.add(reset);
            watchPanel.add(lap);
            watchPanel.add(close);
            watchPanel.add(colorLabel);
            watchPanel.add(colorPanel);
            timer = new Timer(delay, b);
            
            getContentPane().add(watchPanel, BorderLayout.NORTH);
            getContentPane().add(dispPanel, BorderLayout.CENTER);
            
            setBackground(new Color(51,51,51));
            setPreferredSize(new Dimension(700,700));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("StopWatch");
            pack();
            setLocationByPlatform(true);
            setVisible(true);
            
        }

        public class ButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                final int timebase = 60;
                final int centsecbase = 99;
                final int showbase = 10;

                if (minute == timebase && second == timebase && csec == centsecbase) {
                    minute = 0;
                    second = 0;
                    csec = 0;
                }
                csec++;
                if (second == timebase) {
                    minute++;
                    second = 0;
                }
                if (csec == centsecbase) {
                    second++;
                    csec = 0;
                }
                if (event.getSource() == start) {
                    timer.start();
                } else if (event.getSource() == reset) {
                   
                    timer.stop();
                    minute = 0;
                    second = 0;
                    csec = 0;
                    watch.setText("00:00:00");
                    disp.setText("");
                    
                } else if (event.getSource() == stop) {
                    csec--;
                    timer.stop();
                }
                else if(event.getSource() == lap)
                {
                	disp.setText(watch.getText());
                }
                else if(event.getSource() == colorLabel)
                {
                	Color color = JColorChooser.showDialog(watchPanel, "Select an Color", Color.black);
                	watch.setForeground(color);
                	disp.setForeground(color);
                	
                }
                else if(event.getSource() == colorPanel)
                {
                	Color color = JColorChooser.showDialog(watchPanel, "Select an Color", Color.black);
                	watchPanel.setBackground(color);
                	dispPanel.setBackground(color);
                }
                else if(event.getSource() == close)
                {
                	setVisible(false);
                }
                watch.setText(((minute < showbase) ? "0" : "") + minute + ":"
                        + ((second < showbase) ? "0" : "") + second + ":"
                        + ((csec < showbase) ? "0" : "") + csec);
            }
        }


    public static void main(String[] args) {
        
    }

}