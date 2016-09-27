package com.example.wuht.spinningwheel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private WheelView mWheelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWheelView = (WheelView) findViewById(R.id.view);

        mWheelView.starV();
    }
}
