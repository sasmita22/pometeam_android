package com.project.pentacode.pomestaff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Staff;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    @BindView(R.id.profile_name)
    TextView txtName;
    @BindView(R.id.profile_jabatan)
    TextView txtJabatan;
    @BindView(R.id.profile_email)
    TextView txtEmail;
    @BindView(R.id.profile_telephone)
    TextView txtTelephone;
    @BindView(R.id.profile_git)
    TextView txtGit;

    public static ProfileFragment newInstance(String nip) {
        Bundle args = new Bundle();
        args.putString("nip",nip);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_activity,container,false);
        ButterKnife.bind(view);
        Staff staff = ModelGenerator.getStaff(getArguments().getString("nip"));
        txtName.setText(staff.name);
        txtJabatan.setText(staff.jabatan);
        txtEmail.setText(staff.email);
        txtTelephone.setText(staff.telephone);
        txtGit.setText(staff.git);
        return view;
    }
}
