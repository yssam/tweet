package com.codepath.apps.MySimpleTweets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NewPostFragment extends Fragment {
    @BindView(R.id.etEdit) EditText etEdit;
    private Unbinder unbinder;

    public String getPostContent() {
        return postContent;
    }

    private String postContent;
        // The onCreateView method is called when Fragment should create its View object hierarchy,
        // either dynamically or via XML layout inflation.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            // Defines the xml file for the fragment
            View view = inflater.inflate(R.layout.fragment_new_post, parent, false);
            unbinder = ButterKnife.bind(this, view);
            return view;
        }

        // This event is triggered soon after onCreateView().
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            // Setup any handles to view objects here
        }

    public static NewPostFragment newInstance() {
        NewPostFragment newPostFragment = new NewPostFragment();
        //Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        //args.putString("someTitle", someTitle);
        //fragmentDemo.setArguments(args);
        return newPostFragment;
    }

    // When binding a fragment in onCreateView, set the views to null in onDestroyView.
    // ButterKnife returns an Unbinder on the initial binding that has an unbind method to do this automatically.
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        //int SomeInt = getArguments().getInt("someInt", 0);
        //String someTitle = getArguments().getString("someTitle", "");
    }

    public void closeFragment(){
        postContent = etEdit.getText().toString();
        //System.out.println(postContent);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.hide(this);
        ft.commit();
    }
}
