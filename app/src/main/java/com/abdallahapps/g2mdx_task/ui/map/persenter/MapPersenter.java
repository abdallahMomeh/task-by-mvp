package com.abdallahapps.g2mdx_task.ui.map.persenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.APP;
import com.abdallahapps.g2mdx_task.generalUtils.Utils;
import com.abdallahapps.g2mdx_task.ui.map.view.MapView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;

public class MapPersenter implements LocationListener {

    MapView mapView ;
    LocationManager locationManager;

    public MapPersenter (MapView mapView){
        this.mapView=mapView;
        locationManager = (LocationManager) APP.context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void getStoresLocation(){
        List<Location> locations = new ArrayList<>();
        mapView.showNearestSotres(locations);

        /* String type = "store";  ///placeText.getText().toString();
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&types=" + type);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + getString(R.string.GOOGLE_API_KEY));

        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = googlePlacesUrl.toString();
        googlePlacesReadTask.execute(toPass);
*/

    }



    public void getCurreentLocation() {
        Location location = null;
        if (isProviderEnabled()){

            if (ActivityCompat.checkSelfPermission(APP.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(APP.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            Toast.makeText(APP.context, "get currentLocation", Toast.LENGTH_SHORT).show();
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);

        }else {
            Toast.makeText(APP.context, "افتح GPS أولا", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isProviderEnabled(){
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return true;
        }else
            return false;
    }

    public boolean checkLocationPermission(){
        if (Build.VERSION.SDK_INT>=23){
            if (Utils.checkPermission(APP.context, Manifest.permission.ACCESS_COARSE_LOCATION)){
                return true;
            }else
                return false;
        }else
            return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        mapView.showMyLocation(location);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
