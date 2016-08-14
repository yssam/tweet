package com.codepath.apps.MySimpleTweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sam on 8/6/16.
 */

public class User {
    // list attributes

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getTagLine() {
        return tagLine;
    }

    private String name;
    private long id;
    private String screenName;
    private String profileImageUrl;
    private String tagLine;
    private int followers;
    private int following;



    // deserialize the user json => User
    public static User fromJSON(JSONObject json){
        User u = new User();
        // Extract and fill the values
        try {
            u.name = json.getString("name");
        }catch (JSONException e) {
            u.name = " ";
            e.printStackTrace();
        }
        try {
            u.id = json.getLong("id_str");
        } catch (JSONException e1) {
            u.id = 0;
            e1.printStackTrace();
        }
        try {
            u.screenName = json.getString("screen_name");
        } catch (JSONException e1) {
            u.screenName = " ";
            e1.printStackTrace();
        }
        try {
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            u.profileImageUrl = " ";
            e.printStackTrace();
        }

        try {
            u.followers = json.getInt("followers_count");
        } catch (JSONException e) {
            u.followers = 0;
            e.printStackTrace();
        }
        try {
            u.following = json.getInt("friends_count");
        } catch (JSONException e) {
            u.following = 0;
            e.printStackTrace();
        }
        try {
            u.tagLine = json.getString("description");
        } catch (JSONException e) {
            u.tagLine = "";
            e.printStackTrace();
        }


        // Return a user
        return u;
    }
}
