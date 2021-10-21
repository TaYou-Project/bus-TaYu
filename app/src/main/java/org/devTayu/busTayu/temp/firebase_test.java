package org.devTayu.busTayu.temp;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class firebase_test extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }
}
