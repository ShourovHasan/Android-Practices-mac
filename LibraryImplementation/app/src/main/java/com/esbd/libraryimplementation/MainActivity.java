package com.esbd.libraryimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button ButtonView, ButtonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);
        ButtonView = findViewById(R.id.ButtonView);
        ButtonPrevious = findViewById(R.id.ButtonPrevious);

        Picasso.get()
                .load("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg")
                .resize(100, 50)
                .into(imageView);
        ButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get()
                        .load("https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
                        .resize(100, 50)
                        .placeholder(R.drawable.logo)
                        .into(imageView);
            }
        });
        ButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get()
                        .load("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Procesing.jpg")
                        .resize(100, 50)
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.error)
                        .into(imageView);
            }
        });
    }
}