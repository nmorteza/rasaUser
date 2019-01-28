
package com.rassa.rassauser.webApi.register.logout.modelLogout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rassa.rassauser.webApi.shareModel.Message;

import java.io.Serializable;
import java.util.List;


public class ResponseLogout implements Serializable
{

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    private final static long serialVersionUID = -143695244767199935L;

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
}
