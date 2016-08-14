// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets;

import android.support.design.widget.FloatingActionButton;

import butterknife.Unbinder;
import butterknife.internal.Finder;

public class TimelineActivity_ViewBinding<T extends TimelineActivity> implements Unbinder {
  protected T target;

  public TimelineActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.myFab = finder.findRequiredViewAsType(source, R.id.fabNewPost, "field 'myFab'", FloatingActionButton.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.myFab = null;

    this.target = null;
  }
}
