package club.resource.chat;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import club.EJB.interfaces.LocalUser;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	@EJB
	private LocalUser userEJB;

	@Override
	public void init(EndpointConfig config) {		
	}

	@Override
	public void destroy() {	
	}

	@Override
	public ChatMessage decode(String textmessage) throws DecodeException {

		
		JsonObject jsonObject = Json.createReader(new StringReader(textmessage)).readObject();		

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setSender(jsonObject.getString("sender"));
		chatMessage.setMessage(jsonObject.getString("message"));
		chatMessage.setRecipient(jsonObject.getString("recipient"));
		chatMessage.setChatRoom(jsonObject.getString("chatRoom"));
		chatMessage.setReceived(formatTime());

		return chatMessage;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}
	
	public String formatTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
		String format = simpleDateFormat.format(new Date());
		return format;
	
	}
}
