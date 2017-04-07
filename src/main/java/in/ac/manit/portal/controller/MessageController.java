package in.ac.manit.portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mongodb.DBObject;

import in.ac.manit.portal.model.UserMessage;
import in.ac.manit.portal.service.MessageService;
import in.ac.manit.portal.service.ProfileDataService;

@Controller
public class MessageController {
	
	private MessageService messageService = new MessageService() ;
	
	
	@RequestMapping(value = "new-message" , method = RequestMethod.POST)
	public String addNewMessage(
			@RequestParam("senderID")String senderID, @RequestParam("senderName")String senderName, @RequestParam("senderProfileImage") String senderProfileImage,
			@RequestParam("recieverID") String recieverID, @RequestParam("recieverName")String recieverName, @RequestParam("recieverProfileImage") String recieverProfileImage,
			@RequestParam("conversationID") String conversationID, @RequestParam("message") String message, RedirectAttributes redirectAttributes) {
		
		UserMessage reciever = new UserMessage(recieverID, recieverName, recieverProfileImage, conversationID) ;
		System.out.println("Reciever: " + reciever);
		UserMessage sender = new UserMessage(senderID, senderName, senderProfileImage, conversationID) ;
		System.out.println("Sender: " + sender);
		messageService.createNewMessage(conversationID, sender, reciever, message) ;
		redirectAttributes.addAttribute("userID", senderID) ;
		return "redirect:message" ;
	}
	
	@RequestMapping(value = "getConversationsByID", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getConversationByID(@RequestParam("conversationID") String conversationID) {
		return messageService.getConversationByID(conversationID) ;
	}
	
	/* 
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	 public String messages(ModelMap model) {
		 
		 DBObject profileData = null ;
		 ProfileDataService pds = new ProfileDataService();
		 profileData = pds.getProfileData() ;	 
		 model.addAttribute("profile", profileData) ;
		 String userID = "" ;
		 return loadMessages(userID, model) ;
	 }
	 */
	
	@RequestMapping(value = "message" , method = {RequestMethod.POST, RequestMethod.GET} )
	public String loadMessages( HttpServletRequest request, ModelMap model) {
		
		String userID = (String) (request.getParameter("userID")==null ? "invalid": request.getParameter("userID"))  ; 
		if(userID.equals("invalid")) {
			HttpSession session = request.getSession() ;
			if(session.getAttribute("userID")!=null) {
				userID = (String) session.getAttribute("userID") ;
			}
			else {
				return "redirect:login" ;
			}
		}
		
		DBObject messageData = messageService.getAllMessagesID(userID) ;
		if(messageData!=null) {
			System.out.println("Onloading: " + messageData.get("userConversationIDs").toString());
			
			ObjectMapper mapper = new ObjectMapper();
			TypeFactory typeFactory = mapper.getTypeFactory();
			List<UserMessage> userMessages = null ;
			try {
				userMessages = mapper.readValue(messageData.get("userConversationIDs").toString(), 
						typeFactory.constructCollectionType(List.class, UserMessage.class));
				
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
			
			model.addAttribute("messageData", userMessages) ;
		}
		
		ProfileDataService pds = new ProfileDataService() ;
		DBObject profileData = pds.getProfileData(userID) ;
		model.addAttribute("senderData",profileData) ;
		model.addAttribute("profile", profileData) ;
		return "user-message" ;
		
	}
	
}
