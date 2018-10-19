package com.project.pentacode.pomestaff;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.project.pentacode.pomestaff.model.Project;

import java.util.ArrayList;

public class MainProjectFragment extends Fragment {
    OnListProjectInteraction mListener;

    public MainProjectFragment() {
        setHasOptionsMenu(true);
    }

    public static MainProjectFragment newInstance(ArrayList<Parcelable> obj) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("data",obj);
        MainProjectFragment fragment = new MainProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.project_fragment, container, false);

        if (rootView instanceof RecyclerView){
            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MainProjectRecyclerViewAdapter(getArguments().<Project>getParcelableArrayList("data"),mListener));
        }
        return rootView;
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
