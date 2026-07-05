import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BarangDAO dao = new BarangDAO();

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("      MENU TOKO RETAIL");
            System.out.println("==================================");
            System.out.println("1. Tampil Semua Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Hapus Data");
            System.out.println("0. Keluar");
            System.out.println("==================================");
            System.out.print("Pilihan : ");

            pilihan = input.nextInt();
            input.nextLine(); // membersihkan newline

            switch (pilihan) {

                case 1:
                    dao.tampilData();
                    break;

                case 2:
                    dao.tambahData();
                    break;

                case 3:
                    dao.cariData();
                    break;

                case 4:
                    dao.ubahData();
                    break;

                case 5:
                    dao.hapusData();
                    break;

                case 0:
                    System.out.println("\nTerima kasih telah menggunakan program.");
                    break;

                default:
                    System.out.println("\nPilihan tidak tersedia!");

            }

        } while (pilihan != 0);

        input.close();

    }
}