package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_team_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_choose_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseTeamAdapter());
    }

    @OnClick(R.id.choose_team_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
