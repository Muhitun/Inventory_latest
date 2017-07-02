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

public class InvoiceTestListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = "nexa_"+this.getClass().getSimpleName();

    private ArrayList<InvoiceListDatatype> list;
    private List<InvoiceListDatatype> productDataModelList = new ArrayList<InvoiceListDatatype>();
    private Context context;
    private InvoiceTestListAdapter.ItemStyle itemStyle;

    private final int VIEW_ITEM = 1;
    private final int VIEW_LOADING = 0;

    public InvoiceTestListAdapter(Context context, InvoiceTestListAdapter.ItemStyle itemStyle, List<InvoiceListDatatype> productDataModelList) {
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

            if(itemStyle== InvoiceTestListAdapter.ItemStyle.GRID_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.invoicelisttest, parent, false); // fixed title
            } else if (itemStyle== InvoiceTestListAdapter.ItemStyle.LIST_VIEW) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.invoicelisttest, parent, false);  // same fixed
            }

            return new InvoiceTestListAdapter.ItemViewHolder(itemView);

        }
        //else {
//             itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.progressbar_item, parent, false);  // loading
//
//            return new ProgressBarViewHolder(itemView);
//        }
        return new InvoiceTestListAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof InvoiceTestListAdapter.ItemViewHolder) {


            int globalcolor = Color.parseColor("#f2f1f1");

            if(i%2==0){
                //
                ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).product.setBackgroundColor(globalcolor);
                ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stockIn.setBackgroundColor(globalcolor);
                ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stockOut.setBackgroundColor(globalcolor);
                ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stock.setBackgroundColor(globalcolor);

            }
            ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).product.setText(productDataModelList.get(i).getProduct());
            ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stockIn.setText(productDataModelList.get(i).getStockIn());
            ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stockOut.setText(productDataModelList.get(i).getStockOut());
            ((InvoiceTestListAdapter.ItemViewHolder)viewHolder).stock.setText(productDataModelList.get(i).getStock());


            /*Glide.with(context)
                    .load(productDataModelList.get(i))
                    .placeholder(R.drawable.demo_icon)
                    .error(R.drawable.demo_icon)
                    .into(((ItemViewHolder)viewHolder).iconImage);*/

        } else {
            ((InvoiceTestListAdapter.ProgressBarViewHolder) viewHolder).progressBar.setIndeterminate(true);
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
            stock = (TextView)view.findViewById(R.id.tv4);
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
