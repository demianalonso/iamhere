package com.scvsoft.iamhere;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Place {

    private String name;
    private Location center;
    private double radiusInMeters;
    private boolean isInside = false;

    public Place() {
        center = new Location("fixedprovider");
        center.setLatitude(-34.602612);
        center.setLongitude(-58.454544);

        radiusInMeters = 100;

        name = "Oficinas SCV";
    }

    public boolean updateIsInside(boolean isInside) {
        boolean hasChanged = this.isInside != isInside;
        this.isInside = isInside;
        return hasChanged;
    }

    public boolean contains(Location location) {
        return center.distanceTo(location) <= radiusInMeters;
    }

    public double getRadius() {
        return radiusInMeters;
    }

    public LatLng getLatLng() {
        return new LatLng(center.getLatitude(), center.getLongitude());
    }

    public boolean onLocationChanged(Location location) {
        return updateIsInside(this.contains(location));
    }

    public boolean isInside() {
        return isInside;
    }
}
