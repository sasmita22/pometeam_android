package com.project.pentacode.pomestaff;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.LoginUser;
import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainProjectFragment.OnListProjectInteraction,NavigationView.OnNavigationItemSelectedListener {
    MainProjectFragment projectFragment;
    MainDashboardFragment dashboardFragment;
    MainNotificationFragment notificationFragment;
    View container;
    String prefNip;
    String prefNama;
    String prefToken;
    CircleImageView navImage;
    TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //initiate container;
        container = findViewById(R.id.main_content).findViewById(R.id.container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_content).findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //configure drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //get header navdraw view
        View headerView = navigationView.getHeaderView(0);
        navImage = headerView.findViewById(R.id.navdraw_image);
        navEmail = headerView.findViewById(R.id.navdraw_email);

        //getShared
        SharedPreferences sp1=this.getSharedPreferences("LoginData", MODE_PRIVATE);

        prefNip = sp1.getString("nip", "");
        prefToken = sp1.getString("token", null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Staff> staffCall = service.getStaff(prefNip,prefToken);
        staffCall.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                navEmail.setText(response.body().getEmail());
                Glide.with(getApplicationContext())
                        .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+response.body().getImage())
                        .into(navImage);
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        prefNama = sp1.getString("nama", "kosong");
        //Toast.makeText(this, prefNip, Toast.LENGTH_SHORT).show();

        //Staff staff = ModelGenerator.getStaff(prefNip);
        //Toast.makeText(this, staff.email, Toast.LENGTH_SHORT).show();

        //ganti email di header navdraw
        //TextView txtEmail = headerView.findViewById(R.id.navdraw_email);
        //txtEmail.setText(staff.email);



        initFragment();

    }

    private void initFragment(){
        projectFragment = new MainProjectFragment();
        dashboardFragment = new MainDashboardFragment();
        notificationFragment = new MainNotificationFragment();

        //initiate fragment
        changeFragment(projectFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.barcode) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickProjectList(Project project) {
        Toast.makeText(this, project.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.navdraw_dashboard) {
            changeFragment(dashboardFragment);
        } else if (id == R.id.navdraw_myproject) {
            changeFragment(projectFragment);
        } else if (id == R.id.navdraw_notifications) {
            changeFragment(notificationFragment);
        } else if (id == R.id.navdraw_aboutme) {
            startActivity(new Intent(this,ProfileActivity.class));
        } else if (id == R.id.navdraw_logout) {
            startActivity(new Intent(this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }

   /* public boolean isInvolved(Parcelable project){
        Project mProject = (Project) project;
        Step mStep;
        Staff mStaff;
        if (mProject.projectManager.nip.equals(prefNip)){
            return true;
        } else {
            String s = "\n"+mProject.title;
            for (int i = 0; i < mProject.steps.size();i++){
                mStep = mProject.steps.get(i);
                s += "\n   "+mStep.name;
                for (int j=0;j < mStep.team.size();j++){
                    mStaff = mStep.team.get(j);
                    s += "\n      "+mStaff.name;
                    if(mStaff.nip.equals(prefNip)){
                        //Log.d("terlibat",((Project) project).title+" | "+mStep.name+" | "+mStaff.nip+" == "+prefNip);
                        return true;
                    }
                }
            }
            Log.d("step",s);
        }
        return false;
    }*/

   /* public ArrayList<Parcelable> getMyProjects(ArrayList<Parcelable> allProjects){
        ArrayList<Parcelable> myProject = new ArrayList<>();

        for(int i = 0;i < allProjects.size();i++){
            if (isInvolved(allProjects.get(i))){
                myProject.add(fixProject(allProjects.get(i)));
            }
        }
        return myProject;
    }*/

    public void changeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container.getId(),fragment);
        ft.commit();
    }

   /* public Parcelable fixProject(Parcelable project){
        Project mProject = (Project) project;
        Step mStep;
        Staff mStaff;
        String position = null;
        boolean isStaff = false;
        int teamSize = 0;
        String s = "\n "+mProject.title;

        for (int i = 0; i < mProject.steps.size();i++){
            mStep = mProject.steps.get(i);
            teamSize += mStep.team.size();
            for (int j=0;j < mStep.team.size();j++){
                mStaff = mStep.team.get(j);
                if(mStaff.nip.equals(prefNip)){
                    isStaff = true;
                    position = mStep.name;
                }
                s += "\n "+mStaff;
            }
        }

        Log.d("anggota", s);
        if(isStaff){
            mProject.userPosition = position;
        }else{
            mProject.userPosition = "P. Manager";
        }
        mProject.jumlahTeam = teamSize+1;
        return mProject;
    }*/
}
