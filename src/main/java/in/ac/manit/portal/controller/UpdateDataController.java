package in.ac.manit.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.DBObject;

import in.ac.manit.portal.service.ProfileDataService;

@Controller
public class UpdateDataController {

	
	@RequestMapping(value = "/checkUserHandle", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserHandle(@RequestParam ("userHandle") String userHandle) {
		
		System.out.println(userHandle) ;
		ProfileDataService pds = new ProfileDataService();
		String flag = pds.userHandleAvailability(userHandle);
		return "{\"isAvailable\": "+ flag + "}" ;
	}
	
	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	@ResponseBody
	public String addFaculty(@RequestParam("userID") String userID, @RequestParam("loginType") String loginType,
			@RequestParam("imageURL") String imageURL, @RequestParam("userName") String userName, 
			@RequestParam("userHandle") String userHandle, @RequestParam("userAbout") String userAbout,
			@RequestParam("branch") String branch, @RequestParam("batch") String batch, @RequestParam("pointer") String pointer,
			@RequestParam("type") String type, @RequestParam("contact") String contact, @RequestParam("email") String email,
			@RequestParam("dob") String dob) {
		
		ProfileDataService pds = new ProfileDataService() ;
		return pds.addNewFaculty(userID, loginType, imageURL, userName, userHandle, userAbout, branch, batch, pointer,
				type, contact, email, dob) ;	
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestParam ("userHandle") String newUserHandle, @RequestParam ("userAbout") String newUserAbout, @RequestParam ("uploadedImage") CommonsMultipartFile file) {

		    ModelAndView mv = new ModelAndView("user-profile-edit"); 
			
		    ProfileDataService pds = new ProfileDataService();
		    
		    if(!file.isEmpty()){
		    	System.out.println("not Empty");
		    	pds.uploadProfileImage(file);
		    }
		    
		    
			DBObject profileData = pds.updateProfileData(newUserHandle, newUserAbout) ;
			
			mv.addObject("profile", profileData);
			mv.addObject("successMessage","Profile Updated Successfully.");
			
			return mv;
			
	}
	
	@RequestMapping(value = "/updatePersonalData", method = RequestMethod.POST)
	@ResponseBody
	public void updateProfile(@RequestParam ("contact") String newContact, @RequestParam ("dob") String newDob) {

			
		    ProfileDataService pds = new ProfileDataService();
		    pds.updatePersonalData(newContact, newDob);
			
	}
		
}
	
