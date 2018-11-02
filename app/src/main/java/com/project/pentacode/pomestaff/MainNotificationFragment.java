package com.project.pentacode.pomestaff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainNotificationFragment extends Fragment {
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

        TextView view = ((View) container.getParent()).findViewById(R.id.main_title_toolbar);
        view.setText("Notification");

        return v;
    }
}
