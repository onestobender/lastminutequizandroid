package com.giumig.apps.lastminutequiz.interfaces;

import com.giumig.apps.lastminutequiz.model.PurchasedGood;

import java.util.ArrayList;

/**
 * Created by gius on 14/12/16.
 */

public interface IOnPurchase {

    public void onPurchase(ArrayList<PurchasedGood> purchasedItems);
}
