package com.project.techflier.stumbleapp.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.fragment.MediaBytesDetailFragment;

import java.lang.reflect.Method;

/**
 * Created by anuja on 1/9/2018.
 */

public class MediaBytesDetails extends Activity {

    WebView mWebView;
    private boolean mIsPaused = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_detail_mediabyte);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        setContentView(R.layout.activity_detail);
        System.out.println("-------------------------------- t0-----");

        if(savedInstanceState == null)
        {
            System.out.println("-------------------------------- t1-----");

            Bundle arguments = new Bundle();
            System.out.println("-------------------------------- t2)-----");
            arguments.putParcelable(MediaBytesDetailFragment.ARG_MEDIABYTES,
                    getIntent().getParcelableExtra(Intent.EXTRA_TEXT));
            System.out.println("-------------------------------- t3-----");
            MediaBytesDetailFragment fragment = new MediaBytesDetailFragment();
            System.out.println("-------------------------------- t4-----");
            fragment.setArguments(arguments);
            System.out.println("-------------------------------- t5-----");
            getFragmentManager().beginTransaction().add(R.id.mediabyte_detail_container, fragment).commit();

        }

    }
}
