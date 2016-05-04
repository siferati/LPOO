package com.example.tiago.lpoo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.lpoo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonPlayListener(View view)
    {
        Intent intent = new Intent(this, GameLoopActivity.class);
        startActivity(intent);
    }
}
