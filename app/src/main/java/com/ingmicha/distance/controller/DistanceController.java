package com.ingmicha.distance.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ingmicha.distance.fragment.DistanceView;

import org.json.JSONObject;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/30/18.
 */
public class DistanceController {

    private Context context;
    private String from;
    private String to;
    private DistanceView distanceView;

    public DistanceController(Context context, String from, String to, DistanceView distanceView) {
        this.context = context;
        this.from = from;
        this.to = to;
        this.distanceView = distanceView;
    }

    public void result(){

        RequestQueue queue = Volley.newRequestQueue(context);

        final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+from+"&destinations="+to+"&key=AIzaSyDuefJ5CqZIEgSW-1FaroNtzkCztUi-FU0";
        //final String url =
                //"https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=AIzaSyDuefJ5CqZIEgSW-1FaroNtzkCztUi-FU0";

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());

                        JsonObject j = new JsonParser().parse(response.toString()).getAsJsonObject();

                        JsonArray rows = j.get("rows").getAsJsonArray();

                        JsonArray elemets = rows.get(0).getAsJsonObject().get("elements").getAsJsonArray();

                        String distance = elemets.get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").getAsString();

                        String duration = elemets.get(0).getAsJsonObject().get("duration").getAsJsonObject().get("text").getAsString();

                        distanceView.showDistanceDetail(distance,duration);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.networkResponse.toString());
                        distanceView.showDistanceError();
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);
    }
}
