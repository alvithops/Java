package model;

import java.time.LocalDate;

public class Pemeriksaan {
    private String keluhan;
    private double biayaKonsultasi;
    private double biayaTindakan;
    private final Dokter dokter;
    private final Pasien pasien;
    private final LocalDate tanggal;

    public Pemeriksaan(Pasien pasien, Dokter dokter, String keluhan, double biayaTindakan) {
        if (pasien == null || dokter == null) {
            throw new IllegalArgumentException("Pasien dan dokter tidak boleh null");
        }
        setKeluhan(keluhan);
        setBiayaTindakan(biayaTindakan);
        this.pasien = pasien;
        this.dokter = dokter;
        this.biayaKonsultasi = dokter.getTarif();
        this.tanggal = LocalDate.now();
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        if (keluhan == null || keluhan.isBlank()) {
            throw new IllegalArgumentException("Keluhan tidak boleh kosong");
        }
        this.keluhan = keluhan;
    }

    public double getBiayaKonsultasi() {
        return biayaKonsultasi;
    }

    public double getBiayaTindakan() {
        return biayaTindakan;
    }

    public void setBiayaTindakan(double biayaTindakan) {
        if (biayaTindakan < 0) {
            throw new IllegalArgumentException("Biaya tindakan tidak boleh negatif");
        }
        this.biayaTindakan = biayaTindakan;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public double hitungTotal() {
        return pasien.hitungBiaya(this);
    }
}
