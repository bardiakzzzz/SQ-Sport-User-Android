package ir.sq.apps.squserside.controllers;

import java.util.ArrayList;
import java.util.List;

import ir.sq.apps.squserside.models.Club;

/**
 * Created by Mohammad on 5/29/2018.
 */

public class ClubHandler {
    private List<Club> clubs;
    private static final ClubHandler ourInstance = new ClubHandler();

    public static ClubHandler getInstance() {
        return ourInstance;
    }

    private ClubHandler() {
        clubs = new ArrayList<>();
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Club> getClubs() {
        return clubs;
    }
}
