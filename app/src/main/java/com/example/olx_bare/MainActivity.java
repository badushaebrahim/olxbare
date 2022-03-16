package com.example.olx_bare;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bm;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Fragment frag = null;
    FloatingActionButton mAddAlarmFab, mAddPersonFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addAlarmActionText, addPersonActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible, testr = true;
    Spinner dropdown;
    ViewPager viewpager;
    //new
    RequestQueue queue;
    RecyclerView reses;
    RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;
    List<Listing> Liste;

    //end new

    // ViewPagerAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /// bm = findViewById(R.id.bottenav);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
      //  Toast.makeText(getApplicationContext(), "me" + me, LENGTH_SHORT).show();
        make();
        p1();
        //frags();

        // View view = inflater.inflate(R.layout.fragment_first, container, false);

        //bm.setSelectedItemId(R.id.product);


    }

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "hello", LENGTH_SHORT).show();

        super.onBackPressed();
    }

    public void addpord(View v) {

        //startActivity(i);
    }

    void frags() {
       // getSupportFragmentManager().beginTransaction().replace(R.id.mainco, new first()).commit();
    }

    void make() {
        //no app bar
        // try
        // {
        this.getSupportActionBar().hide();
        // }
        // catch (NullPointerException e){}
//no appbar
// We can then use the data

        Log.d("TAG", "onCreate: id of user ");
        //shit
        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        //mAddPersonFab = findViewById(R.id.add_person_fab);
        mAddFab = findViewById(R.id.add_fab);
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        // mAddPersonFab = findViewById(R.id.add_person_fab);
        addAlarmActionText =
                findViewById(R.id.add_alarm_action_text);
        //addPersonActionText =
        // findViewById(R.id.add_person_action_text);
        // Now set all the FABs and all the action name
        // texts as GONE
        mAddAlarmFab.setVisibility(View.GONE);
        // mAddPersonFab.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        // addPersonActionText.setVisibility(View.GONE);
        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are
        // invisible
        isAllFabsVisible = false;
        // Set the Extended floating action button to
        // shrinked state initially
        mAddFab.shrink();
        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            mAddAlarmFab.show();
                            // mAddPersonFab.show();
                            addAlarmActionText
                                    .setVisibility(View.VISIBLE);
                            // addPersonActionText
                            //   .setVisibility(View.VISIBLE);

                            // Now extend the parent FAB, as
                            // user clicks on the shrinked
                            // parent FAB
                            mAddFab.extend();
                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {
                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            mAddAlarmFab.hide();
                            //  mAddPersonFab.hide();
                            addAlarmActionText
                                    .setVisibility(View.GONE);
                            // addPersonActionText
                            //.setVisibility(View.GONE);
                            // Set the FAB to shrink after user
                            // closes all the sub FABs
                            mAddFab.shrink();
                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });
        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
      /*  mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText
                                (Success
                                                .this, "Person Added",
                                        Toast.LENGTH_SHORT).show();
                    }
                });*/
        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, addprod.class);
                        intent.putExtra("me", "rumba");
                        startActivity(intent);
                        onPause();
                    }
                });
        //shit here
        // new2.setText(me);
        //new3.setText(pwd);
        //get_data

//set the spinners adapter to the previously created one.
        dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{" ","Account", "Listings", "Message", "Logout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                items);
////set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        // dropdown.onClick();
        //TextView textView = (TextView)dropdown.getSelectedView();
        ///* String result = textView.getText().toString();
       /* switch (result){
            case " ":
                break;
            case "My Listings":
                Toast.makeText(getApplicationContext(), "my listing", LENGTH_SHORT).show();
                break;
            case "Message":
                Toast.makeText(getApplicationContext(), "message", LENGTH_SHORT).show();
                break;
            case "Logout":
                Logout();
                break;
        }*/
       // viewpager = (ViewPager) findViewById(R.id.vpPager);

        /*bm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag =null;
                            switch (item.getItemId()){
                case R.id.product:
                    Toast.makeText(getApplicationContext(),"selected first frag",Toast.LENGTH_SHORT).show();
                 frag = new first();

                    break;
                case R.id.service:
                    Toast.makeText(getApplicationContext(),"selected second frag",Toast.LENGTH_SHORT).show();

                    frag = new secondFragment();

                    break;
            }
          //  getSupportFragmentManager().beginTransaction().remove(R.id.vpPager,frag).commit();

            getSupportFragmentManager().beginTransaction().replace(R.id.vpPager,frag).commit();
            return true;
        }
    });*/
//kill
        // DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        //  actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        //   drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //  actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) dropdown.getSelectedView();
                String result = textView.getText().toString();
                switch (result) {
                    case " ":
                        break;
                    case "Account":
                        Actions("Account");
                        break;
                    case "Listings":
                        //Toast.makeText(getApplicationContext(), "my listing", LENGTH_SHORT).show();
                        Actions("Listings");
                        break;
                    case "Message":
                        Actions("Message");
                        //Toast.makeText(getApplicationContext(), "message", LENGTH_SHORT).show();
                        break;
                    case "Logout":
                        Actions("Login");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
  void setrec(){
        reses=findViewById(R.id.mydraw);

    reslay = new LinearLayoutManager(this);
    reses.setLayoutManager(reslay);
    Liste = new ArrayList<>();
}
    public void p1(){setrec();
    da n = new da();
        JsonArrayRequest jar = new JsonArrayRequest(n.URL + "getdata.php",
                responce -> {
                    try {
                        new da(responce.toString());
                        parse_data(responce);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(MainActivity.this, "data get error", Toast.LENGTH_SHORT).show());
        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jar);
    }
    public void parse_data (JSONArray jarray) throws JSONException {
        int i=0;
        for (i = 0; i < jarray.length(); i++) {
            JSONObject jos = jarray.getJSONObject(i);
            Listing l = new Listing();
            l.setLink(jos.getString("imagelink"));
            l.setDetail(jos.getString("long_details"));
            l.setHead(jos.getString("Listing_title"));
            l.setLat((float) jos.getDouble("latitude"));
            l.setLongi((float) jos.getDouble("longi"));
            l.setSellerid(jos.getInt("sellerid"));
            l.setNumber(jos.getString("contactno"));
            l.setAddress(jos.getString("address"));
            l.setType(jos.getString("type"));
            l.setLid(jos.getInt("Lid"));
            l.setprice(jos.getString("expected_price"));
            Liste.add(l);
          //  System.out.println(l.getHead());
        }
        System.out.println("runs in first"+i);
        Log.d("TAG", "parse_data: "+i);
        Toast.makeText(getApplicationContext(), "at p-arese end", LENGTH_SHORT).show();
        //adapter
        resinf rar = new resinf(Liste, this);
        reses.setAdapter(rar);
    }

    public void Actions(String where) {
        switch (where) {
            case "Login":
                killpref();//custom functin remove shared prefrenc variable stored inside
                Intent intent = new Intent(MainActivity.this, Login.class);
                //intent.putExtra("lname",header);
                //intent.putExtra("rid",);
                startActivity(intent);
                finish();
                break;
            case "Listings":
                Intent intent2 = new Intent(MainActivity.this, MyListings.class);
                //intent.putExtra("lname",header);
                // intent.putExtra("rid",);
                startActivity(intent2);
                finish();
                break;
            case "Message":
                Intent intent22 = new Intent(MainActivity.this, messageslist.class);
                //intent.putExtra("lname",header);
                // intent.putExtra("rid",);
                startActivity(intent22);
                finish();
                break;
            case "Account":
                Intent inr = new Intent(MainActivity.this,Editaccount.class);
                startActivity(inr);
                finish();
                break;
        }
    }

    @Override
    protected void onRestart() {
        setContentView(R.layout.activity_main);
        /// bm = findViewById(R.id.bottenav);
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        int me = sh.getInt("uid", 0);
        //Toast.makeText(getApplicationContext(), "me" + me, LENGTH_SHORT).show();
        //make();
       // frags();

        super.onRestart();
    }

    void killpref() {
        SharedPreferences prefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

    }


}