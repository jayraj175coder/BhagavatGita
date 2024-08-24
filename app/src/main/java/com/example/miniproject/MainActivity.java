package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bttmNav;
    private NavController navController;

    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttmNav = findViewById(R.id.bttmNav);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        NavigationUI.setupWithNavController(bttmNav, navController);
        drawer_layout = findViewById(R.id.drawer_layout);
        navigation_drawer = findViewById(R.id.navigation_drawer);

        toggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close);

        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigation_drawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment temp = null;

        // Check the ID of the selected item and set the appropriate fragment
        if (item.getItemId() == R.id.navigation_v) {
            temp = new video_lec();
            Toast.makeText(this, "Video lectures are clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.navigation_g) {
            temp = new gitaSaar();
            Toast.makeText(this, "Gita Saar is clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.navigation_web) {
            temp = new buyGita();
            Toast.makeText(this, "Visit website", Toast.LENGTH_SHORT).show();
        } else {
            return false; // Handle unexpected cases
        }

        // Replace the fragment if temp is not null
        if (temp != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, temp).commit();
        }

        // Close the drawer
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }}
