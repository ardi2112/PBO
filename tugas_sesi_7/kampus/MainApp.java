package kampus;

public class MainApp {
    public static void main(String[] args) {
        // Mengimplementasikan Object Creation dari Generic Class
        DatabaseGeneric<AnggotaKampus> sistemKampus = new DatabaseGeneric<>();

        // Mengimplementasikan Class & Object (Instansiasi objek Dosen dan Mahasiswa)
        AnggotaKampus dosen1 = new Dosen("DSN01", "Pak Budi", "Pemrograman Java");
        AnggotaKampus mhs1 = new Mahasiswa("MHS01", "Andi Supriyadi", "Teknik Informatika");
        AnggotaKampus mhs2 = new Mahasiswa("MHS02", "Siti Rahma", "Sistem Informasi");

        // Memasukkan objek ke dalam Collection
        sistemKampus.tambahData(dosen1);
        sistemKampus.tambahData(mhs1);
        sistemKampus.tambahData(mhs2);

        // Menjalankan aplikasi
        System.out.println("==================================================");
        System.out.println("      SISTEM INFORMASI AKADEMIK SEDERHANA         ");
        System.out.println("==================================================");
        
        sistemKampus.jalankanSemuaAktivitas();
    }
}