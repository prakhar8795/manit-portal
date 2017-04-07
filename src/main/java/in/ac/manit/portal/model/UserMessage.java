package in.ac.manit.portal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMessage {
	

	@JsonProperty("userID") private String userID ;
	@JsonProperty("userName") private String userName ;
	@JsonProperty("userProfileImage") private String userProfileImage ;
	@JsonProperty("conversationID") private String conversationID ;
	
	@JsonCreator
	public UserMessage(@JsonProperty("userID")String userID, @JsonProperty("userName") String userName, 
			@JsonProperty("userProfileImage") String userProfileImage, @JsonProperty("conversationID") String conversationID) {
		setUserID(userID) ;
		setUserName(userName) ;
		setUserProfileImage(userProfileImage) ;
		setConversationID(conversationID) ;
		System.out.println("Reciever: " + userID + " " + userName + " " + userProfileImage + " " + conversationID);
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserProfileImage() {
		return userProfileImage;
	}
	
	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConversationID() {
		return conversationID;
	}

	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}
}
