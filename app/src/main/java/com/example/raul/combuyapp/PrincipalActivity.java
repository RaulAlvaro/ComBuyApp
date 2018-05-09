package com.example.raul.combuyapp;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class PrincipalActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks{


    private GoogleMap mMap;

    // Geolocalizacion
    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleApiClient apiClient;
    private Location lastLocation=new Location("dummyprovider");
    private TextView lat, longit;

    public double longitud=0.0;
    public double latitud=new Double("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lat = findViewById(R.id.lat);
        longit = findViewById(R.id.longit);

        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());


        if(status == ConnectionResult.SUCCESS){

            apiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);


            mapFragment.getMapAsync(this);


            //LocalnegocioController localnegocioController = new LocalnegocioController();
            //localnegocioController.start();



        }else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity)getApplicationContext(),10);
            dialog.show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-12.057108, -77.079939);
        LatLng sydney1 = new LatLng(-12.057229, -77.079801);

        //Log.e("VALORES ON MAP", String.valueOf(lastLocation.getLatitude()));
        //Log.e("VALORES ON MAP", String.valueOf(lastLocation.getLongitude()));

        //LatLng ubicac = new LatLng(latitud,longitud);


        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Marker in Sydney 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //mMap.addMarker(new MarkerOptions().position(ubicac).title("Marker in Sydney 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        float zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomlevel));
        //mMap.moveCamera(CameraUpdateFactory(sydney));
    }

    //AQUI INICIA LA GEOLOCALIZACION

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.

        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services

        Log.e("VALORES", "esta POR ENTRAR ALV :´V");
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);

        } else {

            //Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            //FusedLocationProviderClient listLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            FusedLocationProviderClient listLocation = LocationServices.getFusedLocationProviderClient(this);

            Task<Location> locationResult= listLocation.getLastLocation();


            /*
            locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if(task.isSuccessful()){
                        lastLocation = task.getResult();
                        Log.e("VALORES", String.valueOf(lastLocation.getLatitude()));
                        Log.e("VALORES", String.valueOf(lastLocation.getLongitude()));
                        LatLng ubicac = new LatLng(latitud,longitud);
                        mMap.addMarker(new MarkerOptions().position(ubicac).title("Marker in Sydney 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                    }else{
                        Log.e("VALORES", "TASK ISN'T COMPLETE");
                    }

                }
            });
            */
            locationResult.addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    lastLocation = location;
                    Log.e("VALORES", String.valueOf(lastLocation.getLatitude()));
                    Log.e("VALORES", String.valueOf(lastLocation.getLongitude()));
                    LatLng ubicac = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(ubicac).title("Marker in Sydney 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    float zoomlevel = 16;
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicac,zoomlevel));
                }
            });


            //latitud = lastLocation.getLatitude();
            //longitud = lastLocation.getLongitude();
            //updateUI(lastLocation);


        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services
        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }
    /*
    private void updateUI(Location loc) {
        if (loc != null) {
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
        } else {
            lblLatitud.setText("Latitud: (desconocida)");
            lblLongitud.setText("Longitud: (desconocida)");
        }
    }
    */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido

                @SuppressWarnings("MissingPermission")
                Location lastLocation =
                        LocationServices.FusedLocationApi.getLastLocation(apiClient);

                //updateUI(lastLocation);
                //latitud = lastLocation.getLatitude();
                //longitud = lastLocation.getLongitude();



                /////
            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }








}
