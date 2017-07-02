package com.example.royex.inventoryapps;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

//import com.bumptech.glide.Glide;

//import app.kulshee.ryx.kulshee.R;

/**
 * Created by Royex Technologies on 3/20/2017.
 */

public class TypeARvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "nexa_"+this.getClass().getSimpleName();

    private ArrayList<data> list;
    private List<data> productDataModelList = new ArrayList<data>();
    private Context context;
    private ItemStyle itemStyle;

    private final int VIEW_ITEM = 1;
    private final int VIEW_LOADING = 0;

    public TypeARvAdapter(Context context, ItemStyle itemStyle, List<data> productDataModelList) {
        this.productDataModelList = productDataModelList;
        this.context = context;
        this.itemStyle = itemStyle;
    }

    @Override
    public int getItemViewType(int position) {
        return productDataModelList.get(position) != null ? VIEW_ITEM : VIEW_LOADING;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = null;

        if(viewType == VIEW_ITEM){

            if(itemStyle== ItemStyle.GRID_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.datadesign, parent, false); // fixed title
            } else if (itemStyle== ItemStyle.LIST_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.datadesign, parent, false);  // same fixed
            }

            return new ItemViewHolder(itemView);

        }
        //else {
//             itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.progressbar_item, parent, false);  // loading
//
//            return new ProgressBarViewHolder(itemView);
//        }
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ItemViewHolder) {


            int globalcolor = Color.parseColor("#f2f1f1");
            if(i%2==0){
                //
                ((ItemViewHolder)viewHolder).product.setBackgroundColor(globalcolor);
                ((ItemViewHolder)viewHolder).stockIn.setBackgroundColor(globalcolor);
                ((ItemViewHolder)viewHolder).stockOut.setBackgroundColor(globalcolor);
                ((ItemViewHolder)viewHolder).stock.setBackgroundColor(globalcolor);
            }
            ((ItemViewHolder)viewHolder).product.setText(productDataModelList.get(i).getProduct());
            ((ItemViewHolder)viewHolder).stockIn.setText(productDataModelList.get(i).getStockIn());
            ((ItemViewHolder)viewHolder).stockOut.setText(productDataModelList.get(i).getStockOut());
            ((ItemViewHolder)viewHolder).stock.setText(productDataModelList.get(i).getStock());

            /*Glide.with(context)
                    .load(productDataModelList.get(i))
                    .placeholder(R.drawable.demo_icon)
                    .error(R.drawable.demo_icon)
                    .into(((ItemViewHolder)viewHolder).iconImage);*/

        } else {
            ((ProgressBarViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        //Toast.makeText(context, productDataModelList.size()+"", Toast.LENGTH_LONG).show();
        return productDataModelList.size();

    }

    ///////-------------------------ItemViewHolder Classes----------------------------/////////

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView product, stockIn, stockOut, stock;

        public ItemViewHolder(View view) {
            super(view);
            product = (TextView)view.findViewById(R.id.tv1);  //  reference of the textview
            stockIn = (TextView) view.findViewById(R.id.tv2);
            stockOut = (TextView)view.findViewById(R.id.tv3);
            stock = (TextView)view.findViewById(R.id.tv4);
        }
    }

    public static class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressBarViewHolder(View v) {
            super(v);
           // progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public enum ItemStyle {
        LIST_VIEW,
        GRID_VIEW;
    }

}
