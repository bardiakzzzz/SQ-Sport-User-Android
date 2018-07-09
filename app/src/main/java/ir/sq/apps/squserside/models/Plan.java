package ir.sq.apps.squserside.models;

public class Plan {
    private int price;
    private int status;
    private int day;
    private long id;
    private String date;
    private String time;

    public Plan(long id, int price, int status, int day, String date, String time) {
        this.price = price;
        this.status = status;
        this.day = day;
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public int getDay() {
        return day;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


}
