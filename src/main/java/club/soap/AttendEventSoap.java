package club.soap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import club.DAO.Event;
import club.DAO.User;
import club.EJB.LoginHandlerable;
import club.EJB.interfaces.LocalEvent;
import club.EJB.interfaces.LocalUser;
import club.exceptions.LoginException;
import javax.xml.ws.handler.MessageContext;

@WebService
public class AttendEventSoap implements LoginHandlerable {

	@EJB
	private LocalEvent eventEJB;

	@EJB
	private LocalUser userEJB;

    @Resource
    WebServiceContext wsctx;

	private User user;

	//TODO: SOAP can handle params in a better way than send them as headerparams. EventId should be a normal param. 
	//TODO: Think that Soap can handle login and password in a "real" AUTH-way instead of use of header params.  
	//TODO: error handle this method(in a good way and soap correct way). no event found. invalid login. login is not approved. user already at event.
	//TODO: find a correct name/location standard and for class, package and method names.
	@WebMethod
	public String attendEvent() throws LoginException {

		MessageContext mctx = wsctx.getMessageContext();
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

		//Yes, this is real ugly. Could not make correct way of params to work
		List userList = (List) http_headers.get("Username");
		List passList = (List) http_headers.get("Password");
		List eventList = (List) http_headers.get("EventId");
		String username = userList.get(0).toString();
		String password = passList.get(0).toString();
		Integer eventId = Integer.parseInt(eventList.get(0).toString());
		//---
		
		userEJB.loginUser(username,password,this);
		if(eventEJB==null) throw new RuntimeException("event not found");
		Event event = eventEJB.getById(eventId);
		if(event==null) throw new RuntimeException("event not found");
		event.getAttendees().add(user);
		eventEJB.save(event);
		
		return "Ok"; //TODO: what should a soap return?
	}

	//TODO: can see this method in SOAP-client. thats wrong. 'hide' it somehow. try move to make an local anon class/object in attendEvent that implements LoginHandlerable	
	@Override
	public void onLogin(User tryLoginUser) {
		user = tryLoginUser;		
	}
}
