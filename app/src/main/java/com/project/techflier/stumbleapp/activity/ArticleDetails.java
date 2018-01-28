package com.project.techflier.stumbleapp.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.project.techflier.stumbleapp.MainActivity;
import com.project.techflier.stumbleapp.R;
import com.project.techflier.stumbleapp.data.StumbleContract;
import com.project.techflier.stumbleapp.fragment.ArticleDetailFragment;
import com.project.techflier.stumbleapp.fragment.FavoriteFragment;
import com.project.techflier.stumbleapp.model.ArticleDataModel;
import com.project.techflier.stumbleapp.rest.RetrofitDataInterface;


import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anuja on 1/17/2018.
 */

public class ArticleDetails extends AppCompatActivity  {

    private ArticleDataModel data;
    private String baseURL = "https://www.techflier.com";
    private ArticleDataModel articleData = new ArticleDataModel();
    WebView webView;
    @BindView(R.id.article_toolbar)
    Toolbar toolbar;
    private boolean favorite;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_detail);
        setContentView(R.layout.fragment_detail_mediabyte);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_share:
                                System.out.println("--------------------------------action_share t1-----");
                                break;

                            case R.id.action_thumb_up:
                                System.out.println("--------------------------------action_thumb_up t1-----");
                                break;

                            case R.id.action_thumb_down:
                                System.out.println("--------------------------------action_thumb_down t1-----");
                                break;

                        }
                        return true;
                    }
                });

        if (savedInstanceState == null) {
            System.out.println("--------------------------------ArticleDetails t1-----");

            data = Parcels.unwrap(getIntent().getParcelableExtra(Intent.EXTRA_TEXT));
            webView = (WebView) findViewById(R.id.postwebview);
            getRetrofit(data.getArticle_id().toString());
            System.out.println("--------------------------------ArticleDetails t4-----");
            isFavorite();


//            String html = data.getArticle_content().getYoutubeURL();
//            System.out.println("============= html ================" + data.getArticle_link());
//
//
//            webView.getSettings().setJavaScriptEnabled(true);
//            webView.loadUrl(data.getArticle_link());
//
//            // webView.loadData(data.getArticle_content().getYoutubeURL().toString(),"text/html","UTF-8");
//
//            // to open webview inside app -- otherwise It will open url in device browser
//            webView.setWebViewClient(new WebViewClient());


        }
    }

    public void getRetrofit(String ID){

        System.out.println("--------------------------------ArticleDetails t2-----");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitDataInterface service = retrofit.create(RetrofitDataInterface.class);

        Call<ArticleDataModel> call = service.getArticleById(ID);
        call.enqueue(new Callback<ArticleDataModel>() {
            @Override
            public void onResponse(Call<ArticleDataModel> call, Response<ArticleDataModel> response) {
                articleData  = response.body();
                updateView(articleData);

            }

            @Override
            public void onFailure(Call<ArticleDataModel> call, Throwable t) {

            }
        });


    }
    public void updateView(ArticleDataModel articleDatastr )
    {
        System.out.println("--------------------------------ArticleDetails t3-----");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(articleDatastr.getArticle_link());

        // webView.loadData(data.getArticle_content().getYoutubeURL().toString(),"text/html","UTF-8");

        // to open webview inside app -- otherwise It will open url in device browser
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
       // mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fab, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isFavorite() {

        Cursor articleCursor = getApplicationContext().getContentResolver().query(
                StumbleContract.StumbleEntry.CONTENT_URI,
                new String[]{StumbleContract.StumbleEntry.COLUMN_ARTICLE_ID},
                StumbleContract.StumbleEntry.COLUMN_ARTICLE_ID + "=" + data.getArticle_id().toString(),
                null,
                null
        );
        if (articleCursor != null && articleCursor.moveToFirst()) {

            articleCursor.close();
            System.out.println("---------------- fab true --------");
            return favorite = true;
        } else {
            System.out.println("---------------- fab false --------");
            return favorite = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_fab:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                if(favorite)
                {
                    item.setIcon(R.drawable.ic_favorite);
                    //     displayToast(getString(R.string.fav_added));
                    favorite=false;
                    Toast.makeText(this, "Remove from favorite...", Toast.LENGTH_SHORT)
                            .show();
                }
                else
                {
                    item.setIcon(R.drawable.ic_favorite_black);
                    favorite=true;
                    markAsFavorite();
                    Toast.makeText(this, "Added As favorite...", Toast.LENGTH_SHORT)
                            .show();

                }
                break;
            case android.R.id.home:

                Intent intent = new Intent(ArticleDetails.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // Log.d(LOG_TAG, "onPrepareOptionsMenu");
        if(favorite)
        {
            menu.findItem(R.id.action_fab).setIcon(R.drawable.ic_favorite_black);
            favorite = true;

        }

        return super.onPrepareOptionsMenu(menu);

    }

    public void markAsFavorite()
    {
        ContentValues articleValues = new ContentValues();
        articleValues.put(StumbleContract.StumbleEntry.COLUMN_ARTICLE_ID,data.getArticle_id());
        articleValues.put(StumbleContract.StumbleEntry.COLUMN_TITLE,data.getArticle_title().getRendered());
//        articleValues.put(StumbleContract.StumbleEntry.COLUMN_CONTENT,data.getArticle_content().getYoutubeURL());
//        articleValues.put(StumbleContract.StumbleEntry.COLUMN_DATE,data.getArticle_date());
//        articleValues.put(StumbleContract.StumbleEntry.COLUMN_LINK,data.getArticle_link());
        articleValues.put(StumbleContract.StumbleEntry.COLUMN_MEDIA,data.getMfeatured_media());
        System.out.println("----- start data inserted:::-----");
        getApplicationContext().getContentResolver().insert(StumbleContract.StumbleEntry.CONTENT_URI,articleValues);
        System.out.println("----- done data insertion:::-----");
        System.out.println("----- done data insertion:::-----");
        System.out.println("----- done data insertion:::-----");
    }

    public void removeFromFavorite()
    {

    }


}

