package com.abdallahapps.g2mdx_task.ui.map.view;

import android.location.Location;

import com.abdallahapps.g2mdx_task.ui.base.view.BaseView;

import java.util.List;

public interface MapView extends BaseView {

    void showNearestSotres(List<Location> storesLocation);
    void showMyLocation(Location myLocation);
}
