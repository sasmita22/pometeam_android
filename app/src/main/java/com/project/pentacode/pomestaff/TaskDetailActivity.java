package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.Task;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailActivity extends AppCompatActivity {
    @BindView(R.id.task_detail_title)
    TextView textTitle;
    @BindView(R.id.task_detail_deskripsi)
    TextView textDeskripsi;
    @BindView(R.id.task_detail_deadline)
    TextView textDeadline;
    @BindView(R.id.task_detail_status)
    TextView textStatus;
    @BindView(R.id.responsible_staff_name)
    TextView textStaffName;
    @BindView(R.id.responsible_staff_jabatan)
    TextView textStaffJabatan;
    @BindView(R.id.responsible_staff_image)
    CircleImageView imageStaff;
    int idTask;
    int idProject;
    int idStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_activity);

        ButterKnife.bind(this);

        idTask = getIntent().getIntExtra("id_task",0);
        idProject = getIntent().getIntExtra("id_project",0);
        idStep = getIntent().getIntExtra("id_step",0);

        String token = getSharedPreferences("LoginData",MODE_PRIVATE).getString("token",null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Task> taskCall = service.getTask(idTask,token);
        taskCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    setDataToView(response.body());
                } else {
                    Toast.makeText(TaskDetailActivity.this, response.message()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Toast.makeText(TaskDetailActivity.this, t.getMessage()+"", Toast.LENGTH_SHORT).show();;
            }
        });
    }

    private void setDataToView(Task task){
        Toast.makeText(this, task.getName()+"", Toast.LENGTH_SHORT).show();
        textTitle.setText(task.getName());
        textDeskripsi.setText(task.getDeskripsi());
        textDeadline.setText(task.getDeadlineAt());
        String status = "";
        if (task.getStatus()==0) {
            status = "Belum Selesai";
        } else if (task.getStatus()==1) {
            status = "Selesai ("+task.getFinishedAt()+")";
        } else {
            status = "Sedang Ditinjau";
        }
        textStatus.setText(status);

        if (task.getHandledBy() != null){
            textStaffName.setText(task.getHandledBy().getName());
            textStaffJabatan.setText(task.getHandledBy().getJabatan());
            Glide.with(this)
                    .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+task.getHandledBy().getImage())
                    .into(imageStaff);
        }
    }

    @OnClick(R.id.task_detail_taken_by)
    void onClicktakenBy(View v){
        Intent intent = new Intent(this,ChooseStaffResponsibleActivity.class);
        intent.putExtra("id_project",idProject);
        intent.putExtra("id_step",idStep);
        startActivity(intent);
    }

    @OnClick(R.id.task_detail_back)
    void onClickback(View v){
        onBackPressed();
    }
}
