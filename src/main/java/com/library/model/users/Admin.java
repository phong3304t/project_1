package com.library.model.users;

public class Admin extends Guest {
    public Admin(String name, String email, String password, String position) {
        super(name, email, password, position);
    }

    @Override
    public String userInfo() {
        return "Admin [" + super.userInfo() + "]";
    }
}
