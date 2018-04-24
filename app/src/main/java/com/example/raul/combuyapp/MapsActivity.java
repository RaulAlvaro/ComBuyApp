package com.example.raul.combuyapp;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toast toast0 = Toast.makeText(getApplicationContext(),"HOLAAAA", Toast.LENGTH_LONG);
        toast0.show();
        //int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        /*
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());


        if(status == ConnectionResult.SUCCESS){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            Toast toast1 = Toast.makeText(getApplicationContext(),"ENTRA", Toast.LENGTH_LONG);
            toast1.show();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            Toast toast2 = Toast.makeText(getApplicationContext(),"CARGO", Toast.LENGTH_LONG);
            toast2.show();
        }else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity)getApplicationContext(),10);
            dialog.show();
            Toast toast = Toast.makeText(getApplicationContext(),"NO DA", Toast.LENGTH_LONG);
            toast.show();
        }

        */
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-12.063740, -77.073688);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        float zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomlevel));

    }
}
