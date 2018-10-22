package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.LoggingMXBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainProjectFragment.OnListProjectInteraction,NavigationView.OnNavigationItemSelectedListener {
    MainProjectFragment projectFragment;
    MainDashboardFragment dashboardFragment;
    String prefNip;
    String prefNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        //getShared
        SharedPreferences sp1=this.getSharedPreferences("LoginData", MODE_PRIVATE);

        prefNip = sp1.getString("nip", "kosong");
        prefNama = sp1.getString("nama", "kosong");
        //Toast.makeText(this, prefNip, Toast.LENGTH_SHORT).show();

        Staff staff = ModelGenerator.getStaff(prefNip);
        Toast.makeText(this, staff.email, Toast.LENGTH_SHORT).show();

        //ganti email di header navdraw
        TextView txtEmail = headerView.findViewById(R.id.navdraw_email);
        txtEmail.setText(staff.email);


        projectFragment = MainProjectFragment.newInstance(getMyProjects(ModelGenerator.getProjects()));

        dashboardFragment = new MainDashboardFragment();

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Dashboard",
                        "Project",
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                if (position==0)
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, dashboardFragment)
                            .commit();
                else
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container,projectFragment)
                            .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


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

        if (id == R.id.navdraw_myprofile) {
            startActivity(new Intent(this,ProfileActivity.class));
        } else if (id == R.id.navdraw_notifications) {

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

    public boolean isInvolved(Parcelable project){
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
    }

    public ArrayList<Parcelable> getMyProjects(ArrayList<Parcelable> allProjects){
        ArrayList<Parcelable> myProject = new ArrayList<>();

        for(int i = 0;i < allProjects.size();i++){
            if (isInvolved(allProjects.get(i))){
                myProject.add(fixProject(allProjects.get(i)));
            }
        }
        return myProject;
    }

    public Parcelable fixProject(Parcelable project){
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
    }
}
