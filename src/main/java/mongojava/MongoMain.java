package mongojava;

import com.mongodb.DBCursor;

public class MongoMain {

	public static void main(String[] args) {

		DBCursor result = MongoConfig.get().connectDb("test").findByKey("Employee", "age", 33);
		while (result.hasNext()) {
			System.out.println(result.next());
		}
		 
	}

}
