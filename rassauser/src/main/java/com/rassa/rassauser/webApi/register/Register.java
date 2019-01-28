package com.rassa.rassauser.webApi.register;


import com.rassa.rassauser.webApi.register.VerifyCode.VerifyCode;
import com.rassa.rassauser.webApi.register.VerifyCode.iVerifyCode;
import com.rassa.rassauser.webApi.register.logout.Logout;
import com.rassa.rassauser.webApi.register.logout.iLogout;
import com.rassa.rassauser.webApi.register.registerUserInfo.Profile;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;

public class Register implements iRegister {
    @Override
    public VerifyCode verifyCode() {
        return new VerifyCode();
    }

    @Override
    public VerifyCode verifyCode(iVerifyCode.iResult iResult) {
        return new VerifyCode(iResult);
    }

    @Override
    public Profile profile() {
        return new Profile();
    }

    @Override
    public Profile profile(iProfile.iResult iResult) {
        return new Profile(iResult);
    }

    @Override
    public Logout logout() {
        return new Logout();
    }

    @Override
    public Logout logout(iLogout.iResult iResult) {
        return new Logout(iResult);
    }
}
