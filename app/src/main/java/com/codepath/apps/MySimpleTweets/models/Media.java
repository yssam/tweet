package com.codepath.apps.MySimpleTweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sam on 8/7/16.
 */

public class Media {

    private String type;
    private String mediaUrl;
    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getType() {
        return type;
    }


    public static Media fromJSONArray(JSONArray jsonArray){
        Media u = new Media();
        // Extract and fill the values
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject mediaJson = null;
            try {
                mediaJson = jsonArray.getJSONObject(i);
                try {
                    u.type = mediaJson.getString("type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    u.mediaUrl = mediaJson.getString("media_url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        // Return a user
        return u;
    }
}
