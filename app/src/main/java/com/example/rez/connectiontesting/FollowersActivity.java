package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowersActivity extends AppCompatActivity implements CallBackMe {
  TextView followersName;
    String url;
    String profileUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
         followersName=(TextView)findViewById(R.id.FollowerName);

        Intent i=getIntent();
        url=i.getStringExtra("url");
        //Toast.makeText(this,"", Toast.LENGTH_SHORT).show();

        JsonRetriever.RetrieveFromURL(this, url, this);
    }

    public void buttonclicked(View v){
        if (profileUrl == "")
        {
            Toast.makeText(this, "Please Try Again later", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("url", profileUrl);
            startActivity(i);
        }
    }

    @Override
    public void CallThis(String jsonText) {

        try {

            JSONArray json=new JSONArray(jsonText);
            JSONObject myfirstFollower = json.getJSONObject(1);
            followersName.setText(myfirstFollower.getString("login"));

            profileUrl = myfirstFollower.getString("url");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
