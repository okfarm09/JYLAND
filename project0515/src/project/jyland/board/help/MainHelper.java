package project.jyland.board.help;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainHelper {
	
	public static String message(String msg) {
		String a = "";
		if(msg.length() > 30) {
			a += msg;
			a = a.substring(0, 30) + "...";
		} else {
			a = msg;
		}
		return a;
	}
	
	public static String mmdd(Date wdate) {
		String sdate = wdate.toString();
		String mm = sdate.substring(5,7);
		String dd = sdate.substring(8,10);
		return mm + "/" + dd;
	}

}

