package com.giumig.apps.lastminutequiz.singleton;

import android.util.Log;

import com.giumig.apps.lastminutequiz.interfaces.IOnPurchase;
import com.giumig.apps.lastminutequiz.model.Good;
import com.giumig.apps.lastminutequiz.model.PurchasedGood;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by gius on 14/12/16.
 */

public class CartManager {

    private static CartManager instance;
    private static final String TAG = "CartManager";

    public static CartManager getInstance() {
        if(instance == null)
        {
            instance = new CartManager();
        }

        return instance;
    }


    private ArrayList<Good> cartItems = new ArrayList<>();


    public void startSession()  {
        emptyCart();
    }

    public void emptyCart() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(Good item)    {
        Log.d(TAG, "adding to cart " + item.toString());
        cartItems.add(item);
    }

    public void purchase(IOnPurchase callback)  {

        ArrayList<PurchasedGood> purchasedItems = new ArrayList<>();

        for(Good currentItem : cartItems)
        {
            PurchasedGood currentPurchasedItem = new PurchasedGood();
            currentPurchasedItem.setGood(currentItem);

            if(!isBasicSaleTaxExempt(currentItem))
            {
                //10% sales tax
                double saleTax = round( currentItem.getPrice() * 0.1);
                currentPurchasedItem.setBasicSaleTax(saleTax);
                Log.d(TAG, "sale tax for " + currentItem.getPrice() + ": " + saleTax);

            }

            if(currentItem.isImported())
            {
                //5% import tax
                double importTax = round( currentItem.getPrice() * 0.05);
                currentPurchasedItem.setImportTax(importTax);
                Log.d(TAG, "import tax for " + currentItem.getPrice() + ": " + importTax);

            }


            purchasedItems.add(currentPurchasedItem);
        }

        callback.onPurchase(purchasedItems);

    }



    private boolean isBasicSaleTaxExempt(Good item)  {
        return item.getType() == Good.GoodType.BOOK
                || item.getType() == Good.GoodType.FOOD
                || item.getType() == Good.GoodType.MEDICAL_PRODUCT;

    }


    private double round(double value) {

        DecimalFormat df = new DecimalFormat("0.00");
        String formate = df.format(value);
        try {
            return (double) df.parse(formate);
        } catch (ParseException e) {
            e.printStackTrace();
            return value;
        }
    }


}
