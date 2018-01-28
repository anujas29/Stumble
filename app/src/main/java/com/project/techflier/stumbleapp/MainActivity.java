package com.project.techflier.stumbleapp;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.project.techflier.stumbleapp.activity.CategoryActivity;
import com.project.techflier.stumbleapp.data.StumbleContract;
import com.project.techflier.stumbleapp.fragment.ArticleFragment;
import com.project.techflier.stumbleapp.fragment.FavoriteFragment;
import com.project.techflier.stumbleapp.fragment.MediabytesFragment;
import com.project.techflier.stumbleapp.fragment.SignOutFragment;
//import com.project.techflier.stumbleapp.fragment.VideosFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;


    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // tags used to attach the fragments
    public static final String TAG_SHARE = "share";
    public static final String TAG_ARTICLE = "article";
    public static final String TAG_MEDIA = "mediabyte";
    public static final String TAG_ACTIVIY = "Activity";
    public static final String TAG_LIKES = "Likes";
    public static final String TAG_LIST = "list";
    public static final String TAG_TECHFLIERS = "Activity";
    public static final String TAG_SUBMIT = "Activity";
    public static final String TAG_LOGOUT = "logout";
    public static final String TAG_AB = "about";
    public static String CURRENT_TAG = TAG_ARTICLE;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    public static int navItemIndex = 1;

    public Fragment fragment;
    TextView tvSave;

    private Fragment sendFragment;

//    @BindView(R.id.spinner)
//    Spinner mySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        loadHomeFragment();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
//                R.layout.custom_spinner_item,
//                getResources().getStringArray(R.array.names));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(myAdapter);
//
//        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,
//                        mySpinner.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    private void loadHomeFragment() {
        selectNavMenu();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        } else {
            fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commit();
            drawer.closeDrawers();
        }
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {

            case 1:
                System.out.println("--------------- call 1 ----------");
                sendFragment = new ArticleFragment();
                break;
            case 2:
                System.out.println("--------------- call 2 -------------");
                sendFragment = new MediabytesFragment();
                break;
            case 4:
                System.out.println("--------------- call 3 -------------------------");
                sendFragment = new FavoriteFragment();
                break;

            case 6:
                System.out.println("--------------- call 6 -----------------");
                //sendFragment = new SignOutFragment();
                // launch new intent instead of loading fragment
                startActivity(new Intent(MainActivity.this, SignOutFragment.class));
                drawer.closeDrawers();
                break;

            default:
                sendFragment = new MediabytesFragment();
        }
        return sendFragment;
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        System.out.println("--------------- onNavigationItemSelected--------------------------"+menuItem);
        System.out.println("--------------- onNavigationItemSelected--------------------------"+menuItem);
        switch (menuItem.getItemId()) {
            case R.id.nav_article:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ARTICLE;
                break;
            case R.id.nav_mediabyte:
                navItemIndex = 2;
                CURRENT_TAG = TAG_MEDIA;
                break;
            case R.id.nav_likes:
                navItemIndex = 4;
                CURRENT_TAG = TAG_LIKES;
                System.out.println("--------------- onNavigationItemSelected CURRENT_TAG -------"+CURRENT_TAG);
                break;
            case R.id.nav_logout:
                System.out.println("--------------- call nav_logout -------------");
//                navItemIndex = 6;
//                CURRENT_TAG = TAG_LOGOUT;
                startActivity(new Intent(MainActivity.this, SignOutFragment.class));
                drawer.closeDrawers();
            default:
                navItemIndex = 0;
        }
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);

        loadHomeFragment();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.main, menu);
//
//        MenuItem item = menu.findItem(R.id.spinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        return super.onCreateOptionsMenu(menu);
    }
}