package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Dashboard.DashboardBottomNav;
import com.google.android.material.snackbar.Snackbar;

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
            buttonLogin.setEnabled(false);

            // Progress Dialog Here......
            relativeLayoutProgress.setVisibility(View.VISIBLE);

            Intent intent = new Intent(Signin.this, DashboardBottomNav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void loginForgotPassword(View view) {
    }

    public void loginBack(View view) {
        // Back button pressed
        finish();
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