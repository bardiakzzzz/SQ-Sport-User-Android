package ir.sq.apps.squserside.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import ir.sq.apps.squserside.models.User;
import ir.sq.apps.squserside.utils.Constants;

public class RequestHandler {
    private static final String TAG = "Request Class";
    private static SharedPreferences sharedPref;

    public static void getUser(Context context) {

        final User user = new User();
        String access_token = getToken(context);
        AndroidNetworking.get(UrlHandler.getUserURL.getUrl())
                .addHeaders(Constants.AUTHORIZATION, access_token)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("Test", "Object" + response.toString());
                            JSONObject userObject = response.getJSONArray("users").getJSONObject(0);
                            user.setUserName(userObject.getString("userName"));
                            user.setEmail(userObject.getString("email"));
                            Log.i("Test", user.getUserName().toString());
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

    private static String getUserName(Context context) {
        sharedPref = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        String userName = sharedPref.getString(Constants.USERNAME, "");
        if (userName.isEmpty()) {
            Log.e(TAG, "NO UserName PRESENTED!");
            return "";
        }
        return userName;
    }

    public static String getToken(Context context) {

        sharedPref = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        String access_token = sharedPref.getString(Constants.ACCESS_TOKEN, "");
        if (access_token.isEmpty()) {
            Log.e(TAG, "NO ACCESS_TOKEN PRESENTED!");
            return "";
        }
        return access_token;
    }

}

