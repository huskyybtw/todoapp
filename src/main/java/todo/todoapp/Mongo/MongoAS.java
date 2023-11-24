
package todo.todoapp.Mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import todo.todoapp.Enums.Role;
import todo.todoapp.Enums.TaskStatus;
import todo.todoapp.General.Assignment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public class MongoAS {
    private static final ConnectionString URI = new ConnectionString("mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/");
    private static final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private static final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private static final MongoCollection<Document> COLLECTION = DATABASE.getCollection("Assignments");

    // IF NO USER WITH THIS NAME IS IN THE DATABASE
    // INSERTS DOCUMENT INTO COLLECTION
    // ACCEPTS ASSIGNMENT OBJECT OF DOCUMENT INFO
    public static void insertOne(Assignment task) {

        if (!checkSingle("TITLE", task.getTITLE())) {
            try {
                Document document = task.toDocument();
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
            System.out.println("Document found:");
            return true;
        } else {
            System.out.println("No Document was found");
            return false;
        }
    }

    public static Assignment getSingle(String TITLE_VALUE) {
        try {
            Document search = new Document("TITLE", TITLE_VALUE);
            Document found = COLLECTION.find(search).first();

            // PROBLEM WILL BE HERE
            if (found != null) {
                // CONVERT DATE INTO LOCALDATETIME FOR CONSTRUCTOR
                LocalDateTime temp_localdate = found.getDate("deadline").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                return new Assignment(
                        found.getString("TITLE"),
                        found.getString("CREATED_BY"),
                        found.getList("assigned_users", String.class),
                        temp_localdate,
                        found.getString("description")
                );
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
                // CONVERT DATE INTO LOCALDATETIME FOR CONSTRUCTOR
                LocalDateTime temp_localdate = found.getDate("deadline").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Assignment assignment = new Assignment(
                        found.getString("TITLE"),
                        found.getString("CREATED_BY"),
                        found.getList("assigned_users", String.class),
                        temp_localdate,
                        found.getString("description")
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



