package club.backingBeans.event;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.event.Event;
import club.EJB.interfaces.LocalEvent;
import club.backingBeans.BasicFrontendBean;
import club.backingBeans.user.LoginUserBean;

@Named(value="listEventBean")
@RequestScoped
public class ListEventBean extends BasicFrontendBean {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@EJB
	private LocalEvent eventsEJB;
	
	@PostConstruct
	public void init() {
	}
		
	public List<Event> getAll(){
		return eventsEJB.getAll();
	}
}
