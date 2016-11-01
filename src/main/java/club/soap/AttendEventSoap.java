package club.soap;


import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import club.DAO.event.Event;
import club.DAO.user.User;
import club.EJB.interfaces.LocalEvent;
import club.EJB.interfaces.LocalUser;
import club.exceptions.LoginException;

@WebService
public class AttendEventSoap {

	@EJB
	private LocalEvent eventEJB;

	@EJB
	private LocalUser userEJB;
	    	
	@WebMethod
	public void attendEvent(int userId, int eventId) throws LoginException {
						
		User user = userEJB.getById(userId);
		Event event = eventEJB.getById(eventId);
		if(user==null) throw new RuntimeException("user not found");
		if(event==null) throw new RuntimeException("event not found");
		event.getAttendees().add(user);
		eventEJB.save(event);
	}

	@WebMethod
	public void unAttendEvent(int userId, int eventId) throws LoginException {
						
		User user = userEJB.getById(userId);
		Event event = eventEJB.getById(eventId);
		if(user==null) throw new RuntimeException("user not found");
		if(event==null) throw new RuntimeException("event not found");
		event.getAttendees().remove(user);
		eventEJB.save(event);
	}
		
}
