package main;

import model.Dokter;
import model.DokterUmum;
import model.Pasien;
import model.PasienUmum;
import model.PetugasAdministrasi;
import service.RumahSakit;

public class Main {
    public static void main(String[] args) {
        RumahSakit rumahSakit = new RumahSakit("RS Sehat Selalu");

        PetugasAdministrasi petugas = new PetugasAdministrasi("PT001", "Siti");
        Pasien andi = new PasienUmum("PS001", "Andi");
        Dokter budi = new DokterUmum("Dr. Budi", 150_000);

        rumahSakit.tambahPetugas(petugas);
        rumahSakit.tambahPasien(andi);
        rumahSakit.tambahDokter(budi);

        rumahSakit.layaniPemeriksaan(andi, budi, "Demam", 100_000);

        rumahSakit.tampilkanLaporan(andi);
    }
}
