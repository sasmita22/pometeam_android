package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {
    public String title;
    public String client;
    public int numOfStaff;
    public String date;
    public String position;
    public float percentage;

    public Project(String title, String client, int numOfStaff, String date, String position, float percentage) {
        this.title = title;
        this.client = client;
        this.numOfStaff = numOfStaff;
        this.date = date;
        this.position = position;
        this.percentage = percentage;
    }

    protected Project(Parcel in) {
        title = in.readString();
        client = in.readString();
        numOfStaff = in.readInt();
        date = in.readString();
        position = in.readString();
        percentage = in.readFloat();
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
        parcel.writeString(client);
        parcel.writeInt(numOfStaff);
        parcel.writeString(date);
        parcel.writeString(position);
        parcel.writeFloat(percentage);
    }
}
