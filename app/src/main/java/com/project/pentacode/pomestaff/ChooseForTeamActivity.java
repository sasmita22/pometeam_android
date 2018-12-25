package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseForTeamActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview_choose_for_team)
    RecyclerView rvChooseStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_for_team_activity);
        ButterKnife.bind(this);

        rvChooseStaff.setAdapter(new ChooseForTeamAdapter(this));
    }

    @OnClick(R.id.choose_for_team_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
