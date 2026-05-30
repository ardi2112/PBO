import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\ardia\\Documents\\teknik_informatika\\semester_4\\PBO\\tugas_sesi_11\\students.csv"; 
        String line;
        String csvSplitBy = ",";
        
        int indeks = 0;
        int lineCount = 0;
        
        System.out.println("NIM, NAMA, UMUR, PRODI");
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                indeks++;
                lineCount++;
                
                if (indeks > 1) {
                    String[] student = line.split(csvSplitBy);
                    if (student.length >= 4) {
                        System.out.println(student[0].trim() + ", " + 
                                           student[1].trim() + ", " + 
                                           student[2].trim() + ", " + 
                                           student[3].trim());
                    }
                }
            }
            
            System.out.println("-----------------------------------");
            System.out.println("Total jumlah baris dalam file: " + lineCount);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}