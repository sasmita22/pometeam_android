package com.project.pentacode.pomestaff;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

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
    @BindView(R.id.show_team_image)
    CircleImageView projectPMImage;
    @BindView(R.id.show_team_name)
    TextView projectPMName;
    @BindView(R.id.show_team_jabatan)
    TextView projectPMJabatan;
    @BindView(R.id.project_detail_range_date)
    TextView projectRangeDate;
    @BindView(R.id.project_detail_ended_at)
    TextView projectEndedAt;
    @BindView(R.id.btn_manageproject)
    Button buttonManage;
    @BindView(R.id.btn_gotoworkspace)
    Button buttonWorkspace;
    @BindView(R.id.btn_managestep)
    Button buttonManageStep;
    @BindView(R.id.caution)
    CardView cautionMessage;
    int id_project;
    int id_step;
    int position_id;
    Project project;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail_activity);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("PROJECT",MODE_PRIVATE);

        id_project = sharedPreferences.getInt("project",0);
        id_step = sharedPreferences.getInt("step",0);
        position_id = sharedPreferences.getInt("position_id",-1);

        //getShared
        SharedPreferences sp1= getApplicationContext().getSharedPreferences("LoginData", MODE_PRIVATE);

        String prefToken = sp1.getString("token", null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Project> projectCall = service.getProjectDetail(id_project,prefToken);
        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                project = response.body();
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
    void workspaceClick(View v){
        Intent intent = new Intent(this,ProjectTaskListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_manageproject)
    void manageProjectClick(View v){
        Intent intent = new Intent(this,ProjectStepActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_managestep)
    void onClickManageStep(View v){
        Intent intent = new Intent(this,StepDetailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.project_back_button)
    void backPressed(View v){
        onBackPressed();
    }

    @OnClick(R.id.project_detail_more)
    void onClickMore(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.inflate(R.menu.qrcodemenu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.qrcode :
                        Dialog dialog = new Dialog(ProjectDetailActivity.this);
                        dialog.setContentView(R.layout.qrcode_dialog);
                        ImageView imageView = dialog.findViewById(R.id.qrcode_image);
                        Glide.with(getApplicationContext())
                                .load(RetrofitClientInstance.BASE_URL_IMAGE_QRCODE+project.getQrcode())
                                .into(imageView);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }

    private void setDataToView(Project project){
        if (position_id == -1){
            cautionMessage.setVisibility(View.VISIBLE);
            buttonWorkspace.setVisibility(View.GONE);
            buttonManage.setVisibility(View.GONE);
            buttonManageStep.setVisibility(View.GONE);
        }else if (position_id == 0){
            cautionMessage.setVisibility(View.GONE);
            buttonWorkspace.setVisibility(View.GONE);
            buttonManage.setVisibility(View.VISIBLE);
            buttonManageStep.setVisibility(View.GONE);
        } else if (position_id == 1) {
            cautionMessage.setVisibility(View.GONE);
            buttonWorkspace.setVisibility(View.GONE);
            buttonManage.setVisibility(View.GONE);
            buttonManageStep.setVisibility(View.VISIBLE);
        } else if (position_id == 2) {
            cautionMessage.setVisibility(View.GONE);
            buttonWorkspace.setVisibility(View.VISIBLE);
            buttonManage.setVisibility(View.GONE);
            buttonManageStep.setVisibility(View.GONE);
        }

        Glide.with(getApplicationContext())
                .load(RetrofitClientInstance.BASE_URL_IMAGE_COMPANY+project.getImage())
                .into(imageBanner);

        projectName.setText(project.getName());
        projectDeskripsi.setText(project.getDeskripsi());
        projectClient.setText(project.getClient());

        if (project.getProjectManagerObject() != null) {
            Glide.with(getApplicationContext())
                    .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+project.getProjectManagerObject().getImage())
                    .into(projectPMImage);
            projectPMName.setText(project.getProjectManagerObject().getName());
            projectPMJabatan.setText(project.getProjectManagerObject().getJabatan());
        }

        projectRangeDate.setText(project.getStartAt()+" - "+project.getDeadlineAt());
        projectEndedAt.setText(project.getEndedAt()==null?"-":project.getEndedAt());
    }
}
