package ir.sq.apps.squserside.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.ClubHandler;
import ir.sq.apps.squserside.controllers.UrlHandler;
import ir.sq.apps.squserside.models.Club;
import ir.sq.apps.squserside.models.Plan;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getClubsFromServer();
    }

    public void getClubsFromServer() {
        AndroidNetworking.get(UrlHandler.getAllClubsURL.getUrl())
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("LOG", response.toString());
                ClubHandler.getInstance().setClubs(parseClubs(response));
                startActivity(new Intent(SplashActivity.this, NavHolderActivity.class));
            }

            @Override
            public void onError(ANError anError) {
                Log.e("Get User Error", "error message: " + anError.getErrorBody() + " code : " + anError.getErrorBody());
            }
        });
    }

    private List<Club> parseClubs(JSONObject response) {
        List<Club> clubs = new ArrayList<>();
        try {
            JSONArray clubsJson = response.getJSONArray("object");
            for (int i = 0; i < clubsJson.length(); i++) {
                JSONObject clubObject = clubsJson.getJSONObject(i);
                String name = clubObject.getString("name");
                String owner = clubObject.getString("owner");
                String ownerUserName = clubObject.getString("ownerUserName");
                String telePhoneNumber = clubObject.getString("telePhoneNumber");
                String cellPhoneNumber = clubObject.getString("cellPhoneNumber");
                String address = clubObject.getString("address");
                int type = clubObject.getInt("type");
                Double latitude = clubObject.getDouble("latitude");
                Double longtitude = clubObject.getDouble("longtitude");
                Club club = new Club(ownerUserName, name, owner, telePhoneNumber, cellPhoneNumber, address, latitude, longtitude, type);
                club.setId(clubObject.getLong("id"));
                setClubPlans(club, clubObject.getJSONArray("weeklyPlan"));
                JSONArray imagesJsonArray = clubObject.getJSONArray("images");
                JSONArray tagsJsonArray = clubObject.getJSONArray("tagList");
                for (int j = 0; j < tagsJsonArray.length(); j++) {
                    club.addTags(tagsJsonArray.getJSONObject(j).getString("name"));
                }
                for (int j = 0; j < imagesJsonArray.length(); j++) {
                    String img = ((JSONObject) imagesJsonArray.get(j)).getString("name");
                    club.addNameImage(img);
                    getImageFrom(club, String.valueOf(img));
                }
                clubs.add(club);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    private void setClubPlans(Club club, JSONArray plansArray) throws JSONException {
        for (int j = 0; j < plansArray.length(); j++) {
            JSONObject receiptObject = plansArray.getJSONObject(j);
            int price = receiptObject.getInt("price");
            int status = receiptObject.getInt("status");
            int day = receiptObject.getInt("day");
            long id = receiptObject.getLong("id");
            String date = receiptObject.getString("date");
            String time = receiptObject.getString("time");
            Plan plan = new Plan(id, price, status, day, date, time);
            club.addPlan(plan);
        }
    }

    private void getImageFrom(final Club club, final String imageUrl) {
        final String url = UrlHandler.getImageClubURL.getUrl() + imageUrl;
        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .setBitmapMaxHeight(100)
                .setBitmapMaxWidth(100)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        Log.i("IMAGE LOAD", "url: " + url);
                        club.addImage(bitmap);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("IMAGE LOAD", "url : " + url + " error message: " + anError.getErrorBody() + " code : " + anError.getErrorBody());
                    }
                });
    }

}
