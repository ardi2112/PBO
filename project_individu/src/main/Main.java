package main;

import model.Pemasukan;
import model.Pengeluaran;
import service.KasService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final KasService service = new KasService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihanUtama = -1;
        while (pilihanUtama != 4) {
            System.out.println("\n=== APLIKASI MANAJEMEN KAS PRIBADI ===");
            System.out.println("1. Kelola Pemasukan");
            System.out.println("2. Kelola Pengeluaran");
            System.out.println("3. Laporan Keuangan");
            System.out.println("4. Keluar Program");
            System.out.print("Pilih menu: ");
            
            try {
                pilihanUtama = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                
                switch (pilihanUtama) {
                    case 1 -> menuPemasukan();
                    case 2 -> menuPengeluaran();
                    case 3 -> menuLaporan();
                    case 4 -> System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    default -> System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void menuPemasukan() {
        int pilihan = -1;
        while (pilihan != 3) {
            System.out.println("\n-- KELOLA PEMASUKAN --");
            System.out.println("1. Tambah Pemasukan");
            System.out.println("2. Lihat Data Pemasukan");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
                if (pilihan == 1) {
                    System.out.print("Masukkan Keterangan: ");
                    String ket = scanner.nextLine();
                    System.out.print("Masukkan Jumlah (Rp): ");
                    double jumlah = scanner.nextDouble();
                    
                    service.tambahTransaksi("MASUK", ket, jumlah);
                    System.out.println("Pemasukan berhasil dicatat!");
                } else if (pilihan == 2) {
                    List<Pemasukan> list = service.getDaftarPemasukan();
                    System.out.printf("\n| %-4s | %-12s | %-25s | %-17s |\n", "ID", "TANGGAL", "KETERANGAN", "JUMLAH");
                    for (Pemasukan p : list) p.tampilData();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Kesalahan input format angka!");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }

    private static void menuPengeluaran() {
        int pilihan = -1;
        while (pilihan != 3) {
            System.out.println("\n-- KELOLA PENGELUARAN --");
            System.out.println("1. Tambah Pengeluaran");
            System.out.println("2. Lihat Data Pengeluaran");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
                if (pilihan == 1) {
                    System.out.print("Masukkan Keterangan: ");
                    String ket = scanner.nextLine();
                    System.out.print("Masukkan Jumlah (Rp): ");
                    double jumlah = scanner.nextDouble();
                    
                    service.tambahTransaksi("KELUAR", ket, jumlah);
                    System.out.println("Pengeluaran berhasil dicatat!");
                } else if (pilihan == 2) {
                    List<Pengeluaran> list = service.getDaftarPengeluaran();
                    System.out.printf("\n| %-4s | %-12s | %-25s | %-17s |\n", "ID", "TANGGAL", "KETERANGAN", "JUMLAH");
                    for (Pengeluaran p : list) p.tampilData();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Kesalahan input format angka!");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }

    private static void menuLaporan() {
        int pilihan = -1;
        while (pilihan != 3) {
            System.out.println("\n-- LAPORAN KEUANGAN --");
            System.out.println("1. Lihat Semua Transaksi");
            System.out.println("2. Lihat Total Saldo");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            try {
                pilihan = scanner.nextInt();
                if (pilihan == 1) {
                    service.tampilkanLaporanKeuangan();
                } else if (pilihan == 2) {
                    double saldo = service.getTotalSaldo();
                    System.out.printf("\n===================================\n");
                    System.out.printf(" TOTAL SALDO SAAT INI: Rp%,.2f\n", saldo);
                    System.out.printf("===================================\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus angka!");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }
}