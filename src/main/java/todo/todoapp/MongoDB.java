package todo.todoapp;

public class MongoDB {

    private final connectionString = "mongodb+srv://admin:admin@cluster0.iou5rdl.mongodb.net/?retryWrites=true&w=majority";
    private final mongoClient = MongoClients.create(connectionString);
    private final database = mongoClient.getDatabase("test");
    MongoCollection<Document> W_collection = database.getCollection("Worker");
    MongoCollection<Document> S_collection = database.getCollection("Supervisor");
    MongoCollection<Document> A_collection = database.getCollection("Admin");

}
