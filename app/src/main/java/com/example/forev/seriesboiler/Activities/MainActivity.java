package com.example.forev.seriesboiler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.seriesboiler.Fragments.AdviceFragment;
import com.example.forev.seriesboiler.Fragments.CategoryFragment;
import com.example.forev.seriesboiler.Fragments.HomeFragment;
import com.example.forev.seriesboiler.Fragments.MyListFragment;
import com.example.forev.seriesboiler.Fragments.ProfileFragment;
import com.example.forev.seriesboiler.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference reference;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        bundle = new Bundle();

        if(user == null)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this,"Welcome "+user.getUid().toString(),Toast.LENGTH_LONG).show();
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,new HomeFragment()).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View headView = navigationView.getHeaderView(0);

            final TextView firstname = headView.findViewById(R.id.firstnameTextView);
            final TextView email = headView.findViewById(R.id.emailTextView);
            reference.child("Users/").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    firstname.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //email.setText(user.getEmail()); // şimdilik database çektim
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_mylist:
                            selectedFragment = new MyListFragment();
                            break;
                        case R.id.nav_advice:
                            selectedFragment = new AdviceFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment selectedFragment = null;
        int inn=1;

        if (id == R.id.nav_home) {
            selectedFragment = new HomeFragment();
            inn=1;
        } else if (id == R.id.nav_profile) {
            selectedFragment = new ProfileFragment();
            inn=1;
        } else if (id == R.id.nav_exit) {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            inn=0;
        } else if (id == R.id.nav_comedy) {
            bundle.putString("categoryid","1");
            inn=2;
        } else if (id == R.id.nav_sci) {
            bundle.putString("categoryid","2");
            inn=2;
        } else if (id == R.id.nav_crime) {
            bundle.putString("categoryid","3");
            inn=2;
        }else if (id == R.id.nav_mystery) {
            bundle.putString("categoryid","4");
            inn=2;
        }else if (id == R.id.nav_romance) {
            bundle.putString("categoryid","5");
            inn=2;
        }else if (id == R.id.nav_drama) {
            bundle.putString("categoryid","6");
            inn=2;
        }else if (id == R.id.nav_hero) {
            bundle.putString("categoryid","7");
            inn=2;
        }else if (id == R.id.nav_animation) {
            bundle.putString("categoryid","8");
            inn=2;
        }

        if(inn == 1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe,
                    selectedFragment).commit();
        }
        else if(inn == 2)
        {
            selectedFragment = new CategoryFragment();
            selectedFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainframe,selectedFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
