package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.DatabaseConnection;

import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.SignInClass;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.UserDataClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    //Signin API
//    @FormUrlEncoded
//    @POST("signin")
//    Call<UserDataClass> signInAttempt(@Field("emailId") String email,@Field("password") String password);

    //Get User All The Details by emailid
    @GET("user/details/email/{emailid}")
    Call<UserDataClass> getUserData(@Path("emailid") String email_id);

    @GET("user/users")
    Call<ArrayList<UserDataClass>> getAllUser();
}
