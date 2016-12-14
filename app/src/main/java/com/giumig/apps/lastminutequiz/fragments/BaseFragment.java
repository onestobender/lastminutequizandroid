package com.giumig.apps.lastminutequiz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gius on 14/12/16.
 */

public class BaseFragment extends Fragment {

    protected View rootView;
    protected String TAG;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = getClass().getSimpleName();

        /**
         * avoid that underlaying (stacked) fragments intercept this touch events
         */
        if(rootView != null)
        {
            rootView.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

    }
}
