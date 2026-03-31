package sesi_2;

public class HandPhone {
    String jenis_hp;
    int tahun_pembuatan;

    // Perbaikan: return type void, karena method ini hanya mengisi data
    public void setDataHP(String jenis_hp, int tahun_pembuatan) {
        // this digunakan untuk membedakan atribut dengan parameter
        this.jenis_hp = jenis_hp;
        this.tahun_pembuatan = tahun_pembuatan;
    }

    // Perbaikan: method getJenisHP mengembalikan nilai String
    public String getJenisHP() {
        return jenis_hp;
    }

    // Tambahan method getTahunPembuatan
    public int getTahunPembuatan() {
        return tahun_pembuatan;
    }

    // Perbaikan: signature main yang benar
    public static void main(String[] args) {
        HandPhone hp = new HandPhone();

        // Variabel lokal untuk parameter
        String jenis = "Android";
        int tahun = 2023;

        hp.setDataHP(jenis, tahun);
        System.out.println(hp.getJenisHP());
        System.out.println(hp.getTahunPembuatan());
    }
}
