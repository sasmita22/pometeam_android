package com.project.pentacode.pomestaff.model;

import java.io.Serializable;

public class Staff implements Serializable {
    public String nip;
    public String nama;
    public String jabatan;
    public String photoResource;

    public Staff(String nip, String nama, String jabatan, String photoResource) {
        this.nip = nip;
        this.nama = nama;
        this.jabatan = jabatan;
        this.photoResource = photoResource;
    }
}
