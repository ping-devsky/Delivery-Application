package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes;

import android.content.Context;
import android.content.SharedPreferences;

//except commit use apply as it store data in background process


public class SharedPreferenceManager {
    public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
    private final SharedPreferences sharedpreferencesData;
    public SharedPreferenceManager(Context context) {
        sharedpreferencesData = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    //All setters method
    public void setEmail(String email)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("email_id", email);
        editor.apply();
    }

    public void setAccPassword(String password)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("password", password);
        editor.apply();
    }

    public void setFirstName(String fname)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("firstName", fname);
        editor.apply();
    }

    public void setLastName(String lname)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("lastName", lname);
        editor.apply();
    }

    public void setContact(long contact)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putLong("contactNo", contact);
        editor.apply();
    }

    public void setProfile(String image)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("profileImage", image);
        editor.apply();
    }

    public void setUserid(String id)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("userId", id);
        editor.apply();
    }

    public void setUserRole(int role)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putInt("userRole", role);
        editor.apply();
    }











    //All getters Methods

    public String getEmail()
    {
        return sharedpreferencesData.getString("email_id", "");
    }
    public String getAccPassword()
    {
        return sharedpreferencesData.getString("password", "");
    }
    public String getFirstname()
    {
        return sharedpreferencesData.getString("firstName", "");
    }

    public String getLastName()
    {
        return sharedpreferencesData.getString("lastName", "");
    }
    public long getContact()
    {
        return sharedpreferencesData.getLong("contactNo", -1);
    }
    public String getProfile()
    {
        return sharedpreferencesData.getString("profileImage", "");
    }
    public String getUserId()
    {
        return sharedpreferencesData.getString("userId", "");
    }
    public int getRole()
    {
        return sharedpreferencesData.getInt("userRole", -1);
    }

}
