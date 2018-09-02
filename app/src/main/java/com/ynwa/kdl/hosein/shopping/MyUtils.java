package com.ynwa.kdl.hosein.shopping;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ynwa.kdl.hosein.shopping.pojo.LoginStatus;
import com.ynwa.kdl.hosein.shopping.pojo.UserInfo;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class MyUtils {

    public static final String BASE_URL_LOGIN_REGISTER = "http://moviesapi.ir/";

    public static final String PREF_SAVE_USER_INFO = "save_user_info";
    public static final String PREF_LIST_USER_INFO = "list_user_info";

    public static final String PREF_LOGIN = "loginStatus";
    public static final String PREF_LOGIN_EMAIL = "email";
    public static final String PREF_LOGIN_STATUS = "status";
    private static final String PREF_NAME = "user_name";
    private static final String PREF_EMAIL = "user_email";

    public static Random random = new Random();


    public static void saveUserInfoToPref(Context context, UserInfo userInfo){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_SAVE_USER_INFO, MODE_PRIVATE).edit();
        List<UserInfo> list = fetchListOfUsers(context);
        if (list == null){
            list = new ArrayList<>();
        }
        list.add(userInfo);
        Gson gson = new Gson();
        String jsonData = gson.toJson(list);
        editor.putString(PREF_LIST_USER_INFO, jsonData);
        editor.apply();
    }

    private static List<UserInfo> fetchListOfUsers(Context context){
        Gson gson = new Gson();
        Type type = new TypeToken<List<UserInfo>>(){}.getType();

        SharedPreferences pref = context.getSharedPreferences(PREF_SAVE_USER_INFO, MODE_PRIVATE);
        String jsonData = pref.getString(PREF_LIST_USER_INFO, null);
        return gson.fromJson(jsonData, type);
    }

    public static UserInfo fetchCurrentUserInfoFromPref(Context context, String email){
        UserInfo userInfo = new UserInfo();
        List<UserInfo> list ;
        Gson gson = new Gson();


        SharedPreferences pref = context.getSharedPreferences(PREF_SAVE_USER_INFO, MODE_PRIVATE);
        String jsonData = pref.getString(PREF_LIST_USER_INFO, null);

        Type type = new TypeToken<List<UserInfo>>(){}.getType();
        list = gson.fromJson(jsonData, type);
        if (list == null){
            list = new ArrayList<>();
        }

        for (UserInfo ui : list)
        {
            if (ui.getEmail().equals(email))
                userInfo = ui;
        }
        return  userInfo;
    }

    public static boolean checkUserPref(Context context){

        String name = getName(context);
        String email = getEmail(context);

        if (name != null  &&  email != null)
        {
            return true;
        }
        else
            return false;
    }

    public static void removeUserInfoFromPref(Context context){
        SharedPreferences.Editor pref = context.getSharedPreferences(MyUtils.PREF_SAVE_USER_INFO, MODE_PRIVATE).edit();
        pref.clear();
        pref.apply();
    }

    public static String getName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_SAVE_USER_INFO, MODE_PRIVATE);
        return pref.getString(PREF_NAME, "name");
    }

    public static String getEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_SAVE_USER_INFO, MODE_PRIVATE);
        return pref.getString(PREF_EMAIL, "email");
    }


    public static void saveLoginStatus(Context context, String email, boolean status) {
        clearLoginStatus(context);
        SharedPreferences pref = context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_LOGIN_EMAIL, email);
        editor.putBoolean(PREF_LOGIN_STATUS, status);
        editor.apply();
    }

    public static LoginStatus getLoginStatus(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);

        LoginStatus login = new LoginStatus();
        login.setUserEmail(pref.getString(PREF_LOGIN_EMAIL, "---"));
        login.setLoggedIn(pref.getBoolean(PREF_LOGIN_STATUS, false));

        return login;
    }

    public static void clearLoginStatus(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }


    public static String getAccessToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("save_token", MODE_PRIVATE);
        return preferences.getString("access_token", null);
    }

    public static String getRefreshToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences("save_token", MODE_PRIVATE);
        return preferences.getString("refresh_token", null);
    }

    private void saveToken(Context context, String access_token, String refresh_token){

        SharedPreferences preferences = context.getSharedPreferences("save_token", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("access_token", access_token);
        edit.putString("refresh_token", refresh_token);
        edit.apply();
    }

    public static long incrementId(Number currentId){

        if (currentId == null){
            return 1; // is first record
        }
        else {
            return currentId.longValue() + 1;
        }
    }

    public static String priceSeprator(String price){

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("##,###,###", dfs);

        return df.format( Integer.valueOf(price) );
    }

    public static String priceSeprator(int price){

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("##,###,###", dfs);

        return df.format( Integer.valueOf(price) );
    }

    public static int randomNumber(int max){

        return random.nextInt(max);
    }


}
