// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets.models;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.MySimpleTweets.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ViewHolder2_ViewBinding<T extends ViewHolder2> implements Unbinder {
  protected T target;

  public ViewHolder2_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.ivProfileImage = finder.findRequiredViewAsType(source, R.id.ivProfileImage, "field 'ivProfileImage'", ImageView.class);
    target.tvName = finder.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvUserName = finder.findRequiredViewAsType(source, R.id.tvUserName, "field 'tvUserName'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
    target.tvRelativeTime = finder.findRequiredViewAsType(source, R.id.tvRelativeTime, "field 'tvRelativeTime'", TextView.class);
    target.ivUrlImage = finder.findRequiredViewAsType(source, R.id.ivUrlImage, "field 'ivUrlImage'", ImageView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivProfileImage = null;
    target.tvName = null;
    target.tvUserName = null;
    target.tvBody = null;
    target.tvRelativeTime = null;
    target.ivUrlImage = null;

    this.target = null;
  }
}
