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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.project.pentacode.pomestaff.model.LoginUser;
import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
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

        ImageButton btnQRCode = findViewById(R.id.main_content).findViewById(R.id.btn_qrcode);

        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,QRCodeActivity.class);
                startActivityForResult(intent,1);
            }
        });

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
            finish();
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

    public void changeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container.getId(),fragment);
        ft.commit();
    }

    private void showProjectByQRCode(Project project){
        SharedPreferences sp = MainActivity.this.getSharedPreferences("PROJECT",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("position_id",project.getPosition_id());
        editor.putInt("step",project.getStepWorkOn());
        editor.putInt("project",project.getIdProject());
        editor.apply();

        Intent intent = new Intent(this,ProjectDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String qrcode = data.getStringExtra("qrcode");
                int idProject = 0;
                try {
                    JSONObject jsonObject = new JSONObject(qrcode);
                    idProject = jsonObject.getInt("project_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
                Call<Project> call = serviceInterface.getProjectQRCode(prefNip,idProject,prefToken);
                call.enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        if (response.isSuccessful()){
                            showProjectByQRCode(response.body());
                            //Toast.makeText(MainActivity.this, response.body().getIdProject()+"", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        }
    }
}
