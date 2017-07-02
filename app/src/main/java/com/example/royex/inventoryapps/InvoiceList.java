package com.example.royex.inventoryapps;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceList extends Fragment {

    Button search;
    ImageView barcode;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    InvoiceTestListAdapter.ItemStyle itemStyle;
//    public Invoice() {
//        // Required empty public constructor
//    }

    View myview;
    public InvoiceList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_invoice_list, container, false);
        search = (Button)myview.findViewById(R.id.searchtolist);
        barcode = (ImageView)myview.findViewById(R.id.imageView);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new InvoiceWithImage();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("InvoiceWithImage");
                ft.commit();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Invoice();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("Invoice");
                ft.commit();
            }
        });
        init();
        return myview;
    }

    public void init(){
        InvoiceListDatatype invoice = new InvoiceListDatatype();
        List<InvoiceListDatatype> alist = new ArrayList<>();
        alist.add(invoice);
        alist.add(new InvoiceListDatatype("#9231","Andru","100","$10,000"));
        alist.add(new InvoiceListDatatype("#3211","Simmons","200","$20,000"));
        alist.add(new InvoiceListDatatype("#1232","Peter","220","$41,000"));
        alist.add(new InvoiceListDatatype("#2313","Jones","210","$51,000"));
        alist.add(new InvoiceListDatatype("#9087","Andrew","260","$71,000"));
        alist.add(new InvoiceListDatatype("#1243","Michaels","920","$91,000"));
        recyclerView = (RecyclerView)myview.findViewById(R.id.recyle);
//        adapter = new TypeARvAdapter(getContext(), TypeARvAdapter.ItemStyle.LIST_VIEW, alist);
        adapter = new InvoiceTestListAdapter(getContext(), InvoiceTestListAdapter.ItemStyle.LIST_VIEW, alist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
