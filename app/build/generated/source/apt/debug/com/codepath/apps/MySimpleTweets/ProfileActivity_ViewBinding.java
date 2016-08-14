// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ProfileActivity_ViewBinding<T extends ProfileActivity> implements Unbinder {
  protected T target;

  public ProfileActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.tvName = finder.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvTagline = finder.findRequiredViewAsType(source, R.id.tvTagline, "field 'tvTagline'", TextView.class);
    target.tvFollowers = finder.findRequiredViewAsType(source, R.id.tvFollowers, "field 'tvFollowers'", TextView.class);
    target.tvFollowing = finder.findRequiredViewAsType(source, R.id.tvFollowing, "field 'tvFollowing'", TextView.class);
    target.ivProfileImage = finder.findRequiredViewAsType(source, R.id.ivProfileImage, "field 'ivProfileImage'", ImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvName = null;
    target.tvTagline = null;
    target.tvFollowers = null;
    target.tvFollowing = null;
    target.ivProfileImage = null;

    this.target = null;
  }
}
