package ir.sq.apps.squserside.controllers;


public enum UrlHandler {
    getImageClubURL(""),
    signUpUserURL("api/users/sign-up"),
    signInUserURL("oauth/token"),
    getAllClubsURL("api/clubs"),
    getUserURL("api/users"),
    reserveUrl("api/reserve");

    private String url;
    private String baseUrl = "http://192.168.43.191:8080/";

    private UrlHandler(String url) {
        this.url = url;
    }

    public String getUrl() {
        String url = baseUrl + this.url;
        return url;
    }
}
