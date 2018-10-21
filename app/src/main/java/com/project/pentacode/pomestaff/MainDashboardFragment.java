package com.project.pentacode.pomestaff;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainDashboardFragment extends Fragment {

    public MainDashboardFragment() {
    }

    public static MainDashboardFragment newInstance() {

        Bundle args = new Bundle();
        MainDashboardFragment fragment = new MainDashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(77f,1));
        barEntries.add(new BarEntry(60f,2));
        barEntries.add(new BarEntry(45f,3));
        barEntries.add(new BarEntry(22f,4));
        barEntries.add(new BarEntry(98f,5));
        BarDataSet barDataSet = new BarDataSet(barEntries,"BarDataSet");

        BarData barData = new BarData(barDataSet);

        BarChart barChart = rootView.findViewById(R.id.barchartView);
        barChart.setData(barData);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu,menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
