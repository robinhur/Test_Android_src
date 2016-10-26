package com.example.huza.test_161005_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    EditText et;
    Button btn;

    String name;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        lv = (ListView) findViewById(R.id.listView);
        et = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);

        name = "user" + new Random().nextInt(10000);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        lv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(name, et.getText().toString());
                reference.child("message").push().setValue(chatData);
                et.setText("");
            }
        });

        reference.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData newData = dataSnapshot.getValue(ChatData.class);
                adapter.add(newData.getId() + " : " + newData.getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void logout(View v) {
        FirebaseAuth.getInstance().signOut();

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
