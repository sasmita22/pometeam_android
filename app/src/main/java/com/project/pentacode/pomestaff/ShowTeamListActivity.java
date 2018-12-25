package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowTeamListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_team_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_show_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShowTeamListAdapter(this));
    }

    @OnClick(R.id.show_team_back)
    void onClickBack(View v){
        onBackPressed();
    }

    @OnClick(R.id.show_team_add)
    void onClickAdd(View v){
        startActivity(new Intent(this,ChooseForTeamActivity.class));
    }
}
