package todo.todoapp.General;

import org.bson.Document;
import todo.todoapp.Enums.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment {
    private final Date CREATION_DATE;
    private final String TITLE; //  UNIQE ID FOR ASSIGMENTS
    private final String CREATED_BY;
    private final boolean ERROR;

    private List<String> assigned_users = new ArrayList<>(); // LIST OF USERS
    private Date deadline;
    private TaskStatus status;

    // IF ERROR
    public Assignment(){
        this.ERROR = true;
        this.CREATION_DATE = null;
        this.CREATED_BY = null;
        this.TITLE = null;
    }

    // IF ASSIGNED TO A SINGLE USER
    public Assignment(String title, String creator, String user, Date deadline){
        this.ERROR = false;
        this.CREATION_DATE = new Date(); // TODAY'S DATE
        this.deadline = deadline;
        this.status = TaskStatus.ONGOING;

        this.TITLE = title;
        this.CREATED_BY = creator;
        this.assigned_users.add(user);
    }
    // IF ASSIGNED TO LIST OF USERS
    public Assignment(String title,String creator,List<String> users,Date deadline){
        this.ERROR = false;
        this.CREATION_DATE = new Date(); // TODAY'S DATE
        this.deadline = deadline;
        this.status = TaskStatus.ONGOING;

        this.TITLE = title;
        this.CREATED_BY = creator;

        this.assigned_users.addAll(users);
    }

    // MONGODB TO DOCUMENT CONVERSION
    public Document toDocument() {
        Document document = new Document();

        document.append("TITLE",this.TITLE);
        document.append("CREATED_BY", this.CREATED_BY);
        document.append("CREATION_DATE", this.CREATION_DATE);
        document.append("deadline", this.deadline);
        document.append("status", this.status.name());
        document.append("assigned_users", this.assigned_users);

        return document;
    }


    public void setAssigned_user(String user){
        assigned_users.add(user);
    }

    //SETTERS
    public void setDeadLine(Date date){
        deadline = date;
    }

    // GETTERS
    public Date getCREATION_DATE(){
        return CREATION_DATE;
    }
    public String getCREATED_BY(){
        return CREATED_BY;
    }
    public String getTITLE(){return TITLE;}
    public Date getDeadline(){
        return deadline;
    }
    public List<String> getAssigned_users() {
        return new ArrayList<>(assigned_users);
    }




}
