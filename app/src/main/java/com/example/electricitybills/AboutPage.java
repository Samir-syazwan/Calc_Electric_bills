package com.example.electricitybills;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AboutPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Action toolbar

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        TextView etUrl = findViewById(R.id.etUrl);
        etUrl.setMovementMethod(LinkMovementMethod.getInstance());

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int selected = item.getItemId();

        if (selected == R.id.nav_home) {
            Intent intent = new Intent(AboutPage.this, MainActivity.class);
            startActivity(intent);
        } else if (selected == R.id.about_page) {
            Intent intent = new Intent(AboutPage.this, AboutPage.class);
            startActivity(intent);
        }
        return true;
    }
}