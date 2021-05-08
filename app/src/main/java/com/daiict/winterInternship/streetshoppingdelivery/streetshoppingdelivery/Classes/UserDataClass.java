package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes;

import android.service.autofill.UserData;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class UserDataClass {
    @SerializedName("userId")
    private String userId;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("emailId")
    private String emailId;

    @SerializedName("password")
    private String password;

    @SerializedName("userRole")
    private int userRole;

    @SerializedName("profileImage")
    private String profileImage;

    @SerializedName("contactNo")
    private long contactNo;

    @SerializedName("createdAt")
    private Timestamp createdAt;

    @SerializedName("updatedAt")
    private Timestamp updatedAt;

    public UserDataClass(String userId,String firstName,String lastName,String emailId,String password,int userRole,String profileImage,long contactNo)
    {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.userId = userId;
        this.profileImage = profileImage;
        this.contactNo = contactNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public int getUserRole() {
        return userRole;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public long getContactNo() {
        return contactNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
