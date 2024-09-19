package com.example.functionaltraining.Presentation.Dash.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.functionaltraining.DataAccess.SharedPreferences.SessionManager;
import com.example.functionaltraining.Presentation.AccessAcount.MainActivity;
import com.example.functionaltraining.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.functionaltraining.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SessionManager sessionManager;
    private ImageView logoutIcon;
    private ActivityHomeBinding binding;
    private TextView nameDrawer, emailDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameDrawer = binding.navView.getHeaderView(0).findViewById(R.id.tv_name_nav_header);
        emailDrawer = binding.navView.getHeaderView(0).findViewById(R.id.tv_email_nav_header);

        setSupportActionBar(binding.appBarHome.toolbar);

        // Referencia al icono de logout
        logoutIcon = findViewById(R.id.logout_icon);

//        binding.appBarHome.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_plan, R.id.nav_rutinas, R.id.nav_dias, R.id.nav_ejercicios, R.id.nav_diario, R.id.nav_alimentos)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager = new SessionManager(getBaseContext());
        emailDrawer.setText(sessionManager.getUserEmail());
        displaySesion();
        logoutIcon.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), getString(R.string.user)+sessionManager.getUserEmail()+getString(R.string.user_deslogueado), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            sessionManager.logout();
        });
    }

    private void displaySesion(){
        if (!sessionManager.isLoggedIn()) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), getString(R.string.user) + sessionManager.getUserEmail() + getString(R.string.user_deslogueado), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}