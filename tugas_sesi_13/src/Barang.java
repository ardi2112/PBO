public class Barang {

    // Atribut
    private String kode;
    private String namaBarang;
    private int harga;
    private int stok;

    // Constructor kosong
    public Barang() {

    }

    // Constructor dengan parameter
    public Barang(String kode, String namaBarang, int harga, int stok) {
        this.kode = kode;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

}