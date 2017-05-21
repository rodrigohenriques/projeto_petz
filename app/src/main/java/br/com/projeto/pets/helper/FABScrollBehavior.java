package br.com.projeto.pets.helper;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by srolemberg on 08/01/16.
 */
public class FABScrollBehavior extends FloatingActionButton.Behavior {

	public FABScrollBehavior(Context context, AttributeSet attributeSet) {
		super();
	}


	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, final FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
		if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
			//correçao no 25.0.1
			//http://stackoverflow.com/questions/41807601/onnestedscroll-called-only-once
			child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
				@Override
				public void onShown(FloatingActionButton fab) {
					super.onShown(fab);
				}

				@Override
				public void onHidden(FloatingActionButton fab) {
					super.onHidden(fab);
					child.setVisibility(View.INVISIBLE);
				}
			});
		} else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
			child.show();
		}
	}


	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
				super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

	}
}
