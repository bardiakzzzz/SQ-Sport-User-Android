package ir.sq.apps.squserside.controllers;

import ir.sq.apps.squserside.models.User;

public class UserHandler {
    private static final UserHandler ourInstance = new UserHandler();
    private User user;
    public static UserHandler getInstance() {
        return ourInstance;
    }

    private UserHandler() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
