package club.resource.chat;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	@Override
	public void init(EndpointConfig config) {		
	}

	@Override
	public void destroy() {	
	}

	@Override
	public ChatMessage decode(String textmessage) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject jsonObject = Json.createReader(new StringReader(textmessage)).readObject();
		chatMessage.setSender(jsonObject.getString("sender"));
		chatMessage.setMessage(jsonObject.getString("message"));
		chatMessage.setReceived((new Date()));
		
		return chatMessage;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

}
