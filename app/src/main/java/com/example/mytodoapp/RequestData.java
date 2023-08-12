package com.example.mytodoapp;

import com.google.gson.annotations.SerializedName;

public class RequestData {
    @SerializedName("InputEmail")
    private String InputEmail;

    @SerializedName("InputPassword")
    private String InputPassword;

    public RequestData(String emailId, String password) {
        this.InputEmail = emailId;
        this.InputPassword = password;
    }

    public String getEmailId() {
        return InputEmail;
    }

    public String getPassword() {
        return InputPassword;
    }
}
