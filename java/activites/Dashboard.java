package com.example.bloodbank.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.fragments.AboutUs;
import com.example.bloodbank.fragments.AchievmentsView;
import com.example.bloodbank.fragments.BlankFragment;
import com.example.bloodbank.fragments.BloodInfo;
import com.example.bloodbank.fragments.HomeView;
import com.example.bloodbank.fragments.NearByHospitalActivity;
import com.example.bloodbank.fragments.SearchDonorFragment;
import com.example.bloodbank.viewmodels.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    private TextView getUserEmail,getUserName;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);





        mAuth=FirebaseAuth.getInstance();

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,PostActivity.class));
            }
        });

         drawerLayout=findViewById(R.id.drawer_layout);
         ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
         );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);

        getUserEmail=header.findViewById(R.id.UserEmailView);
        getUserName=header.findViewById(R.id.UserNameView);


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new HomeView()).commit();
            navigationView.getMenu().getItem(0).setChecked(true);
        }

        fetchData();


    }

    private void fetchData() {

        pd.show();

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");



         databaseReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {

             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  String name = dataSnapshot.getValue(UserData.class).getName();

                 getUserName.setText(name);
                 getUserEmail.setText(mAuth.getCurrentUser().getEmail());
                 pd.dismiss();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 pd.dismiss();
             }

         });

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        authenticated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        authenticated();
    }

    private void authenticated(){
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser ==null){
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        Fragment fragment=null;
        switch (id){
            case R.id.home_page:
               fragment=new HomeView();
              break;
            case R.id.userprofile:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
               return true;
            case R.id.user_achiev:
                fragment=new AchievmentsView();
                    break;
            case R.id.logout:
                mAuth.signOut();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.blood_storage:
                fragment=new SearchDonorFragment();
                break;
            case R.id.nearby_hospital:
                fragment=new NearByHospitalActivity();
                break;
        }

         getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();

         drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.donateinfo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new BloodInfo()).commit();
                break;
            case R.id.devinfo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new AboutUs()).commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
