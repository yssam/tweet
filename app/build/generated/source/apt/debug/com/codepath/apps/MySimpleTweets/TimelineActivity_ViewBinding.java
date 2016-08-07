// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets;

import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TimelineActivity_ViewBinding<T extends TimelineActivity> implements Unbinder {
  protected T target;

  public TimelineActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.rvTweets = finder.findRequiredViewAsType(source, R.id.rvTweets, "field 'rvTweets'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvTweets = null;

    this.target = null;
  }
}
