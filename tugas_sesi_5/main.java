import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Student[] students = new Student[10];
    static Teacher[] teachers = new Teacher[10];
    static int studentCount = 0;
    static int teacherCount = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEM MANAJEMEN AKADEMIK - OOP      ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1 -> tambahMahasiswa();
                case 2 -> tambahDosen();
                case 3 -> tambahMataKuliahMahasiswa();
                case 4 -> lihatNilaiMahasiswa();
                case 5 -> lihatRataNilaiMahasiswa();
                case 6 -> tambahMataKuliahDosen();
                case 7 -> hapusMataKuliahDosen();
                case 8 -> lihatSemuaMahasiswa();
                case 9 -> lihatSemuaDosen();
                case 0 -> {
                    System.out.println("\nTerima kasih! Program selesai.");
                    running = false;
                }
                default -> System.out.println("Pilihan tidak valid, coba lagi.\n");
            }
        }

        scanner.close();
    }

    static void tampilkanMenu() {
        System.out.println("\n--- MENU UTAMA ---");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tambah Dosen");
        System.out.println("3. Tambah Mata Kuliah & Nilai Mahasiswa");
        System.out.println("4. Lihat Nilai Mahasiswa");
        System.out.println("5. Lihat Rata-Rata Nilai Mahasiswa");
        System.out.println("6. Tambah Mata Kuliah yang Diampu Dosen");
        System.out.println("7. Hapus Mata Kuliah dari Dosen");
        System.out.println("8. Lihat Semua Mahasiswa");
        System.out.println("9. Lihat Semua Dosen");
        System.out.println("0. Keluar");
        System.out.println("------------------");
    }

    // ===================== MAHASISWA =====================

    static void tambahMahasiswa() {
        System.out.println("\n-- Tambah Mahasiswa --");
        System.out.print("Nama Mahasiswa : ");
        String nama = scanner.nextLine();
        System.out.print("Alamat         : ");
        String alamat = scanner.nextLine();

        students[studentCount++] = new Student(nama, alamat);
        System.out.println("Mahasiswa berhasil ditambahkan: " + students[studentCount - 1]);
    }

    static void tambahMataKuliahMahasiswa() {
        if (studentCount == 0) {
            System.out.println("Belum ada mahasiswa terdaftar.");
            return;
        }

        System.out.println("\n-- Tambah Mata Kuliah & Nilai Mahasiswa --");
        tampilkanDaftarMahasiswa();
        int idx = bacaInt("Pilih nomor mahasiswa: ") - 1;

        if (idx < 0 || idx >= studentCount) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        System.out.print("Nama Mata Kuliah : ");
        String matkul = scanner.nextLine();
        int nilai = bacaInt("Nilai (0-100)    : ");

        students[idx].addCourseGrade(matkul, nilai);
    }

    static void lihatNilaiMahasiswa() {
        if (studentCount == 0) {
            System.out.println("Belum ada mahasiswa terdaftar.");
            return;
        }

        System.out.println("\n-- Lihat Nilai Mahasiswa --");
        tampilkanDaftarMahasiswa();
        int idx = bacaInt("Pilih nomor mahasiswa: ") - 1;

        if (idx < 0 || idx >= studentCount) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        students[idx].printGrades();
    }

    static void lihatRataNilaiMahasiswa() {
        if (studentCount == 0) {
            System.out.println("Belum ada mahasiswa terdaftar.");
            return;
        }

        System.out.println("\n-- Rata-Rata Nilai Mahasiswa --");
        tampilkanDaftarMahasiswa();
        int idx = bacaInt("Pilih nomor mahasiswa: ") - 1;

        if (idx < 0 || idx >= studentCount) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        double rata = students[idx].getAverageGrade();
        System.out.printf("Rata-rata nilai %s: %.2f%n", students[idx].getName(), rata);
    }

    static void lihatSemuaMahasiswa() {
        System.out.println("\n-- Daftar Semua Mahasiswa --");
        if (studentCount == 0) {
            System.out.println("Belum ada mahasiswa terdaftar.");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    static void tampilkanDaftarMahasiswa() {
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + students[i].getName());
        }
    }

    // ===================== DOSEN =====================

    static void tambahDosen() {
        System.out.println("\n-- Tambah Dosen --");
        System.out.print("Nama Dosen : ");
        String nama = scanner.nextLine();
        System.out.print("Alamat     : ");
        String alamat = scanner.nextLine();

        teachers[teacherCount++] = new Teacher(nama, alamat);
        System.out.println("Dosen berhasil ditambahkan: " + teachers[teacherCount - 1]);
    }

    static void tambahMataKuliahDosen() {
        if (teacherCount == 0) {
            System.out.println("Belum ada dosen terdaftar.");
            return;
        }

        System.out.println("\n-- Tambah Mata Kuliah Dosen --");
        tampilkanDaftarDosen();
        int idx = bacaInt("Pilih nomor dosen: ") - 1;

        if (idx < 0 || idx >= teacherCount) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        System.out.print("Nama Mata Kuliah: ");
        String matkul = scanner.nextLine();

        teachers[idx].addCourse(matkul);
    }

    static void hapusMataKuliahDosen() {
        if (teacherCount == 0) {
            System.out.println("Belum ada dosen terdaftar.");
            return;
        }

        System.out.println("\n-- Hapus Mata Kuliah Dosen --");
        tampilkanDaftarDosen();
        int idx = bacaInt("Pilih nomor dosen: ") - 1;

        if (idx < 0 || idx >= teacherCount) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        teachers[idx].printCourses();
        System.out.print("Nama Mata Kuliah yang dihapus: ");
        String matkul = scanner.nextLine();

        teachers[idx].removeCourse(matkul);
    }

    static void lihatSemuaDosen() {
        System.out.println("\n-- Daftar Semua Dosen --");
        if (teacherCount == 0) {
            System.out.println("Belum ada dosen terdaftar.");
            return;
        }
        for (int i = 0; i < teacherCount; i++) {
            System.out.println((i + 1) + ". " + teachers[i]);
            teachers[i].printCourses();
        }
    }

    static void tampilkanDaftarDosen() {
        for (int i = 0; i < teacherCount; i++) {
            System.out.println((i + 1) + ". " + teachers[i].getName());
        }
    }

    // ===================== HELPER =====================

    static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka, coba lagi.");
            }
        }
    }
}