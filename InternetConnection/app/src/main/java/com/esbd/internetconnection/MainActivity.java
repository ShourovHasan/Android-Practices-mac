package com.esbd.internetconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView showInternet;
    Button internetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showInternet = findViewById(R.id.showInternet);
        internetBtn = findViewById(R.id.internetBtn);

        internetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){
                    showInternet.setText("Internet is Connected");
                } else {
                    showInternet.setText("");
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("For Internet Connection test")
                            .setMessage("Please connect your internet")
                            .show();
                }
            }
        });


    }
}