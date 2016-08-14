// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.astuetz.PagerSlidingTabStrip;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TimelineActivity_ViewBinding<T extends TimelineActivity> implements Unbinder {
  protected T target;

  public TimelineActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.vpPager = finder.findRequiredViewAsType(source, R.id.viewpager, "field 'vpPager'", ViewPager.class);
    target.tabStrip = finder.findRequiredViewAsType(source, R.id.tabs, "field 'tabStrip'", PagerSlidingTabStrip.class);
    target.myFab = finder.findRequiredViewAsType(source, R.id.fabNewPost, "field 'myFab'", FloatingActionButton.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.vpPager = null;
    target.tabStrip = null;
    target.myFab = null;

    this.target = null;
  }
}
