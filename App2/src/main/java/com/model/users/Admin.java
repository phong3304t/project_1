package com.model.users;

public class Admin {
    protected static int idCounter = 1;
    protected int id;
    protected String name;
    protected String email;
    protected String password;

    /**
     * constructor 1.
     */
    public Admin() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.password = "";
    }

    /**
     * Admin constructor.
     *
     * @param name name
     * @param email email
     * @param password password
     */
    public Admin(String name, String email, String password) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return "Admin";
    }
}
