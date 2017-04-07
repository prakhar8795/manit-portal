package in.ac.manit.portal.controller;

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
			@RequestParam("uploaded-file") CommonsMultipartFile file, @RequestParam("scope-list") String scopeList) {

		System.out.println(scopeList);
		NewsFeedService nfs = new NewsFeedService();
		System.out.println(videoLink);
		nfs.saveNewPost(content, videoLink, image, file, scopeList);

		return "redirect:/home";

	}
}
