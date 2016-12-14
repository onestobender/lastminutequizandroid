package com.giumig.apps.lastminutequiz.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.fragments.DataProcessingFragment;
import com.giumig.apps.lastminutequiz.fragments.SelectionFragment;

public class MainActivity extends AppCompatActivity {


    private static final String FRAGMENT_TAG_SELECTION = "FRAGMENT_TAG_SELECTION";
    private static final String FRAGMENT_TAG_PROCESSING = "FRAGMENT_TAG_PROCESSING";


    public static final String SELECTED_INPUT_DATA_EVENT = "SELECTED_INPUT_DATA_EVENT";
    public static final String SELECTED_INPUT_DATA_KEY = "SELECTED_INPUT_DATA_KEY";


    private String TAG;
    private ProgressDialog loadingProgress;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG = getClass().getSimpleName();

        initResources();

        //register to data input event
        LocalBroadcastManager.getInstance(this).registerReceiver(selectedInputDataReceiver,
                new IntentFilter(SELECTED_INPUT_DATA_EVENT));

        addSelectionFragment();
    }


    @Override
    protected void onDestroy() {

        //unregister
        LocalBroadcastManager.getInstance(this).unregisterReceiver(selectedInputDataReceiver);
        super.onDestroy();
    }


    private BroadcastReceiver selectedInputDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int selectedData = intent.getIntExtra(SELECTED_INPUT_DATA_KEY, 1);
            Log.d(TAG, "data input selected: " + selectedData);

            addDataProcessingFragment(selectedData);
        }
    };





    private void initResources()    {

        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentsContainer);

        loadingProgress = new ProgressDialog(this);
        loadingProgress.setCancelable(false);
        loadingProgress.setMessage(getString(R.string.loading_progressMessage));
    }


    public void showProgress() {
        loadingProgress.show();
    }

    public void hideProgress() {
        loadingProgress.dismiss();
    }




    private void addSelectionFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SelectionFragment fragment = SelectionFragment.newInstance();
        transaction.add(fragmentContainer.getId(), fragment, FRAGMENT_TAG_SELECTION);
        transaction.addToBackStack(FRAGMENT_TAG_SELECTION);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    private void addDataProcessingFragment(int selectedDataset) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DataProcessingFragment fragment = DataProcessingFragment.newInstance(selectedDataset);
        transaction.add(fragmentContainer.getId(), fragment, FRAGMENT_TAG_PROCESSING);
        transaction.addToBackStack(FRAGMENT_TAG_PROCESSING);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }


    @Override
    public void onBackPressed() {

        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
        if(fragmentCount == 1)
        {
            /**
             * selection fragment is on stack top, so close activity
             */
            MainActivity.this.finish();
        }
        else
        {
            /**
             * pop fragment from top
             */
            getSupportFragmentManager().popBackStackImmediate();
        }


    }
}
