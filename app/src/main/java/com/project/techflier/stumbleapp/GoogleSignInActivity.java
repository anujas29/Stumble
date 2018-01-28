package com.project.techflier.stumbleapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by USER on 19-12-2017.
 */

public class GoogleSignInActivity extends AppCompatActivity  implements
        View.OnClickListener {

private static final String TAG = "GoogleActivity";
private static final int RC_SIGN_IN = 9001;

// [START declare_auth]
public static FirebaseAuth mAuth;
// [END declare_auth]

public static GoogleSignInClient mGoogleSignInClient;
private TextView mStatusTextView;
private TextView mDetailTextView;


   // private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private SignInButton btnSignIn;
    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
    btnSignOut = (Button) findViewById(R.id.btn_sign_out);

    if (getIntent().getBooleanExtra("LOGOUT", false))
    {
        System.out.println("---------------------- GoogleSignInClient if------------------------------");
        signOut();
    }

        // Button listeners
    btnSignIn.setOnClickListener(this);
//    btnSignOut.setOnClickListener(this);
//    btnRevokeAccess.setOnClickListener(this);

     GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build();

     mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
     mAuth = FirebaseAuth.getInstance();

}


  @Override
   public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        }

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
        // Google Sign In was successful, authenticate with Firebase
        GoogleSignInAccount account = task.getResult(ApiException.class);
        firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
        // Google Sign In failed, update UI appropriately
        Log.w(TAG, "Google sign in failed", e);
        // [START_EXCLUDE]
        updateUI(null);
        // [END_EXCLUDE]
        }
        }
        }

   private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        //showProgressDialog();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
   @Override
   public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
        // Sign in success, update UI with the signed-in user's information
        Log.d(TAG, "signInWithCredential:success");
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
        } else {
        // If sign in fails, display a message to the user.
        Log.w(TAG, "signInWithCredential:failure", task.getException());
        Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
        updateUI(null);
         }
        }
        });
   }

   private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        }


   private void signOut() {
        // Firebase sign out
       System.out.println("---------------------- GoogleSignInClient signOut()------------------------------");
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
        new OnCompleteListener<Void>() {
   @Override
    public void onComplete(@NonNull Task<Void> task) {
        updateUI(null);
        }
        });
        }



   public void updateUI(FirebaseUser user) {
       // hideProgressDialog();
        if (user != null) {
//        mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//        mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

//        findViewById(R.id.btn_sign_in).setVisibility(View.GONE);
//        findViewById(R.id.btn_sign_out).setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        } else {
       // mStatusTextView.setText(R.string.signed_out);
       // mDetailTextView.setText(null);

        findViewById(R.id.btn_sign_in).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_sign_out).setVisibility(View.GONE);
        }
        }

@Override
public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign_in) {
        signIn();
        }
//        else if (i == R.id.btn_sign_out) {
//        signOut();
//        }
        }
        }