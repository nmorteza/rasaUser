package com.rassa.rassauser.user.register.userInfo;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class PUserInfo implements iPUserInfo {

    private iVUserInfo iVUserInfo;
    private MUserInfo mUserInfo;

    public PUserInfo(iVUserInfo iVUserInfo) {
        this.iVUserInfo = iVUserInfo;
        mUserInfo=new MUserInfo(this);
    }

    @Override
    public Context getContext() {
        return iVUserInfo.getContext();
    }

    @Override
    public void setUserInfo(ParamsRegister paramsRegister) {
        iVUserInfo.onStartSetUserInfo();
        mUserInfo.setUserInfo(paramsRegister);
    }

    @Override
    public void setUserInfoSuccess(UserInfo userInfo) {
        iVUserInfo.setUserInfoSuccess(userInfo);
    }

    @Override
    public void setUserInfoFailed(String msg) {
        iVUserInfo.setUserInfoFailed(msg);
    }

    @Override
    public void getUserInfo(String phoneNumber) {
        mUserInfo.getUserInfo(phoneNumber);
    }

    @Override
    public void getUserInfoSuccess(UserInfo userInfo) {
        iVUserInfo.getUserInfoSuccess(userInfo);
    }

    @Override
    public void getUserInfoFailed(String msg) {
        iVUserInfo.getUserInfoFailed(msg);
    }
}