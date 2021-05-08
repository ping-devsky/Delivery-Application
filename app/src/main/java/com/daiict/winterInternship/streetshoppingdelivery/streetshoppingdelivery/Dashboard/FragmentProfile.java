package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.Classes.SharedPreferenceManager;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn.Signin;


public class FragmentProfile extends Fragment {
    ImageView logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        logout = view.findViewById(R.id.profile_btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutButtonPressed(v);
            }
        });
        return view;
    }
    public void logoutButtonPressed(View view) {
        AlertDialog.Builder Builder = new AlertDialog.Builder(getContext());
        View view_pop = getLayoutInflater().inflate(R.layout.popup_logout, null);

        final Button confirm, cancel;
        //hooks
        confirm = view_pop.findViewById(R.id.settings_pop_btn_confirm_logout);
        cancel = view_pop.findViewById(R.id.settings_pop_btn_reject_logout);

        //setting the view
        Builder.setView(view_pop);
        final Dialog dialog = Builder.create();
        dialog.show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager sharedPrefManager = new SharedPreferenceManager(getContext());
                sharedPrefManager.setAccPassword(null);
                sharedPrefManager.setEmail(null);

                dialog.dismiss();
                Intent intent = new Intent(getActivity(), Signin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}