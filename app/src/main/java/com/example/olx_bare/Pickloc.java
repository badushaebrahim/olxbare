package com.example.olx_bare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LongSparseArray;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

    public class Pickloc extends AppCompatActivity implements OnMapReadyCallback {
        private GoogleMap mMap;
        Geocoder geocoder;
        String LATTITUDE = "0.0", LONGITUDE = "0.0", MAPADDRESS, NAME, OWNERNAME, CONTACT, LICENSE, EMAILID, PASSWORD;
        List<Address> addresses;
        EditText mapaddress;
        Button mapbtn;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) { //no app bar
            try
            {
                this.getSupportActionBar().hide();
            }
            catch (NullPointerException e){}
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pickloc);
            //Obtain the SupportMapFragment and get notified when the map is ready to be used.
           SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            mapaddress = findViewById(R.id.map_address);
            mapbtn = findViewById(R.id.map_btn);
            geocoder = new Geocoder(this, Locale.getDefault());
            addresses = new ArrayList<>();
            mapbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MAPADDRESS = mapaddress.getText().toString();
                    mapAction();
                }
            });
        }
    
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
            final LatLng aluva = new LatLng(10.1107322, 76.3480637);
            float zoomLevel = 13.0f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aluva, zoomLevel));
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    try {
                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                        Log.d("***", "\n"    + addresses.toString());
                        if (addresses.size() > 0) {
                            String address = addresses.get(0).getAddressLine(0);
                            mapaddress.setText(address);
                        }
    
    //                    String arr1[] = addresses.toString().split(":");
    //
    //                    Log.d("***", "\n arr 1  " + arr1[1]);
    //
    //                    String arr2[] = arr1[1].split("]");
    //                    Log.d("***", "\n arr 2  " + arr2[0]);
    //                    mapaddress.setText(arr2[0]);
    
    
                        LATTITUDE = "" + latLng.latitude;
                        LONGITUDE = "" + latLng.longitude;
    //                    Toast.makeText(MyMap.this, ""+arr2[0]+"", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Log.d("***", e + "\n" + addresses.toString());
                        e.printStackTrace();
                    }
                    mMap.addMarker(new MarkerOptions().position(latLng).title(addresses.toString()).snippet("My Loc").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    //                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    float zoomLevel = 13.0f; //This goes up to 21
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aluva, zoomLevel));
                }
            });

        }
        public void mapAction() {
            da n= new da();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, n.URL + "regapi.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.print(response);
                String TAG = "regisrer";
                if (response.trim().equals("success")) {
                    Log.d(TAG, "onResponse: uss");
                    Intent i = new Intent(Pickloc.this,
                            Login.class);
                       /*  i . p u T E x t r a ("n a  m e ", n a m e);
                        i.p u t E x t r a("pwd",pwd);
                        i.putExtra("email",email);
                        i.putExtra("number",phoneno);
                        i.putExtra("address",address);
                        */
                    startActivity(i);
        finish();


                } else if (response.trim().equals("failure")) {
                    Log.d(TAG, "onResponse: fil");


                }
                Log.d(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                NAME=getIntent().getExtras().getString("name");
                data.put("name", NAME);
                EMAILID=getIntent().getExtras().getString("email");
                data.put("email", EMAILID);
                PASSWORD=getIntent().getExtras().getString("password");
                data.put("password", PASSWORD);
                MAPADDRESS=getIntent().getExtras().getString("address");
                data.put("address", MAPADDRESS);
                String phoneno =getIntent().getExtras().getString("number");
                data.put("number", phoneno);
               data.put("lati", LATTITUDE);
              data.put("longi", LONGITUDE);
                return data;
            }
        };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }


    }