package com.rassa.rassauser.webApi.register;


import com.rassa.rassauser.webApi.register.VerifyCode.VerifyCode;
import com.rassa.rassauser.webApi.register.VerifyCode.iVerifyCode;
import com.rassa.rassauser.webApi.register.logout.Logout;
import com.rassa.rassauser.webApi.register.logout.iLogout;
import com.rassa.rassauser.webApi.register.registerUserInfo.Profile;
import com.rassa.rassauser.webApi.register.registerUserInfo.iProfile;

public interface iRegister {
    String apiAddress = "register/";

    VerifyCode verifyCode();
    VerifyCode verifyCode(iVerifyCode.iResult iResult);

    Profile profile();
    Profile profile(iProfile.iResult iResult);

    Logout logout();
    Logout logout(iLogout.iResult iResult);
}
