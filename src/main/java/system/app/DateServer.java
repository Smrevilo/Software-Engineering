package system.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {
	
	private Calendar calendar;

	public DateServer() {
		calendar = new GregorianCalendar();
	}
	
	public int getYear() {
		return calendar.get(Calendar.YEAR)%100;
	}
}
