package com.rassa.rassauser.user.edit.profile;

import android.content.Context;

import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.webApi.register.Register;
import com.rassa.rassauser.webApi.register.registerUserInfo.Profile;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class MEditUserInfo implements iMEditUserInfo {

    private iPUEditUserInfo iPUserInfo;
    private Profile profile;
    private UserProfile userProfile;

    public MEditUserInfo(iPUEditUserInfo iPUserInfo) {
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

                }

                @Override
                public void onFailedGetUserInfo(int ErrorId, String ErrorMessage) {
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



}
