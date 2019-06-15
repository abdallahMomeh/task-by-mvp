package com.abdallahapps.g2mdx_task.ui.map.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.generalUtils.GooglePlacesReadTask;
import com.abdallahapps.g2mdx_task.generalUtils.Utils;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.map.persenter.MapPersenter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MapActivity extends BaseActivity implements MapView{

    MapPersenter mapPersenter;
    // Google Map
    private GoogleMap googleMap;

    private int PROXIMITY_RADIUS = 5000;
    public static final int LocationRequest=100;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        APP.context=this;
        mapPersenter = new MapPersenter(this);

        try {
            // Loading map
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white_24);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initilizeMap() {
        if (googleMap == null) {

            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    googleMap = map;
                    if (Utils.checkPermission(MapActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)){
                        mapPersenter.getCurreentLocation();
                    }else {
                        Utils.permissionGrant(MapActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION,LocationRequest);
                    }

                }
            });

        }

    }


    @Override
    public void onSuccess() { }

    @Override
    public void onError(int type) {
    }



    @Override
    public void showNearestSotres(List<Location> storesLocation) {

    }



    @Override
    public void showMyLocation(Location myLocation) {

        mapPersenter.getStoresLocation();            // get nearest stores
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

        LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        googleMap.animateCamera(cameraUpdate);

        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).draggable(true));

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==LocationRequest){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
                mapPersenter.getCurreentLocation();
            }else
                Toast.makeText(this, "التطبيق يحتاج أذن لتحديد المكان", Toast.LENGTH_SHORT).show();
        }
    }
}
