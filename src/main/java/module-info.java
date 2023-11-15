module todo.todoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens todo.todoapp to javafx.fxml;
    exports todo.todoapp;
}