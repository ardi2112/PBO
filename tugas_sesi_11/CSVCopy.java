import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String fileAsal = "C:\\Users\\ardia\\Documents\\teknik_informatika\\semester_4\\PBO\\tugas_sesi_11\\students.csv";
        String fileTujuan = "C:\\Users\\ardia\\Documents\\teknik_informatika\\semester_4\\PBO\\tugas_sesi_11\\copied_students.csv";
        
        System.out.println("Memulai proses penyalinan file...");
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileAsal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTujuan))) {
            
            String line;
            int barisTersalin = 0;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                barisTersalin++;
            }
            
            System.out.println("Penyalinan selesai dengan sukses!");
            System.out.println("Jumlah baris yang disalin: " + barisTersalin);
            System.out.println("File baru tersimpan di: " + fileTujuan);
            
        } catch (IOException e) {
            System.out.println("Gagal menyalin file. Pastikan file asal 'students.csv' sudah ada di folder.");
            e.printStackTrace();
        }
    }
}