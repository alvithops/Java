package model;

public class PetugasAdministrasi {
    private String idPetugas;
    private String nama;

    public PetugasAdministrasi(String idPetugas, String nama) {
        setIdPetugas(idPetugas);
        setNama(nama);
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        if (idPetugas == null || idPetugas.isBlank()) {
            throw new IllegalArgumentException("ID petugas tidak boleh kosong");
        }
        this.idPetugas = idPetugas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama petugas tidak boleh kosong");
        }
        this.nama = nama;
    }
}
