package com.example.royex.inventoryapps;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by royex on 6/22/17.
 */

public class AddProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final String TAG = "nexa_"+this.getClass().getSimpleName();

    private ArrayList<AddProductDatatype> list;
    private List<AddProductDatatype> productDataModelList = new ArrayList<AddProductDatatype>();
    private Context context;
    private AddProductAdapter.ItemStyle itemStyle;

    private final int VIEW_ITEM = 1;
    private final int VIEW_LOADING = 0;

    public AddProductAdapter(Context context, AddProductAdapter.ItemStyle itemStyle, List<AddProductDatatype> productDataModelList) {
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

            if(itemStyle== AddProductAdapter.ItemStyle.GRID_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.add_product_test, parent, false); // fixed title
            } else if (itemStyle== AddProductAdapter.ItemStyle.LIST_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.add_product_test, parent, false);  // same fixed
            }

            return new AddProductAdapter.ItemViewHolder(itemView);

        }
        //else {
//             itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.progressbar_item, parent, false);  // loading
//
//            return new ProgressBarViewHolder(itemView);
//        }
        return new AddProductAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof AddProductAdapter.ItemViewHolder) {


            int globalcolor = Color.parseColor("#f2f1f1");

            if(i%2==0){
                //
                ((AddProductAdapter.ItemViewHolder)viewHolder).product.setBackgroundColor(globalcolor);
                ((AddProductAdapter.ItemViewHolder)viewHolder).stockIn.setBackgroundColor(globalcolor);
                ((AddProductAdapter.ItemViewHolder)viewHolder).stockOut.setBackgroundColor(globalcolor);


            }
            ((AddProductAdapter.ItemViewHolder)viewHolder).product.setText(productDataModelList.get(i).getProduct());
            ((AddProductAdapter.ItemViewHolder)viewHolder).stockIn.setText(productDataModelList.get(i).getStockIn());
            ((AddProductAdapter.ItemViewHolder)viewHolder).stockOut.setText(productDataModelList.get(i).getStockOut());



            /*Glide.with(context)
                    .load(productDataModelList.get(i))
                    .placeholder(R.drawable.demo_icon)
                    .error(R.drawable.demo_icon)
                    .into(((ItemViewHolder)viewHolder).iconImage);*/

        } else {
            ((AddProductAdapter.ProgressBarViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        //Toast.makeText(context, productDataModelList.size()+"", Toast.LENGTH_LONG).show();
        return productDataModelList.size();

    }

    ///////-------------------------ItemViewHolder Classes----------------------------/////////

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView product, stockIn, stockOut, stock, edit;

        public ItemViewHolder(View view) {
            super(view);
            product = (TextView)view.findViewById(R.id.tv1);  //  reference of the textview
            stockIn = (TextView) view.findViewById(R.id.tv2);
            stockOut = (TextView)view.findViewById(R.id.tv3);
            //stock = (TextView)view.findViewById(R.id.tv4);
            //edit = (TextView)view.findViewById(R.id.tv5);
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
