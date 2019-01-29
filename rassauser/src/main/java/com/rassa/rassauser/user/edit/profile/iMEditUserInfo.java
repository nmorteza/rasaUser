package com.rassa.rassauser.user.edit.profile;

import android.content.Context;

import com.rassa.rassauser.webApi.register.registerUserInfo.model.ParamsRegister;


/**
 * Created by Mehdi on 6/29/2017 AD.
 */

public interface iMEditUserInfo {
    Context getContext();

    void setUserInfo(ParamsRegister paramsRegister);


}
