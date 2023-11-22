
package todo.todoapp.Mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import todo.todoapp.General.Assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MongoAS {
    private static final ConnectionString URI = new ConnectionString("mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/");
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Assignments");

    // IF NO TASK WITH THIS TITLE IS IN THE DATABSE
    // INSTERTS DOCUMENT INTO COLLECTION
    // ACCEPTS ASSIGMENT OBJECT OF DOCUMENT INFO
    // RETURNS TRUE IF ALL GOOD
    public static boolean insert_one(Assignment task){

        if (check_single("TITLE", task.getTITLE())){
            return false; // THAT TASK ALREDY EXISTS
        }

        else {
            Document document = task.toDocument();
            COLLECTION.insertOne(document);
            return true;
        }
    }

    // GETS DOCUMENT INFO
    // RETURNS TRUE IF FOUND DOCUMENT
    public static boolean check_single(String key, String value) {
        Document search = new Document(key, value);
        Document found = COLLECTION.find(search).first();

        if (found != null) {
            System.out.println("Document found:");
            return true;
        } else {
            System.out.println("No Document was found");
            return false;
        }
    }

    public static Assignment get_single(String TITLE_VALUE) {
        try {
            Document search = new Document("TITLE", TITLE_VALUE);
            Document found = COLLECTION.find(search).first();

            // PROBLEM WILL BE HERE
            if (found != null) {
                return new Assignment(
                        found.getString("TITLE"),
                        found.getString("CREATED_BY"),
                        found.getList("assigned_users", String.class),
                        found.getDate("deadline"));
            }

            else {
                System.out.println("empty");
                return new Assignment();
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return new Assignment();
        }
    }

    public static List<Assignment> findTasks(String user) {
        try {
            Document search = new Document("assigned_users", user);
            FindIterable<Document> foundDocuments = COLLECTION.find(search);

            List<Assignment> taskList = new ArrayList<>();

            for (Document found : foundDocuments) {
                Assignment assignment = new Assignment(
                        found.getString("TITLE"),
                        found.getString("CREATED_BY"),
                        found.getList("assigned_users", String.class),
                        found.getDate("deadline")
                );
                taskList.add(assignment);
            }

            if (!taskList.isEmpty()) {
                return taskList;
            } else {
                System.out.println("No tasks found for user: " + user);
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}



