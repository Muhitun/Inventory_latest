package com.example.royex.inventoryapps;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TripStart extends Fragment {


    public TripStart() {
        // Required empty public constructor
    }

    View view;
    Button download,start;
    ImageView first, second, third, fourth;
    TextView product, inStock, stockSale;
    int counter;
    ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_trip_start, container, false);
        first = (ImageView)view.findViewById(R.id.imageView16);
        start = (Button)view.findViewById(R.id.button3);
        second = (ImageView)view.findViewById(R.id.imageView15);
        third = (ImageView)view.findViewById(R.id.imageView13);
        fourth = (ImageView)view.findViewById(R.id.imageView9);
        product = (TextView)view.findViewById(R.id.imageView10);
        inStock = (TextView)view.findViewById(R.id.imageView11);
        stockSale = (TextView)view.findViewById(R.id.imageView12);
        download = (Button)view.findViewById(R.id.button1);
        counter = 0;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable gl = new GlobalVariable();
                gl.setValue(1);
                Toast.makeText(getContext(), "Trip started", Toast.LENGTH_LONG).show();
                Fragment fragment = new Home();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        return view;
    }

}
