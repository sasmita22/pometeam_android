package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetailActivity extends AppCompatActivity {
    @BindView(R.id.project_back_button)
    ImageButton backButton;
    @BindView(R.id.project_detail_banner)
    ImageView imageBanner;
    @BindView(R.id.project_detail_project_name)
    TextView projectName;
    @BindView(R.id.project_detail_desc)
    TextView projectDeskripsi;
    @BindView(R.id.project_detail_client)
    TextView projectClient;
    @BindView(R.id.project_detail_pm_image)
    CircleImageView projectPMImage;
    @BindView(R.id.project_detail_pm_name)
    TextView projectPMName;
    @BindView(R.id.project_detail_pm_jabatan)
    TextView projectPMJabatan;
    @BindView(R.id.project_detail_range_date)
    TextView projectRangeDate;
    @BindView(R.id.project_detail_ended_at)
    TextView projectEndedAt;
    int id_project;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail_activity);
        ButterKnife.bind(this);

        id_project = getIntent().getIntExtra("id_project",0);
        //Toast.makeText(this, id_project+"", Toast.LENGTH_SHORT).show();

        //getShared
        SharedPreferences sp1= getApplicationContext().getSharedPreferences("LoginData", MODE_PRIVATE);

        String prefToken = sp1.getString("token", null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Project> projectCall = service.getProjectDetail(id_project,prefToken);
        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                setDataToView(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Toast.makeText(ProjectDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        backButton.bringToFront();
    }

    @OnClick(R.id.btn_gotoworkspace)
    void workspaceClick(View v){ startActivity(new Intent(this,ProjectTaskListActivity.class));
    }

    @OnClick(R.id.btn_manageproject)
    void manageProjectClick(View v){
        Intent intent = new Intent(this,ProjectStepActivity.class);
        intent.putExtra("id_project",id_project);
        startActivity(intent);
    }

    @OnClick(R.id.project_back_button)
    void backPressed(View v){
        onBackPressed();
    }

    private void setDataToView(Project project){
        Glide.with(getApplicationContext())
                .load("http://10.0.2.2:8000/images/company/"+project.getImage())
                .into(imageBanner);

        projectName.setText(project.getName());
        projectDeskripsi.setText(project.getDeskripsi());
        projectClient.setText(project.getClient());
        Glide.with(getApplicationContext())
                .load("http://10.0.2.2:8000/images/profile/"+project.getProjectManagerObject().getImage())
                .into(projectPMImage);
        projectPMName.setText(project.getProjectManagerObject().getName());
        projectPMJabatan.setText(project.getProjectManagerObject().getJabatan());
        projectRangeDate.setText(project.getStartAt()+" - "+project.getDeadlineAt());
        projectEndedAt.setText(project.getEndedAt()==null?"-":project.getEndedAt());
    }
}
