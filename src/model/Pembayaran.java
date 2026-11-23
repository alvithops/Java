package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Pembayaran {
    private final Pemeriksaan pemeriksaan;

    public Pembayaran(Pemeriksaan pemeriksaan) {
        if (pemeriksaan == null) {
            throw new IllegalArgumentException("Pemeriksaan tidak boleh null");
        }
        this.pemeriksaan = pemeriksaan;
    }

    public double getBiayaKonsultasi() {
        return pemeriksaan.getBiayaKonsultasi();
    }

    public double getBiayaTindakan() {
        return pemeriksaan.getBiayaTindakan();
    }

    public double hitungTotal() {
        return pemeriksaan.hitungTotal();
    }

    public static String formatRupiah(double nilai) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.of("id", "ID"));
        simbol.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0", simbol);
        return "Rp" + format.format(nilai);
    }
}
