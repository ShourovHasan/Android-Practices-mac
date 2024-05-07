package com.esbd.layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        =====

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);


        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://esbd.xyz/complex.json";

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    String name = response.getString("name");
                    String age = response.getString("age");
                    JSONArray videos = response.getJSONArray("videos");

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Name: ").append(name).append("\n");
                    stringBuilder.append("Age: ").append(age).append("\n\n");
                    for (int i = 0; i < videos.length(); i++) {
                        JSONObject video = videos.getJSONObject(i);
                        stringBuilder.append("Video Title: ").append(video.getString("title")).append("\n");
                        stringBuilder.append("Video ID: ").append(video.getString("video_id")).append("\n\n");
                    }

                    textView.setText(stringBuilder.toString());

                } catch (JSONException e) {
                    Log.d("serverError", "JSON Parsing error: " + e.getMessage());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                // Check if the error has a network response
                if (error.networkResponse != null) {
                    Log.d("serverError", "Error Response code: " + error.networkResponse.statusCode);
                }
                Log.d("serverError", "Error: " + error.toString());
                // If there's a message or cause, log that too
                if (error.getMessage() != null) {
                    Log.d("serverError", "Error message: " + error.getMessage());
                }
                if (error.getCause() != null) {
                    Log.d("serverError", "Cause: " + error.getCause());
                }
            }

        });

// Add the request to the RequestQueue.
                queue.add(jsonObjectRequest);






//        =====
    }
}