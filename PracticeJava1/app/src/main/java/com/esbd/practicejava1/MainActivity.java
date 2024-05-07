package com.esbd.practicejava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imageCover;
    Button bGone, bShow, bGoneAll;

    LinearLayout layoutRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        starting code for functionality
        imageCover = findViewById(R.id.imageCover);
        bShow = findViewById(R.id.bShow);
        bGone = findViewById(R.id.bGone);
        bGoneAll = findViewById(R.id.bGoneAll);
        layoutRoot = findViewById(R.id.layoutRoot);

        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageCover.setVisibility(View.VISIBLE);
            }
        });
        bGone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageCover.setVisibility(View.GONE);
            }
        });
        bGoneAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutRoot.setVisibility(View.GONE);
            }
        });






    }
}