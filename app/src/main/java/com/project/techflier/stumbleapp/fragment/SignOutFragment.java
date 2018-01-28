package com.project.techflier.stumbleapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.techflier.stumbleapp.GoogleSignInActivity;
import com.project.techflier.stumbleapp.R;

/**
 * Created by anuja on 1/8/2018.
 */

public class SignOutFragment extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_in);

        System.out.println("---------------- SignOutFragment ----------------------------------------");
        Intent login = new Intent(SignOutFragment.this, GoogleSignInActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        login.putExtra("LOGOUT", true);
        startActivity(login);

        finish();
    }


}
