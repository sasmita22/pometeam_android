package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepTeamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_team_activity);
        ButterKnife.bind(this);



        RecyclerView recyclerView = findViewById(R.id.rv_stepteam);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StepTeamAdapter());
    }

    @OnClick(R.id.action_menu_add)
    void addStaffClick(View v){
        startActivity(new Intent(this,ChooseStaffResponsibleActivity.class));
    }
}
