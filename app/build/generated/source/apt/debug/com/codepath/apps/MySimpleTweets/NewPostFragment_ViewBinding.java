// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.MySimpleTweets;

import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class NewPostFragment_ViewBinding<T extends NewPostFragment> implements Unbinder {
  protected T target;

  public NewPostFragment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.etEdit = finder.findRequiredViewAsType(source, R.id.etEdit, "field 'etEdit'", EditText.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etEdit = null;

    this.target = null;
  }
}
