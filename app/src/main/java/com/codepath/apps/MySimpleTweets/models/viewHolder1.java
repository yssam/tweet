package com.codepath.apps.MySimpleTweets.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.MySimpleTweets.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sam on 8/7/16.
 */

public class ViewHolder1 extends RecyclerView.ViewHolder {
    public ImageView getIvProfileImage() {
        return ivProfileImage;
    }

    public TextView getTvName() {
        return tvName;
    }

    public TextView getTvUserName() {
        return tvUserName;
    }

    public TextView getTvBody() {
        return tvBody;
    }

    public TextView getTvRelativeTime() {
        return tvRelativeTime;
    }

    public void setIvProfileImage(ImageView ivProfileImage) {
        this.ivProfileImage = ivProfileImage;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public void setTvUserName(TextView tvUserName) {
        this.tvUserName = tvUserName;
    }

    public void setTvBody(TextView tvBody) {
        this.tvBody = tvBody;
    }

    public void setTvRelativeTime(TextView tvRelativeTime) {
        this.tvRelativeTime = tvRelativeTime;
    }

    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvUserName) TextView tvUserName;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.tvRelativeTime) TextView tvRelativeTime;

    public ViewHolder1(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}