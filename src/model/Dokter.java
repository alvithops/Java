package model;

public abstract class Dokter {
    private String nama;
    private double tarif;

    public Dokter(String nama, double tarif) {
        setNama(nama);
        setTarif(tarif);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama dokter tidak boleh kosong");
        }
        this.nama = nama;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        if (tarif < 0) {
            throw new IllegalArgumentException("Tarif dokter tidak boleh negatif");
        }
        this.tarif = tarif;
    }

    public abstract String getSpesialisasi();
}
