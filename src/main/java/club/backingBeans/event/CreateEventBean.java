package club.backingBeans.event;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Event;
import club.DAO.User;
import club.EJB.EventEJB;
import club.EJB.interfaces.LocalEvent;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.BasicFrontendBean;
import club.backingBeans.user.LoginUserBean;

@Named(value="createEventBean")
@RequestScoped
public class CreateEventBean extends BasicFrontendBean{
	
	private String title;
	private String text;
	private User author;
	private String startTime; // TODO: find datetime picker, else implement own converter from string?
	private int durationInMinutes;
	private List<User> attendees;
	
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalEvent eventEJB;
	
	
	public String create() {
		Event event = getEventFromFields();
		
		Event createdEvent = eventEJB.save(event);
		if(createdEvent != null) {
			//TODO: make post-details able to handle both events and news + using postBean, or split
			// into separate components
			//return "post-details.xhtml?faces-redirect=true&id=" + createdEvent.getId();			
			return "";
		} else {
			return "";
		}
		
	}


	/*
	 * Getters and setters
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getDurationInMinutes() {
		return durationInMinutes;
	}
	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
	/*
	 * End of getters and setters
	 */
	
	
	@PostConstruct
	private void init() {
		System.out.println("In CreateEventBean init()");
		super.redirectIfNotAdmin(loginUserBean);
		setAuthorFromLoggedInUser();
	}
	
	private void setAuthorFromLoggedInUser() {
		author = loginUserBean.getUser();
	}
	
	private Event getEventFromFields() {
		
		Event event = new Event();
		
		event.setAuthor(this.author);
		event.setTitle(this.title);
		event.setText(this.text);
		event.setStartTime(convertStringToStartTimeTimestamp());
		event.setDurationInMinutes(this.durationInMinutes);
		event.setCreated(Timestamp.from(Instant.now()));
		event.setAttendees(new ArrayList<User>());
		
		return event;
	}


	private Timestamp convertStringToStartTimeTimestamp() {
		//TODO: fix actual conversion or solve with datetime-picker
		TemporalAmount twoDays = Duration.ofDays(2);
		return Timestamp.from(Instant.now().plus(twoDays));
	}
	
	
}
