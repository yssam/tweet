package com.codepath.apps.MySimpleTweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.TwitterApplication;
import com.codepath.apps.MySimpleTweets.TwitterClient;
import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeTimelineFragment extends TweetsListFragment{
    private TwitterClient client;
    private long MinId = 1;
    private long LargeId = 1;
    private long localLargeId = 1;
    private boolean first = true;
    private SwipeRefreshLayout swipeContainer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the client
        client = TwitterApplication.getRestClient(); //singleton client
        //setSwapListener();
        populateTimeline(0);
    }

    //Send an API request to get the timeline json
    //Fill the listview by creating the tweet objects from the json
    private void populateTimeline(final int page) {
        client.getHomeTimeline(MinId, LargeId, page, new JsonHttpResponseHandler() {
            //SUCCESS

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                ArrayList<Tweet> tmpTweets = new ArrayList<Tweet>(Tweet.fromJSONArray(json));
                for(Tweet t:tmpTweets){
                    localLargeId = Math.max(localLargeId, t.getUid());
                    MinId = (page==0)? t.getUid():Math.min(MinId, t.getUid());
                }

                if(first) {
                    LargeId = localLargeId;
                    first = false;
                }
                else if(MinId <= LargeId) LargeId = localLargeId;
                addAll(tmpTweets);

                adapterNotifyDataSetChanged();
                Log.d("DEBUG", json.toString());
              //  swipeContainer.setRefreshing(false);
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





  /*  private void setSwapListener() {
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                MinId = 1;
                LargeId = 1;
                localLargeId = 1;
                clear();
                populateTimeline(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }*/

}
