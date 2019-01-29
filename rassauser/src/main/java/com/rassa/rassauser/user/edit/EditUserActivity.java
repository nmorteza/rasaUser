package com.rassa.rassauser.user.edit;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rassa.rassauser.R;
import com.rassa.rassauser.user.edit.profile.FragmentEditUserInfo;
import com.rassa.rassauser.user.register.sendPhoneNumber.FragmentSendPhoneNumber;
import com.rassa.rassauser.user.register.userInfo.FragmentUserInfo;
import com.rassa.rassauser.user.register.verifyCode.FragmentVerifyCode;
import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.utils.Utils;
import com.rassa.rassauser.utils.customViews.ProgressView;
import com.rassa.rassauser.webApi.register.Register;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;

public class EditUserActivity extends AppCompatActivity implements
        FragmentEditUserInfo.OnFragmentInteractionListener {
    private ProgressView progressView;
    private FragmentEditUserInfo fragmentEditUserInfo;
    UserProfile userProfile;

    private String KEY_FRGEditUserInfo = "KEY_FRGEditUserInfo";

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    private void setUpFrg() {
        fragmentEditUserInfo = new FragmentEditUserInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rs_activity_register_user);
     /*   if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_blue_700));

        }*/
        userProfile = new UserProfile(this);
        setUpFrg();
        progressView = findViewById(R.id.progressView);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_fragment, fragmentEditUserInfo, KEY_FRGEditUserInfo).commit();
    }

    public static void clearLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // fragmentEditUserInfo
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public void onStartSetUserInfo() {

    }

    @Override
    public void setUserInfoSuccess() {


        Intent intent = new Intent();
        intent.putExtra("Refresh",true);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void setUserInfoFailed(String msg) {

    }
}
