package com.example.royex.inventoryapps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    int checkValue;

    public Home() {
        // Required empty public constructor
        GlobalVariable gl = new GlobalVariable();
        checkValue = gl.getValue();

    }


    View view;
    ImageView trip,inventory,invoices;
    TextView banner;
    int counter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        banner = (TextView)view.findViewById(R.id.tripbanner);
        if(checkValue == 1){
            banner.setText("Trip close");
        }else {
            banner.setText("Trip start");
        }

        trip = (ImageView)view.findViewById(R.id.trip);
        inventory = (ImageView)view.findViewById(R.id.inventory);
        invoices = (ImageView)view.findViewById(R.id.invoices);
        invoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Clicked trip", Toast.LENGTH_LONG).show();

                Fragment fragment = new InvoiceList();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("InvoiceList");
                ft.commit();
            }
        });
//
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Inventory();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("Inventory");
                ft.commit();
            }
        });

        trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValue == 1){
//                    Toast.makeText(getContext(), "Trip closed", Toast.LENGTH_LONG).show();
//                    banner.setText("Trip start");
                    Fragment fragment = new CloseTrip();
                    android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.addToBackStack("CloseTrip");
                    ft.commit();
                }else {
                    Fragment fragment = new BlankDownload();
                    android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.addToBackStack("BlankDownload");
                    ft.commit();
                }
            }
        });
        return view;
    }

}
