package com.huza.test_161002_firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private UserData signed_user;
    private UserData foundUser;

    TextView tv;
    TextView tv2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);

        signed_user = new UserData();

        fireBaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDatabase.getReference();

        Log.d("Test_161002_firebase2", "MainActivity Start!!");

        signed_user.setUid(getIntent().getStringExtra("loggedUser"));

        databaseReference.child("users").child(signed_user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Test_161002_firebase2", "found user : " + dataSnapshot.toString());

                signed_user.setEmail(dataSnapshot.child("email").getValue().toString());
                signed_user.setName(dataSnapshot.child("name").getValue().toString());
                signed_user.setAge(Integer.valueOf(dataSnapshot.child("age").getValue().toString()));

                Log.d("Test_161002_firebase2", signed_user.getUid() + " : " + signed_user.getName()+ " : " + signed_user.getEmail() + " : " + signed_user.getAge() );

                tv.setText(signed_user.getName() + "님 환영합니다\n"+signed_user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    public void signout(View v) {
        FirebaseAuth.getInstance().signOut();
        Log.d("Test_161002_firebase2", "signed_out");

        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void btn_delete_clicked(View v) {
        Log.d("Test_161002_firebase2", "btn_delete_clicked");

        databaseReference.child("times").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("Test_161002_firebase2", "found email for delete : " + dataSnapshot.toString());
                        Log.d("Test_161002_firebase2", "childrenCount for delete : " + dataSnapshot.getChildrenCount());

                        if (dataSnapshot.getChildrenCount()!=0){
                            for (DataSnapshot children : dataSnapshot.getChildren()){
                                if (children.getKey().toString().equals(foundUser.getUid())){
                                    Log.d("Test_161002_firebase2", "found users uid : " + children.getKey().toString());
                                    Log.d("Test_161002_firebase2", "found users time value : " + children.getValue().toString());
                                    children.getRef().setValue(null);
                                    return;
                                }
                            }
                            Toast.makeText(MainActivity.this, "시간 정보가 없습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "유저 정보를 찾을 수 없습니다", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void btn_find_clicked(View v) {
        Log.d("Test_161002_firebase2", "btn_find_clicked");

        databaseReference.child("users").orderByChild("email").equalTo(editText.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.d("Test_161002_firebase2", "found email : " + dataSnapshot.toString());
                        Log.d("Test_161002_firebase2", "childrenCount : " + dataSnapshot.getChildrenCount());

                        if (dataSnapshot.getChildrenCount()!=0){
                            for (DataSnapshot children : dataSnapshot.getChildren()){
                                foundUser = children.getValue(UserData.class);
                                Log.d("Test_161002_firebase2", "found user name: " + foundUser.getName());
                                tv2.setText("대상 : " + foundUser.getName());
                            }
                        } else {
                            tv2.setText("검색실패, Email을 확인해주세요");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });

    }
}
