package com.example.royex.inventoryapps;


import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.hardware.Camera;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.Result;
import com.journeyapps.barcodescanner.camera.CameraSurface;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceWithImage extends Fragment{


    SurfaceView camera;
    View myview;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    TextView show;
    private ZXingScannerView zXingScannerView;
    private static final String TAG = "QR_ACTIVITY";
    private static final int REQUEST_CAMERA = 0;


    public InvoiceWithImage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_invoice_with_image, container, false);
        show = myview.findViewById(R.id.show);
        camera = (SurfaceView) myview.findViewById(R.id.camera_view);
        camera.setZOrderMediaOverlay(true);

//        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
//                Manifest.permission.CAMERA);
//        if(permissionCheck ==  PackageManager.PERMISSION_GRANTED)
//            initCameraSource();
//        else
//            requestCameraPermission();

        barcodeDetector =
                new BarcodeDetector.Builder(getContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();


        cameraSource = new CameraSource
                .Builder(getContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .build();

        Toast.makeText(getActivity(), "isOperatonal..." + barcodeDetector.isOperational(), Toast.LENGTH_SHORT).show();


        camera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
//                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(camera.getHolder());
                        //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    }
//
//
                catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("nexa_","inside surfaceDestroyed method");
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {


            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                //Toast.makeText(getContext(),"detected something...",Toast.LENGTH_SHORT).show();
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    show.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            show.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
//                            Toast.makeText(getContext(), barcodes.valueAt(0).displayValue, Toast.LENGTH_LONG).show();
//                            getExitTransition();
                        }
                    });
                    //Toast.makeText(getContext(), "Retrieved value is " + barcodes.valueAt(0), Toast.LENGTH_LONG).show();
                }
            }

        });


        return myview;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {

            case RequestCameraPermissionID:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                            try {
                                cameraSource.start(camera.getHolder());


                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }

            }
            break;

        }
    }

//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case RequestCameraPermissionID: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission has granted . Call the QR scan Activity
//
//                } else {
//                    // permission denied
//                }
//                return;
//            }
//        }
//    }

//    private void requestCameraPermission() {
//        Log.i("CAM","CAMERA permission has NOT been granted. Requesting permission.");
//
//        // BEGIN_INCLUDE(camera_permission_request)
//        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.CAMERA))
//        {
//
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{Manifest.permission.CAMERA},
//                    REQUEST_CAMERA);
//
//
//        } else {
//
//
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
//                    REQUEST_CAMERA);
//        }
//        // END_INCLUDE(camera_permission_request)
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        if (requestCode == REQUEST_CAMERA)
//        {
//
//            Log.i(TAG, "Received response for Camera permission request.");
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
//                initCameraSource();
//            } else {
//                Log.i(TAG, "CAMERA permission was NOT granted.");
//
//            }
//
//
//        }
//        else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

//    public void initCameraSource(){
//        barcodeDetector =
//                new BarcodeDetector.Builder(getContext())
//                        .setBarcodeFormats(Barcode.QR_CODE)
//                        .build();
//
//
//        cameraSource = new CameraSource
//                .Builder(getContext(), barcodeDetector)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedFps(24)
//                .setAutoFocusEnabled(true)
//                .build();
//
//        Toast.makeText(getActivity(), "isOperatonal..." + barcodeDetector.isOperational(), Toast.LENGTH_SHORT).show();
//
//
//        camera.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                //if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                try {
//                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(camera.getHolder());
//                        //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
//                    }
//
//
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                //cameraSource.stop();
//            }
//        });
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                //Toast.makeText(getContext(),"detected something...",Toast.LENGTH_SHORT).show();
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//
//                if (barcodes.size() != 0) {
//                    show.post(new Runnable() {    // Use the post method of the TextView
//                        public void run() {
//                            show.setText(    // Update the TextView
//                                    barcodes.valueAt(0).displayValue
//                            );
////                            Toast.makeText(getContext(), barcodes.valueAt(0).displayValue, Toast.LENGTH_LONG).show();
////                            getExitTransition();
//                        }
//                    });
//                    //Toast.makeText(getContext(), "Retrieved value is " + barcodes.valueAt(0), Toast.LENGTH_LONG).show();
//                }
//            }
//
//        });
//    }


    @Override
    public void onDestroy() {
        cameraSource.release();
        super.onDestroy();
    }
}
