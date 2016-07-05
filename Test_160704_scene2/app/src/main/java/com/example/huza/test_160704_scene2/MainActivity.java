package com.example.huza.test_160704_scene2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ViewGroup rootContainer;
    Scene scene1;
    Scene scene2;
    Transition transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootContainer = (ViewGroup) findViewById(R.id.root_container);

        transition = TransitionInflater.from(this).inflateTransition(R.transition.transition);

        scene1 = Scene.getSceneForLayout(rootContainer, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(rootContainer, R.layout.scene2, this);
        scene1.enter();
    }

    public void gotoSCENE2(View v) {
        TransitionManager.go(scene2, transition);
    }
    public void gotoSCENE1(View v) {
        TransitionManager.go(scene1, transition);
    }
}
