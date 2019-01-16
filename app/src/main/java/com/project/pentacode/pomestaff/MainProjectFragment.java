package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainProjectFragment extends Fragment {
    OnListProjectInteraction mListener;
    RecyclerView recyclerView;

    public MainProjectFragment() {
        setHasOptionsMenu(true);

    }

    public static MainProjectFragment newInstance() {
        MainProjectFragment fragment = new MainProjectFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        String prefNip;
        String prefToken;

        //getShared
        SharedPreferences sp1= getContext().getSharedPreferences("LoginData", MODE_PRIVATE);

        prefNip = sp1.getString("nip", "");
        prefToken = sp1.getString("token", null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Project>> projectCall = service.getProjectList(prefNip,prefToken);
        projectCall.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                setDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.project_fragment, container, false);


        TextView view = ((View) container.getParent()).findViewById(R.id.main_title_toolbar);
        view.setText("My Project");

        recyclerView = rootView.findViewById(R.id.recyclerview_project_list);

        if (recyclerView instanceof RecyclerView){
            Context context = rootView.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        }
        return rootView;
    }

    private void setDataList(List<Project> projects){
        if (recyclerView instanceof RecyclerView){
            Context context = getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            ArrayList<Project> list = new ArrayList<>();
            list.addAll(projects);
            recyclerView.setAdapter(new MainProjectRecyclerViewAdapter(list,mListener));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListProjectInteraction) {
            mListener = (OnListProjectInteraction) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListProjectInteraction{
        void onClickProjectList(Project project);
    }
}
