package com.example.royex.inventoryapps;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inventory extends Fragment {

    ImageView barcode;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TypeARvAdapter.ItemStyle itemStyle;
    public Inventory() {
        // Required empty public constructor
    }

    View myview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_inventory, container, false);
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
        init();
        return myview;
    }

    public void init(){
        data data = new data();
        List<data> alist = new ArrayList<>();
        alist.add(data);
        alist.add(new data("Samsung","1","20","10"));
        alist.add(new data("Iphone","1","20","10"));
        alist.add(new data("nokia","1","20","10"));
        alist.add(new data("htc","1","20","10"));
        alist.add(new data("oppo","1","20","10"));
        recyclerView = (RecyclerView)myview.findViewById(R.id.recyle);
        adapter = new TypeARvAdapter(getContext(), TypeARvAdapter.ItemStyle.LIST_VIEW, alist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

  //  }

}
