package ir.sq.apps.squserside.models;

public class Receipt {

    private int id;
    private int price;
    private String date;
    private String time;
    private String clubName;
    private String clubAddress;

    public Receipt(int price, String date, String clubName) {
        this.price = price;
        this.date = date;
        this.clubName = clubName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubAdress() {
        return clubAddress;
    }

    public void setClubAdress(String clubAdress) {
        this.clubAddress = clubAdress;
    }
}