package com.rassa.rassauser.user.edit.profile;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class PEditUserInfo implements iPUEditUserInfo {

    private iVEditUserInfo iVUserInfo;
    private MEditUserInfo mUserInfo;

    public PEditUserInfo(iVEditUserInfo iVUserInfo) {
        this.iVUserInfo = iVUserInfo;
        mUserInfo=new MEditUserInfo(this);
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


}