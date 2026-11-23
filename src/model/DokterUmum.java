package model;

public class DokterUmum extends Dokter {

    public DokterUmum(String nama, double tarif) {
        super(nama, tarif);
    }

    @Override
    public String getSpesialisasi() {
        return "Dokter Umum";
    }
}
