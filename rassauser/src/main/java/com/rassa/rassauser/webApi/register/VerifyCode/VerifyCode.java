package com.rassa.rassauser.webApi.register.VerifyCode;

import android.os.Build;
import android.util.Log;

import com.rassa.rassauser.BuildConfig;
import com.rassa.rassauser.utils.RUserApp;
import com.rassa.rassauser.utils.ErrorMessage;
import com.rassa.rassauser.utils.Utils;
import com.rassa.rassauser.webApi.WebService;
import com.rassa.rassauser.webApi.register.VerifyCode.ModelVerifyCode.ParamsVerifyCode;
import com.rassa.rassauser.webApi.register.VerifyCode.ModelVerifyCode.ResponseVerifyCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mehdi on 1/23/2018 AD.
 */

public class VerifyCode implements iVerifyCode {


    private iResult iResult;

    public VerifyCode(){

    }
    public VerifyCode(iResult iResult){
        this.iResult=iResult;
    }

    @Override
    public void startSendVerifyCode(final String phoneNumber, final String smsCode) {
        Log.d("ffff", "onResponse: " + phoneNumber);
        Log.d("ffff", "onResponse: " + smsCode);
        ParamsVerifyCode paramsVerifyCode=new ParamsVerifyCode();
        paramsVerifyCode.setPhoneNumber(phoneNumber);
        paramsVerifyCode.setCode(smsCode);
        paramsVerifyCode.setAppVersion(""+ BuildConfig.VERSION_CODE);
        paramsVerifyCode.setDeviceId(Utils.getDeviceId(RUserApp.getContext()));
//        paramsVerifyCode.setDeviceModel(Value.getDeviceModel());
        paramsVerifyCode.setOsType(Long.valueOf(0));
        paramsVerifyCode.setOsVersion(""+Build.VERSION.SDK_INT);

        Call<ResponseVerifyCode> call = new WebService().getClient().create(apiRequest.class).verifyCode(paramsVerifyCode);


        call.enqueue(new Callback<ResponseVerifyCode>() {
            @Override
            public void onResponse(Call<ResponseVerifyCode> call, Response<ResponseVerifyCode> response) {
                if (response.code() == 200) {
                    if (response.body().getOk()) {
                        Log.d("ffff", "onResponse: " + response.body().getOk());
                        if (iResult != null) {
                            iResult.onSuccessSendVerifyCode(response.body().getExtra());
                        }
                    } else {
                        Log.d("TAG", "onResponseVerifyCode: "+(iResult == null));
                        if (iResult != null) {
                            if(response.body().getMessages().size()>0) {
                                iResult.onFailedSendVerifyCode(0, response.body().getMessages().get(0).getDescription());
                            }else{
                                iResult.onFailedSendVerifyCode(0, ErrorMessage.ERROR_NETWORK_SERVER_SIDE.getErrorMessage());
                            }
                        }
                    }
                }else {
                    Log.d("TAG", " onResponseVerifyCode !200: "+(iResult == null));

                    if (iResult != null) {
                        iResult.onFailedSendVerifyCode(0, ErrorMessage.getErrorByCode(response.code()));
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseVerifyCode> call, Throwable t) {
                Log.d("TAG", " onFailure onResponseVerifyCode: "+(iResult == null));
                t.printStackTrace();
                if (iResult != null) {
                    iResult.onFailedSendVerifyCode(0, ErrorMessage.ERROR_NETWORK_UNAVALABLE.getErrorMessage());
                }
            }
        });
    }





}
