package todo.todoapp.Mongo;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import todo.todoapp.General.Assignment;
import todo.todoapp.General.Person;
import todo.todoapp.Enums.Role;

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
    // ACCEPTS PERSON OBJECT OF DOCUMENT INFO
    // RETURNS TRUE IF ALL GOOD
    public static boolean insert_one(Person person){

        if (check_single("username",person.getUsername())){
            System.out.println("username alredy exists ERROR");
            return false; // THAT USERNAME ALREDY EXISTS
        }

        else {
            Document document = person.toDocument();
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
            return true;
        } else {
            System.out.println("No Document was found");
            return false;
        }
    }
    //GETS SINGLE DOCUMENT
    //RETURNS PERSON WITH FOUND DATA
    //IF ERROR RETURNS PERSON
    public static Person get_single(String username_value) {
        try {
            Document search = new Document("username", username_value);
            Document found = COLLECTION.find(search).first();


            if (found != null) {

                // BIG ERROR WITH PROGRAM CATCHING EXCEPTION
                // IF ONE OF THE FILEDS IS NULL CONSTRUCTOR WONT BE CALLED
                // BECAREFULL
                // WORKS FOR NOW BUT NOT EXPANDABLE
                if (found.get("role") == null)
                return new Person(
                        found.getString("username"),
                        found.getString("password"),
                        found.getString("name"),
                        found.getString("surname")
                       );
                else{
                    return new Person(
                            found.getString("username"),
                            found.getString("password"),
                            found.getString("name"),
                            found.getString("surname"),
                            Role.valueOf(found.getString("role"))
                    );
                }
            }
            else {
                System.out.println("empty");
                return new Person();
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return new Person();
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


