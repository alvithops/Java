package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Dokter;
import model.Pasien;
import model.Pembayaran;
import model.Pemeriksaan;
import model.PetugasAdministrasi;

public class RumahSakit {
    private static final String PEMISAH_ATAS = "========================================";
    private static final String PEMISAH_TENGAH = "----------------------------------------";

    private final String namaRumahSakit;
    private final List<Pasien> daftarPasien = new ArrayList<>();
    private final List<Dokter> daftarDokter = new ArrayList<>();
    private final List<PetugasAdministrasi> daftarPetugas = new ArrayList<>();

    public RumahSakit(String namaRumahSakit) {
        if (namaRumahSakit == null || namaRumahSakit.isBlank()) {
            throw new IllegalArgumentException("Nama rumah sakit tidak boleh kosong");
        }
        this.namaRumahSakit = namaRumahSakit;
    }

    public String getNamaRumahSakit() {
        return namaRumahSakit;
    }

    public void tambahPasien(Pasien pasien) {
        if (pasien == null) {
            throw new IllegalArgumentException("Pasien tidak boleh null");
        }
        daftarPasien.add(pasien);
    }

    public void tambahDokter(Dokter dokter) {
        if (dokter == null) {
            throw new IllegalArgumentException("Dokter tidak boleh null");
        }
        daftarDokter.add(dokter);
    }

    public void tambahPetugas(PetugasAdministrasi petugas) {
        if (petugas == null) {
            throw new IllegalArgumentException("Petugas tidak boleh null");
        }
        daftarPetugas.add(petugas);
    }

    public List<Pasien> getDaftarPasien() {
        return Collections.unmodifiableList(daftarPasien);
    }

    public List<Dokter> getDaftarDokter() {
        return Collections.unmodifiableList(daftarDokter);
    }

    public List<PetugasAdministrasi> getDaftarPetugas() {
        return Collections.unmodifiableList(daftarPetugas);
    }

    public Pasien cariPasien(String idPasien) {
        for (Pasien pasien : daftarPasien) {
            if (pasien.getIdPasien().equals(idPasien)) {
                return pasien;
            }
        }
        return null;
    }

    public Pemeriksaan layaniPemeriksaan(Pasien pasien, Dokter dokter, String keluhan, double biayaTindakan) {
        Pemeriksaan pemeriksaan = new Pemeriksaan(pasien, dokter, keluhan, biayaTindakan);
        pasien.tambahPemeriksaan(pemeriksaan);
        return pemeriksaan;
    }

    public double hitungTotalTagihan(Pasien pasien) {
        double total = 0;
        for (Pemeriksaan pemeriksaan : pasien.getRiwayatPemeriksaan()) {
            total += pemeriksaan.hitungTotal();
        }
        return total;
    }

    public void tampilkanLaporan(Pasien pasien) {
        System.out.println(PEMISAH_ATAS);
        System.out.println("SISTEM MANAJEMEN RUMAH SAKIT");
        System.out.println(PEMISAH_ATAS);
        System.out.println("DATA PASIEN");
        System.out.printf("%-10s: %s%n", "ID Pasien", pasien.getIdPasien());
        System.out.printf("%-10s: %s%n", "Nama", pasien.getNama());
        System.out.printf("%-10s: %s%n", "Jenis", pasien.getJenis());
        System.out.println();

        List<Pemeriksaan> riwayat = pasien.getRiwayatPemeriksaan();
        for (Pemeriksaan pemeriksaan : riwayat) {
            Dokter dokter = pemeriksaan.getDokter();
            System.out.println("DATA DOKTER");
            System.out.printf("%-12s: %s%n", "Nama Dokter", dokter.getNama());
            System.out.printf("%-12s: %s%n", "Spesialisasi", dokter.getSpesialisasi());
            System.out.printf("%-12s: %s%n", "Tarif", Pembayaran.formatRupiah(dokter.getTarif()));
            System.out.println();

            System.out.println("DATA PEMERIKSAAN");
            System.out.printf("%-16s: %s%n", "Keluhan", pemeriksaan.getKeluhan());
            System.out.printf("%-16s: %s%n", "Biaya Tindakan", Pembayaran.formatRupiah(pemeriksaan.getBiayaTindakan()));
            System.out.printf("%-16s: %s%n", "Biaya Konsultasi", Pembayaran.formatRupiah(pemeriksaan.getBiayaKonsultasi()));
        }

        System.out.println(PEMISAH_TENGAH);
        System.out.println("RINCIAN PEMBAYARAN");
        tampilkanRincianBiaya(pasien);
        System.out.println(PEMISAH_ATAS);
    }

    private void tampilkanRincianBiaya(Pasien pasien) {
        double totalKonsultasi = 0;
        double totalTindakan = 0;
        for (Pemeriksaan pemeriksaan : pasien.getRiwayatPemeriksaan()) {
            totalKonsultasi += pemeriksaan.getBiayaKonsultasi();
            totalTindakan += pemeriksaan.getBiayaTindakan();
        }

        double biayaBruto = totalKonsultasi + totalTindakan;
        double totalPembayaran = hitungTotalTagihan(pasien);
        double potongan = biayaBruto - totalPembayaran;

        System.out.println("Biaya Konsultasi : " + Pembayaran.formatRupiah(totalKonsultasi));
        System.out.println("Biaya Tindakan   : " + Pembayaran.formatRupiah(totalTindakan));
        if (potongan > 0) {
            System.out.println("Potongan         : -" + Pembayaran.formatRupiah(potongan));
        }
        System.out.println("Total Pembayaran: " + Pembayaran.formatRupiah(totalPembayaran));
    }

    public void tampilkanRiwayat(Pasien pasien) {
        System.out.println(PEMISAH_ATAS);
        System.out.println("RIWAYAT PEMERIKSAAN PASIEN");
        System.out.println(PEMISAH_ATAS);
        System.out.printf("%-10s: %s%n", "ID Pasien", pasien.getIdPasien());
        System.out.printf("%-10s: %s%n", "Nama", pasien.getNama());
        System.out.printf("%-10s: %s%n", "Jenis", pasien.getJenis());
        System.out.println(PEMISAH_TENGAH);

        int nomor = 1;
        for (Pemeriksaan pemeriksaan : pasien.getRiwayatPemeriksaan()) {
            System.out.println("Pemeriksaan ke-" + nomor++);
            System.out.printf("%-16s: %s%n", "Tanggal", pemeriksaan.getTanggal());
            System.out.printf("%-16s: %s%n", "Dokter", pemeriksaan.getDokter().getNama());
            System.out.printf("%-16s: %s%n", "Spesialisasi", pemeriksaan.getDokter().getSpesialisasi());
            System.out.printf("%-16s: %s%n", "Keluhan", pemeriksaan.getKeluhan());
            System.out.printf("%-16s: %s%n", "Total Biaya", Pembayaran.formatRupiah(pemeriksaan.hitungTotal()));
            System.out.println(PEMISAH_TENGAH);
        }

        System.out.println("Total Tagihan   : " + Pembayaran.formatRupiah(hitungTotalTagihan(pasien)));
        System.out.println(PEMISAH_ATAS);
    }
}
