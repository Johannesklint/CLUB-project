package club.backingBeans.event;
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
	
	private Integer durationInMinutes;
	private List<User> attendees;
	private String startTime;
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
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
		return "";
	}
			

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	
}
