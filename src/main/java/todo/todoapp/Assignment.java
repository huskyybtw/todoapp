package todo.todoapp;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment {
    private final Date CREATION_DATE;
    private Date deadline;

    private final String CREATED_BY;
    private List<String> assigned_users = new ArrayList<>(); // LIST OF USERS

    private TaskStatus status;

    Assignment(Date deadline,String creator,List<String> users){
        this.CREATION_DATE = new Date(); // TODAY'S DATE
        this.deadline = deadline;

        this.status = TaskStatus.ONGOING;
        this.CREATED_BY = creator;

        for (int i= 0 ; i<users.size(); i++){
            this.assigned_users.add(users.get(i));
        }
    }

    // MONGODB TO DOCUMENT CONVERSION
    public Document toDocument() {
        Document document = new Document();

        document.append("CREATION_DATE", this.CREATION_DATE);
        document.append("deadline", this.deadline);
        document.append("CREATED_BY", this.CREATED_BY);
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
    public Date getDeadline(){
        return deadline;
    }
    public List<String> getAssigned_users() {
        return new ArrayList<>(assigned_users);
    }




}
