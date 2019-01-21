package com.project.pentacode.pomestaff;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.ApiMessage;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStepActivity extends AppCompatActivity {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_description)
    EditText edtDescription;
    @BindView(R.id.edt_deadline)
    EditText edtDeadline;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    int idProject, idStep;
    String token;
    String deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_step_activity);

        ButterKnife.bind(this);

        SharedPreferences sharedLoginData = getSharedPreferences("LoginData",MODE_PRIVATE);

        SharedPreferences sharedPreferences = getSharedPreferences("PROJECT",MODE_PRIVATE);
        idProject = sharedPreferences.getInt("project",0);
        idStep = sharedPreferences.getInt("step",0);
        token = sharedLoginData.getString("token",null);

        edtDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddStepActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String months[] = new DateFormatSymbols().getMonths();
                edtDeadline.setText(dayOfMonth+" "+months[month]+" "+year);
                deadline = year+"-"+(month+1)+"-"+dayOfMonth;
            }
        };
    }

    @OnClick(R.id.add_step_back)
    void onClickBack(View v){
        onBackPressed();
    }

    @OnClick(R.id.btn_submit)
    void onClickSubmit(View v){
        //Toast.makeText(this, deadline, Toast.LENGTH_SHORT).show();
        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<ApiMessage> messageCall = serviceInterface.createStep(idProject,edtName.getText().toString(),edtDescription.getText().toString(),deadline,token);
        messageCall.enqueue(new Callback<ApiMessage>() {
            @Override
            public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddStepActivity.this, "Create Step Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddStepActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiMessage> call, Throwable t) {
                Toast.makeText(AddStepActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
