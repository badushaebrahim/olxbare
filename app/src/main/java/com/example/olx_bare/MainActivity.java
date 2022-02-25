package com.example.olx_bare;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bm ;
    Fragment frag = null;
    FloatingActionButton mAddAlarmFab, mAddPersonFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addAlarmActionText, addPersonActionText;
    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible,testr=true;
    ViewPager viewpager;
   // ViewPagerAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bm = findViewById(R.id.bottenav);

       // View view = inflater.inflate(R.layout.fragment_first, container, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainco,new first()).commit();
    bm.setSelectedItemId(R.id.product);

            //no app bar
            try
            {
                this.getSupportActionBar().hide();
            }
            catch (NullPointerException e){}
//no appbar
// We can then use the data

        Log.d("TAG", "onCreate: id of user " );
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
                       // Intent intent = new Intent(Success.this, addprod.class);
                        // intent.putExtra("me", "rumba");
                        //startActivity(intent);
                        //finish();
                    }
                });
        //shit here
        // new2.setText(me);
        //new3.setText(pwd);
        //get_data
       Spinner dropdown=findViewById(R.id.spinner);
        String[] items = new String[]{" ","Product", "Service"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.

        dropdown.setAdapter(adapter);
        viewpager =(ViewPager) findViewById(R.id.vpPager);

        bm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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
    });


    }
    }