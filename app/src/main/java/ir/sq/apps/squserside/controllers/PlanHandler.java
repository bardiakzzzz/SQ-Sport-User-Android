package ir.sq.apps.squserside.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.models.Club;
import ir.sq.apps.squserside.models.Plan;
import ir.sq.apps.squserside.utils.Constants;
import ir.sq.apps.squserside.views.WeekViewEvent;

public class PlanHandler {
    public static List<WeekViewEvent> getEventsFromClub(Context context, Club club, int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();
        Calendar startTime, endTime;
        WeekViewEvent event;
        for (Plan p :
                club.getPlans()) {
            String time[] = p.getTime().split("-");
            startTime = Calendar.getInstance();
            startTime.add(Calendar.DAY_OF_MONTH, p.getDay() - startTime.get(Calendar.DAY_OF_WEEK));
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            startTime.set(Calendar.MINUTE, 0);
            startTime.set(Calendar.MONTH, newMonth - 1);
            startTime.set(Calendar.YEAR, newYear);
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR, Integer.parseInt(time[1]) - Integer.parseInt(time[0]));
            event = new WeekViewEvent(p.getId(), "", startTime, endTime);
            int color = getPlanColor(p);
            event.setColor(context.getResources().getColor(color));
            events.add(event);
        }
        return events;
    }

    private static int getPlanColor(Plan p) {
        int color = -1;
        switch (p.getStatus()) {
            case Constants.PLAN_AVAILABLE:
                color = R.color.status_available;
                break;
            case Constants.PLAN_NOTAVAILABLE:
                color = R.color.status_unavailable;
                break;
            case Constants.PLAN_RESERVED:
                color = R.color.status_reserved;
                break;
        }
        return color;
    }

    public static Plan getPlanById(Club club, long id) {
        for (Plan p :
                club.getPlans()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
