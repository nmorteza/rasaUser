package com.rassa.rassauser.webApi.register.VerifyCode;


import com.rassa.rassauser.webApi.register.VerifyCode.ModelVerifyCode.ParamsVerifyCode;
import com.rassa.rassauser.webApi.register.VerifyCode.ModelVerifyCode.ResponseVerifyCode;
import com.rassa.rassauser.webApi.register.iRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mehdi on 1/29/2018 AD.
 */

public interface iVerifyCode {

    void startSendVerifyCode(String phoneNumber, String smsCode);

    interface iResult {
        void onSuccessSendVerifyCode(String jwt);

        void onFailedSendVerifyCode(int ErrorId, String ErrorMessage);
    }

    interface apiRequest {
        @POST(iRegister.apiAddress + "VerifyCode")
        Call<ResponseVerifyCode> verifyCode(@Body ParamsVerifyCode paramsVerifyCode);
    }

}
