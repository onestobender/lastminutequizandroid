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

            double finalPrice = currentItem.getPrice();

            if(!isBasicSaleTaxExempt(currentItem) && currentItem.isImported())
            {
                double saleTax = (currentItem.getPrice() * 15) / 100;
                //  double saleTax = round(currentItem.getPrice(), 10);
                currentPurchasedItem.setBasicSaleTax(round(saleTax));
     //           Log.d(TAG, "sale tax for " + currentItem.getPrice() + ": " + saleTax);
            }
            else {


                if (!isBasicSaleTaxExempt(currentItem)) {
                    //10% sales tax
                    double saleTax = (currentItem.getPrice() * 10) / 100;
                    //  double saleTax = round(currentItem.getPrice(), 10);
                    currentPurchasedItem.setBasicSaleTax(round(saleTax));
                 //   Log.d(TAG, "sale tax for " + currentItem.getPrice() + ": " + round(saleTax));


//                    finalPrice = finalPrice + saleTax;
//                finalPrice = round(finalPrice + saleTax);
                }

                if (currentItem.isImported()) {
                    //5% import tax
                    double importTax = (currentItem.getPrice() * 5) / 100;
                    //double importTax = round(currentItem.getPrice(), 5);
                    currentPurchasedItem.setImportTax(round(importTax));
             //       Log.d(TAG, "import tax for " + currentItem.getPrice() + ": " + round(importTax));


//                    finalPrice = finalPrice + importTax;
//              finalPrice = round(finalPrice + importTax);
                }

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

        return Math.round(value * 20) / 20.0;
/*
        DecimalFormat df = new DecimalFormat("#.##");
        String formate = df.format(value);
        try {
            double rounded = (double) df.parse(formate);
            Log.d(TAG, "" + value + " was rounded to " + rounded);
            return rounded;
        } catch (ParseException e) {
            Log.d(TAG, "" + value + "was not rounded");
            e.printStackTrace();
            return value;
        }
*/
    }



}
