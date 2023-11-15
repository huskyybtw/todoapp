module todo.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;


    opens todo.todoapp to javafx.fxml;
    exports todo.todoapp;
}