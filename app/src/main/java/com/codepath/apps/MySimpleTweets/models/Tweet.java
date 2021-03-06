package com.codepath.apps.MySimpleTweets.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sam on 8/6/16.
 */


// Parse the JSON + Store the date, encapsulate state logic or display logic.
public class Tweet {
    // list out the attributes
    private String body;
    private long uid; //unique id for the tweet
    private String createAt;
    private User user;



    private Media media;

    public String getCreateAt() {
        return createAt;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public Media getMedia() {
        return media;
    }

    // Deserialize the JSON
    // Tweet.fromJSON("{ ... }") => <Tweet>
    public static Tweet fromJSON(JSONObject jsonObject) {

        Tweet tweet = new Tweet();
        //Extract the values from the json, store them
        try {
            tweet.body = jsonObject.getString("text");

        } catch (JSONException e) {
            tweet.body = " ";
            e.printStackTrace();
        }
        try {
            tweet.uid = jsonObject.getLong("id");
        } catch (JSONException e) {
            tweet.uid = 0;
            e.printStackTrace();
        }
        try {
            tweet.createAt = jsonObject.getString("created_at");
        } catch (JSONException e) {
            tweet.createAt = " ";
            e.printStackTrace();
        }

        try {
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tweet.media = Media.fromJSONArray(jsonObject.getJSONObject("extended_entities").getJSONArray("media"));
            Log.d("DEBUG", tweet.media.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Return the tweet object
        return tweet;
    }

    // Tweet.fromJSONArray([{ ... }, { ... } ] => List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        //Iterate the json array and create tweets
        for(int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                //System.out.println("id="+tweet.getUid());
                if(tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }
}
