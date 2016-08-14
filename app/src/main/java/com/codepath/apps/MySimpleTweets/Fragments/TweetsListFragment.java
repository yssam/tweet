package com.codepath.apps.MySimpleTweets.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.MySimpleTweets.Adapter.TweetsArrayAdapter;
import com.codepath.apps.MySimpleTweets.ItemClickSupport;
import com.codepath.apps.MySimpleTweets.ProfileActivity;
import com.codepath.apps.MySimpleTweets.R;
import com.codepath.apps.MySimpleTweets.models.EndlessRecyclerViewScrollListener;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sam on 8/11/16.
 */

public abstract class TweetsListFragment extends Fragment{

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    //private ListView lvTweets;
    RecyclerView rvTweets;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    protected long MinId = 1;
    protected long LargeId = 1;
    protected long localLargeId = 1;
    protected boolean first = true;

    // inflation logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        ButterKnife.bind(this, v);
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweets);
        rvTweets.setAdapter(aTweets);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTweets.setLayoutManager(linearLayoutManager);
        setScrollListener();
        setSwapListener();
        setRvListener();
        populateTimeline(0);
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
        MinId = 1;
        LargeId = 1;
        localLargeId = 1;
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
                populateTimeline(page);
            }
        });
    }

      private void setSwapListener() {
        // Lookup the swipe container view
        //swipeContainer = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
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

    }

    protected abstract void populateTimeline(int page);

    public void setRvListener(){
        ItemClickSupport.addTo(rvTweets).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // do it
                        //Toast.makeText(getActivity(), Integer.toString(position)+" item clicked", Toast.LENGTH_SHORT).show();
                        String screenName = tweets.get(position).getUser().getScreenName();
                        Intent i = new Intent(getActivity(), ProfileActivity.class);
                        i.putExtra("screen_name", screenName);
                        startActivity(i);

                    }
                }
        );
    }
}
