package com.popularmovies.vpaliy.popularmoviesapp.ui.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public final class ScrollBehavior extends AppBarLayout.Behavior {

    private static final int TOP_CHILD_FLING_THRESHOLD = 3;
    private boolean isPositive;
    private static final String TAG=ScrollBehavior.class.getSimpleName();

    public ScrollBehavior() {
    }

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        Log.d(TAG,"layoutDepends:"+dependency.getClass().getSimpleName());
        return  super.layoutDependsOn(parent, child, dependency);
    }


    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG,"onNestedFling:"+target.getClass().getSimpleName());
        if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
            velocityY = velocityY * -1;
        }
        if (!(target instanceof RecyclerView) && velocityY < 0) {
            RecyclerView recycler = findRecycler((ViewGroup) target);
            if (recycler != null){
                target = recycler;
            }
        }
        if (target instanceof RecyclerView && velocityY < 0) {
            final RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d(TAG,"onNestedScroll::"+target.getClass().getSimpleName());
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        isPositive = dy > 0;
    }

    @Nullable
    private RecyclerView findRecycler(ViewGroup container){
        for (int i = 0; i < container.getChildCount(); i++) {
            View childAt = container.getChildAt(i);
            if (childAt instanceof RecyclerView){
                return (RecyclerView) childAt;
            }
            if (childAt instanceof ViewGroup){
                return findRecycler((ViewGroup) childAt);
            }
        }
        return null;
    }

}