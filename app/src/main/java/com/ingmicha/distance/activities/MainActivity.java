package com.ingmicha.distance.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ingmicha.distance.R;
import com.ingmicha.distance.fragment.DistanceFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new DistanceFragment())
                    .commit();
        }

    }
}
