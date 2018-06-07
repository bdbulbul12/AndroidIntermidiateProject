package com.example.bulbul.googlemapproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("User Location"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,10));

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try{

                    List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

                    if(listAddress!=null && listAddress.size()>0){
                        Log.i("place info",listAddress.get(0).toString());
                        String address="";

                        if(listAddress.get(0).getSubThoroughfare() !=null){

                            address +=listAddress.get(0).getSubThoroughfare() + " ";
                        }

                        if(listAddress.get(0).getSubThoroughfare() !=null){

                            address +=listAddress.get(0).getSubThoroughfare() + " ,";
                        }

                        if(listAddress.get(0).getLocality() !=null){
                            address +=listAddress.get(0).getLocality() + " ,";
                        }

                        if(listAddress.get(0).getPostalCode() !=null){
                            address +=listAddress.get(0).getPostalCode() + " ,";
                        }

                        if(listAddress.get(0).getCountryName() !=null){
                            address +=listAddress.get(0).getCountryName() + " ,";
                        }

                           Toast.makeText(MapsActivity.this,address,Toast.LENGTH_LONG).show();

                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(Build.VERSION.SDK_INT>23){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
        }else {

            //Check Permission for marshmallow

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //Ask For permissoion
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("User Location"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));
            }
        }

       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera

    }
}
