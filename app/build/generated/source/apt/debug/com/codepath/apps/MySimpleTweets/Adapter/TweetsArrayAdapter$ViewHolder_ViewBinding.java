// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets.Adapter;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.MySimpleTweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetsArrayAdapter$ViewHolder_ViewBinding<T extends TweetsArrayAdapter.ViewHolder> implements Unbinder {
  protected T target;

  public TweetsArrayAdapter$ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.ivProfileImage = finder.findRequiredViewAsType(source, R.id.ivProfileImage, "field 'ivProfileImage'", ImageView.class);
    target.tvUserName = finder.findRequiredViewAsType(source, R.id.tvUserName, "field 'tvUserName'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivProfileImage = null;
    target.tvUserName = null;
    target.tvBody = null;

    this.target = null;
  }
}
