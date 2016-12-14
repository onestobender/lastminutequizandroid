package com.giumig.apps.lastminutequiz.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.activities.MainActivity;

/**
 * Created by gius on 13/12/16.
 */

public class SelectionFragment extends BaseFragment {


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

        data1Button.setTag(1);
        data2Button.setTag(2);
        data3Button.setTag(3);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        data1Button.setOnClickListener(dataSelectedClickListener);
        data2Button.setOnClickListener(dataSelectedClickListener);
        data3Button.setOnClickListener(dataSelectedClickListener);
    }



    private View.OnClickListener dataSelectedClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selected = (int) v.getTag();
            Intent intent = new Intent(MainActivity.SELECTED_INPUT_DATA_EVENT);
            intent.putExtra(MainActivity.SELECTED_INPUT_DATA_KEY, selected);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        }
    };



}
