package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable,Parcelable {
    public int id;
    public String name;
    public String description;
    public Date startAt;
    public Date deadlineAt;
    public Date finishedAt;
    public int priority;

    public Task(int id, String name, String description, Date startAt, Date deadlineAt, Date finishedAt, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.deadlineAt = deadlineAt;
        this.finishedAt = finishedAt;
        this.priority = priority;
    }

    protected Task(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        startAt = (Date) in.readSerializable();
        deadlineAt = (Date) in.readSerializable();
        finishedAt = (Date) in.readSerializable();
        priority = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
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
        dest.writeString(description);
        dest.writeSerializable(startAt);
        dest.writeSerializable(deadlineAt);
        dest.writeSerializable(finishedAt);
        dest.writeInt(priority);
    }
}
