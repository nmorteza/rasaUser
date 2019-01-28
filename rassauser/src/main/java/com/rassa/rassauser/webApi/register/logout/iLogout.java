package com.rassa.rassauser.webApi.register.logout;

import com.rassa.rassauser.webApi.register.iRegister;
import com.rassa.rassauser.webApi.register.logout.modelLogout.ResponseLogout;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mehdi on 2/3/2018 AD.
 */

public interface iLogout {

    void doLogout(String phoneNumber, String deviceId);

    interface iResult {
        void onSuccessLogout();
        void onFailedLogout(int errorId, String errorMessage);
    }


    interface apiRequest {
        @POST(iRegister.apiAddress + "Logout")
        Call<ResponseLogout> logout(@Query("phoneNumber") String phoneNumber, @Query("deviceId") String deviceId);
    }
}
