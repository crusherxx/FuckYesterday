package com.example.splash.main.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.splash.R;

public class BottomAnim {

    public static void show(View show){
        show.clearAnimation ();
        Animation animationShow = AnimationUtils.loadAnimation (show.getContext (), R.anim.main_tab_translate_show);
        show.startAnimation (animationShow);
        show.setVisibility (View.VISIBLE);
    }

    public static void hide(View show){
        show.clearAnimation ();
        Animation animationHide = AnimationUtils.loadAnimation (show.getContext (), R.anim.main_tab_translate_hide);
        show.startAnimation (animationHide);
        show.setVisibility (View.INVISIBLE);
    }
}
