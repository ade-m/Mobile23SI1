package edu.uph.m23si1.aplikasipertama.model;

public class Pasien {
    /*
    {
            "nama": "Ade Maulana",
            "nik": "654321",
            "alamat": "Jl. Merdeka No. 10",
            "no_hp": "08123456789"
        }
     */
    private String nama,nik,alamat,no_hp;

    public Pasien() {
    }

    public Pasien(String alamat, String nama, String nik, String no_hp) {
        this.alamat = alamat;
        this.nama = nama;
        this.nik = nik;
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
