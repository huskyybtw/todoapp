package todo.todoapp.General;

import org.bson.Document;
import todo.todoapp.Enums.Role;

public class Person {

    private String username;
    private String password;

    private Role role;
    private String name;
    private String surname;

    private int team; // TEAM 0 MEANS UNASSIGNED

    public Person(){}
    public Person(String username, String password, String name, String surname,Role role,int team){
        this.setUsername(username);
        this.setPassword(password);


        this.setName(name);
        this.setSurname(surname);
        this.setRole(role);
        this.setTeam(team);
    }
    // CONVERTS OBJECT INTO DOCUMENT FOR MONGODB
    public Document toDocument() {
        Document document = new Document();

        document.append("username",this.username);
        document.append("password", this.password);
        document.append("name", this.name);
        document.append("surname", this.surname);
        document.append("role", this.role);
        document.append("team",this.team);

        return document;
    }
    // UGLY OOP FEATURE

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getTeam() {
        return team;
    }

}
