package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable,Parcelable {
    public int id;
    public String name;
    public ArrayList<Staff> team;
    public ArrayList<Task> tasks;
    public int priority;

    public Step(int id, String name, ArrayList<Staff> team, ArrayList<Task> tasks, int priority) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.tasks = tasks;
        this.priority = priority;
    }

    protected Step(Parcel in) {
        id = in.readInt();
        name = in.readString();
        team = in.createTypedArrayList(Staff.CREATOR);
        tasks = in.createTypedArrayList(Task.CREATOR);
        priority = in.readInt();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(team);
        dest.writeTypedList(tasks);
        dest.writeInt(priority);
    }
}
