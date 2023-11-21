package todo.todoapp.Mongo;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;

import java.util.HashMap;

/*
CLASS FOR CONNECTION WITH DATABASE

 */

public class MongoDB {
    // CONNECTION SETUP
    private static final ConnectionString URI = new ConnectionString("mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/");
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Collection");

    // IF NO USER WITH THIS NAME IS IN THE DATABSE
    // INSTERTS DOCUMENT INTO COLLECTION
    // ACCEPTS HASHMAP OF DOCUMENT INFO
    // RETURNS TRUE IF ALL GOOD
    public static boolean insert_one(HashMap<String,String> map){

        if (check_single("username",map.get("username"))){
            System.out.println("username alredy exists ERROR");
            return false; // THAT USERNAME ALREDY EXISTS
        }

        else {
            Document document = new Document();
            for (String i : map.keySet()) {
                document.append(i, map.get(i));
            }
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
            System.out.println(found.get("name"));
            System.out.println(found.get("password"));
            return true;
        } else {
            System.out.println("No Document was found");
            return false;
        }
    }
    //GETS SINGLE DOCUMENT
    //PUTS IT INTO HASHMAP
    //RETURNS HASHMAP WITH FOUND DATA
    //IF ERROR RETURNS EMPTY MAP
    public static HashMap<String, Object> get_single(String username_value) {
        try {
            Document search = new Document("username", username_value);
            Document found = COLLECTION.find(search).first();

            if (found != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", found.get("username"));
                map.put("name", found.get("name"));
                map.put("surname", found.get("surname"));
                return map;
            } else {
                System.out.println("empty");
                return new HashMap<>();
            }
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    // RETURNS USERNAME THAT FITS A PASSWORD
    public static String check_user_password(String user_value, String password_value) {
        // CHECKS IF USER WITH THIS PASSWORD EXIST
        Document search = new Document("password", password_value);
        Document found = COLLECTION.find(search).first();

        if (found != null) { // USER FOUND IN DATABASE
            if (found.get("username").equals(user_value)) {
                return user_value; // RETURNS BACK USERNAME IF PASSWORD WAS CORRECT
            } else {
                return ""; //RETURNS EMPTY STRING IF USER AND PASSWORD DOESNT MATH
            }
        } else { // USER NOT FOUND
            return ""; // RETURNS EMPTY STRING IF USER WITH THIS PASSWORD IS NOT IN DATABASE
        }

    }










}


