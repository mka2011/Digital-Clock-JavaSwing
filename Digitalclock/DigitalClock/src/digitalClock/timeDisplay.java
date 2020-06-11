package digitalClock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.TimeZone;

public class timeDisplay extends Thread{
	Digitaclock dc;
	String timeZone;
	int hourFormat;
	int hours;
	String ampm;
	
	timeDisplay(Digitaclock dc){
		this.dc = dc;
		this.start();
	}
	 
	void setTimeZone(String timeZone) { 
		this.timeZone = timeZone;
	}
	
	public void run() { 
		while(true)
		{
			Date dateHelper = new Date();
			DateFormat df = new SimpleDateFormat("HH : mm : ss");
			df.setTimeZone(TimeZone.getTimeZone(timeZone));
			String time ;
			if(hourFormat == 12) 
			{
				String timeTemp = df.format(dateHelper);
				int hours = Integer.parseInt(timeTemp.substring(0,2));
				if(hours >= 12) 
				{
					String timeTemp2 = Integer.parseInt(timeTemp.substring(0,2))%12+"";
					time = timeTemp2 + timeTemp.substring(2) + " PM";
				}
				else
				{
					String timeTemp2 = Integer.parseInt(timeTemp.substring(0,2))%12+"";
					time = timeTemp2 + timeTemp.substring(2) + " AM";
				}
			}
			else
				time = df.format(dateHelper);
			int day = dateHelper.getDay();
			String date = dateHelper.getDate()+"";
			int month = dateHelper.getMonth();
			int year = 1900 + dateHelper.getYear();
			switch(day) {
				case 0 : dc.dayLabel.setText("Sunday");
					break;
				case 1 : dc.dayLabel.setText("Monday");
					break;
				case 2 : dc.dayLabel.setText("Tuesday");
					break;
				case 3 : dc.dayLabel.setText("Wednesday");
					break;
				case 4 : dc.dayLabel.setText("Thursday");
					break;
				case 5 : dc.dayLabel.setText("Friday");
					break;
				case 6 : dc.dayLabel.setText("Saturday");
					break;
				
			}
			
			switch(month) {
				case 0 : dc.dateLabel.setText(date+" January "+year);
					break;
				case 1 : dc.dateLabel.setText(date+" February "+year);
					break;
				case 2 : dc.dateLabel.setText(date+" March"+year);
					break;
				case 3 : dc.dateLabel.setText(date+" April"+year);
					break;
				case 4 : dc.dateLabel.setText(date+" May "+year);
					break;
				case 5 : dc.dateLabel.setText(date+" June "+year);
					break;
				case 6 : dc.dateLabel.setText(date+" July "+year);
					break;
				case 7 : dc.dateLabel.setText(date+" August "+year);
					break;
				case 8 : dc.dateLabel.setText(date+" September "+year);
					break;
				case 9 : dc.dateLabel.setText(date+" October "+year);
					break;
				case 10 : dc.dateLabel.setText(date+" November "+year);
					break;
				case 11 : dc.dateLabel.setText(date+" December "+year);
					break;
			}
			
			dc.timeLabel.setText(time);
		}
	}
}
