package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.SignInClass;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.UserDataClass;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Dashboard.DashboardBottomNav;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.DatabaseConnection.API;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signin extends AppCompatActivity {
    EditText editTextEmailLogin;
    EditText editTextPasswordLogin;
    Button buttonLogin;

    //Progress Bar
    RelativeLayout relativeLayoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editTextEmailLogin = findViewById(R.id.login_edit_email);
        editTextPasswordLogin = findViewById(R.id.login_edit_password);
        buttonLogin = findViewById(R.id.login_btn_login);

        relativeLayoutProgress = findViewById(R.id.progress_bar_login_rl);

    }

    public void loginCreateAccount(View view) {
        // Create Account Button
        Intent intent = new Intent(Signin.this, SignUP.class);
        startActivity(intent);
        finish();
    }

    public void btnLoginOnClick(View view) {
        // Login Button Check whether the data is valid or not
        if (emailidValidation() && validatePassword()) {
            editTextEmailLogin.setEnabled(false);
            editTextPasswordLogin.setEnabled(false);
            buttonLogin.setEnabled(true);

            // Progress Dialog Here......
            relativeLayoutProgress.setVisibility(View.VISIBLE);
            //SignInConnection(editTextEmailLogin.getText().toString().trim(),editTextPasswordLogin.getText().toString().trim());
            // dataFetchAttempt();
            if(idPassCheck())
            {
                Intent intent = new Intent(Signin.this, DashboardBottomNav.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //CallDataFetching Method

                //set progressbar Off
                relativeLayoutProgress.setVisibility(View.GONE);
                startActivity(intent);
                finish();
            }
        }

        }



    public void loginForgotPassword(View view) {
    }

    public void loginBack(View view) {
        // Back button pressed
        finish();
    }

    private void SignInConnection(String email,String pass)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://street-shopping-2.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API instanceofapi = retrofit.create(API.class);
        Call<UserDataClass> call = instanceofapi.getUserData(email);
        Log.e("The Data passed is : ",email+" : "+pass);

        call.enqueue(new Callback<UserDataClass>() {
            @Override
            public void onResponse(Call<UserDataClass> call, Response<UserDataClass> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Login","password Wrong");

                    Snackbar.make(findViewById(android.R.id.content), "Please Enter Valid Credentials", Snackbar.LENGTH_LONG)
                            .setAction("Okay", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    editTextEmailLogin.setText("");
                                    editTextPasswordLogin.setText("");
                                    //set progressbar Off
                                    relativeLayoutProgress.setVisibility(View.GONE);
                                }
                            })
                            .setActionTextColor(Color.RED)
                            .show();
                }
                if(response.isSuccessful())
                {
                    Log.e("Login","Login Sucessful Signin attempt");
                                    //This has to be added on completion of the sign in attempt
                                    Intent intent = new Intent(Signin.this, DashboardBottomNav.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    //CallDataFetching Method

                                    //set progressbar Off
                                    relativeLayoutProgress.setVisibility(View.GONE);
                                    startActivity(intent);
                                    finish();
                 }
                }

            @Override
            public void onFailure(Call<UserDataClass> call, Throwable t) {
                editTextEmailLogin.setEnabled(true);
                editTextPasswordLogin.setEnabled(true);
                Log.e("btnLoginOnClick: ", "Authentication Failed!!!");

                relativeLayoutProgress.setVisibility(View.GONE);
                View rootView = getWindow().getDecorView().getRootView();
                Snackbar authenticationFailed = Snackbar.make(rootView, "Authentication Failed! No User Found", Snackbar.LENGTH_SHORT);
                authenticationFailed.show();
                buttonLogin.setEnabled(true);
            }
        });
    }

    public void dataFetchAttempt()
    {

        String text = "aakashT@best.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://street-shopping-2.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<UserDataClass> call = instanceofapi.getUserData(text);
        Log.e("Data Sent",text);

        call.enqueue(new Callback<UserDataClass>() {
            @Override
            public void onResponse(Call<UserDataClass> call, Response<UserDataClass> response) {
                if(response.isSuccessful())
                {
                   UserDataClass userDataClass = response.body();
                    Log.e("Data Got is : ",userDataClass.getEmailId() +" "+userDataClass.getPassword());

//                    if(userDataClass != null)
//                    {
//                        //Perform Shared Preference
////                        sharedPrefManager  = new SharedPrefManager(Login.this);
////                        sharedPrefManager.setEmail(userDataClass.getEmailID());
////                        sharedPrefManager.setAccPassword(userDataClass.getAccPass());
////                        sharedPrefManager.setRolePreference(userDataClass.getUserRole());
////                        sharedPrefManager.setUserId(userDataClass.getUserRole());
////                        sharedPrefManager.setUserName(userDataClass.getUsername());
////                        sharedPrefManager.setImage(userDataClass.getProfileImage());
//
//                        //set progressbar Off
//                        relativeLayoutProgress.setVisibility(View.GONE);
//
//                        Intent intent = new Intent(Login.this, SystemDashboard.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else
//                    {
//                        relativeLayoutProgress.setVisibility(View.GONE);
//                        View rootView = getWindow().getDecorView().getRootView();
//                        Snackbar authenticationFailed = Snackbar.make(rootView, "Oops! Something went wrong", Snackbar.LENGTH_SHORT);
//                        authenticationFailed.show();
//                        editTextEmailLogin.setEnabled(true);
//                        editTextPasswordLogin.setEnabled(true);
//                        buttonLogin.setEnabled(true);
//                    }
                }

            }

            @Override
            public void onFailure(Call<UserDataClass> call, Throwable t) {
                editTextEmailLogin.setEnabled(true);
                editTextPasswordLogin.setEnabled(true);
                Log.e("Not fetched: ", "Authentication Failed!!!");

                relativeLayoutProgress.setVisibility(View.GONE);
                View rootView = getWindow().getDecorView().getRootView();
                Snackbar authenticationFailed = Snackbar.make(rootView, "Authentication Failed", Snackbar.LENGTH_SHORT);
                authenticationFailed.show();
                buttonLogin.setEnabled(true);
            }
        });




    }




    private boolean idPassCheck()
    {
        if(editTextEmailLogin.getText().toString().equals("aakashT@best.com") && editTextPasswordLogin.getText().toString().equals("87654321"))
        {
            return  true;
        }
        else {
            relativeLayoutProgress.setVisibility(View.GONE);
            Snackbar.make(findViewById(android.R.id.content), "Please Enter Valid Credentials", Snackbar.LENGTH_LONG)
                    .setAction("Okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editTextEmailLogin.setText("");
                            editTextPasswordLogin.setText("");
                            //set progressbar Off
                            relativeLayoutProgress.setVisibility(View.GONE);
                            buttonLogin.setEnabled(true);
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
            editTextEmailLogin.setEnabled(true);
            editTextPasswordLogin.setEnabled(true);
            editTextEmailLogin.setText("");
            editTextPasswordLogin.setText("");
            return false;
        }
    }


    private boolean emailidValidation()
    {
        String emailtext = editTextEmailLogin.getText().toString().trim();


        if (TextUtils.isEmpty(emailtext)) {
            editTextEmailLogin.setError("Enter Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            editTextEmailLogin.setError("Email Address Should be Valid");
            return false;
        } else {
            editTextEmailLogin.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = editTextPasswordLogin.getText().toString();

        if (TextUtils.isEmpty(password)) {
            View rootView = getWindow().getDecorView().getRootView();
            Snackbar notEmptyErrorMsg = Snackbar.make(rootView, "Password Cannot Be Empty.", Snackbar.LENGTH_SHORT);
            notEmptyErrorMsg.show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}