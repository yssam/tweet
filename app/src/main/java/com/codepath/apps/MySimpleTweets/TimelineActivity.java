package com.codepath.apps.MySimpleTweets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    //private ListView lvTweets;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        //Create the arraylist (data source)
        tweets = new ArrayList<>();
        //Construct the adapter from data source
        aTweets = new TweetsArrayAdapter(this, tweets);
        //Connect adapter to list view
        rvTweets.setAdapter(aTweets);
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        //Get the client
        client = TwitterApplication.getRestClient(); //singleton client
        populateTimeline();
    }

    //Send an API request to get the timeline json
    //Fill the listview by creating the tweet objects from the json
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            //SUCCESS

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", response.toString());

                //Toast.makeText(TimelineActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();

                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO LISTVIEW
                tweets.addAll(Tweet.fromJSONArray(response));
                aTweets.notifyDataSetChanged();
                //Log.d("DEBUG", aTweets.toString());
            }


            //FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", errorResponse.toString());
                //Toast.makeText(TimelineActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
