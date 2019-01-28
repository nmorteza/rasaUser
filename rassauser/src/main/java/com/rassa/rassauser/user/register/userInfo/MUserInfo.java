package com.rassa.rassauser.user.register.userInfo;

import android.content.Context;

import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.webApi.register.Register;
import com.rassa.rassauser.webApi.register.registerUserInfo.Profile;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class  MUserInfo implements iMUserInfo {

    private iPUserInfo iPUserInfo;
    private Profile profile;
    private UserProfile userProfile;

    public MUserInfo(iPUserInfo iPUserInfo) {
        this.iPUserInfo = iPUserInfo;
        userProfile = new UserProfile(getContext());
    }

    @Override
    public Context getContext() {
        return iPUserInfo.getContext();
    }

    @Override
    public void setUserInfo(ParamsRegister paramsRegister) {

        new Register().profile(new iProfile.iResult() {
            @Override
            public void onSuccessGetUserInfo(UserInfo userInfo) {
                userProfile.saveUserInfo(userInfo);
                userProfile.setKeyRegistered(true);

                iPUserInfo.getUserInfoSuccess(userInfo);
            }

            @Override
            public void onFailedGetUserInfo(int ErrorId, String ErrorMessage) {
                iPUserInfo.getUserInfoFailed(ErrorMessage);
            }

            @Override
            public void onSuccessSetUserInfo(UserInfo userInfo) {
                userProfile.saveUserInfo(userInfo);
                userProfile.setKeyRegistered(true);
                iPUserInfo.setUserInfoSuccess(userInfo);

            }

            @Override
            public void onFailedSetUserInfo(int ErrorId, String ErrorMessage) {
                iPUserInfo.setUserInfoFailed(ErrorMessage);

            }
        }).startSetUserInfo(paramsRegister);
    }


    @Override
    public void getUserInfo(String phoneNumber) {
        profile.startGetUserInfo(userProfile.getKeyJwt("-1"),phoneNumber);
    }
}
