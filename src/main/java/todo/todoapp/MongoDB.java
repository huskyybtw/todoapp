package todo.todoapp;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private final String URI = "mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient MONGOCLIENT = MongoClients.create(URI);
    private final MongoDatabase DATABASE = MONGOCLIENT.getDatabase("test");
    private MongoCollection<Document> W_COLLECTION = DATABASE.getCollection("Worker");
    private MongoCollection<Document> S_COLLECTION = DATABASE.getCollection("Supervisor");
    private MongoCollection<Document> A_COLLECTION = DATABASE.getCollection("Admin");
}
