package com.rassa.rassauser.webApi.register.Login;



import com.rassa.rassauser.webApi.register.Login.LoginModel.ParamsLogin;
import com.rassa.rassauser.webApi.register.Login.LoginModel.ResponseLogin;
import com.rassa.rassauser.webApi.register.iRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mehdi on 2/3/2018 AD.
 */

public interface iLogin {

    void doLogin(String userName, String password);

    interface iResult {
        void onSuccessLogin(String message);
        void onFailedLogin(int errorId, String errorMessage);
    }


    interface apiRequest {
        @Headers("Content-Type: application/json")
        @POST(iRegister.apiAddress + "Login")
        Call<ResponseLogin> login(@Body ParamsLogin paramsLogin);
    }
}
