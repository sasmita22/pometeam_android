package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Client implements Serializable,Parcelable {
    public int id;
    public String nama;
    public String alamat;
    public String telephone;

    public Client(int id, String nama, String alamat, String telephone) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telephone = telephone;
    }

    protected Client(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        alamat = in.readString();
        telephone = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(telephone);
    }
}
