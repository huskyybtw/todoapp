package todo.todoapp;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.HashMap;
import java.util.Map;

/*
CLASS FOR CONNECTION WITH DATABASE

 */

public class MongoDB {
    // CONNECTION SETUP
    private static final String URI = "mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/?retryWrites=true&w=majority";
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Collection");

    // INSTERTS DOCUMENT INTO COLLECTION
    // ACCEPTS HASHMAP OF DOCUMENT INFO
    public static boolean insert_one(HashMap<String,String> map){
        Document document = new Document();
        for (String i : map.keySet()){
            document.append(i,map.get(i));
        }
        COLLECTION.insertOne(document);
        return true;
    }
    // GETS DOCUMENT INFO
    public static boolean get_one(String key,String value){
        Document search = new Document(key,value);
        Document found = COLLECTION.find(search).first();

        if (found!=null){
            for (Map.Entry<String, Object> entry : found.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                return true;
            }
        }
        else {
            System.out.println("empty");
            return false;
        }
        return false;
    }
}


