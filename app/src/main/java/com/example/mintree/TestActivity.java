package com.example.mintree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import myView.DrawTree;

import android.content.pm.ActivityInfo;
import android.os.Bundle;


import com.example.mintree.fragments.Fragment_1;


public class TestActivity extends AppCompatActivity {
    private DrawTree drawTree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.text);
        drawTree=findViewById(R.id.drawTree);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,new Fragment_1()).commit();




    }



}
