package club.backingBeans.event;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.event.Event;
import club.DAO.news.News;
import club.DAO.user.User;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.post.PostBean;
import club.backingBeans.user.LoginUserBean;


public abstract class EventBean extends PostBean<Event> {

	private int selectedNewsId;
	
	private int durationInMinutes;
	private List<User> attendees;
	private Date startTime = Date.from(Instant.now().minusSeconds(500 * 10000000));
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalNews newsEJB;
	
	public EventBean() {
		System.out.println("Creating new NewsBean");
	}
	
	@PostConstruct
	public void init() {
		super.setAuthor(loginUserBean.getUser());
	}
	
	
	

	
	public String deleteNews(){

		News newsToUpdate = newsEJB.getById(selectedNewsId);
		newsToUpdate.setHidden(true);

		if(newsEJB.save(newsToUpdate) != null){
			return "news-list.xhtml";
		}
		return ""; //TODO: do error handler
	}
			

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	
}
