package com.codepath.apps.MySimpleTweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.MySimpleTweets.Fragments.HomeTimelineFragment;
import com.codepath.apps.MySimpleTweets.Fragments.MentionsTimelineFragment;
import com.codepath.apps.MySimpleTweets.Fragments.TweetsListFragment;

import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {


    NewPostFragment newPostFragment;
    TweetsListFragment fragmentTweetsList;
    FragmentTransaction ft;


   /* @BindView(R.id.fabNewPost)
    FloatingActionButton myFab;*/
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Get the viewPager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        // Set the viewPager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        // Find the pager sliding tabstrip
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the pager tabstrip to the viewPager
        tabStrip.setViewPager(vpPager);

        ButterKnife.bind(this);

//        setFabListener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

   /* private void setFabListener() {
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(TimelineActivity.this, "Post new Tweet!", Toast.LENGTH_SHORT);
                //System.out.println("Post new Tweet!");
                setFragments();
            }
        });
    }*/


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
        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem mi) {
        // launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
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
                return new HomeTimelineFragment();
            }
            else if(position == 1) {
                return new MentionsTimelineFragment();
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

/*

    public void onPostClick(View v){
        //Log.d("DEBUG", "Post button sent");
        newPostFragment.closeFragment();
        //Toast.makeText(this, newPostFragment.getPostContent(), Toast.LENGTH_SHORT).show();
        String PostContent = newPostFragment.getPostContent();
        populateNewTweet(PostContent);
        clearList();
        populateTimeline(0);
    }

    public void onCancelClick(View v){
        newPostFragment.closeFragment();
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
*/
}