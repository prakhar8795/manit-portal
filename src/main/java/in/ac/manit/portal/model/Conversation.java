package in.ac.manit.portal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Conversation {
	
	@JsonProperty("sender") private UserMessage sender; 
	@JsonProperty("reciever")  private UserMessage reciever;
	@JsonProperty("message") private String message;
	
	@JsonCreator
	public Conversation(@JsonProperty("sender") UserMessage sender,@JsonProperty("reciever")  UserMessage reciever,
			@JsonProperty("message") String message) {
		setSender(sender) ;
		setReciever(reciever) ;
		setMessage(message) ;
	}
	
	public UserMessage getSender() {
		return sender;
	}
	public void setSender(UserMessage sender) {
		this.sender = sender;
	}
	public UserMessage getReciever() {
		return reciever;
	}
	public void setReciever(UserMessage reciever) {
		this.reciever = reciever;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
