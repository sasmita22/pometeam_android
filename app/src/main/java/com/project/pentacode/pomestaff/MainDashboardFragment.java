package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Dashboard;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDashboardFragment extends Fragment {
    @BindView(R.id.rv_dashboard_task_staff)
    RecyclerView rvDashboardTeam;
    @BindView(R.id.rv_dashboard_task_leader)
    RecyclerView rvDashboardLeader;
    @BindView(R.id.rv_dashboard_task_manager)
    RecyclerView rvDashboardManager;
    @BindView(R.id.expand_list_task_staff)
    ImageButton btnExpandStaff;
    @BindView(R.id.expand_list_task_leader)
    ImageButton btnExpandLeader;
    @BindView(R.id.expand_list_task_manager)
    ImageButton btnExpandManager;
    List<Dashboard> dashboardsStaff,dashboardsLeader,dashboardsManager;

    public MainDashboardFragment() {

    }

    public static MainDashboardFragment newInstance() {
        Bundle args = new Bundle();
        MainDashboardFragment fragment = new MainDashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ButterKnife.bind(this,rootView);
        TextView view = ((View) container.getParent()).findViewById(R.id.main_title_toolbar);
        view.setText("Dashboard");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LoginData",Context.MODE_PRIVATE);
        String nip = sharedPreferences.getString("nip",null);
        String token = sharedPreferences.getString("token",null);

        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);

        Call<List<Dashboard>> staffCall = serviceInterface.getStaffDashboard(nip,token);
        staffCall.enqueue(new Callback<List<Dashboard>>() {
            @Override
            public void onResponse(Call<List<Dashboard>> call, Response<List<Dashboard>> response) {
                if(response.body() != null){
                    setListStaffRV(response.body());
                }
                //Toast.makeText(getContext(), "As Staff : "+response.body().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Dashboard>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Dashboard>> leaderCall = serviceInterface.getLeaderDashboard(nip,token);
        leaderCall.enqueue(new Callback<List<Dashboard>>() {
            @Override
            public void onResponse(Call<List<Dashboard>> call, Response<List<Dashboard>> response) {
                if(response.body() != null){
                    setListLeaderRV(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Dashboard>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Dashboard>> managerCall = serviceInterface.getManagerDashboard(nip,token);
        managerCall.enqueue(new Callback<List<Dashboard>>() {
            @Override
            public void onResponse(Call<List<Dashboard>> call, Response<List<Dashboard>> response) {
                if(response.body() != null){
                    setListManagerRV(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Dashboard>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    private void setListStaffRV(List<Dashboard> dashboardsStaff){
        ArrayList<Dashboard> listStaff = new ArrayList<>();
        listStaff.addAll(dashboardsStaff);
        rvDashboardTeam.setAdapter(new DashboardStaffAdapter(getContext(),listStaff));
    }

    private void setListLeaderRV(List<Dashboard> dashboardsLeader){
        ArrayList<Dashboard> listLeader = new ArrayList<>();
        listLeader.addAll(dashboardsLeader);
        rvDashboardLeader.setAdapter(new DashboardLeaderAdapter(getContext(),listLeader));
    }

    private void setListManagerRV(List<Dashboard> dashboardsManager){
        ArrayList<Dashboard> listManager = new ArrayList<>();
        listManager.addAll(dashboardsManager);
        rvDashboardManager.setAdapter(new DashboardManagerAdapter(getContext(),listManager));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu,menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.expand_list_task_leader)
    void onClickExpandLeader(View v){
        if (rvDashboardLeader.getVisibility() == View.GONE){
            rvDashboardLeader.setVisibility(View.VISIBLE);
            btnExpandLeader.setImageResource(R.drawable.ic_expand_less);
        }else{
            rvDashboardLeader.setVisibility(View.GONE);
            btnExpandLeader.setImageResource(R.drawable.ic_expand);
        }
    }

    @OnClick(R.id.expand_list_task_manager)
    void onClickExpandManager(View v){
        if (rvDashboardManager.getVisibility() == View.GONE){
            rvDashboardManager.setVisibility(View.VISIBLE);
            btnExpandManager.setImageResource(R.drawable.ic_expand_less);
        }else{
            rvDashboardManager.setVisibility(View.GONE);
            btnExpandManager.setImageResource(R.drawable.ic_expand);
        }
    }

    @OnClick(R.id.expand_list_task_staff)
    void onClickExpandStaff(){
        if (rvDashboardTeam.getVisibility() == View.GONE){
            rvDashboardTeam.setVisibility(View.VISIBLE);
            btnExpandStaff.setImageResource(R.drawable.ic_expand_less);
        }else{
            rvDashboardTeam.setVisibility(View.GONE);
            btnExpandStaff.setImageResource(R.drawable.ic_expand);
        }
    }
}
