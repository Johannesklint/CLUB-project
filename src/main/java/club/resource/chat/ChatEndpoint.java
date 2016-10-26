package club.resource.chat;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import club.EJB.interfaces.LocalUser;

@ServerEndpoint(value = "/chat/{room}/{cpcid}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {

	private final Logger LOG = Logger.getLogger(getClass().getName());

	@EJB
	private LocalUser userEJB;

	@OnOpen
	public void open(final Session session, @PathParam("room") final String room, @PathParam("cpcid") final String cpcid) {
		
		session.getUserProperties().put("room", room);
		session.getUserProperties().put("cpcid", cpcid);

		if(userEJB.getUserByCpcid(cpcid)==null) {
		
		try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    }

	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {

				
		try {
			for (Session s : session.getOpenSessions()) {
				
				if(isMessageForSession(s,chatMessage)) {
					s.getBasicRemote().sendObject(chatMessage);					
				}				
			}
		} catch (IOException | EncodeException e) {
			LOG.log(Level.WARNING, "onMessage failed", e);
		}

	}

	private boolean isMessageForSession(Session session, ChatMessage chatMessage) {

		String sessionInRoom = (String) session.getUserProperties().get("room");
		String sessionCpcid = (String) session.getUserProperties().get("cpcid");
		String messageRecipient = chatMessage.getRecipient();
		String messageSender = chatMessage.getSender();
		
		if(messageSender.equals(sessionCpcid)) {
			return true;
		}
		else if(sessionInRoom!=null && !sessionInRoom.equals("")) {

			return false; //TODO: fix room logic
			
		}
		else if(messageRecipient!=null && !messageRecipient.equals("")) {

			System.out.println("#------");
			System.out.print(messageRecipient);
			System.out.print(sessionCpcid);
			System.out.println("#a-----");
			
			if(messageRecipient.equals(sessionCpcid)) {
				return true;
			}
			
		}

		return false;
	}

}
