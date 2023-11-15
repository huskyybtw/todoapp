package todo.todoapp;

/*
CLASS FOR STORING INFORMATION FROM DATA BASE IN RUNTIME
COMUNICATES WITH MONGODB
 */

import java.util.HashMap;

public class Database {

    public static void DB_add(){
        boolean successfull; // = false
        HashMap<String,String> map = new HashMap<>();

        successfull = MongoDB.insert_one(map);
        if (!successfull) {System.out.println("error with mongodb");}
    }
}
