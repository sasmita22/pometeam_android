package com.project.pentacode.pomestaff.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Staff implements Parcelable {
    @SerializedName("nip")
    @Expose
    public String nip;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("jabatan")
    @Expose
    public String jabatan;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("telephone")
    @Expose
    public String telephone;

    protected Staff(Parcel in) {
        nip = in.readString();
        name = in.readString();
        email = in.readString();
        jabatan = in.readString();
        image = in.readString();
        telephone = in.readString();
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nip);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(jabatan);
        dest.writeString(image);
        dest.writeString(telephone);
    }
}
