package in.ac.manit.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodb.DBObject;

import in.ac.manit.portal.service.NewsFeedService;
import in.ac.manit.portal.service.ProfileDataService;
import in.ac.manit.portal.util.MongoDBUtil;

@Controller
public class BaseController {

	private static int counter = 0;
	private static String VIEW_INDEX = "signup";
	MongoDBUtil mongoDB = new MongoDBUtil() ;
	
	List<DBObject> feedList ;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp(ModelMap model) {

		VIEW_INDEX = "signup";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		model.addAttribute("isSignUp", "Sign Up");
		model.addAttribute("title", "Sign Up");
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		VIEW_INDEX = "login";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		
		//One time establishing of db connection
		System.out.println(mongoDB.initializeDBConnection()) ;
		
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Authenticate(@RequestParam ("userID") String userID, @RequestParam ("password") String pass, RedirectAttributes redirectAttributes, ModelMap model) {
		if(mongoDB.authenticateUser(userID, pass))
		{
			redirectAttributes.addFlashAttribute("name", userID);
			//Getting NewsFeed From Database
			NewsFeedService nfs = new NewsFeedService();
			feedList = nfs.getAllFeeds();
			
			DBObject profileData = null ;
			ProfileDataService pds = new ProfileDataService();
			profileData = pds.getProfileData(userID) ;
			
			
			redirectAttributes.addFlashAttribute("feedList", feedList) ;
			redirectAttributes.addFlashAttribute("profile", profileData) ;
			
			return "redirect:/home";
			
		}
		else
		{
			model.addAttribute("loginFailedText", "Invalid User ID or Password");
			return "login";
		}
		
		// Spring uses InternalResourceViewResolver and return back index.jsp

	}
	
	@RequestMapping(value = "/forgotPasswd", method = RequestMethod.GET)
	public String forgotPasswd(ModelMap model) {

		VIEW_INDEX = "signup";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		model.addAttribute("title", "Forgot Password");
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		VIEW_INDEX = "home";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);

		//Getting NewsFeed From Database
		NewsFeedService nfs = new NewsFeedService();
		feedList = nfs.getAllFeeds();
		
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData() ;
		
		model.addAttribute("feedList", feedList);
		model.addAttribute("profile", profileData) ;
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	

	@RequestMapping(value = "/newsFeed", method = RequestMethod.GET)
	public String newsFeed(ModelMap model) {
		VIEW_INDEX = "newsFeed";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(ModelMap model) {
		VIEW_INDEX = "result";
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap model) {

		VIEW_INDEX = "user-profile-edit";
		
		DBObject profileData = null ;
		ProfileDataService pds = new ProfileDataService();
		profileData = pds.getProfileData() ;
		
		
		model.addAttribute("profile", profileData) ;
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;
		
	}
	
	 @RequestMapping(value = "/post", method = RequestMethod.GET)
	 public String postUpdate(ModelMap model) {
	 VIEW_INDEX = "post-new-update";
	 DBObject profileData = null ;
	 ProfileDataService pds = new ProfileDataService();
	 profileData = pds.getProfileData() ;
		
	 
	 model.addAttribute("profile", profileData) ;

	 // Spring uses InternalResourceViewResolver and return back index.jsp
	 return VIEW_INDEX;

	 }

	 @RequestMapping(value = "/add", method = RequestMethod.GET)
	 public String addFaculty(ModelMap model) {
	 VIEW_INDEX = "add-new-faculty";
	 DBObject profileData = null ;
	 ProfileDataService pds = new ProfileDataService();
	 profileData = pds.getProfileData() ;	 
	 model.addAttribute("profile", profileData) ;

	 // Spring uses InternalResourceViewResolver and return back index.jsp
	 return VIEW_INDEX;

	 }

	 @RequestMapping(value = "/message", method = RequestMethod.GET)
	 public String messages(ModelMap model) {
	 VIEW_INDEX = "user-message";
	 DBObject profileData = null ;
	 ProfileDataService pds = new ProfileDataService();
	 profileData = pds.getProfileData() ;	 
	 model.addAttribute("profile", profileData) ;

	 // Spring uses InternalResourceViewResolver and return back index.jsp
	 return VIEW_INDEX;

	 }
	

}