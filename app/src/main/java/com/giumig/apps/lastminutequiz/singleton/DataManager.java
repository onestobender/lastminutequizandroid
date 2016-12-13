package com.giumig.apps.lastminutequiz.singleton;

import android.os.AsyncTask;
import android.util.Log;

import com.giumig.apps.lastminutequiz.interfaces.IOnApplicationDataLoad;
import com.giumig.apps.lastminutequiz.model.Good;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gius on 13/12/16.
 */

public class DataManager {

    private static DataManager instance;

    private static final String TAG = "DataManager";

    public static DataManager getInstance() {
        if(instance == null)
            instance = new DataManager();

        return instance;
    }




    public void loadData1Async(IOnApplicationDataLoad callbacks) {

        Data1LoadAsyncTask task = new Data1LoadAsyncTask();
        task.execute(callbacks);
    }




    private class Data1LoadAsyncTask extends AsyncTask<IOnApplicationDataLoad, Void, List<Good>> {

        private IOnApplicationDataLoad callbacks;

        @Override
        protected List<Good> doInBackground(IOnApplicationDataLoad... params) {
            callbacks = params[0];

            try {
                Log.d(TAG, "fake retrieving data delay...(e.g. json dowload from an API)");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * Input 1:
             1 book at 12.49
             1 music CD at 14.99
             1 chocolate bar at 0.85

             */
            ArrayList<Good> goods = new ArrayList<>();

            Good book = new Good();
            book.setType(Good.GoodType.BOOK);
            book.setPrice(12.49);

            Good cd = new Good();
            cd.setType(Good.GoodType.GENERIC);
            cd.setPrice(14.99);

            Good chocolateBar = new Good();
            chocolateBar.setType(Good.GoodType.FOOD);
            chocolateBar.setPrice(0.85);

            goods.add(book);
            goods.add(cd);
            goods.add(chocolateBar);

            return goods;


            /**
             *  if an error occurred (e.g. network problems) return null
             *  and propagate to UI IOnApplicationDataLoad.onApplicationDataLoadfail()
             */


        }


        @Override
        protected void onPostExecute(List<Good> goods) {
            super.onPostExecute(goods);

            callbacks.onApplicationDataLoadSuccess(goods);

        }
    }





}
