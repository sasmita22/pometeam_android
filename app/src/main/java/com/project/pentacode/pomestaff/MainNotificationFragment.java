package com.project.pentacode.pomestaff;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Notifikasi;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainNotificationFragment extends Fragment {
    @BindView(R.id.recyclerview_notifikasi)
    RecyclerView recyclerView;

    public MainNotificationFragment() {

    }

    public static MainNotificationFragment newInstance() {

        Bundle args = new Bundle();

        MainNotificationFragment fragment = new MainNotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notification_fragment,container,false);

        ButterKnife.bind(this,v);

        TextView view = ((View) container.getParent()).findViewById(R.id.main_title_toolbar);
        view.setText("Notification");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        String nip = sharedPreferences.getString("nip",null);
        String token = sharedPreferences.getString("token",null);

        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Notifikasi>> call = serviceInterface.getNotifikasi(nip,token);
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                if (response.isSuccessful()){
                    setRecyclerView(response.body());
                }else{
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void setRecyclerView(List<Notifikasi> notifikasis){
        ArrayList<Notifikasi> data = new ArrayList<>();
        data.addAll(notifikasis);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new NotificationAdapter(data,getContext()));
    }
}
