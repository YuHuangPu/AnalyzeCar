package Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private String getlog = null;
	private String writelog = null;
	public static void show(String log){
		System.out.println("["+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"] : " + log);
	}
	public static void write(){
		
	}
}
