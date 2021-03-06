package com.codepath.apps.MySimpleTweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Sam on 8/13/16.
 */

public class MentionsTimelineFragment extends TweetsListFragment{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Send an API request to get the timeline json
    //Fill the listview by creating the tweet objects from the json
    public void populateTimeline(final int page) {
        client.getMentionsTimeline(MinId, LargeId, page, new JsonHttpResponseHandler() {
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
}
