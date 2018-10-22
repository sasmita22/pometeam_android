package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Staff implements Serializable,Parcelable {
    public String nip;
    public String name;
    public String email;
    public String telephone;
    public String git;
    public String jabatan;
    public String photoResource;

    public Staff(String nip, String name, String email, String telephone, String git, String jabatan, String photoResource) {
        this.nip = nip;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.git = git;
        this.jabatan = jabatan;
        this.photoResource = photoResource;
    }

    protected Staff(Parcel in) {
        nip = in.readString();
        name = in.readString();
        email = in.readString();
        telephone = in.readString();
        git = in.readString();
        jabatan = in.readString();
        photoResource = in.readString();
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nip);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(telephone);
        dest.writeString(git);
        dest.writeString(jabatan);
        dest.writeString(photoResource);
    }
}
