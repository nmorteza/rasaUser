package com.rassa.rassauser.user.register.userInfo;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;


/**
 * Created by Mehdi on 6/29/2017 AD.
 */

public interface iMUserInfo {
    Context getContext();

    void setUserInfo(ParamsRegister paramsRegister);


}
