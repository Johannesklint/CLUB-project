package club.resource.chat;

import java.util.Date;

import club.DAO.user.User;

public class ChatMessage {

	private String message;
	private String received;

	private String sender;
	private String recipient;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	@Override
	public String toString() {
		return "[msg:"+message+",sender:"+sender+",date:"+received+",recipient:"+recipient+"]";
	}
	
	
}
