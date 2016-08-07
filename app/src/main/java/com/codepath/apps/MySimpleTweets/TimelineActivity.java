package com.codepath.apps.MySimpleTweets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.MySimpleTweets.models.EndlessRecyclerViewScrollListener;
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
    private long maxId;
    private long sinceId;
    private long localSinceId = -1;
    private boolean first = true;

    NewPostFragment newPostFragment;
    FragmentTransaction ft;
    LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeContainer;


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
        linearLayoutManager = new LinearLayoutManager(this);
        rvTweets.setLayoutManager(linearLayoutManager);
        //Get the client
        client = TwitterApplication.getRestClient(); //singleton client
        populateTimeline(0);
        setFabListener();
        setScrollListener();
        setSwapListener();
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

    private void setScrollListener() {
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                System.out.println("now page = " + page);
                populateTimeline(page);
            }
        });
    }

    private void setSwapListener() {
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                populateTimeline(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

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
    private void populateTimeline(final int page) {
        client.getHomeTimeline(maxId, sinceId, page, new JsonHttpResponseHandler() {
            //SUCCESS

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO LISTVIEW

                ArrayList<Tweet> tmpTweets = new ArrayList<Tweet>(Tweet.fromJSONArray(json));
                for(Tweet t:tmpTweets){
                    localSinceId = Math.max(localSinceId, t.getUid());
                    maxId = (page==0)? t.getUid():Math.min(maxId, t.getUid());
                }

                if(first) {
                    sinceId = localSinceId;
                    first = false;
                }
                else if(maxId <= sinceId) sinceId = localSinceId;
                tweets.addAll(tmpTweets);

                aTweets.notifyDataSetChanged();
                //Log.d("DEBUG", aTweets.toString());
                swipeContainer.setRefreshing(false);
            }


            //FAILURE

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("DEBUG", errorResponse.toString());
                //Toast.makeText(TimelineActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                swipeContainer.setRefreshing(false);
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