package com.example.royex.inventoryapps;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Invoice extends Fragment {

    Button add;
    ImageView barcode;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter newAdapter;
    RecyclerView.LayoutManager layoutManager;
    AddProductAdapter.ItemStyle itemStyle;
//    public Invoice() {
//        // Required empty public constructor
//    }

    View myview, mView;
    ImageView close, save;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview =  inflater.inflate(R.layout.fragment_invoice, container, false);
       // return inflater.inflate(R.layout.fragment_invoice, container, false);
        add = (Button)myview.findViewById(R.id.addbtn);
        save = (ImageView)myview.findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new InvoiceCreated();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("InvoiceCreated");
                ft.commit();
            }
        });
        barcode = (ImageView)myview.findViewById(R.id.imageView1);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new InvoiceWithImage();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("InvoiceWithImage");
                ft.commit();
                Toast.makeText(getContext(), "adf", Toast.LENGTH_LONG).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                 mView = inflater.inflate(R.layout.add_product_layout, null);
                close = (ImageView)mView.findViewById(R.id.imageView_close);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                addProduct();

                dialog.show();
            }
        });
        init();
        return myview;
    }

    public void init(){
        InvoiceDatatype invoice = new InvoiceDatatype();
        List<InvoiceDatatype> alist = new ArrayList<>();
        alist.add(invoice);
        alist.add(new InvoiceDatatype("Samsung","$1000","100","$10,000"));
        alist.add(new InvoiceDatatype("Iphone","$5100","200","$20,000"));
        alist.add(new InvoiceDatatype("nokia","$3100","220","$41,000"));
        alist.add(new InvoiceDatatype("htc","$7651","210","$51,000"));
        alist.add(new InvoiceDatatype("oppo","$2231","260","$71,000"));
        alist.add(new InvoiceDatatype("walton","$5431","920","$91,000"));
        recyclerView = (RecyclerView)myview.findViewById(R.id.recyle);
//        adapter = new TypeARvAdapter(getContext(), TypeARvAdapter.ItemStyle.LIST_VIEW, alist);
        adapter = new recycleAdapter(getContext(), recycleAdapter.ItemStyle.LIST_VIEW, alist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void addProduct(){
        AddProductDatatype invoice = new AddProductDatatype();
        List<AddProductDatatype> alist = new ArrayList<>();
        alist.add(invoice);
        alist.add(new AddProductDatatype("1 Pcs","100","100"));
        alist.add(new AddProductDatatype("6 Pcs","83.3","500"));
        alist.add(new AddProductDatatype("12 Pcs","79.16","950"));

        recyclerView = (RecyclerView)mView.findViewById(R.id.recyle);
//        adapter = new TypeARvAdapter(getContext(), TypeARvAdapter.ItemStyle.LIST_VIEW, alist);
        adapter = new AddProductAdapter(getContext(), AddProductAdapter.ItemStyle.LIST_VIEW, alist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
