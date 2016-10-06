package club.backingBeans;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Event;
import club.DAO.News;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.user.LoginUserBean;


public abstract class EventBean extends PostBean<Event> {

	private int selectedNewsId;
	private Timestamp startTime;
	private int durationInMinutes;
	private List<User> attendees;
	
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
		super.redirectIfNotLoggedIn(loginUserBean);
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
			
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	
}
