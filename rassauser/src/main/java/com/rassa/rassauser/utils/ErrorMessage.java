package com.rassa.rassauser.utils;


import com.rassa.rassauser.R;

/**
 * Created by Mehdi on 1/23/2018 AD.
 */

public enum ErrorMessage {
        ERROR_NETWORK_SERVER_SIDE(-1, RUserApp.getContext().getString(R.string.rs_error_server_side)),
        ERROR_NETWORK_UNAVALABLE(0, RUserApp.getContext().getString(R.string.rs_error_network_connection)),
        ERROR_404(404,RUserApp.getContext().getString(R.string.rs_error_server_not_found));




    /**************** Error Settings *****************************************************/
	/**/		 private int errorCode;
    /**/		 private String errorMessage;
    /**/
     ErrorMessage(int errorCode, String errorMessage) {
	/**/		        this.errorCode=errorCode;
	/**/			    this.errorMessage=errorMessage;
	/**/		    }
    /**/			public int getErrorCode() { return this.errorCode; }
    /**/		    public String getErrorMessage() { return this.errorMessage; }

    public static String getErrorByCode(int errorCode){

        for(ErrorMessage error:ErrorMessage.values()){
            if(error.errorCode==errorCode){
                return error.errorMessage;
            }
        }
        return ERROR_NETWORK_UNAVALABLE.errorMessage;
    }
    /**************** Sprite Settings *****************************************************/
}
