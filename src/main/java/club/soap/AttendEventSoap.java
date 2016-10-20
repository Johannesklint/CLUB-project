package club.soap;


import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import club.DAO.event.Event;
import club.DAO.user.User;
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
	
	
	// ---------------------------------------------------------
	// this is not needed if there is no "public" soap. A Soap used internally do not require login, just a simple UserId is needed.
	private User user = null;

	@Resource
	private WebServiceContext wsctx;
    	
	@WebMethod
	public void attendEventAUTH(String username, String password, int eventId) throws LoginException {
						
		LoginHandlerable loginHandlerable = new LoginHandlerable() {
			
			@Override
			public void onLogin(User tryLoginUser) {
				setUser(tryLoginUser);
			}
		};
		
		//TODO: use HTTP Basic AUTH if client can handle it
		//MessageContext mctx = wsctx.getMessageContext();
//		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
//		String authorization = ((List) http_headers.get("Authorization")).get(0).toString();
		// Basic HFKJAHJFKHRJKA <-- Base decode last part and its should give you <username>:<password> and use these to verify who is attending / logging in
		// DatatypeConverter.parseBase64Binary(lexicalXSDBase64Binary) <-- this worls
		// ----
		
		userEJB.loginUser(username,password,loginHandlerable);
		Event event = eventEJB.getById(eventId);
		if(event==null) throw new RuntimeException("event not found");
		event.getAttendees().add(user);
		eventEJB.save(event);
	}

	private void setUser(User user) {
		this.user = user;
	}
	// -----------------------------------------------
	
	
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
