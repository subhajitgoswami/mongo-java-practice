package mongojava;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * @author Shamik Mitra
 *
 */
public class MongoConfig {
	private static MongoConfig ctx = new MongoConfig();
	private MongoClient client;
	private DB db;

	private MongoConfig() {
		try {
			init();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() throws UnknownHostException {
		this.client = new MongoClient("localhost", 27017);
	}

	public static MongoConfig get() {
		return ctx;
	}

	public MongoConfig connectDb(String dbname) {
		if (db != null) {
			throw new RuntimeException("Already conected to " + db.getName() + "can't connect " + dbname);
		}
		this.db = client.getDB(dbname);
		Set<String> s = db.getCollectionNames();
		System.out.println("DB Details :: " + db.getName());
		System.out.println("Collection Names :: " + s.toString());
		return ctx;
	}

	public <T, X> DBCursor findByKey(String collectionName, String key, Integer value) {
		DBCollection collection = db.getCollection(collectionName);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		System.out.println("search Query ::" + searchQuery);
		DBCursor cursor = collection.find(searchQuery);
		return cursor;
	}

	public void insertCollection(String collection) {
		DBCollection col = db.getCollection(collection);
		BasicDBObject document = new BasicDBObject();
		document.put("name", "Jhumpa");
		document.put("address", "bhilai");
		document.put("age", 33);
		col.insert(document);
	}
}