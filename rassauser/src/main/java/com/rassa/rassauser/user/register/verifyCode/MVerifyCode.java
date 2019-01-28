package com.rassa.rassauser.user.register.verifyCode;

import android.content.Context;

import com.rassa.rassauser.utils.UserProfile;
import com.rassa.rassauser.webApi.register.Register;
import com.rassa.rassauser.webApi.register.VerifyCode.iVerifyCode;


public class  MVerifyCode implements iMVerifyCode {

    private iPVerifyCode iPVerifyCode;
    private UserProfile userProfile;

    public MVerifyCode(iPVerifyCode iPVerifyCode) {
        this.iPVerifyCode = iPVerifyCode;
        userProfile = new UserProfile(getContext());
    }

    @Override
    public Context getContext() {
        return iPVerifyCode.getContext();
    }

    @Override
    public void VerifyCode(String phoneNumber, String verifyCodeString) {
        new Register().verifyCode(new iVerifyCode.iResult() {
            @Override
            public void onSuccessSendVerifyCode(String jwt) {
                userProfile.setKeyJwt(jwt);
                iPVerifyCode.VerifyCodeSuccess();
            }

            @Override
            public void onFailedSendVerifyCode(int ErrorId, String ErrorMessage) {
                iPVerifyCode.VerifyCodeFailed(ErrorMessage);
            }
        }).startSendVerifyCode(phoneNumber, verifyCodeString);
    }
}
