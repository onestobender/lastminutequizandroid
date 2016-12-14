package com.giumig.apps.lastminutequiz.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.activities.MainActivity;
import com.giumig.apps.lastminutequiz.adapters.GoodsListAdapter;
import com.giumig.apps.lastminutequiz.interfaces.IOnApplicationDataLoad;
import com.giumig.apps.lastminutequiz.interfaces.IOnPurchase;
import com.giumig.apps.lastminutequiz.model.Good;
import com.giumig.apps.lastminutequiz.model.PurchasedGood;
import com.giumig.apps.lastminutequiz.singleton.CartManager;
import com.giumig.apps.lastminutequiz.singleton.DataManager;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gius on 13/12/16.
 */

public class DataProcessingFragment extends BaseFragment {


    private RecyclerView itemsToPurchaseRecyclerView;
    private AppCompatButton purchaseButton;

    private ArrayList<Good> dataset;


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
        itemsToPurchaseRecyclerView = (RecyclerView) rootView.findViewById(R.id.itemsToPurchaseRecyclerView);
        itemsToPurchaseRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        purchaseButton = (AppCompatButton) rootView.findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartManager cartManager = CartManager.getInstance();
                cartManager.startSession();

                for(Good current : dataset)
                {
                    cartManager.addToCart(current);
                }

                cartManager.purchase(iOnPurchase);

            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /**
         * retrieve appropriate dataset (e.g. network download)
         */

        ((MainActivity) getActivity()).showProgress();
        purchaseButton.setVisibility(View.GONE);

        int selectedDataset = retrieveDatasetIndex();

        if(selectedDataset == 1)
        {
            DataManager.getInstance().loadData1Async(iOnApplicationDataLoad);
        }
        else if(selectedDataset == 2)
        {
            DataManager.getInstance().loadData2Async(iOnApplicationDataLoad);
        }
        else if(selectedDataset == 3)
        {
            DataManager.getInstance().loadData3Async(iOnApplicationDataLoad);
        }







    }




    private IOnApplicationDataLoad iOnApplicationDataLoad = new IOnApplicationDataLoad() {
        @Override
        public void onApplicationDataLoadSuccess(List<Good> goods) {
            ((MainActivity) getActivity()).hideProgress();
            purchaseButton.setVisibility(View.VISIBLE);

            dataset = new ArrayList<>();
            dataset.addAll(goods);

            GoodsListAdapter adapter = new GoodsListAdapter(dataset);
            itemsToPurchaseRecyclerView.setAdapter(adapter);
        }

        @Override
        public void onApplicationDataLoadfail() {
            ((MainActivity) getActivity()).hideProgress();
        }
    };




    private IOnPurchase iOnPurchase = new IOnPurchase() {
        @Override
        public void onPurchase(ArrayList<PurchasedGood> purchasedItems) {

            Log.d(TAG, "purchase complete");
            CartManager.getInstance().emptyCart();

            showPurchaseResult(purchasedItems);
        }
    };


    private void showPurchaseResult(List<PurchasedGood> items)  {

        String message = "";
        double finalTaxes = 0.0;
        double total = 0.0;

        for(PurchasedGood current : items)
        {
            message += current.getGood().getType() + ": " + current.getFinalPrice() + "\n";
            finalTaxes += current.getBasicSaleTax() + current.getImportTax();
            total += current.getFinalPrice();
        }

        message += "\n" + getString(R.string.purchase_taxes) + ": " + finalTaxes + "\n";
        message += getString(R.string.purchase_total) + ": " + total;

        //show dialog
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.purchased_title))
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        ((MainActivity) getActivity()).popFragment();
                    }
                })
                .setCancelable(false)
                .show();

    }





}
