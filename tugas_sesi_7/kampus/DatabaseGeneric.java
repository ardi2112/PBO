package kampus;

import java.util.ArrayList;

// Mengimplementasikan konsep Generic Class dibatasi khusus untuk objek AnggotaKampus
public class DatabaseGeneric<T extends AnggotaKampus> {
    // Mengimplementasikan konsep Collection (ArrayList)
    private ArrayList<T> listData = new ArrayList<>();

    // Method untuk menambah data ke collection
    public void tambahData(T data) {
        listData.add(data);
    }

    // Method untuk menampilkan seluruh data (Polymorphism in action)
    public void jalankanSemuaAktivitas() {
        for (T item : listData) {
            item.deskripsiPeran();
            item.aktivitasRutinku();
            System.out.println("--------------------------------------------------");
        }
    }
}