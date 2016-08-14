package com.codepath.apps.MySimpleTweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.MySimpleTweets.Adapter.TweetsArrayAdapter;
import com.codepath.apps.MySimpleTweets.R;
import com.codepath.apps.MySimpleTweets.models.EndlessRecyclerViewScrollListener;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sam on 8/11/16.
 */

public class TweetsListFragment extends Fragment{

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    //private ListView lvTweets;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    LinearLayoutManager linearLayoutManager;

    // inflation logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        ButterKnife.bind(this, v);
        rvTweets.setAdapter(aTweets);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTweets.setLayoutManager(linearLayoutManager);
        setScrollListener();
        return v;
    }


    // creation lifecycle event
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create the arraylist (data source)
        tweets = new ArrayList<>();
        //Construct the adapter from data source
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
        //Connect adapter to list view


    }

    public void addAll(ArrayList<Tweet> tmpTweets) {
        tweets.addAll(tmpTweets);
    }

    public void clear() {
        tweets.clear();
        adapterNotifyDataSetChanged();
    }

    public void adapterNotifyDataSetChanged() {
        aTweets.notifyDataSetChanged();
    }

    private void setScrollListener() {
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                System.out.println("now page = " + page);
                //populateTimeline(page);
            }
        });
    }
}
