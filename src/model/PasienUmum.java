package model;

public class PasienUmum extends Pasien {

    public PasienUmum(String idPasien, String nama) {
        super(idPasien, nama);
    }

    @Override
    public String getJenis() {
        return "Pasien Umum";
    }

    @Override
    public double hitungBiaya(Pemeriksaan pemeriksaan) {
        return pemeriksaan.getBiayaKonsultasi() + pemeriksaan.getBiayaTindakan();
    }
}
