package in.ac.manit.portal.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBUtil {
	public static DB dbStatic = null ;
	
	public MongoDBUtil() {
		initializeDBConnection() ;
	}
	
	private boolean initializeDBConnection()
	{
		try {
			MongoClient mongo = new MongoClient( "localhost" , 27017 );
			dbStatic = mongo.getDB("test");
			return true ;
		} catch(Exception e) {
			System.out.println("Excetion Occured" + e.toString()) ;
			return false ;
		}
	}	
}