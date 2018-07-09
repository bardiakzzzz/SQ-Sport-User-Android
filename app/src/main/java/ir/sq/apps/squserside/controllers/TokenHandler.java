package ir.sq.apps.squserside.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ir.sq.apps.squserside.utils.Constants;

public class TokenHandler {

    private static final String TAG = TokenHandler.class.getSimpleName();
    private static SharedPreferences sharedPref;

    public static String getToken(Context context) {

        sharedPref = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        String access_token = sharedPref.getString(Constants.ACCESS_TOKEN, "");
        if (access_token.isEmpty()) {
            Log.e(TAG, "NO ACCESS_TOKEN PRESENTED!");
            return "";
        }
        return access_token;
    }

    public static void clearToken(Context context) {
        sharedPref = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(Constants.ACCESS_TOKEN);
        editor.apply();
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

    public static boolean hasToken(Context context) {
        sharedPref = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        String token = sharedPref.getString(Constants.ACCESS_TOKEN, "");
        return !token.isEmpty() && token.length() > 0;
    }
}
