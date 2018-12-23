package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_leader_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_choose_leader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseLeaderAdapter());
    }

    @OnClick(R.id.choose_leader_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
