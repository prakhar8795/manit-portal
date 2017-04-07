package in.ac.manit.portal.util;

import java.util.ArrayList;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBUtil {
	public static DB dbStatic = null ;
	
	
	public static boolean initializeDBConnection()
	{
		try {
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			dbStatic = mongo.getDB("test");
			//insertIntoDB();
			
			
			return true ;
		} catch(Exception e) {
			System.out.println("Excetion Occured" + e.toString()) ;
			return false ;
		}
	}
	
	public boolean insertIntoDB()
	{
		DBCollection table = dbStatic.getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("name", "prakhar");
		document.put("password", "qwerty");
		document.put("createdDate", new Date());
		table.insert(document);
		return true ;
	}
	
	public boolean authenticateUser(String name, String pass)
	{
		DBCollection table = dbStatic.getCollection("userCredentials");
		
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
