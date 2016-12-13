package com.giumig.apps.lastminutequiz.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giumig.apps.lastminutequiz.R;

/**
 * Created by gius on 13/12/16.
 */

public class SelectionFragment extends Fragment {

    private View rootView;

    private AppCompatButton data1Button, data2Button, data3Button;


    public static SelectionFragment newInstance()
    {
        SelectionFragment f = new SelectionFragment();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_selection, container, false);
        initResources();
        return rootView;
    }

    private void initResources()    {

        data1Button = (AppCompatButton) rootView.findViewById(R.id.data1Button);
        data2Button = (AppCompatButton) rootView.findViewById(R.id.data2Button);
        data3Button = (AppCompatButton) rootView.findViewById(R.id.data3Button);

        data1Button.setOnClickListener(data1ClickListener);
        data2Button.setOnClickListener(data2ClickListener);
        data3Button.setOnClickListener(data3ClickListener);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    private View.OnClickListener data1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener data2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener data3ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


}
