package app.entity;

public class Account{
    private int id;
    private String username;
    private String pass;
    private int Service;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getService() {
        return Service;
    }

    public void setService(int Service) {
        this.Service = Service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account(int id, String username, String pass, int Service, String name) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.Service = Service;
        this.name = name;
    }
}
