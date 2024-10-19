package com.library.model.users;

public abstract class Guest {
    private static int idCounter = 1;
    private int id;
    private String position;
    private String name;
    private String email;
    private String password;

    /**
     * Guest constructor.
     */
    public Guest(String position, String name, String email, String password) {
        this.id = idCounter++;
        this.position = position;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position.equals("User") || position.equals("Admin")) {
            this.position = position;
        } else {
            System.out.println("<Invalid position>");
        }
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

    public String userInfo() {
        return "Name: " + name + ", Email: " + email + "]";
    }
}
