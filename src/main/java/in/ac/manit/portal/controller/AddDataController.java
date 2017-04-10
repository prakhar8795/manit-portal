package in.ac.manit.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.ac.manit.portal.service.NewsFeedService;

@Controller
public class AddDataController {

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String updateProfile(@RequestParam("post-content") String content,
			@RequestParam("video-link") String videoLink, @RequestParam("uploaded-image") CommonsMultipartFile image,
			@RequestParam("uploaded-file") CommonsMultipartFile file, @RequestParam("scope-list") String scopeList, HttpServletRequest request) {

		HttpSession session = request.getSession() ;
	 	if(!checkValidSession(session)) {
	 		return "error" ;
	 	}
	 	
	 	System.out.println(scopeList);
		NewsFeedService nfs = new NewsFeedService();
		System.out.println(videoLink);
		
		nfs.saveNewPost(content, videoLink, image, file, scopeList, session);

		return "redirect:/home";

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
