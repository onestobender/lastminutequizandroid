package com.giumig.apps.lastminutequiz.adapters;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giumig.apps.lastminutequiz.R;
import com.giumig.apps.lastminutequiz.model.Good;

import java.util.ArrayList;

/**
 * Created by gius on 21/03/16.
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {


    private ArrayList<Good> dataSet;

    public GoodsListAdapter(ArrayList<Good> dataSet)    {
        this.dataSet = dataSet;
    }





    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    /*
     *  Create new views (invoked by the layout manager)
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		/*
		 * create a new view
		 */
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good, parent, false);


        /*
         * set the view's size, margins, paddings and layout parameters
         */
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    /*
     *  Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

		/*
		 * get element from your dataset at this position
		 */
        final Good currentItem = dataSet.get(position);

		/*
		 * replace the contents of the view with that element
		 */
        holder.goodHeaderLine.setText(currentItem.getType().toString());

        if(currentItem.isImported())
        {
            holder.goodImported.setText(holder.goodImported.getContext().getString(R.string.imported_good_header) );
            holder.goodImported.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.goodImported.setVisibility(View.GONE);
        }

        holder.goodPrice.setText("" + currentItem.getPrice());

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView goodHeaderLine;
        public TextView goodImported;
        public TextView goodPrice;


        public MyViewHolder(View itemLayoutView)	{
            super(itemLayoutView);

            this.itemView = itemLayoutView;
            this.goodHeaderLine = (TextView) itemLayoutView.findViewById(R.id.goodHeaderLine);
            this.goodImported = (TextView) itemLayoutView.findViewById(R.id.goodImported);
            this.goodPrice = (TextView) itemLayoutView.findViewById(R.id.goodPrice);

        }
    }


}
