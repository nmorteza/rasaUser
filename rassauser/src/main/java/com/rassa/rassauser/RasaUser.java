package com.rassa.rassauser;

import android.content.Context;
import android.content.Intent;

import com.rassa.rassauser.user.edit.EditUserActivity;
import com.rassa.rassauser.user.edit.profile.FragmentEditUserInfo;
import com.rassa.rassauser.user.register.RegisterUserActivity;
import com.rassa.rassauser.user.register.sendPhoneNumber.FragmentSendPhoneNumber;
import com.rassa.rassauser.user.register.userInfo.FragmentUserInfo;
import com.rassa.rassauser.user.register.verifyCode.FragmentVerifyCode;
import com.rassa.rassauser.utils.UserProfile;

public class RasaUser {

    private Context context;
    private UserProfile userProfile;
    public static String baseUrl;

    public RasaUser(Context context,String baseUrl) {
        this.context = context;
        this.userProfile = new UserProfile(context);
        this.baseUrl=baseUrl;
    }


    ///////////////////////////////////////////////////////////////////////////
    // userProfile
    ///////////////////////////////////////////////////////////////////////////

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public boolean userIsRegistered(){
        return userProfile.getKeyRegistered(false);
    }


    ///////////////////////////////////////////////////////////////////////////
    // intent
    ///////////////////////////////////////////////////////////////////////////

    public Intent getIntentActivityRegisterUser() {
        Intent intent = new Intent(context, RegisterUserActivity.class);
        return intent;
    }

    public Intent getIntentActivityEditUser() {
        Intent intent = new Intent(context, EditUserActivity.class);
        return intent;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Fragment
    ///////////////////////////////////////////////////////////////////////////

    public FragmentSendPhoneNumber getFragmentSendPhoneNumber() {
        FragmentSendPhoneNumber fragmentSendPhoneNumber = new FragmentSendPhoneNumber();
        return fragmentSendPhoneNumber;
    }


    public FragmentVerifyCode getFragmentVerifyCode() {
        FragmentVerifyCode fragmentVerifyCode = new FragmentVerifyCode();
        return fragmentVerifyCode;
    }

    public FragmentUserInfo getFragmentUserInfo() {
        FragmentUserInfo fragmentUserInfo = new FragmentUserInfo();
        return fragmentUserInfo;
    }

    public FragmentEditUserInfo getFragmentEditUserInfo() {
        FragmentEditUserInfo fragmentEditUserInfo = new FragmentEditUserInfo();
        return fragmentEditUserInfo;
    }


}
