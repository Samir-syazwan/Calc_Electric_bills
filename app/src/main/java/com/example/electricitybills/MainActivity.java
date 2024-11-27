package com.example.electricitybills;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tvFinCharges, tvTotCharges;
    EditText etKWH, etRebate;
    Button submit;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit);
        tvFinCharges = findViewById(R.id.tvFinCharges);
        tvTotCharges = findViewById(R.id.tvTotCharges);
        etKWH = findViewById(R.id.etKWH);
        etRebate = findViewById(R.id.etRebate);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Display totBill
        submit.setOnClickListener(view -> {
            String input = etKWH.getText().toString();
            String inputR = etRebate.getText().toString();
            try {
                double units = Double.parseDouble(input);
                double totBill = calcBill(units);
                tvTotCharges.setText(String.format("Total Charges: RM %.2f", totBill));
                double rebatePer = inputR.isEmpty() ? 0 : Double.parseDouble(inputR);

                if (rebatePer <0 || rebatePer >5){
                    tvFinCharges.setText(String.format("No Rebate for %.2f%%", rebatePer));
                    Toast.makeText(MainActivity.this,"Rebate must be between 0-5", Toast.LENGTH_SHORT).show();
                    return;
                }
                double finBill = totBill - (totBill * rebatePer/100);
                tvFinCharges.setText(String.format("Final Charges (after %.2f%% rebate): RM %.2f", rebatePer, finBill));
            } catch (NumberFormatException nfe) {
                Toast.makeText(MainActivity.this, "Please Insert Valid Units and Rebate", Toast.LENGTH_SHORT).show();
            }
        });

        //Action toolbar
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
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }


    //Block calculation
    private double calcBill(double unitConsume) {
        double totBill = 0;

        if (unitConsume <= 200) {
            totBill += unitConsume * 0.218;
        } else if (unitConsume <= 300) {
            totBill += 200 * 0.218;
            totBill += (unitConsume - 200) * 0.334;
        } else if (unitConsume <= 600) {
            totBill += 200 * 0.218;
            totBill += 100 * 0.334;
            totBill += (unitConsume - 300) * 0.516;
        } else {
            totBill += 200 * 0.218;
            totBill += 100 * 0.334;
            totBill += 300 * 0.516;
            totBill += (unitConsume - 600) * 0.546;
        }

        return totBill;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int selected = item.getItemId();

        if (selected == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (selected == R.id.about_page) {
            Intent intent = new Intent(MainActivity.this, AboutPage.class);
            startActivity(intent);
        }

        return true;
    }

}

