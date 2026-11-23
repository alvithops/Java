package model;

public class PasienBPJS extends Pasien {
    private static final double PERSEN_POTONGAN = 0.10;

    public PasienBPJS(String idPasien, String nama) {
        super(idPasien, nama);
    }

    @Override
    public String getJenis() {
        return "Pasien BPJS";
    }

    @Override
    public double hitungBiaya(Pemeriksaan pemeriksaan) {
        double biayaBruto = pemeriksaan.getBiayaKonsultasi() + pemeriksaan.getBiayaTindakan();
        return biayaBruto - (biayaBruto * PERSEN_POTONGAN);
    }
}
