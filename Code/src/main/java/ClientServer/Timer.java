package ClientServer;

import Abstract.AbstractClient;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Timer extends Thread
{
	private Calendar startTime = null;
	private Calendar currentTime = null;
	private AbstractClient parent = null;
	private boolean running = true;

	public void setClient(AbstractClient parent){
		this.parent = parent;
	}

	public Timer(){
		startTime = new GregorianCalendar();
	}

	public void setRunning(boolean inRunning){
		running = inRunning;
	}
	
	public String getTimePassed(){

		int second = currentTime.get(Calendar.SECOND) - startTime.get(Calendar.SECOND);
		int minute = currentTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE);
		int hour = currentTime.get(Calendar.HOUR) - startTime.get(Calendar.HOUR);

		if(second<0){
			second=60+second;
			minute--;
		}

		if(minute<0){
			minute=60+minute;
			hour--;
		}

		String time = "";

		if(hour<10){
			time=time+"0";
		}

		time = time+hour+":";

		if(minute<10){
			time=time+"0";
		}

		time = time+minute+":";

		if(second<10){
			time=time+"0";
		}

		time = time+second;

		return time;
	}
	
	public void run()
	{
			while(running){
			currentTime = new GregorianCalendar();
			parent.setClock(getTimePassed());
			try{
				sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public static void main (String[] args){
		Timer timer = new Timer();
		timer.start();
		while(true){ }
	}

}
