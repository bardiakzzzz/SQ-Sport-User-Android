package ir.sq.apps.squserside.controllers;

import android.util.Log;

/**
 * Created by Mohammad on 5/27/2018.
 */

public enum UrlHandler {
    getImageClubURL(""),
    signUpUserURL("users/sign-up"),
    signInUserURL("oauth/token"),
    getAllClubsURL("api/clubs"),
    getUserURL("users");

    private String url;
    private String baseUrl = "http://192.168.43.82:8080/";

    private UrlHandler(String url) {
        this.url = url;
    }

    public String getUrl() {
        String url = baseUrl + this.url;
        return url;
    }
}
