package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.UserDataClass;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Dashboard.DashboardBottomNav;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.DatabaseConnection.API;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUP extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextContact;
    EditText editTextPassword;
    EditText editTextFname;
    EditText editTextLname;
    TextView errorDetails;

    private static final Pattern ALPHABET_ONLY = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p);

        editTextEmail = findViewById(R.id.txtv_signup_email);
        editTextContact = findViewById(R.id.txtv_signup_contact);
        editTextPassword = findViewById(R.id.txtv_signup_pass);
        editTextFname = findViewById(R.id.txtv_signup_fname);
        editTextLname = findViewById(R.id.txtv_signup_lname);
        errorDetails = findViewById(R.id.txtPasswordMsg);
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextPassword.getText().length() <= 0) {
                    errorDetails.setText(getResources().getString(R.string.password_message));
                    errorDetails.setVisibility(View.VISIBLE);
                }
                if (!PASSWORD_PATTERN.matcher(editTextPassword.getText().toString()).matches()) {
                    errorDetails.setText(getResources().getString(R.string.signup_password_pattern));
                    errorDetails.setVisibility(View.VISIBLE);
                } else {
                    errorDetails.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void backBtnSignUp(View view) {
        finish();
    }


    public void loginBtnSignUp(View view) {
       // signupData();
        Intent intent = new Intent(this, Signin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    public void registerButtonClicked(View view) {
        if (validateFname() && validateLname() && emailidValidation() && validateContact() && validatePassword()) {
            Snackbar.make(findViewById(android.R.id.content), "Signed Up Successful ! Login... ", Snackbar.LENGTH_LONG)
                    .setAction("Okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SignUP.this, Signin.class);
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            editTextContact.setText("");
                            editTextFname.setText("");
                            editTextLname.setText("");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
        } else {

        }
    }


    private boolean emailidValidation() {
        String emailtext = editTextEmail.getText().toString().trim();


        if (TextUtils.isEmpty(emailtext)) {
            editTextEmail.setError("Enter Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            editTextEmail.setError("Email Address Should be Valid");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    private boolean validateFname() {
        String fname = editTextFname.getText().toString().trim();
        /*
         *If First Name is Empty or First Name contains not only Alphabets then return false
         * Else Return True
         */
        if (TextUtils.isEmpty(fname)) {
            editTextFname.setError("Enter First Name");
            return false;
        } else if (!ALPHABET_ONLY.matcher(fname).matches()) {
            editTextFname.setError("First Name contains only Alphabets");
            return false;
        } else {
            editTextFname.setError(null);
            return true;
        }
    }

    private boolean validateLname() {
        String lname = editTextLname.getText().toString().trim();
        /*
         *If First Name is Empty or First Name contains not only Alphabets then return false
         * Else Return True
         */
        if (TextUtils.isEmpty(lname)) {
            editTextLname.setError("Enter Last Name");
            return false;
        } else if (!ALPHABET_ONLY.matcher(lname).matches()) {
            editTextLname.setError("Last Name contains only Alphabets");
            return false;
        } else {
            editTextLname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = editTextPassword.getText().toString();

        /*
         * If password isn't matches the given password format or expression then return false
         * Else return true
         */

        if (TextUtils.isEmpty(password)) {
            errorDetails.setText(getResources().getString(R.string.signup_password_error));
            errorDetails.setVisibility(View.VISIBLE);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            errorDetails.setText(getResources().getString(R.string.password_message));
            errorDetails.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorDetails.setVisibility(View.GONE);
            return true;
        }

    }

    private boolean validateContact() {
        String contact = editTextContact.getText().toString().trim();
        /*
         *If First Name is Empty or First Name contains not only Alphabets then return false
         * Else Return True
         */
        if (TextUtils.isEmpty(contact)) {
            editTextContact.setError("Enter Contact Number");
            return false;
        } else if (contact.length() < 10) {
            editTextContact.setError("Contact Number is not Valid");
            return false;
        } else {
            editTextContact.setError(null);
            return true;
        }

    }

    private void signupData() {
            String text = "aakashT@best.com";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://street-shopping-2.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API instanceofapi = retrofit.create(API.class);
            Call<UserDataClass> call = instanceofapi.getUserData(text.trim());
            call.enqueue(new Callback<UserDataClass>() {
                @Override
                public void onResponse(Call<UserDataClass> call, Response<UserDataClass> response) {
                    if(response.isSuccessful()) {
                        UserDataClass userDataClass = response.body();
                        Log.e("Data Got is : ", userDataClass.getEmailId() + " " + userDataClass.getPassword());
                    }
                }

                @Override
                public void onFailure(Call<UserDataClass> call, Throwable t) {
                    Log.e("Not fetched: ", "Authentication Failed!!!");

                }
            });
        }
    }

