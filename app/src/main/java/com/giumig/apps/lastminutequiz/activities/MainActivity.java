package com.giumig.apps.lastminutequiz.activities;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.fragments.SelectionFragment;

public class MainActivity extends AppCompatActivity {


    private static final String 


    private String TAG;
    private ProgressDialog loadingProgress;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TAG = getClass().getSimpleName();

        initResources();


        addSelectionFragment();
    }


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
        transaction.add(fragmentContainer.getId(), fragment, "FRAGMENT_TAG_SELECTION");
    }



}
