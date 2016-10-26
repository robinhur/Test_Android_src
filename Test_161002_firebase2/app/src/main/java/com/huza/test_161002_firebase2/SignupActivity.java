package com.huza.test_161002_firebase2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    EditText et_up_id;
    EditText et_up_pw;
    EditText et_up_name;
    EditText et_up_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_up_id = (EditText) findViewById(R.id.text_signup_id);
        et_up_pw = (EditText) findViewById(R.id.text_signup_pw);
        et_up_name = (EditText) findViewById(R.id.text_signup_name);
        et_up_age = (EditText) findViewById(R.id.text_signup_age);

        fireBaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDatabase.getReference();

        mAuth = FirebaseAuth.getInstance();
    }

    public void onSignUp(View v) {
        mAuth.createUserWithEmailAndPassword(et_up_id.getText().toString(), et_up_pw.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Test_161002_firebase2", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "계정 생성에 실패했습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "계정 생성 완료되었습니다\n로그인 해주시기 바랍니다", Toast.LENGTH_SHORT).show();

                            saveUser();

                            finish();
                        }
                    }
                });
    }

    public void saveUser(){
        UserData userData = new UserData(
                et_up_id.getText().toString(),
                et_up_name.getText().toString(),
                mAuth.getCurrentUser().getUid(),
                Integer.valueOf(et_up_age.getText().toString())
        );

        Log.d("Test_161002_firebase2", "signup:" + userData.getUid() + " " + userData.getName() + " " + userData.getEmail() + " " + userData.getAge());

        DatabaseReference fd = databaseReference.child("users").child(userData.getUid());
        fd.setValue(userData);
    }
}
