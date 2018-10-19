package com.project.pentacode.pomestaff.model;

import java.io.Serializable;

public class Client implements Serializable {
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
}
