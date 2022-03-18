package com.example.olx_bare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class newr extends FragmentActivity implements OnMapReadyCallback {
    RequestQueue queue;
    GoogleMap mMap;
  float lat= 35,longi=35;
    TextView head,details,price,address;
    ImageView img;
    String TAG ="Newr Activity";
    String header,contact;
    int sid;
    int lid;
    String pric;
    int la;
    int lo;
Button ns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newr);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
     SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
              .findFragmentById(R.id.map);
     mapFragment.getMapAsync(this);
        head= findViewById(R.id.heading);
        details = findViewById(R.id.Details);
        img = findViewById(R.id.image);
        price=findViewById(R.id.priceu);
        address=findViewById(R.id.Addressed);
        contact= getIntent().getExtras().getString("contact");
    ns=findViewById(R.id.button9);
    ns.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i2 = new Intent(newr.this,Report.class);
            lid =getIntent().getExtras().getInt("lid");
            i2.putExtra("lid",lid);
            startActivity(i2);
            finish();
        }
    });

    }


    public void onMapReady(GoogleMap googleMap) {da n = new da();
     //   String url = n.URL+"readCourses.php";
        mMap = googleMap;
        header=getIntent().getExtras().getString("head");
        head.setText(header);
        String details2= getIntent().getExtras().getString("Details");
        details.setText(details2);
        //load an imge using picasso
        String lin = getIntent().getExtras().getString("imglink");
        da l = new da();
        pric = getIntent().getExtras().getString("price");
        price.setText("Expected Price :-"+pric);
        String addr = getIntent().getExtras().getString("address");
        address.setText(addr);
        String ps= l.URL+"upload/"+lin;
        Picasso.with(this).load(lin).into(img);

            System.out.print((header+details));
        //float l2 = 35;
        // Add a marker in Sydney and move the camera
        //LatLng Kerala = new LatLng(l,l2);
        // int l = getIntent().getExtras().getInt("head");
        //  System.out.print(l);
        // Log.d("TAG", "onMapReady: lat"+l1); Log.d("TAG", "onMapReady: long"+l2);
        Log.d(TAG, "onMapReady: lat "+lat);
        Log.d(TAG, "onMapReady: longi "+longi);
        sid = getIntent().getExtras().getInt("sid");
        lid =getIntent().getExtras().getInt("lid");
        Log.d(TAG, "onMapReady: sid "+sid);
        Log.d(TAG, "onMapReady: Listing id "+lid);

        longi =getIntent().getExtras().getFloat("longi");
        lat = getIntent().getExtras().getFloat("lat");

        Button ki= findViewById(R.id.button2);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        String ch =Integer.toString(me);
        if(sid==me){
            ki.setEnabled(false);
        }
        /* StringRequest gets = new StringRequest( Request.Method.POST,url,new com.android.volley.Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

                JSONObject jsonObject ;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if (!(jsonObject.getString("head") == null)) {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
*/


        // Log.d("maps", "onMapReady: "+l);
        // la = (int) lat;
       //  lo  = (int)longi;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, longi);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Seller Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void gomaps(View v){
        Uri navigationIntentUri = Uri.parse("google.navigation:q=" + lat +"," + longi);//creating intent with latlng
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    public void m(View v){
        // String me=getIntent().getExtras().getString("me");
        //   String pwd=getIntent().getExtras().getString("pwd");
        Intent intent = new Intent(newr.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
    public void mse(View v){
       Intent intent = new Intent(newr.this, messages.class);
        Log.d("TAG", "mse: ok");
        intent.putExtra("head",header);
        intent.putExtra("sid",sid);
        intent.putExtra("lid",lid);
        intent.putExtra("lid",lid);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(newr.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
    public void Callsel(View v){
       // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ""+pric));
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+contact));
        startActivity(intent);
    }




}
/* Scraped need for arrayrequest

JsonArrayRequest jar = new JsonArrayRequest(url,
                responce -> {
                    try {
                        parse_data(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(newr.this, "data get error", Toast.LENGTH_SHORT).show());
        queue = Volley.newRequestQueue(newr.this);
        queue.add(jar);

    }

    private void parse_data(JSONArray jarray) throws JSONException {

            JSONObject jos = jarray.getJSONObject(0);
            Listing l = new Listing();
            String link=jos.getString("imagelink");
            String Deta= jos.getString("long_details");
            String Tit=jos.getString("Listing_title");
            float  longi =(float) jos.getDouble("longi");
            float lat=(float) jos.getDouble("lat");
           int slid=jos.getInt("sellerid");
            int Li =jos.getInt("Lid");

        }*/