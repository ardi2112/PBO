package model;

public class Pemasukan extends Transaksi {
    
    public Pemasukan(String keterangan, double jumlah) {
        super(keterangan, jumlah);
    }

    public Pemasukan(int id, String tanggal, String keterangan, double jumlah) {
        super(id, tanggal, keterangan, jumlah);
    }

    @Override
    public void tampilData() {
        System.out.printf("| %-4d | %-12s | %-25s | Rp%-15.2f |\n", 
                getId(), getTanggal(), getKeterangan(), getJumlah());
    }
}