package com.scvsoft.iamhere;

public interface GeofenceListener {
    void onEnterGeofence(Place place);

    void onLeaveGeofence(Place place);
}
