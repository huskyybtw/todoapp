
package todo.todoapp.Mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import todo.todoapp.General.Assignment;

import java.util.HashMap;


public class MongoAS {
    private static final ConnectionString URI = new ConnectionString("mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/");
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Assignments");

    public static boolean insert_one(Assignment task){

        if (check_single("Title", task.getTITLE())){
            return false; // THAT USERNAME ALREDY EXISTS
        }

        else {
            Document document = task.toDocument();
            COLLECTION.insertOne(document);
            return true;
        }
    }

    // GETS DOCUMENT INFO
    public static boolean check_single(String key, String value) {
        Document search = new Document(key, value);
        Document found = COLLECTION.find(search).first();

        if (found != null) {
            // Access document data
            System.out.println("Document found:");
            // Test czy znajduje poprawna
            return true;
        } else {
            System.out.println("No Document was found");
            return false;
        }
    }

    public static HashMap<String, Object> get_single(Assignment task) {
        try {
            Document search = new Document("title", task.getTITLE());
            Document found = COLLECTION.find(search).first();

            if (found != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("TITLE", found.get("TITLE"));
                System.out.println(found.get("TITLE"));
                return map;
            } else {
                System.out.println("empty");
                return new HashMap<>();
            }
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}



