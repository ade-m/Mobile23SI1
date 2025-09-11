package edu.uph.m23si1.aplikasipertama.api;

import edu.uph.m23si1.aplikasipertama.model.Pasien;

public class PasienResponse {
    private String message;
    private Pasien data;
    public String getMessage() { return message; }
    public Pasien getData() { return data; }
}
