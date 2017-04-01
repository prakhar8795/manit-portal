package in.ac.manit.portal.service;

import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import in.ac.manit.portal.util.MongoDBUtil;

public class NewsFeedService {
	DB db = MongoDBUtil.dbStatic;
	
	DBCollection table = db.getCollection("newsFeed");
	
	
	
	public List<DBObject> getAllFeeds(){
	DBCursor cursor = table.find();
	List<DBObject> feedList  = cursor.toArray();
	
	//System.out.println(doc.get("userName"));
	return feedList;
	}
	
	
}
