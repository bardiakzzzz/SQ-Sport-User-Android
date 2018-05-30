package ir.sq.apps.squserside.models;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 5/27/2018.
 */

public class Club implements Serializable {
    //TODO Complete This Class
    private int type;


    private Double latitude;
    private Double longtitude;
    private Double rate;

    private String name;
    private String ownerUserName;
    private String owner;
    private String telePhoneNumber;
    private String cellPhoneNumber;
    private String address;
    private String openTime;
    private String price;
    private String closeTime;

    private List<Bitmap> images;

    public List<String> getNameImages() {
        return nameImages;
    }

    private List<String> nameImages;
    private List<String> tags;

    public Club(String ownerUserName, String name, String owner, String telePhoneNumber, String cellPhoneNumber, String adress, Double latitude, Double longtitude, int type) {
        this.name = name;
        this.owner = owner;
        this.telePhoneNumber = telePhoneNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.address = adress;
        this.ownerUserName = ownerUserName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.type = type;
        images = new ArrayList<>();
        tags = new ArrayList<>();
        nameImages = new ArrayList<>();
        this.rate = 4.5;
        this.price = "5000 تومان";
    }

    public void addImages(List<Bitmap> images) {
        this.images.addAll(images);
    }

    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    public String formToJson() {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("owner", owner);
            object.put("telePhoneNumber", telePhoneNumber);
            object.put("cellPhoneNumber", cellPhoneNumber);
            object.put("address", address);
            object.put("latitude", latitude);
            object.put("longtitude", longtitude);
            object.put("ownerUserName", ownerUserName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelePhoneNumber() {
        return telePhoneNumber;
    }

    public void setTelePhoneNumber(String telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTags(String tag) {
        this.tags.add(tag);
    }

    public void addImage(Bitmap bitmap) {
        images.add(bitmap);
    }

    public void addNameImage(String img) {
        nameImages.add(img);
    }

    public String getImageName(int pos) {
        return nameImages.get(pos);
    }
}
