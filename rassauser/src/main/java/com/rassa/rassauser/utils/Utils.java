package com.rassa.rassauser.utils;
/*
 * Created by AMiR Ehsan on 7/22/2017.
 */

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

public class Utils {

    public static boolean isHomeOpen = false;

    public static final int RECORD_REQUEST_CODE = 101;
    public static String keyPer = "listPos";
    public static String KEY_SETTING = "KEY_SETTING";
    public static String KEY_REGISTER = "KEY_REGISTER";
    public static String KEY_AUTO_UPDATE = "KEY_AUTO_UPDATE";


    public static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%20";



    public static String KEY_DAY_COUNT = "KEY_DAY_COUNT";
    public static String reference_code = "reference_code";
    public static String KEY_QUESTION = "KEY_QUESTION";
    public static String KEY_PHONENUMBER = "KEY_PHONENUMBER";
    public static String KEY_FCM = "KEY_FCM";
    public static String KEY_JWT = "KEY_JWT";

    public static String KEY_DATA = "KEY_DATA";

    public static void setStringPreference(Context context, String masterKey, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeStringPreference(Context context, String masterKey, String key) {
        SharedPreferences settings = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String getStringPreference(Context context, String masterKey, String key, String defeult) {
        SharedPreferences settings = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        return settings.getString(key, defeult);
    }

    public static void setBooleanPreference(Context context, String masterKey, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanPreference(Context context, String masterKey, String key, boolean defeult) {
        SharedPreferences settings = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defeult);
    }

    public static boolean getPermission(Activity activity) {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED ||
                ACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED ||
                READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED ||
                WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static boolean getSmsPermission(Activity activity) {
        int READ_SMS = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        if (READ_SMS != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static boolean gstoragePermissionIsGranted(Context context) {
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED || WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                RECORD_REQUEST_CODE);
    }

    public static void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                RECORD_REQUEST_CODE);
    }

    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    public static Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNumberMci(String phoneNumber){
        phoneNumber=phoneNumber.
                replace("۰","0").
                replace("۱","1").
                replace("۲","2").
                replace("۳","3").
                replace("۴","4").
                replace("۵","5").
                replace("۶","6").
                replace("۷","7").
                replace("۸","8").
                replace("۹","9");
        if (phoneNumber.startsWith("091") ||
                phoneNumber.startsWith("099")
                ){
            return true;
        }else{
            return false;
        }
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID) + 2;
    }


    public static boolean isForeground(String myPackage) {
        ActivityManager manager = (ActivityManager) RUserApp.getContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = manager.getRunningTasks(1);
        ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
        return componentInfo.getPackageName().equals(myPackage);
    }





}