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

		System.out.println(":......");
		System.out.println(session.getUserProperties().get("jid"));
		System.out.println(chatMessage);
		System.out.println(":.:.....");
		
		
		String room = (String) session.getUserProperties().get("room");
		String recipient = chatMessage.getRecipient();
				
		try {
			for (Session s : session.getOpenSessions()) {

				String jid = (String)s.getUserProperties().get("jid");

				if(!recipient.equals("")) {

					System.out.println("########");
							
					if (s.isOpen() && recipient.equals(jid) ) {
						System.out.println("...1");
						s.getBasicRemote().sendObject(chatMessage);
					}					
				} else {
					
					System.out.println("BBBBBBBB");
					
					if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
						s.getBasicRemote().sendObject(chatMessage);
					}					
					
				}
				
			}
		} catch (IOException | EncodeException e) {
			LOG.log(Level.WARNING, "onMessage failed", e);
		}

	}

}
