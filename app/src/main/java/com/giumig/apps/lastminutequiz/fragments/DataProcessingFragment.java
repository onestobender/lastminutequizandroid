package com.giumig.apps.lastminutequiz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.activities.MainActivity;
import com.giumig.apps.lastminutequiz.interfaces.IOnApplicationDataLoad;
import com.giumig.apps.lastminutequiz.model.Good;
import com.giumig.apps.lastminutequiz.singleton.DataManager;

import java.util.List;

/**
 * Created by gius on 13/12/16.
 */

public class DataProcessingFragment extends Fragment {

    private View rootView;


    public static DataProcessingFragment newInstance(int selectedDataset)
    {
        DataProcessingFragment f = new DataProcessingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.SELECTED_INPUT_DATA_KEY, selectedDataset);
        f.setArguments(bundle);
        return f;
    }


    private int retrieveDatasetIndex()  {
        return getArguments().getInt(MainActivity.SELECTED_INPUT_DATA_KEY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_processing, container, false);
        initResources();
        return rootView;
    }






    private void initResources()    {


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /**
         * retrieve appropriate dataset (e.g. network download)
         */

        ((MainActivity) getActivity()).showProgress();
        int selectedDataset = retrieveDatasetIndex();

        if(selectedDataset == 1)
        {
            DataManager.getInstance().loadData1Async(iOnApplicationDataLoad);
        }
        else if(selectedDataset == 2)
        {

        }
        else if(selectedDataset == 3)
        {

        }







    }




    private IOnApplicationDataLoad iOnApplicationDataLoad = new IOnApplicationDataLoad() {
        @Override
        public void onApplicationDataLoadSuccess(List<Good> goods) {
            ((MainActivity) getActivity()).hideProgress();
        }

        @Override
        public void onApplicationDataLoadfail() {
            ((MainActivity) getActivity()).hideProgress();
        }
    };



}
