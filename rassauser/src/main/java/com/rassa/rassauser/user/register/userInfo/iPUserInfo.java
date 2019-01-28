package com.rassa.rassauser.user.register.userInfo;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


/**
 * Created by Mehdi on 6/29/2017 AD.
 */

public interface iPUserInfo {
    Context getContext();

    void setUserInfo(ParamsRegister paramsRegister);
    void setUserInfoSuccess(UserInfo userInfo);
    void setUserInfoFailed(String msg);

    void getUserInfo(String phoneNumber);
    void getUserInfoSuccess(UserInfo userInfo);
    void getUserInfoFailed(String msg);
}
