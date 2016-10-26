package com.huza.test_161002_firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText et_id;
    EditText et_pw;
    Button btn_login;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Test_161002_firebase2", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("Test_161002_firebase2", "onAuthStateChanged:signed_out");
                }
            }
        };

        et_id = (EditText) findViewById(R.id.text_id);
        et_pw = (EditText) findViewById(R.id.text_pw);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
    }

    private void checkAuth() {
        if (mAuth.getCurrentUser() != null) {
            Log.d("Test_161002_firebase2", "Already logged in : " + mAuth.getCurrentUser().getUid());
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("loggedUser", mAuth.getCurrentUser().getUid());
            startActivity(i);
            finish();
        } else {
            Log.d("Test_161002_firebase2", "There's nothing");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        checkAuth();
    }

    public void onLogIn(View v) {
        mAuth.signInWithEmailAndPassword(et_id.getText().toString(), et_pw.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Test_161002_firebase2", "signinUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("loggedUser", mAuth.getCurrentUser().getUid());
                            startActivity(i);
                            finish();
                        }
                    }
                });


    }

    public void openSignUp(View v) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }
}
