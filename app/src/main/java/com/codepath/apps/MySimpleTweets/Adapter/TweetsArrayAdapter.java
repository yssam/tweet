package com.codepath.apps.MySimpleTweets.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.MySimpleTweets.R;
import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.codepath.apps.MySimpleTweets.models.ViewHolder1;
import com.codepath.apps.MySimpleTweets.models.ViewHolder2;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sam on 8/6/16.
 */

//Taking the Tweet objects and turning them into Views displayed in the list
public class TweetsArrayAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final int NOIMAGE = 0, IMAGE = 1;

    // Store a member variable for the contacts
    private List<Tweet> mTweets;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        mTweets = tweets;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }



    @Override
    public int getItemViewType(int position) {
        if (mTweets.get(position).getMedia() == null || TextUtils.isEmpty(mTweets.get(position).getMedia().getMediaUrl())) {
            return NOIMAGE;
        } else {
            return IMAGE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case NOIMAGE:
                View v1 = inflater.inflate(R.layout.item_tweet, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.item_tweet2, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case NOIMAGE:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
        }
    }
    private void configureViewHolder1(ViewHolder1 viewHolder, int position) {
        // Get the data model based on position
        Tweet tweet = mTweets.get(position);

        // Set item views based on your views and data model
        TextView tvName = viewHolder.getTvName();
        TextView tvUserName = viewHolder.getTvUserName();
        TextView tvBody = viewHolder.getTvBody();
        TextView tvRelativeTime = viewHolder.getTvRelativeTime();
        ImageView ivProfileImage = viewHolder.getIvProfileImage();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvRelativeTime.setText(getRelativeTimeAgo(tweet.getCreateAt()));
        ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
    }

    private void configureViewHolder2(ViewHolder2 viewHolder, int position) {
        // Get the data model based on position
        Tweet tweet = mTweets.get(position);

        // Set item views based on your views and data model
        TextView tvName = viewHolder.getTvName();
        TextView tvUserName = viewHolder.getTvUserName();
        TextView tvBody = viewHolder.getTvBody();
        TextView tvRelativeTime = viewHolder.getTvRelativeTime();
        ImageView ivProfileImage = viewHolder.getIvProfileImage();
        ImageView ivUrlImage = viewHolder.getIvUrlImage();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvRelativeTime.setText(getRelativeTimeAgo(tweet.getCreateAt()));
        ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
        Picasso.with(getContext()).load(tweet.getMedia().getMediaUrl()).into(ivUrlImage);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {

            long dateMillis = sf.parse(rawJsonDate).getTime();
            System.out.println(dateMillis + " " + System.currentTimeMillis());
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            //Log.d("DEBUG", Long.toString(dateMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
    /*
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TweetsArrayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Tweet tweet = mTweets.get(position);

        // Set item views based on your views and data model
        TextView tvName = viewHolder.tvName;
        TextView tvUserName = viewHolder.tvUserName;
        TextView tvBody = viewHolder.tvBody;
        TextView tvRelativeTime = viewHolder.tvRelativeTime;
        ImageView ivProfileImage = viewHolder.ivProfileImage;

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvRelativeTime.setText(getRelativeTimeAgo(tweet.getCreateAt()));
        ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for a recycled view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
    }




    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {

            long dateMillis = sf.parse(rawJsonDate).getTime();
            System.out.println(dateMillis + " " + System.currentTimeMillis());
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            //Log.d("DEBUG", Long.toString(dateMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }*/
}
