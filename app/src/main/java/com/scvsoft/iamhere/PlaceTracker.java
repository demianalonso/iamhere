package com.scvsoft.iamhere;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class PlaceTracker {
    private List<Place> places = new ArrayList<>();

    private boolean hasChanged = false;

    public PlaceTracker(GeofenceListener listener) {
        places.add(new Place(listener));
    }

    public void onLocationChanged(Location location) {
        hasChanged = false;
        for (Place place : places) {
            hasChanged = hasChanged || place.onLocationChanged(location);
        }
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public List<Place> getPlaces() {
        return places;
    }

}
