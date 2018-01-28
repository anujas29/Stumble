package com.project.techflier.stumbleapp.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.techflier.stumbleapp.R;

/**
 * Created by anuja on 1/16/2018.
 */

public class CategoryActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_item);

        System.out.println("--------------------- CategoryActivity called -----------------------------------------");
    }
}
