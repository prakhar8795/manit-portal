package in.ac.manit.portal.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import in.ac.manit.portal.util.MongoDBUtil;

@Service
public class AuthenticationService {
	
	private DB db = null ;
	
	public AuthenticationService() {
		db = MongoDBUtil.dbStatic ;
	}
	
	public boolean authenticateUser(String name, String pass)
	{
		DBCollection table = db.getCollection("studentCredentials");
		
		BasicDBObject andQuery = new BasicDBObject();
		ArrayList<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("userID", name));
		obj.add(new BasicDBObject("password", pass));
		andQuery.put("$and", obj);

		DBCursor cursor = table.find(andQuery);
		if(cursor.hasNext())
			return true;
		
		return false;
	}
	
}
