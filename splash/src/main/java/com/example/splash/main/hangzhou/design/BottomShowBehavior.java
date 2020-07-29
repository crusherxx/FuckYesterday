package com.example.splash.main.hangzhou.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {
    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super (context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        if (dyConsumed+dyUnconsumed>0){
            if (child.getVisibility () == View.VISIBLE){
                BottomAnim.hide (child);
            }
        }else{
            if (child.getVisibility ()!=View.VISIBLE){
                BottomAnim.show (child);
            }

        }
    }
}
