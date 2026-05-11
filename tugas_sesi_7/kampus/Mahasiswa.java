package kampus;

// Mengimplementasikan konsep Inheritance
public class Mahasiswa extends AnggotaKampus {
    private String jurusan;

    public Mahasiswa(String id, String nama, String jurusan) {
        super(id, nama);
        this.jurusan = jurusan;
    }

    // Mengimplementasikan konsep Polymorphism (Overriding method)
    @Override
    public void deskripsiPeran() {
        System.out.println("ID: " + getId() + " | " + getNama() + " adalah seorang MAHASISWA.");
    }

    @Override
    public void aktivitasRutinku() {
        System.out.println("-> Sedang mengikuti perkuliahan di jurusan: " + jurusan);
    }
}