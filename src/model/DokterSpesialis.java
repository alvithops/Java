package model;

public class DokterSpesialis extends Dokter {
    private String bidang;

    public DokterSpesialis(String nama, String bidang, double tarif) {
        super(nama, tarif);
        setBidang(bidang);
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        if (bidang == null || bidang.isBlank()) {
            throw new IllegalArgumentException("Bidang spesialis tidak boleh kosong");
        }
        this.bidang = bidang;
    }

    @Override
    public String getSpesialisasi() {
        return "Spesialis " + bidang;
    }
}
