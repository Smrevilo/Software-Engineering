//Oliver s204182
package system.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {
	
	private Calendar calendar;

	//Oliver s204182
	public DateServer() {
		calendar = new GregorianCalendar();
	}
	
	//Oliver s204182
	public int getYear() {
		return calendar.get(Calendar.YEAR)%100;
	}
	
}
