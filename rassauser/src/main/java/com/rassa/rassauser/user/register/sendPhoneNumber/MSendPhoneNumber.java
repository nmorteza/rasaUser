package com.rassa.rassauser.user.register.sendPhoneNumber;

import android.content.Context;

import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.webApi.register.RegisterRequest.RegisterRequest;
import com.rassa.rassauser.webApi.register.RegisterRequest.iRegisterRequest;
import com.rassa.rassauser.webApi.register.registerUserInfo.model.UserInfo;


public class  MSendPhoneNumber implements iMSendPhoneNumber {

    private iPSendPhoneNumber iPSendPhoneNumber;
    private UserProfile userProfile;

    public MSendPhoneNumber(iPSendPhoneNumber iPSendPhoneNumber) {
        this.iPSendPhoneNumber = iPSendPhoneNumber;
        this.userProfile=new UserProfile(iPSendPhoneNumber.getContext());
    }

    @Override
    public Context getContext() {
        return iPSendPhoneNumber.getContext();
    }

    @Override
    public void sendPhoneNumber(final String phoneNumber) {
        RegisterRequest registerRequest = new RegisterRequest(new iRegisterRequest.iResult() {
            @Override
            public void onSuccessRegisterRequest() {
                userProfile.setKeyPhoneNumber(phoneNumber);
                iPSendPhoneNumber.sendPhoneNumberSuccess();
            }

            @Override
            public void onFailedRegisterRequest(int ErrorId, String ErrorMessage) {
                iPSendPhoneNumber.sendPhoneNumberFailed(ErrorMessage);
            }
        });
        registerRequest.startSendPhoneNumber(phoneNumber);
    }
}
