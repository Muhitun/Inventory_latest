package com.example.royex.inventoryapps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CloseTrip extends Fragment {


    Button close;
    View myview;
    public CloseTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_close_trip, container, false);
        close = (Button)myview.findViewById(R.id.clostButton);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable gl = new GlobalVariable();
                gl.setValue(0);
                Toast.makeText(getContext(), "Trip closed", Toast.LENGTH_LONG).show();
                Fragment fragment = new Home();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        return myview;
    }

}
