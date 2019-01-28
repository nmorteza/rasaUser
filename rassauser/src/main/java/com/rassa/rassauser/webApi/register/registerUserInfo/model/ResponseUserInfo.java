
package com.rassa.rassauser.webApi.register.registerUserInfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rassa.rassauser.webApi.shareModel.Message;

import java.io.Serializable;
import java.util.List;

public class ResponseUserInfo implements Serializable
{

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("extra")
    @Expose
    private UserInfo extra;
    private final static long serialVersionUID = -2756989005002948520L;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public UserInfo getExtra() {
        return extra;
    }

    public void setExtra(UserInfo extra) {
        this.extra = extra;
    }

}
