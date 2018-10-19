package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Project implements Parcelable {
    public String title;
    public Client client;
    public Staff projectManager;
    public Date date;
    public String position;
    public float percentage;
    public ArrayList<Staff> team;
    public ArrayList<Step> steps;

    public Project(String title, Client client, int numOfStaff, Date date, String position, float percentage) {
        this.title = title;
        this.client = client;
        this.date = date;
        this.position = position;
        this.percentage = percentage;
    }


    protected Project(Parcel in) {
        title = in.readString();
        client = (Client) in.readSerializable();
        projectManager = (Staff) in.readSerializable();
        date = (Date) in.readSerializable();
        position = in.readString();
        percentage = in.readFloat();
        team = (ArrayList<Staff>) in.readSerializable();
        steps = (ArrayList<Step>) in.readSerializable();
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
        parcel.writeSerializable(projectManager);
        parcel.writeSerializable(date);
        parcel.writeString(position);
        parcel.writeFloat(percentage);
        parcel.writeSerializable(team);
        parcel.writeSerializable(steps);
    }
}
