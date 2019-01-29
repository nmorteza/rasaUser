package com.rassa.rassauser.user.edit.profile;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


/**
 * Created by Mehdi on 6/29/2017 AD.
 */

public interface iPUEditUserInfo {
    Context getContext();

    void setUserInfo(ParamsRegister paramsRegister);
    void setUserInfoSuccess(UserInfo userInfo);
    void setUserInfoFailed(String msg);

}
