package com.kot.apps.androidcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Bundle bundle = getIntent().getExtras();
        String owoc = bundle.getString("dict").toString();
        Log.d("dict", owoc); // nazwa powinna byc

        TextView nazwa = findViewById(R.id.nazwa);

        nazwa.setText(owoc);

    }
}