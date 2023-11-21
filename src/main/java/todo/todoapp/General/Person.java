package todo.todoapp.General;

import org.bson.Document;
import todo.todoapp.Enums.Role;

public class Person {

    private String username;
    private String password;

    private Role role;
    private String name;
    private String surname;

    public Person(){}

    public Person(String u, String p, String name, String surname){
        this.setUsername(u);
        this.setPassword(p);

        this.setRole(Role.WORKER);
        this.setName(name);
        this.setSurname(surname);

    }

    public Person(String u, String p, String name, String surname,Role role){
        this.setUsername(u);
        this.setPassword(p);

        this.setRole(role);
        this.setName(name);
        this.setSurname(surname);

    }

    public Document toDocument() {
        Document document = new Document();

        document.append("username",this.username);
        document.append("password", this.password);
        document.append("name", this.name);
        document.append("surname", this.surname);
        document.append("role", this.role);

        return document;
    }

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
}
