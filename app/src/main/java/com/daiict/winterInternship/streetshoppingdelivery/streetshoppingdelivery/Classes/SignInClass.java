package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes;

import com.google.gson.annotations.SerializedName;

public class SignInClass {
    @SerializedName("")
    private String emailID;
    @SerializedName("")
    private String password;

    public SignInClass(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }
}
