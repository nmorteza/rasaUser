package com.rassa.rassauser.utils;
/*
 * Created by AMiR Ehsan on 7/22/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class UserProfile {

    private static Context context;

    private static String KEY_USER = "RASA_USER";

    private static String KEY_PHONE_NUMBER = "RASA_USER_KEY_PHONE_NUMBER";
    private static String KEY_FIRST_NAME = "RASA_USER_KEY_FIRST_NAME";
    private static String KEY_LAST_NAME = "RASA_USER_KEY_LAST_NAME";
    private static String KEY_IMG_URL = "RASA_USER_KEY_IMG_URL";
    private static String KEY_FCM = "RASA_USER_KEY_FCM";
    private static String KEY_JWT = "RASA_USER_KEY_JWT";
    private static String KEY_REGISTERED = "RASA_USER_REGISTERED";


    public UserProfile(Context context){
        this.context = context;
    }

    ///////////////////////////////////////////////////////////////////////////
    // setter
    ///////////////////////////////////////////////////////////////////////////




    public  void setKeyPhoneNumber(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_PHONE_NUMBER, value);
        editor.apply();
    }

    public  void setKeyRegistered(boolean value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_REGISTERED, value);
        editor.apply();
    }


    public  void set_KeyFirstName(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_FIRST_NAME, value);
        editor.apply();
    }

    public  void setKeyLastName(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_LAST_NAME, value);
        editor.apply();
    }

    public  void setKeyImgUrl(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_IMG_URL, value);
        editor.apply();
    }

    public  void setKeyFcm(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_FCM, value);
        editor.apply();
    }

    public  void setKeyJwt(String value) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_JWT, value);
        editor.apply();
    }



    ///////////////////////////////////////////////////////////////////////////
    // getter
    ///////////////////////////////////////////////////////////////////////////

    public  boolean getKeyRegistered(boolean defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getBoolean(KEY_REGISTERED, defeult);
    }

    public  String getKeyPhoneNumber(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_PHONE_NUMBER, defeult);
    }


    public  String getKeyFirstName(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_FIRST_NAME, defeult);
    }

    public  String getKeyLastName(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_LAST_NAME, defeult);
    }

    public  String getKeyImgUrl(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_IMG_URL, defeult);
    }

    public  String getKeyFcm(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_FCM, defeult);
    }

    public  String getKeyJwt(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return settings.getString(KEY_JWT, defeult);
    }


    public  void removeUserInfo() {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(KEY_FIRST_NAME);
        editor.remove(KEY_LAST_NAME);
        editor.remove(KEY_PHONE_NUMBER);
        editor.remove(KEY_IMG_URL);
        editor.remove(KEY_JWT);
        editor.remove(KEY_FCM);
        editor.apply();
    }


    public  void saveUserInfo(UserInfo userInfo) {
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_PHONE_NUMBER, userInfo.getPhoneNumber());
        editor.putString(KEY_FIRST_NAME, userInfo.getFirstName());
        editor.putString(KEY_LAST_NAME, userInfo.getLastName());
        editor.putString(KEY_PHONE_NUMBER, userInfo.getPhoneNumber());
        editor.putString(KEY_IMG_URL, userInfo.getImageUrl());
        editor.apply();
    }

    public  UserInfo getUserInfo() {
        UserInfo userInfo=new UserInfo();
        SharedPreferences settings = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);


        userInfo.setFirstName(settings.getString(KEY_FIRST_NAME, ""));
        userInfo.setLastName(settings.getString(KEY_LAST_NAME, ""));
        userInfo.setPhoneNumber(settings.getString(KEY_PHONE_NUMBER, ""));
        userInfo.setImageUrl(settings.getString(KEY_IMG_URL, ""));

        return userInfo;
    }

}