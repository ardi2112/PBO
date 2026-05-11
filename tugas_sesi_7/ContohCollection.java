import java.util.ArrayDeque;
import java.util.ArrayList;

public class ContohCollection {

    public static void main(String[] args) {
        // ==========================================
        // 1. IMPLEMENTASI ARRAYLIST
        // ==========================================
        System.out.println("=== 1. Contoh ArrayList ===");
        ArrayList<String> daftarMataKuliah = new ArrayList<>();
        
        // Menambahkan data
        daftarMataKuliah.add("Struktur Data");
        daftarMataKuliah.add("Pemrograman Berorientasi Objek");
        daftarMataKuliah.add("Basis Data");

        // Menampilkan dan mengakses elemen berdasarkan indeks
        System.out.println("Daftar Matkul   : " + daftarMataKuliah);
        System.out.println("Matkul Indeks 1 : " + daftarMataKuliah.get(1));
        
        
        // ==========================================
        // 2. IMPLEMENTASI ARRAYDEQUE (Sebagai Queue)
        // ==========================================
        System.out.println("\n=== 2. Contoh ArrayDeque (Antrian/Queue) ===");
        ArrayDeque<String> antrianKRS = new ArrayDeque<>();
        
        // Menambahkan data ke akhir antrian (Enqueue)
        antrianKRS.addLast("Mahasiswa A");
        antrianKRS.addLast("Mahasiswa B");
        antrianKRS.addLast("Mahasiswa C");

        System.out.println("Kondisi Antrian : " + antrianKRS);
        
        // Mengambil dan menghapus data dari awal antrian (Dequeue)
        System.out.println("Diproses        : " + antrianKRS.pollFirst());
        System.out.println("Sisa Antrian    : " + antrianKRS);
    }
}