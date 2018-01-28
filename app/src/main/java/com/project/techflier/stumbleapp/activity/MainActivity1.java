//package com.project.techflier.stumbleapp;
//
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//
//import static com.project.techflier.stumbleapp.GoogleSignInActivity.mAuth;
//import static com.project.techflier.stumbleapp.GoogleSignInActivity.mGoogleSignInClient;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private static final String TAG = MainActivity.class.getSimpleName();
//    private static final int RC_SIGN_IN = 007;
//
//    private GoogleApiClient mGoogleApiClient;
//    private ProgressDialog mProgressDialog;
//
//    private Button btnSignOut;
//    private LinearLayout llProfileLayout;
//    private ImageView imgProfilePic;
//    private TextView txtName, txtEmail;
//    //mGoogleSignInClient
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        // btnSignOut = (Button) findViewById(R.id.btn_logout);
//        findViewById(R.id.btn_logout).setOnClickListener(this);
//
//
//
//    }
//
//    private void signOut(){
////        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
////                new ResultCallback<Status>() {
////                    @Override
////                    public void onResult(Status status) {
////                        GlobalState state = ((GlobalState) getApplicationContext());
////                        state.setmGoogleApiClient(mGoogleApiClient);
////                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
////                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        startActivity(login);
////                        finish();
////                    }
////                });
//        System.out.println("-------------------------- main activity signOut called-------------------------------------");
//
//        mAuth.signOut();
//        // Google sign out
//        mGoogleSignInClient.signOut().addOnCompleteListener(this,
//                new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // updateUI(null);
//
//                        Intent login = new Intent(getApplicationContext(), GoogleSignInActivity.class);
//                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(login);
//                        finish();
//                    }
//                });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.btn_logout:
//                signOut();
//                break;
//        }
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
//}