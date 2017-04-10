package in.ac.manit.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodb.BasicDBList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

import in.ac.manit.portal.service.MailSenderService;
import in.ac.manit.portal.service.AuthenticationService;

import in.ac.manit.portal.service.NewsFeedService;
import in.ac.manit.portal.service.ProfileDataService;

@Controller
public class BaseController {

	private static String VIEW_INDEX = "signup";
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {

		String name = ex.getParameterName();
	    System.out.println(name + " parameter is missing");
	    return "error" ;

	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp(ModelMap model) {

		VIEW_INDEX = "signup";
		model.addAttribute("isSignUp", "Sign Up");
		model.addAttribute("title", "Sign Up");
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		
		VIEW_INDEX = "login";
		return VIEW_INDEX;
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		
		VIEW_INDEX = "login";
		HttpSession session = request.getSession() ;
		session.removeAttribute("userID");
		return "redirect:/login" ;
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Authenticate(@RequestParam ("userID") String userID, 
			@RequestParam ("password") String pass, RedirectAttributes redirectAttributes, ModelMap model, HttpServletRequest request ) {
		
		AuthenticationService as = new AuthenticationService() ;
		if(as.authenticateUser(userID, pass))
		{
			redirectAttributes.addFlashAttribute("name", userID);
			
			//Getting NewsFeed From Database
			List<DBObject> feedList = new ArrayList<DBObject>() ;
			
			
			DBObject profileData = null ;
			ProfileDataService pds = new ProfileDataService();
			profileData = pds.getProfileData(userID) ;
			
			NewsFeedService nfs = new NewsFeedService();
			feedList = nfs.getAllFeeds(profileData);
			
			redirectAttributes.addFlashAttribute("feedList", feedList) ;
			redirectAttributes.addFlashAttribute("profile", profileData) ;
			
			HttpSession session = request.getSession() ;
			session.setAttribute("userID", userID);
			System.out.println(session.getAttribute("userID"));
			
			return "redirect:/home";
			
		}
		else
		{
			model.addAttribute("loginFailedText", "Invalid User ID or Password");
			return "login";
		}

	}
	
	@RequestMapping(value = "/forgotPasswd", method = RequestMethod.GET)
	public String forgotPasswd(ModelMap model) {

		VIEW_INDEX = "signup";
		model.addAttribute("title", "Forgot Password");
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/passwordRecovery", method = RequestMethod.POST)
	@ResponseBody
	public String passwordRecovery(@RequestParam ("userInput") String userInput) {
		
		
		MailSenderService mss = new MailSenderService();
		ProfileDataService pds = new ProfileDataService();
		Boolean isID = pds.isIDOrHandle(userInput);
		String flag;
		System.out.println(isID+" IDD");
		if(isID){
			flag = pds.userIDAvailability(userInput);
			
			if(flag.equals("true")){
				flag = "false";
			}
			
			else{
				flag = "true";
				DBObject userData = pds.getUserDataWithUserID(userInput);
				DBObject userCredential = pds.getUserCredentialWithUserID(userInput);
				mss.sendMail(userData.get("email").toString(), userData.get("userName").toString(), userCredential.get("password").toString());
			}
			
		}
		
		else{
			flag = pds.userHandleAvailability(userInput);
			
			if(flag.equals("true")){
				flag = "false";
			}
			
			else{
				flag = "true";
				DBObject userData = pds.getUserDataWithUserHandle(userInput);
				DBObject userCredential = pds.getUserCredentialWithUserHandle(userInput);
				mss.sendMail(userData.get("email").toString(), userData.get("userName").toString(), userCredential.get("password").toString());
			}
		}
		
		return "{\"flag\": "+ flag + "}" ;
	}

	@RequestMapping(value = "/getDataByYearBranch" , method = RequestMethod.POST)
	@ResponseBody
	public String getDataByYearBranch(@RequestParam("year") String year, @RequestParam("branch") String branch,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession() ;
		if(!checkValidSession(session)) {
			return null ;
		}
		
		ProfileDataService pds = new ProfileDataService() ;
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null ;
		try {
			jsonInString = mapper.writeValueAsString(pds.getDataByYearBranch(year, branch));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonInString) ;
		return jsonInString ;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		
		HttpSession session = request.getSession() ;
		if(!checkValidSession(session)) {
			return "error" ;
		}
		
		VIEW_INDEX = "home";
		
		//Getting NewsFeed From Database
		
		List<DBObject> feedList = new ArrayList<DBObject>() ;
		
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData((String)session.getAttribute("userID")) ;
		
		NewsFeedService nfs = new NewsFeedService();
		feedList = nfs.getAllFeeds(profileData);
		
		model.addAttribute("feedList", feedList);
		model.addAttribute("profile", profileData) ;
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(ModelMap model, HttpServletRequest request) {
		
		HttpSession session = request.getSession() ;
		if(!checkValidSession(session)) {
			return "error" ;
		}
		
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData((String)session.getAttribute("userID")) ;
				
		model.addAttribute("profile", profileData) ;
		
		VIEW_INDEX = "result";
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession() ;
		if(!checkValidSession(session)) {
			return "error" ;
		}
		
		VIEW_INDEX = "user-profile-edit";
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData((String)session.getAttribute("userID")) ;		
		model.addAttribute("profile", profileData) ;
		return VIEW_INDEX;
		
	}
	
	 @RequestMapping(value = "/post", method = RequestMethod.GET)
	 public String postUpdate(ModelMap model, HttpServletRequest request) {
		 
	 	HttpSession session = request.getSession() ;
		if(!checkValidSession(session)) {
			return "error" ;
		}
		VIEW_INDEX = "post-new-update";
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData((String)session.getAttribute("userID")) ;
		 String semester = pds.getSemester(profileData.get("batch").toString());
		 BasicDBList subjectList = pds.getStudentSubjectData(profileData.get("branch").toString(), semester);
		 List<DBObject> branches = pds.getBranchNames();
		 
		 model.addAttribute("profile", profileData) ;
		 model.addAttribute("subjectList",subjectList);
		 model.addAttribute("branchList",branches);
	
		 // Spring uses InternalResourceViewResolver and return back index.jsp
		 return VIEW_INDEX;

	 }

	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	 public String addFaculty(ModelMap model, HttpServletRequest request) {
		 HttpSession session = request.getSession() ;
		 	if(!checkValidSession(session)) {
			return "error" ;
		 }
		 VIEW_INDEX = "add-new-faculty";
		 DBObject profileData = null ;
		 ProfileDataService pds = new ProfileDataService();
		 profileData = pds.getProfileData((String)session.getAttribute("userID")) ;	 
		 model.addAttribute("profile", profileData) ;
		 return VIEW_INDEX;

	 }
	 
	 private boolean checkValidSession(HttpSession session) {
		 if(session.getAttribute("userID")!=null) {
			 System.out.println("Reached Here!!") ;
			 long currentTime = System.currentTimeMillis() ;
			 long lastAccessed = session.getLastAccessedTime() ;
			 System.out.println(currentTime-lastAccessed) ;
			 if((currentTime - lastAccessed)>30*60*60) {
				 return false ;
			 }
			 else {
				 return true ;
			 }
		 }
		 return false ;
	 }
	 
}