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

    public void loadData2Async(IOnApplicationDataLoad callbacks) {

        Data2LoadAsyncTask task = new Data2LoadAsyncTask();
        task.execute(callbacks);
    }

    public void loadData3Async(IOnApplicationDataLoad callbacks) {

        Data3LoadAsyncTask task = new Data3LoadAsyncTask();
        task.execute(callbacks);
    }




    private class Data1LoadAsyncTask extends AsyncTask<IOnApplicationDataLoad, Void, List<Good>> {

        private IOnApplicationDataLoad callbacks;

        @Override
        protected List<Good> doInBackground(IOnApplicationDataLoad... params) {
            callbacks = params[0];

            try {
                Log.d(TAG, "fake retrieving data delay...(e.g. json dowload from an API)");
                Thread.sleep(1500);
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
            book.setPrice(new Double(12.49));

            Good cd = new Good();
            cd.setType(Good.GoodType.GENERIC);
            cd.setPrice(new Double(14.99));

            Good chocolateBar = new Good();
            chocolateBar.setType(Good.GoodType.FOOD);
            chocolateBar.setPrice(new Double(0.85));

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




    private class Data2LoadAsyncTask extends AsyncTask<IOnApplicationDataLoad, Void, List<Good>> {

        private IOnApplicationDataLoad callbacks;

        @Override
        protected List<Good> doInBackground(IOnApplicationDataLoad... params) {
            callbacks = params[0];

            try {
                Log.d(TAG, "fake retrieving data delay...(e.g. json dowload from an API)");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * Input 2:
             1 imported box of chocolates at 10.00
             1 imported bottle of perfume at 47.50

             */
            ArrayList<Good> goods = new ArrayList<>();

            Good importedBox = new Good();
            importedBox.setType(Good.GoodType.FOOD);
            importedBox.setPrice(new Double(10.00));
            importedBox.setImported(true);

            Good importedPerfume = new Good();
            importedPerfume.setType(Good.GoodType.GENERIC);
            importedPerfume.setPrice(new Double(47.50));
            importedPerfume.setImported(true);

            goods.add(importedBox);
            goods.add(importedPerfume);

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





    private class Data3LoadAsyncTask extends AsyncTask<IOnApplicationDataLoad, Void, List<Good>> {

        private IOnApplicationDataLoad callbacks;

        @Override
        protected List<Good> doInBackground(IOnApplicationDataLoad... params) {
            callbacks = params[0];

            try {
                Log.d(TAG, "fake retrieving data delay...(e.g. json dowload from an API)");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * Input 3:
             1 imported bottle of perfume at 27.99
             1 bottle of perfume at 18.99
             1 packet of headache pills at 9.75
             1 box of imported chocolates at 11.25


             */
            ArrayList<Good> goods = new ArrayList<>();

            Good importedPerfume = new Good();
            importedPerfume.setType(Good.GoodType.GENERIC);
            importedPerfume.setPrice(new Double(27.99));
            importedPerfume.setImported(true);

            Good perfume = new Good();
            perfume.setType(Good.GoodType.GENERIC);
            perfume.setPrice(new Double(18.99));

            Good pills = new Good();
            pills.setType(Good.GoodType.MEDICAL_PRODUCT);
            pills.setPrice(new Double(9.75));

            Good importedBox = new Good();
            importedBox.setType(Good.GoodType.FOOD);
            importedBox.setPrice(new Double(11.25));
            importedBox.setImported(true);

            goods.add(importedPerfume);
            goods.add(perfume);
            goods.add(pills);
            goods.add(importedBox);

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
