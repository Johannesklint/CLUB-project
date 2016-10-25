package club.resource.chat;

public class ChatPrivateMessage extends ChatMessage {

	private String recipient;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
}
