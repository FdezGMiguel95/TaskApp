package com.example.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        dLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dLayout, tb,
                R.string.Open_nav, R.string.Close_nav);
        dLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new ContactsFragment()).commit();
            navView.setCheckedItem(R.id.nav_contacts);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_contacts){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new ContactsFragment()).commit();
        }else if (item.getItemId() == R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new AboutFragment()).commit();
        }else if (item.getItemId() == R.id.nav_settings){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, new SettingsFragment()).commit();
        }else {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        dLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (dLayout.isDrawerOpen(GravityCompat.START)){
            dLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}