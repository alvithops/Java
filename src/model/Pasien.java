package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pasien {
    private String idPasien;
    private String nama;
    private final List<Pemeriksaan> riwayatPemeriksaan = new ArrayList<>();

    public Pasien(String idPasien, String nama) {
        setIdPasien(idPasien);
        setNama(nama);
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        if (idPasien == null || idPasien.isBlank()) {
            throw new IllegalArgumentException("ID pasien tidak boleh kosong");
        }
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama pasien tidak boleh kosong");
        }
        this.nama = nama;
    }

    public void tambahPemeriksaan(Pemeriksaan pemeriksaan) {
        if (pemeriksaan == null) {
            throw new IllegalArgumentException("Pemeriksaan tidak boleh null");
        }
        riwayatPemeriksaan.add(pemeriksaan);
    }

    public List<Pemeriksaan> getRiwayatPemeriksaan() {
        return Collections.unmodifiableList(riwayatPemeriksaan);
    }

    public abstract String getJenis();

    public abstract double hitungBiaya(Pemeriksaan pemeriksaan);
}
