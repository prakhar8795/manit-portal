package in.ac.manit.portal.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import in.ac.manit.portal.util.MongoDBUtil;

public class ProfileDataService {

	DB db = MongoDBUtil.dbStatic;
	
	DBCollection table = db.getCollection("studentData");
	
	public static DBObject profileData = null ;
	 
	private static String UPLOADED_FOLDER = "C://Users//ASHISH BAGHEL//workspace//portal//src//main//webapp//resources//images//profile//";
	
	public DBObject getProfileData(String userID){
	
	
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("userID", userID);
		DBCursor cursor = table.find(whereQuery);
		profileData = cursor.next();
		
		return profileData;
	
	}
	
	public DBObject getProfileData(){
		return profileData;
	}
	
	public String userHandleAvailability(String userHandle){
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("userHandle", userHandle);
		DBCursor cursor = table.find(whereQuery);
		
		if(cursor.hasNext())
		return "false";
		
		return "true";
	}
	
	public  DBObject updateProfileData(String newUserHandle, String newUserAbout) {
		
		BasicDBObject searchQuery = new BasicDBObject("userID", profileData.get("userID").toString());
		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("userHandle", newUserHandle);
		updateFields.append("userAbout", newUserAbout);
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);
		table.update(searchQuery, setQuery);
		String userID = profileData.get("userID").toString();
		profileData = getProfileData(userID);
		
		return profileData;
	}
	
	public void updateProfileImageLocation(String relativePath){
		
		BasicDBObject query = new BasicDBObject("userID",profileData.get("userID").toString());
		BasicDBObject updateField = new BasicDBObject();
		updateField.append("imageURL", relativePath);
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateField);
		table.update(query, setQuery);
		System.out.println("URL updated");
		
	}
	
	
	public String uploadProfileImage(CommonsMultipartFile file){
		
		String relativePath = "/resources/images/profile/";
		try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + profileData.get("userID") + "." + file.getOriginalFilename().split("\\.")[1]);
            
            Files.write(path, bytes);
            
            relativePath+=  profileData.get("userID") + "." + file.getOriginalFilename().split("\\.")[1];
            updateProfileImageLocation(relativePath);
            

        } catch (Exception e) {
            System.out.println(e);
        }
		return relativePath;
	}
	
	public  void updatePersonalData(String newContact, String newDob) {
		
		BasicDBObject searchQuery = new BasicDBObject("userID", profileData.get("userID").toString());
		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("contact", newContact);
		updateFields.append("dob", newDob);
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);
		table.update(searchQuery, setQuery);
		String userID = profileData.get("userID").toString();
		profileData = getProfileData(userID);

	}
}