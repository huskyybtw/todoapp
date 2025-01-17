package todo.todoapp.Mongo;

import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.ConnectionString;
import todo.todoapp.General.Person;
import todo.todoapp.Enums.Role;

import java.util.ArrayList;
import java.util.List;

/*
CLASS FOR CONNECTION WITH DATABASE

 */

public class MongoDB {
    // CONNECTION SETUP
    private static final ConnectionString URI = new ConnectionString("mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/");
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Collection");

    // IF NO USER WITH THIS NAME IS IN THE DATABASE
    // INSERTS DOCUMENT INTO COLLECTION
    // ACCEPTS PERSON OBJECT OF DOCUMENT INFO
    public static void insertOne(Person person) {

        if (!checkSingle("username", person.getUsername())) {
            try {
                Document document = person.toDocument();
                COLLECTION.insertOne(document);
            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }
        }
    }

    // GETS DOCUMENT INFO
    // RETURNS TRUE IF FOUND DOCUMENT
    public static boolean checkSingle(String key, String value) {
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
    //IF ERROR RETURNS NULL PERSON
    public static Person getSingle(String username_value) {
        try {
            Document search = new Document("username", username_value);
            Document found = COLLECTION.find(search).first();


            if (found != null) {

                // BIG ERROR WITH PROGRAM CATCHING EXCEPTION
                // IF ONE OF THE FILED IS NULL CONSTRUCTOR WONT BE CALLED
                // BECAREFUL
                    return new Person(
                            found.getString("username"),
                            found.getString("password"),
                            found.getString("name"),
                            found.getString("surname"),
                            Role.valueOf(found.getString("role")),
                            found.getInteger("team"));
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
    // UPDATE A KEY + VALUE OF A PERSON P
    public static void updateOne(String updateKey, Object updateValue,Person p){
        try{
        Document search = new Document("username",p.getUsername());
        Document update = new Document("$set",new Document(updateKey,updateValue));

        COLLECTION.updateOne(search,update);
        }
        catch (Exception e){
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }


    // FIND ALL TEAM MEMBERS BY TEAM NUMBER
    // RETURNS LIST OF PERSON'S
    // BIG CHANCE THE ERROR WILL OCCUR LIKE IN GET_SINGLE
    // RETURN EMPTY IF ERROR
    public static List<Person> findTeam(int team) {
        try {
            Document search = new Document("team", team);
            FindIterable<Document> foundDocuments = COLLECTION.find(search);

            List<Person> teamList = new ArrayList<>();

            for (Document found : foundDocuments) {
                Person person = new Person(
                        found.getString("username"),
                        found.getString("password"),
                        found.getString("name"),
                        found.getString("surname"),
                        Role.valueOf(found.getString("role")),
                        found.getInteger("team"));
                teamList.add(person);
                }

            if (!teamList.isEmpty()) {
                return teamList;
            } else {
                System.out.println("Team is empty: " + team);
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // FIND ALL TEAM MEMBERS BY TEAM NUMBER
    // RETURNS LIST OF USERNAMES IN TEAM
    // BIG CHANCE THE ERROR WILL OCCUR LIKE IN GET_SINGLE
    // RETURN EMPTY LIST IF ERROR
    public static List<String> findTeam_usernames(int team) {
        try {
            Document search = new Document("team", team);
            FindIterable<Document> foundDocuments = COLLECTION.find(search);

            List<String> teamList = new ArrayList<>();

            for (Document found : foundDocuments) {
                teamList.add(found.getString("username"));
            }

            if (!teamList.isEmpty()) {
                return teamList;
            } else {
                System.out.println("Team is empty: " + team);
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION : " + e.getMessage());
            return new ArrayList<>();
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


