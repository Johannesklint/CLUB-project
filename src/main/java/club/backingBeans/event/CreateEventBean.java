package club.backingBeans.event;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.event.Event;
import club.DAO.user.User;
import club.backingBeans.user.LoginUserBean;

@Named(value="createEventBean")
@RequestScoped
public class CreateEventBean extends EventBean{
		
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
		
	public String submit() {
		return super.createAndRedirect();
	}
	
	@PostConstruct
	public void init() {
	}
	
	@Override
	public Event getFromFields() {
		
		Event event = new Event();
		event.setAuthor(super.getAuthor());
		event.setTitle(super.getTitle());
		event.setText(super.getText());
		event.setStartTime(convertStartTimeToTimestamp());
		event.setDurationInMinutes(super.getDurationInMinutes());
		event.setCreated(Timestamp.from(Instant.now()));
		event.setAttendees(new ArrayList<User>());
		return event;
	}
	
	@Override
	public Event updateFromFields() {
		
		Event eventToUpdate = (Event)super.getById(getId());
		eventToUpdate.setTitle(super.getTitle());
		eventToUpdate.setText(super.getText());
		eventToUpdate.setDurationInMinutes(super.getDurationInMinutes());
		return eventToUpdate;
	}	
	
	private Timestamp convertStartTimeToTimestamp() {
//		Instant startTimeAsInstant = super.getStartTime().toInstant();

	

		java.util.Date temp;
		try {
			temp = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getStartTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return Timestamp.from(temp.toInstant());
		
//		return new java.sql.Date(temp.getTime());
	
	}

}
