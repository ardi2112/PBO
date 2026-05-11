package kampus;

// Mengimplementasikan konsep Inheritance (extends)
public class Dosen extends AnggotaKampus {
    private String mataKuliah;

    public Dosen(String id, String nama, String mataKuliah) {
        super(id, nama);
        this.mataKuliah = mataKuliah;
    }

    // Mengimplementasikan konsep Polymorphism (Overriding method)
    @Override
    public void deskripsiPeran() {
        System.out.println("ID: " + getId() + " | " + getNama() + " adalah seorang DOSEN.");
    }

    @Override
    public void aktivitasRutinku() {
        System.out.println("-> Sedang mengajar mata kuliah: " + mataKuliah);
    }
}