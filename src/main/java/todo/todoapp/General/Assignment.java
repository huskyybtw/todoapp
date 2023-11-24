package todo.todoapp.General;

import org.bson.Document;
import todo.todoapp.Enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment {

    private final LocalDateTime CREATION_DATE;
    private final String TITLE; //  UNIQUE ID FOR ASSIGNMENTS
    private final String CREATED_BY;
    private final boolean ERROR;

    private List<String> assigned_users = new ArrayList<>(); // LIST OF USERS

    private String description;
    private LocalDateTime deadline;

    private TaskStatus status;

    // IF ERROR
    public Assignment(){
        this.ERROR = true;
        this.CREATION_DATE = null;
        this.CREATED_BY = null;
        this.TITLE = null;
    }

    // IF ASSIGNED TO A SINGLE USER
    public Assignment(String title, String creator, String user, LocalDateTime deadline,String d){
        this.ERROR = false;
        this.CREATION_DATE = LocalDateTime.now(); // TODAY'S DATE
        this.deadline = deadline;
        this.status = TaskStatus.ONGOING;

        this.TITLE = title;
        this.CREATED_BY = creator;
        this.assigned_users.add(user);

        this.description = d;
    }
    // IF ASSIGNED TO LIST OF USERS
    public Assignment(String title,String creator,List<String> users,LocalDateTime deadline,String d){
        this.ERROR = false;
        this.CREATION_DATE = LocalDateTime.now(); // TODAY'S DATE
        this.deadline = deadline;
        this.status = TaskStatus.ONGOING;

        this.TITLE = title;
        this.CREATED_BY = creator;

        this.assigned_users.addAll(users);

        this.description = d;
    }

    // MONGODB TO DOCUMENT CONVERSION
    public Document toDocument() {
        Document document = new Document();

        // LOCALDATETIME INTO DATE CONVERSION FOR MONGODB

        Date temp_cdate = Date.from(this.getCREATION_DATE().atZone(ZoneId.systemDefault()).toInstant());
        Date temp_ddate = Date.from(this.getCREATION_DATE().atZone(ZoneId.systemDefault()).toInstant());

        document.append("TITLE",this.TITLE);
        document.append("CREATED_BY", this.CREATED_BY);
        document.append("CREATION_DATE", temp_cdate);
        document.append("deadline", temp_ddate);
        document.append("status", this.status.name());
        document.append("assigned_users", this.assigned_users);
        document.append("description",this.description);

        return document;
    }


    public void setAssigned_user(String user){
        assigned_users.add(user);
    }

    //SETTERS

    // GETTERS
    public String getCREATED_BY(){
        return CREATED_BY;
    }
    public String getTITLE(){return TITLE;}
    public List<String> getAssigned_users() {
        return new ArrayList<>(assigned_users);
    }
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCREATION_DATE() {
        return CREATION_DATE;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
