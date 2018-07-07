package ir.sq.apps.squserside.controllers;

import android.content.Context;
import android.content.Intent;
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

    public interface OnResponseTransfer {
        Intent go();
    }

    public static void getUser(final Context context, final OnResponseTransfer onResponseTransfer) {

        final User user = new User();
        String access_token = TokenHandler.getToken(context);
        AndroidNetworking.get(UrlHandler.getUserURL.getUrl())
                .addHeaders(Constants.AUTHORIZATION, access_token)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject userObject = response.getJSONObject("object");
                            user.setUserName(userObject.getString("userName"));
                            user.setEmail(userObject.getString("email"));
                            user.setName("محمد");
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
}

