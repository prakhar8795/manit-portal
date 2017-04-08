package in.ac.manit.portal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import in.ac.manit.portal.util.DateAndTimeUtil;
import in.ac.manit.portal.util.MongoDBUtil;
import in.ac.manit.portal.util.SaveUploadedFileUtil;

public class NewsFeedService {
	
	private static String UPLOADED_FOLDER = "C://Users//ASHISH BAGHEL//workspace//portal//src//main//webapp//resources//";
	
	DB db = MongoDBUtil.dbStatic;
	
	
	public NewsFeedService() {
		db = MongoDBUtil.dbStatic ;
	}
		
	public boolean isFeedVisible(DBObject profileData,String [] scope) {

		int len = scope.length;

		if(len<2)
			return false;

		if(profileData.get("userID").toString().equals(scope[0])){
			return true;
		}

		if(scope[1].equals("SF")){
			return true;
		}

		if(scope[1].equals("SO")){
			
			if(profileData.get("loginType").toString().equals("student"))
				return true;
			else
				return false;
		}

		if(scope[1].equals("FO")){
			
			if(profileData.get("loginType").toString().equals("student"))
				return false;
			else
				return true;
		}

		if(len<=2)
			return false;

		if(scope[1].equals("BY") || scope[1].equals("SS")){
			
			if(profileData.get("loginType").toString().equals("faculty"))
				return false;
		}

		if(scope[1].equals("BY")){
			ProfileDataService pds = new ProfileDataService();
			String year = pds.getYear(profileData.get("batch").toString());
			String branchCode = pds.getBranchCode(profileData.get("branch").toString());
			String branchAndYear = branchCode+year;
			
			for(int j=2;j<len;j++){
				if(scope[j].equals(branchAndYear))
					return true;
			}
			
			return false;
		}
		
		if(scope[1].equals("SS")){
			ProfileDataService pds = new ProfileDataService();
	
			String branch = profileData.get("branch").toString();
			String branchCode = pds.getBranchCode(branch);
			
			if(!scope[2].split(" ")[0].equals(branchCode)){
				return false;
			}	
			
			String semester = pds.getSemester(profileData.get("batch").toString());
			BasicDBList dbList = pds.getStudentSubjectData(branch, semester);
			
			int length = dbList.size();
			String subjects[] = new String[length];
			
			for(int k=0;k<length;k++){
				DBObject obj = (DBObject)dbList.get(k);
				subjects[k] = obj.get("id").toString();
			}
			
			HashSet<String> tmp = new HashSet<String>();
			for (String s1 : subjects) {
			    tmp.add(s1);
			}
			for (String s2 : scope) {
			    if (tmp.contains(s2)) {
			    	return true;
			    }
			}
			return false;
			
		}
		return false;
		
	}
	
	public List<DBObject> getAllFeeds(DBObject profileData) {
	
		DBCollection table = db.getCollection("newsFeed");
		DBCursor cursor = table.find();
		List<DBObject> feedList = cursor.toArray();
		List<DBObject> finalFeed = new ArrayList<DBObject>();
		int len = feedList.size();
		
		for(int i=0;i<len;i++) {
			
			String [] scope;
			DBObject obj = feedList.get(i);
			String scopeString = obj.get("scopeList").toString();
			int length = scopeString.split(",").length;
			scope = new String[length];
			
			for(int j=0; j<length; j++) {
				scope[j] = scopeString.split(",")[j];
			}
			
			if(isFeedVisible(profileData,scope)){
				finalFeed.add(obj);
			}
	
		}
		
		Collections.reverse(finalFeed);
		return finalFeed;
	}
	
	public void saveNewPost(String content, String videoLink, CommonsMultipartFile image, CommonsMultipartFile file, String scopeList){
		
		String imageRelativePath="NA";
		String fileRelativePath="NA";
		SaveUploadedFileUtil sufu = new SaveUploadedFileUtil();
		String fileName = file.getOriginalFilename();
		
	    if(!file.isEmpty()){
	    	String uploadedFolder = UPLOADED_FOLDER + "files//newsfeed//";
	    	fileRelativePath = "/resources/files/newsfeed/" + fileName;
	    	
	    	sufu.saveUploadedFile(file, uploadedFolder, fileName);
	    }
	    
	    if(!image.isEmpty()){
	    	
	    	String uploadedFolder = UPLOADED_FOLDER + "images//newsfeed//";
	    	String imageName = image.getOriginalFilename();
	    	imageRelativePath = "/resources/images/newsfeed/" + imageName;
	    	
	    	sufu.saveUploadedFile(image, uploadedFolder, imageName);
	    }
	    
	    
	    addNewPostIntoTable(content, videoLink, imageRelativePath, fileRelativePath, fileName, scopeList);
		
	}
	
	public void addNewPostIntoTable(String content, String videoLink, String imageRelativePath, String fileRelativePath, String fileName, String scopeList){
		
		DBCollection table = db.getCollection("newsFeed");
		System.out.println(videoLink.length());
		if(videoLink.length() == 0)
		{
			videoLink = "NA";
		}
		
		DateAndTimeUtil dt = new DateAndTimeUtil();
		
		ProfileDataService pds = new ProfileDataService();
		DBObject profileData = pds.getProfileData();
		BasicDBObject doc = new BasicDBObject();
		doc.put("userID", profileData.get("userID"));
		doc.put("userName", profileData.get("userName"));
		doc.put("content", content);
		doc.put("date", dt.getCurrentDate());
		doc.put("time", dt.getCurrentTime());
		doc.put("imageURL", imageRelativePath);
		doc.put("fileURL", fileRelativePath);
		doc.put("fileName", fileName);
		doc.put("videoURL", videoLink);
		doc.put("scopeList", scopeList);
		
		table.insert(doc);
	}
	
	public List<DBObject> getAllFeeds(){
		DBCollection table = db.getCollection("newsFeed");
		DBCursor cursor = table.find();
		List<DBObject> feedList  = cursor.toArray();
		return feedList;
	}

}
