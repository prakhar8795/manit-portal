package in.ac.manit.portal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import in.ac.manit.portal.model.UserMessage;
import in.ac.manit.portal.util.MongoDBUtil;

@Service
public class MessageService {
	
	
	private DB db = null ;
	
	public MessageService() {
		db = MongoDBUtil.dbStatic ;
	}
	
	public DBObject getAllMessagesID(String userID) {
		DBCollection table = db.getCollection("conversationIdData") ;
		if(table!=null) {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("userID", userID);
			DBCursor cursor = table.find(whereQuery);
			if(cursor.hasNext()) {
				DBObject profileData = cursor.next();
				return profileData;
			}
			else {
				return null ;
			}
		}
		else {
			return null ;
		}
	}
	
	private boolean insertFirstConversationRecord(String id) {
		DBCollection table = db.getCollection("conversationIdData") ;
		try {
			BasicDBObject newUser = new BasicDBObject();
			newUser.put("userID", id) ;
			List<String> temp = new ArrayList<String>() ;
			newUser.put("userConversationIDs", temp) ;
			table.insert(newUser) ;
			return true ;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return false ;
		}
	}
	
	private List<UserMessage> getConversationsByID(String id) {
		DBCollection table = db.getCollection("conversationIdData") ;
		BasicDBObject searchQuery = new BasicDBObject() ;
		searchQuery.put("userID", id) ;
		DBCursor cursor = table.find(searchQuery) ;
		List<UserMessage> result = new ArrayList<UserMessage>() ;
		if(cursor.hasNext()) {
			DBObject conversationData = cursor.next() ;
			ObjectMapper mapper = new ObjectMapper();
			TypeFactory typeFactory = mapper.getTypeFactory();
			
			try {
				result = mapper.readValue(conversationData.get("userConversationIDs").toString(), 
						typeFactory.constructCollectionType(List.class, UserMessage.class));
				return result ;
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private boolean updateConversationIDs(String id, List<UserMessage>conversationIDs) {
		
		DBCollection table = db.getCollection("conversationIdData") ;
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userID", id) ;
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null ;
		try {
			
			jsonInString = mapper.writeValueAsString(conversationIDs);
			System.out.println(jsonInString);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BasicDBObject updateFields = new BasicDBObject();
			updateFields.append("userConversationIDs", jsonInString);
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", updateFields);
			table.update(searchQuery, setQuery);
			return true ;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false ;
		}
	}
	
	private boolean updateConversationTable(String conversationID, UserMessage sender, UserMessage reciever, String message) {
		
		ObjectMapper mapper = new ObjectMapper();
		String senderJSON = null ;
		String recieverJSON = null ;
		try {
			
			senderJSON = mapper.writeValueAsString(sender);
			recieverJSON = mapper.writeValueAsString(reciever);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BasicDBObject insert = new BasicDBObject() ;
		insert.put("conversationID",conversationID) ;
		insert.put("sender", senderJSON) ;
		insert.put("reciever", recieverJSON) ;
		insert.put("message", message) ;
		
		db.getCollection("conversationData").insert(insert) ;
		
		return true ;
	}
	
	public boolean createNewMessage(String conversationID, UserMessage sender, UserMessage reciever, String message) {
		
		List<UserMessage> recieverConversations = getConversationsByID(reciever.getUserID()) ;
		if(recieverConversations.isEmpty()) {
			insertFirstConversationRecord(reciever.getUserID()) ;
		}
		
		List<UserMessage> senderConversations = getConversationsByID(sender.getUserID()) ;
		if(senderConversations.isEmpty()) {
			insertFirstConversationRecord(sender.getUserID()) ;
		}
		
		int flag=0 ;
		
		for(UserMessage i : senderConversations) {
			if(equal(i,reciever)) {
				flag=1 ;
				break ;
			}
		}
		if(flag!=1)
			senderConversations.add(reciever) ;
		updateConversationIDs(sender.getUserID(), senderConversations) ;
		
		int flagR=0 ;
		for(UserMessage i : recieverConversations) {
			if(equal(i,sender)) {
				flagR=1 ;
				break ;
			}
		}
		if(flagR!=1)
			recieverConversations.add(sender) ;
		updateConversationIDs(reciever.getUserID(), recieverConversations) ;
		
		updateConversationTable(conversationID, sender, reciever, message) ;
		
		return true ;
		
	}
	
	private boolean equal(UserMessage i, UserMessage reciever) {
		if(i.getUserID().equals(reciever.getUserID())) {
			return true ;
		}
		return false;
	}

	public List<String> getConversationByID(String conversationID) {
		BasicDBObject getData = new BasicDBObject() ;
		getData.put("conversationID", conversationID) ;
		DBCursor cursor = db.getCollection("conversationData").find(getData) ;
		List<String> result = new ArrayList<String>() ;
		while(cursor.hasNext()) {
			DBObject temp = cursor.next() ;
			ObjectMapper mapper = new ObjectMapper();
			String tempResult = null ;
			try {
				tempResult = mapper.writeValueAsString(temp) ;
				result.add(tempResult) ;
				System.out.println(tempResult);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return result ;
	}
	
}
