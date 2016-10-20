package club.backingBeans.event;

import java.text.SimpleDateFormat;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import club.DAO.Event;

@Named(value="eventWhenFormater")
@Dependent
public class EventWhenFormater {

	public String render(Event event) {
		
		String a = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(event.getStartTime());
		String b = new SimpleDateFormat("HH:mm").format(event.getEndTime());
		//TODO: if different date, show date on end time
		return a+" - "+b;

	}
}
