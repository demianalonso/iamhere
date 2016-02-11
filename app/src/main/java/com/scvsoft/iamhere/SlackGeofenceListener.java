package com.scvsoft.iamhere;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SlackGeofenceListener implements GeofenceListener {
    private RequestQueue mRequestQueue;

    public SlackGeofenceListener(RequestQueue mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void onEnterGeofence(final Place place) {

        try {
            String url = "https://hooks.slack.com/services/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";

//        [11/02/2016, 16:08] Pablo J. Bertola: @""];
//        [11/02/2016, 16:08] Pablo J. Bertola: @"Content-Type"  : @"application/json"

//        @"Test api integration from iOS app", @"text",
//        @"Pablo J. Bertola", @"username",
//        @"https://avatars.slack-edge.com/2015-07-07/7349085092_8312ade20e4c8d6327f6_192.jpg", @"icon_url",nil];

            JSONObject body = newRequestBody(place);

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, url, body, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(SlackGeofenceListener.class.getSimpleName(), response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(SlackGeofenceListener.class.getSimpleName(), place.getMessage() + ": " + error.getMessage());
                        }
                    });

            mRequestQueue.add(jsObjRequest);
        } catch (JSONException e) {
            Log.e(SlackGeofenceListener.class.getSimpleName(), "Error creating request body", e);
        }
    }

    private JSONObject newRequestBody(Place place) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("text", place.getMessage());
        body.put("username", "Some Android User...");
        body.put("icon_url", "http://g02.a.alicdn.com/kf/HTB1vnl8IFXXXXcGaXXXq6xXFXXXm/Caliente-venta-de-Halloween-navidad-m%C3%A1scara-V-Vendetta-Anonymous-pel%C3%ADcula-de-Guy-Fawkes-Vendetta-m%C3%A1scara-Cosplay.jpg_50x50.jpg");

        return body;
    }

    @Override
    public void onLeaveGeofence(Place place) {
        this.onEnterGeofence(place);
    }
}
