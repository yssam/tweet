package com.codepath.apps.MySimpleTweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.MySimpleTweets.Fragments.HomeTimelineFragment;
import com.codepath.apps.MySimpleTweets.Fragments.MentionsTimelineFragment;
import com.codepath.apps.MySimpleTweets.Fragments.TweetsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {


    NewPostFragment newPostFragment;
    TweetsListFragment homeTimelineFragment;
    TweetsListFragment mentionsTimelineFragment;
    FragmentTransaction ft;
    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.tabs) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.fabNewPost) FloatingActionButton myFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        // Set the viewPager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        // Attach the pager tabstrip to the viewPager
        tabStrip.setViewPager(vpPager);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    private void setFragments() {
        ft = getSupportFragmentManager().beginTransaction();
        newPostFragment = NewPostFragment.newInstance();
        ft.replace(R.id.fmNewPost, newPostFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
        Log.d("DEBUG", item.toString());
        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem mi) {
        // launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("screen_name", "sam_yschu");
        startActivity(i);
    }


    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Home", "Mentions"};

        // Adapter gets the manager insert or remove fragment from activity
        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        // The order and creation of fragments within the pager
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                homeTimelineFragment = new HomeTimelineFragment();
                return homeTimelineFragment;
            }
            else if(position == 1) {
                mentionsTimelineFragment = new MentionsTimelineFragment();
                return mentionsTimelineFragment;
            }
            else
                return null;
        }

        // return the tabTitle at top
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        // How many fragments
        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }



    public void onPostClick(View v){
        //Log.d("DEBUG", "Post button sent");
        newPostFragment.closeFragment();
        //Toast.makeText(this, newPostFragment.getPostContent(), Toast.LENGTH_SHORT).show();
        String PostContent = newPostFragment.getPostContent();
        if(homeTimelineFragment != null) {
            homeTimelineFragment.populateNewTweet(PostContent);
            homeTimelineFragment.clear();
        }
        if(mentionsTimelineFragment != null) {
            mentionsTimelineFragment.populateNewTweet(PostContent);
            mentionsTimelineFragment.clear();
        }
    }

    public void onCancelClick(View v){
        newPostFragment.closeFragment();
    }


}