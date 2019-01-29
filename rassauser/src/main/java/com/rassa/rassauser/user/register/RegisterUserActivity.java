package com.rassa.rassauser.user.register;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rassa.rassauser.R;
import com.rassa.rassauser.user.register.userInfo.FragmentUserInfo;
import com.rassa.rassauser.user.register.sendPhoneNumber.FragmentSendPhoneNumber;
import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.utils.Utils;
import com.rassa.rassauser.utils.customViews.ProgressView;
import com.rassa.rassauser.user.register.verifyCode.FragmentVerifyCode;
import com.rassa.rassauser.webApi.register.Register;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;

public class RegisterUserActivity extends AppCompatActivity implements
        FragmentSendPhoneNumber.OnFragmentInteractionListener,
        FragmentVerifyCode.OnFragmentInteractionListener,
        FragmentUserInfo.OnFragmentInteractionListener {

    private int REQUEST_SMS_PERMMISION = 105;
    private ProgressView progressView;
    private String verifyCode;
    private String phoneNumber;
    private FragmentSendPhoneNumber fragmentSendPhoneNumber;
    private FragmentVerifyCode fragmentVerifyCode;
    private FragmentUserInfo fragmentUserInfo;
    private int fragmentState = 0;
    UserProfile userProfile;
    private final String BROADCAST_UPDATE = "BROADCAST_UPDATE";

    private String KEY_FRGSendPhoneNumber = "KEY_FRGSendPhoneNumber";
    private String KEY_FRGVerifyCode = "KEY_FRGVerifyCode";
    private String KEY_FRGUserInfo = "KEY_FRGUserInfo";

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    private void setUpFrg() {
        fragmentSendPhoneNumber = new FragmentSendPhoneNumber();
        fragmentVerifyCode = new FragmentVerifyCode();
        fragmentUserInfo = new FragmentUserInfo();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onGotKey);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onGotKey);
        super.onPause();
    }

    @Override
    protected void onResume() {
        IntentFilter iff = new IntentFilter(BROADCAST_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(onGotKey, iff);
        super.onResume();
    }

    private BroadcastReceiver onGotKey = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (getSupportFragmentManager().findFragmentByTag(KEY_FRGVerifyCode) != null) {
                fragmentVerifyCode.setCodeToEditTextCode(intent.getStringExtra("code"));
            }
        }
    };

    private static final int RECORD_REQUEST_CODE = 101;

    protected void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS}, RECORD_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rs_activity_register_user);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.rasa_user_md_light_green_A700));
            // getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.red_level_3));
        }
        IntentFilter iff = new IntentFilter(BROADCAST_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(onGotKey, iff);
        userProfile = new UserProfile(this);
        setUpFrg();
        progressView = findViewById(R.id.progressView);
       // getSupportFragmentManager().beginTransaction().add(R.id.frame_fragment, fragmentSendPhoneNumber, KEY_FRGSendPhoneNumber).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, fragmentUserInfo, KEY_FRGUserInfo).addToBackStack(null).commit();

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMMISION) {
            if (getSupportFragmentManager().findFragmentByTag(KEY_FRGSendPhoneNumber) != null) {
                fragmentSendPhoneNumber.getSmsPermissionFromUser();
                IntentFilter iff = new IntentFilter(BROADCAST_UPDATE);
                LocalBroadcastManager.getInstance(this).registerReceiver(onGotKey, iff);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // fragment senPhoneNumber
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onStartSendPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        //progressView.setVisibility(View.VISIBLE);
        //progressView.showProgress();
    }

    @Override
    public void sendPhoneNumberSuccess() {
        fragmentState = 1;
        progressView.setVisibility(View.GONE);
        fragmentVerifyCode = new FragmentVerifyCode();
        // getSupportFragmentManager().beginTransaction().remove(fragmentSendPhoneNumber).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_fragment, fragmentVerifyCode, KEY_FRGVerifyCode).addToBackStack(null).commit();
    }

    @Override
    public void sendPhoneNumberFailed(String msg) {
        progressView.setVisibility(View.VISIBLE);
        progressView.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendPhoneNumber.sendPhoneNumberAgain();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // fragmentVerifyCode
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onStartVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        //progressView.setVisibility(View.VISIBLE);
        //progressView.showProgress();
    }

    @Override
    public void VerifyCodeSuccess() {
        Utils.setStringPreference(this, Utils.KEY_REGISTER, Utils.KEY_PHONENUMBER, phoneNumber);
        this.verifyCode = verifyCode;
        progressView.setVisibility(View.VISIBLE);

        new Register().profile(new iProfile.iResult() {
            @Override
            public void onSuccessGetUserInfo(UserInfo userInfo) {
                progressView.setVisibility(View.GONE);
                if ((userInfo.getFirstName().isEmpty() && userInfo.getLastName().isEmpty()) ||
                        (userInfo.getFirstName() == null && userInfo.getLastName() == null)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, fragmentUserInfo, KEY_FRGUserInfo).addToBackStack(null).commit();
                } else {
                    userProfile.saveUserInfo(userInfo);
                    userProfile.setKeyRegistered(true);
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK, intent);
                    finish();


                }
            }

            @Override
            public void onFailedGetUserInfo(int ErrorId, String ErrorMessage) {

            }

            @Override
            public void onSuccessSetUserInfo(UserInfo userInfo) {

            }

            @Override
            public void onFailedSetUserInfo(int ErrorId, String ErrorMessage) {

            }
        }).startGetUserInfo(userProfile.getKeyJwt("-1"), phoneNumber);
    }

    @Override
    public void VerifyCodeFailed(String message) {

    }


    ///////////////////////////////////////////////////////////////////////////
    // fragmentUserInfo
    ///////////////////////////////////////////////////////////////////////////



    @Override
    public void onStartSetUserInfo() {

    }

    @Override
    public void setUserInfoSuccess() {
        progressView.setVisibility(View.GONE);
        progressView.showProgress();

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void setUserInfoFailed(String msg) {

    }
}
