package club.soap;


import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import club.DAO.Event;
import club.DAO.User;
import club.EJB.LoginHandlerable;
import club.EJB.interfaces.LocalEvent;
import club.EJB.interfaces.LocalUser;
import club.exceptions.LoginException;

@WebService
public class AttendEventSoap {

	@EJB
	private LocalEvent eventEJB;

	@EJB
	private LocalUser userEJB;
	
	private User user = null;
    
	@WebMethod
	public String attendEvent(String username, String password, int eventId) throws LoginException {
						
		LoginHandlerable loginHandlerable = new LoginHandlerable() {
			
			@Override
			public void onLogin(User tryLoginUser) {
				setUser(tryLoginUser);
			}
		};
		
		userEJB.loginUser(username,password,loginHandlerable);
		Event event = eventEJB.getById(eventId);
		if(event==null) throw new RuntimeException("event not found");
		event.getAttendees().add(user);
		eventEJB.save(event);
		
		return "Ok";
	}

	
	private void setUser(User user) {
		this.user = user;
	}
	
}
