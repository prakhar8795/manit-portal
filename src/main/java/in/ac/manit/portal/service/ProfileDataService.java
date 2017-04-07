package in.ac.manit.portal.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

public class ProfileDataService {

	DB db = MongoDBUtil.dbStatic;
	
	DBCollection table = db.getCollection("userData");
	
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
		
		String fileName = profileData.get("userID") + "." + file.getOriginalFilename().split("\\.")[1];
		String relativePath = "/resources/images/profile/" + fileName;
        
		SaveUploadedFileUtil sufu = new SaveUploadedFileUtil();
		sufu.saveUploadedFile(file, UPLOADED_FOLDER, fileName);
		
		updateProfileImageLocation(relativePath);
            
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
	
   public String getYear(String batch){
	   
	   String start = batch.split("-")[0];
	   DateAndTimeUtil dt = new DateAndTimeUtil();
	   String date = dt.getCurrentDate();
	   String currYear = date.split("/")[2];
	   int year = Integer.parseInt(currYear) - Integer.parseInt(start);
	   String currMonth = date.split("/")[1];
	   if(Integer.parseInt(currMonth) >= 6)
	   {
		   year++;
	   }
	   return Integer.toString(year);
   }
	
   public String getSemester(String batch){
	   String start = batch.split("-")[0];
	   
	   DateAndTimeUtil dt = new DateAndTimeUtil();
	   String date = dt.getCurrentDate();
	   String currYear = date.split("/")[2];
	   int year = Integer.parseInt(currYear) - Integer.parseInt(start);
	   String currMonth = date.split("/")[1];
	   if(Integer.parseInt(currMonth) >= 6)
	   {
		   year++;
	   }
	   
	   int sem = year*2;
	   if(Integer.parseInt(currMonth) >= 6)
	   {
		   sem--;
	   }
	   
	   return Integer.toString(sem);
   }
   
   public String getBranchCode(String branch){
	   DBCollection coll = db.getCollection("branchCode");
	   BasicDBObject query = new BasicDBObject();
	   query.put("branchName",branch);
	   DBCursor cursor = coll.find(query);
	   return cursor.next().get("branchCode").toString();
   }
	
	public BasicDBList getStudentSubjectData(String branch, String semester) {
		
			String branchCode = getBranchCode(branch);
		    DBCollection coll = db.getCollection("subjectData");
		    DBCursor cursor = coll.find();
		    DBObject obj1 = cursor.next();
		    
		    DBObject obj2 = (DBObject)obj1.get(branchCode);
		    BasicDBList obj3 = (BasicDBList)obj2.get(semester);
		    
		    return obj3;

	}
	
	public List<DBObject> getBranchNames(){
	    DBCollection coll = db.getCollection("branchCode");
	    DBCursor cursor = coll.find();
	    List<DBObject> branches = cursor.toArray();
	    return branches;
	}
	
	public boolean isIDOrHandle(String userInput){
		try{
			Integer.parseInt(userInput);
		}
		catch(Exception e){
			return false;
		}
		
		if(userInput.length() == 9)
		return true;
		return false;
	}
	
	public String userIDAvailability(String userID){
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("userID", userID);
		DBCursor cursor = table.find(whereQuery);
		
		if(cursor.hasNext())
		return "false";
		
		return "true";
	}
	
	public DBObject getUserDataWithUserID(String userID){
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("userID", userID);
		DBCursor cursor = table.find(whereQuery);
		DBObject obj = cursor.next();
		return obj;
	}
	
	public DBObject getUserDataWithUserHandle(String userHandle){
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("userHandle", userHandle);
		DBCursor cursor = table.find(whereQuery);
		DBObject obj = cursor.next();
		return obj;
	}
	
	public DBObject getUserCredentialWithUserID(String userID){
		DBCollection coll = db.getCollection("userCredentials");
		
		BasicDBObject whereQuery = new BasicDBObject();
		
		whereQuery.put("userID", userID);
		DBCursor cursor = coll.find(whereQuery);
		DBObject obj = cursor.next();
		return obj;
	}
	
	public DBObject getUserCredentialWithUserHandle(String userHandle){
		
		DBObject userHandleObj = getUserDataWithUserHandle(userHandle);
		BasicDBObject whereQuery = new BasicDBObject();
		DBCollection coll = db.getCollection("userCredentials");
		whereQuery.put("userID", userHandleObj.get("userID").toString());
		DBCursor cursor = coll.find(whereQuery);
		DBObject obj = cursor.next();
		return obj;
	}

	public List<String> getDataByYearBranch(String year, String branch) {
		
		BasicDBObject yearBranchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("batch", year));
		obj.add(new BasicDBObject("branch", branch));
		yearBranchQuery.put("$and", obj);
		
		List<String>yearBranchResult = new ArrayList<String>();
		
		DBCursor cursor = db.getCollection("studentData").find(yearBranchQuery);
		while(cursor.hasNext()) {
			DBObject o = cursor.next() ;
			yearBranchResult.add(o.toString());
			System.out.println(o.toString());
		}
		
		return yearBranchResult ;
	}
}