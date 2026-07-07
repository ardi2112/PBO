package model;

public class Pengeluaran extends Transaksi {

    public Pengeluaran(String keterangan, double jumlah) {
        super(keterangan, jumlah);
    }

    public Pengeluaran(int id, String tanggal, String keterangan, double jumlah) {
        super(id, tanggal, keterangan, jumlah);
    }

    @Override
    public void tampilData() {
        System.out.printf("| %-4d | %-12s | %-25s | Rp%-15.2f |\n", 
                getId(), getTanggal(), getKeterangan(), getJumlah());
    }
}