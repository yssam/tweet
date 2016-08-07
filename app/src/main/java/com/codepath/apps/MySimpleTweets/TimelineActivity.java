package com.codepath.apps.MySimpleTweets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity{

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    //private ListView lvTweets;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    @BindView(R.id.fabNewPost) FloatingActionButton myFab;

    NewPostFragment newPostFragment;
    FragmentTransaction ft;

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
        setFabListener();


    }

    private void setFabListener() {
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(TimelineActivity.this, "Post new Tweet!", Toast.LENGTH_SHORT);
                //System.out.println("Post new Tweet!");
                setFragments();
            }
        });
    }

    private void setFragments() {
        ft = getSupportFragmentManager().beginTransaction();
        newPostFragment = NewPostFragment.newInstance();
        ft.replace(R.id.fmNewPost, newPostFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    //Send an API request to get the timeline json
    //Fill the listview by creating the tweet objects from the json
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            //SUCCESS

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                //super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", response.toString());

                //Toast.makeText(TimelineActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();

                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO LISTVIEW
                tweets.addAll(Tweet.fromJSONArray(json));
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



    public void onPostClick(View v){
        //Log.d("DEBUG", "Post button sent");
        newPostFragment.closeFragment();
        //Toast.makeText(this, newPostFragment.getPostContent(), Toast.LENGTH_SHORT).show();
        String PostContent = newPostFragment.getPostContent();
        populateNewTweet(PostContent);
    }

    private void populateNewTweet(String postContent) {
        client.postNewTweet(postContent, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                Toast.makeText(TimelineActivity.this, "PostonSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

}