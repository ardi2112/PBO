import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\ardia\\Documents\\teknik_informatika\\semester_4\\PBO\\tugas_sesi_11\\new_students.csv";
        Scanner input = new Scanner(System.in);
        
        System.out.println("=== PROGRAM INPUT DATA MAHASISWA (CSV) ===");
        System.out.print("Masukkan jumlah mahasiswa yang ingin diinput: ");
        int jumlahData = input.nextInt();
        input.nextLine();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("NIM, NAMA, UMUR, PRODI");
            bw.newLine();
            
            for (int i = 0; i < jumlahData; i++) {
                System.out.println("\nData Mahasiswa ke-" + (i + 1) + ":");
                System.out.print("NIM   : ");
                String nim = input.nextLine();
                System.out.print("Nama  : ");
                String nama = input.nextLine();
                System.out.print("Umur  : ");
                String umur = input.nextLine();
                System.out.print("Prodi : ");
                String prodi = input.nextLine();
                
                String gabungBaris = nim + ", " + nama + ", " + umur + ", " + prodi;
                
                bw.write(gabungBaris);
                bw.newLine();
            }
            
            System.out.println("\nData berhasil disimpan ke: " + csvFile);
            
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file.");
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}