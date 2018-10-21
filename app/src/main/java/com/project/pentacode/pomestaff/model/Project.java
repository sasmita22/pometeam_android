package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Project implements Parcelable {
    public String title;
    public Client client;
    public int jumlahTeam;
    public Staff projectManager;
    public String userPosition;
    public Date startAt;
    public Date deadlineAt;
    public Date finishedAt;
    public float percentage;
    public ArrayList<Step> steps;

    public Project(String title, Client client, int jumlahTeam, Staff projectManager, String userPosition, Date startAt, Date deadlineAt, Date finishedAt, float percentage, ArrayList<Step> steps) {
        this.title = title;
        this.client = client;
        this.jumlahTeam = jumlahTeam;
        this.projectManager = projectManager;
        this.userPosition = userPosition;
        this.startAt = startAt;
        this.deadlineAt = deadlineAt;
        this.finishedAt = finishedAt;
        this.percentage = percentage;
        this.steps = steps;
    }

    protected Project(Parcel in) {
        title = in.readString();
        client = (Client) in.readSerializable();
        jumlahTeam = in.readInt();
        projectManager = (Staff) in.readSerializable();
        userPosition = in.readString();
        startAt = (Date) in.readSerializable();
        deadlineAt = (Date) in.readSerializable();
        finishedAt = (Date) in.readSerializable();
        percentage = in.readFloat();
        steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeSerializable(client);
        parcel.writeInt(jumlahTeam);
        parcel.writeSerializable(projectManager);
        parcel.writeString(userPosition);
        parcel.writeSerializable(startAt);
        parcel.writeSerializable(deadlineAt);
        parcel.writeSerializable(finishedAt);
        parcel.writeFloat(percentage);
        parcel.writeTypedList(steps);
    }
}
