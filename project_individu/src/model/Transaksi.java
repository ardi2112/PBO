package model;

public abstract class Transaksi {
    private int id;
    private String tanggal;
    private String keterangan;
    private double jumlah;

    public Transaksi(String keterangan, double jumlah) {
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }

    // Overloaded Constructor untuk mengambil data lama dari DB
    public Transaksi(int id, String tanggal, String keterangan, double jumlah) {
        this.id = id;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }

    // Metode Polimorfisme yang wajib dioverride
    public abstract void tampilData();
}