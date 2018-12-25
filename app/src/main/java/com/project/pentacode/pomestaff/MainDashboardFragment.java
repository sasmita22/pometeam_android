package com.project.pentacode.pomestaff;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvDashboardLeader.setAdapter(new DashboardLeaderAdapter());
        rvDashboardManager.setAdapter(new DashboardManagerAdapter());
        rvDashboardTeam.setAdapter(new DashboardStaffAdapter());
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
