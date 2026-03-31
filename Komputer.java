package sesi_2;
/**
 * Tugas Pemrograman Berorientasi Objek
 * 
 * ============================================
 * 1. PENJELASAN CLASS DAN OBJECT
 * ============================================
 * Class adalah blueprint atau template yang mendefinisikan atribut (variabel)
 * dan perilaku (method) yang dimiliki oleh suatu objek. Class tidak memiliki
 * wujud fisik, hanya berupa definisi.
 * 
 * Object adalah instance nyata dari sebuah class. Object memiliki nilai konkret
 * untuk atribut yang didefinisikan dalam class dan dapat menjalankan method-
 * method yang ada. Contoh: objek 'mykom' yang dibuat dari class Komputer.
 * 
 * ============================================
 * 2. JENIS-JENIS METHOD
 * ============================================
 * a. Method Konstruktor
 *    - Method khusus yang dipanggil saat objek dibuat. Namanya sama dengan
 *      nama class, tidak memiliki tipe return, dan digunakan untuk inisialisasi.
 * 
 * b. Method Akses (Accessor / Getter)
 *    - Digunakan untuk mengambil nilai atribut. Biasanya publik dan memiliki
 *      tipe return sesuai atribut. Contoh: getJenis(), getMerk().
 * 
 * c. Method Mutator (Setter)
 *    - Digunakan untuk mengubah nilai atribut. Biasanya publik dan bertipe void.
 *      Contoh: setDataKomputer().
 * 
 * d. Method Utilitas (Utility Method)
 *    - Method yang melakukan operasi tertentu selain getter/setter, misalnya
 *      perhitungan atau menampilkan informasi.
 * 
 * e. Method Statis (Static Method)
 *    - Method yang dapat dipanggil tanpa membuat objek, menggunakan kata kunci
 *      static. Contoh: method main().
 * 
 * ============================================
 * 3. PENJELASAN BAGIAN-BAGIAN KODE BERDASARKAN NOMOR
 * ============================================
 * Kode di bawah ini adalah class Komputer yang diberikan dalam gambar.
 * Setiap bagian diberi nomor (1-10) dan dijelaskan dalam komentar.
 */

public class Komputer {
    // (1) Deklarasi class Komputer
    //     - Class ini berisi atribut dan method yang merepresentasikan komputer.

    // (2) Atribut jenis_komputer dengan akses default (package-private)
    String jenis_komputer;

    // (3) Atribut merk dengan akses private
    //     - Hanya dapat diakses di dalam class Komputer.
    private String merk;

    // (4) Method mutator (setter) untuk mengisi data komputer
    //     - Parameter jenis dan merk digunakan untuk mengisi atribut.
    //     - this.merk digunakan untuk membedakan parameter dengan atribut.
    public void setDataKomputer(String jenis, String merk) {
        jenis_komputer = jenis;
        this.merk = merk;
    }

    // (5) Method accessor (getter) untuk mengambil nilai jenis_komputer
    public String getJenis() {
        return jenis_komputer;
    }

    // (6) Method accessor (getter) untuk mengambil nilai merk
    public String getMerk() {
        return merk;
    }

    // (7) Method main (entry point) – method statis
    //     - Program akan dimulai dari sini.
    public static void main(String[] args) {
        // (8) Pembuatan objek mykom dari class Komputer
        Komputer mykom = new Komputer();

        // (9) Memanggil method setDataKomputer untuk mengisi data objek
        mykom.setDataKomputer("LAPTOP", "MACBOOK");

        // (10) Mencetak hasil dari method getter
        System.out.println(mykom.getJenis()); // output: LAPTOP
        System.out.println(mykom.getMerk());  // output: MACBOOK
    }
}