package ir.sq.apps.squserside.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ir.sq.apps.squserside.models.Club;
import ir.sq.apps.squserside.models.Plan;
import ir.sq.apps.squserside.models.Receipt;
import ir.sq.apps.squserside.models.User;
import ir.sq.apps.squserside.utils.Constants;

public class RequestHandler {
    private static final String TAG = "Request Class";

    public interface OnResponseTransfer {
        Intent go();
    }

    public static void reserve(Club club, Plan plan, final Context context, final OnResponseTransfer onResponseTransfer) {
        String access_token = TokenHandler.getToken(context);
        JSONObject body = new JSONObject();
        try {
            body.put("ownerUserName", club.getOwnerUserName());
            body.put("userId", UserHandler.getInstance().getUser().getId());
            body.put("planId", plan.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = UrlHandler.reserveUrl.getUrl() + "/" + club.getOwnerUserName() + "/" + UserHandler.getInstance().getUser().getId() + "/" + plan.getId();
        AndroidNetworking.post(url)
                .addHeaders(Constants.AUTHORIZATION, access_token)
                .addJSONObjectBody(body)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "Signup response: " + response.toString());
                        String status = null;
                        try {
                            status = response.getString(Constants.STATUS);
                        } catch (JSONException e) {
                            Log.e(TAG, "Reserve JSONException: " + e.toString());
                        }
                        if (status == null) {
                            Log.e(TAG, "Reserve response have no status parameter.");
                        } else if (status.equals(Constants.OK)) {
                            Log.i("TAG", "رزرو با موفقیت انجام شد");
                            Intent intent = onResponseTransfer.go();
                            context.startActivity(intent);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "Login request error: " + anError.toString());
                    }
                });
    }


    public static void getUser(final Context context, final OnResponseTransfer onResponseTransfer) {

        String access_token = TokenHandler.getToken(context);
        AndroidNetworking.get(UrlHandler.getUserURL.getUrl())
                .addHeaders(Constants.AUTHORIZATION, access_token)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    public void onResponse(JSONObject response) {
                        User user = new User();
                        try {
                            Log.i("USER", response.toString());
                            JSONObject userObject = response.getJSONObject("object");
                            JSONArray receipts = userObject.getJSONArray("receiptList");
                            setReceipts(receipts, user);
                            user.setUserName(userObject.getString("userName"));
                            user.setEmail(userObject.getString("email"));
                            user.setName("محمد");
                            user.setId(userObject.getLong("id"));
                            UserHandler.getInstance().setUser(user);
                            Intent intent = onResponseTransfer.go();
                            context.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "Couldn't Connected");
                    }
                });
    }

    private static void setReceipts(JSONArray receipts, User user) throws JSONException {
        for (int i = 0; i < receipts.length(); i++) {
            JSONObject r = receipts.getJSONObject(i);
            int price = r.getInt("price");
            String date = r.getString("date");
            String time = r.getString("time");
            String clubName = r.getString("clubName");
//            String clubAddress = r.getString("clubAddress");
            Receipt receipt = new Receipt(price, date, time, clubName, "آدرس باشگاه");
            user.addReceipt(receipt);
        }
    }
}

