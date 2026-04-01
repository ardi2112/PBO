import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    private String nim, nama;
    private int nilai;
    private String grade;

    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim; this.nama = nama; this.nilai = nilai;
        if (nilai >= 80) grade = "A";
        else if (nilai >= 70) grade = "B";
        else if (nilai >= 60) grade = "C";
        else if (nilai >= 50) grade = "D";
        else grade = "E";
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public int getNilai() { return nilai; }
    public String getGrade() { return grade; }
    public boolean lulus() { return grade.equals("A") || grade.equals("B") || grade.equals("C"); }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah mahasiswa: ");
        int n = sc.nextInt(); sc.nextLine();
        ArrayList<Mahasiswa> list = new ArrayList<>();

        // Input data mahasiswa dengan validasi nilai
        for (int i = 0; i < n; i++) {
            System.out.println("\nData mahasiswa ke-" + (i+1));
            System.out.print("NIM : "); String nim = sc.nextLine();
            System.out.print("Nama: "); String nama = sc.nextLine();
            int nilai;
            do {
                System.out.print("Nilai: ");
                if (sc.hasNextInt()) {
                    nilai = sc.nextInt();
                    if (nilai < 0 || nilai > 100) System.out.println("Input nilai anda salah");
                } else {
                    System.out.println("Input nilai anda salah");
                    nilai = -1;
                    sc.next();
                }
            } while (nilai < 0 || nilai > 100);
            sc.nextLine();
            list.add(new Mahasiswa(nim, nama, nilai));
        }

        // Tampilkan data setiap mahasiswa
        System.out.println("\n=================================");
        for (Mahasiswa m : list) {
            System.out.println("NIM : " + m.getNim());
            System.out.println("Nama: " + m.getNama());
            System.out.println("Nilai : " + m.getNilai());
            System.out.println("Grade: " + m.getGrade());
            System.out.println("---");
        }

        // Statistik
        int lulus = 0, tdkLulus = 0, totalNilai = 0;
        ArrayList<String> namaLulus = new ArrayList<>(), namaTdk = new ArrayList<>();
        ArrayList<ArrayList<String>> gradeLists = new ArrayList<>();
        for (int i = 0; i < 5; i++) gradeLists.add(new ArrayList<>());
        String[] gradeLetters = {"A", "B", "C", "D", "E"};

        for (Mahasiswa m : list) {
            totalNilai += m.getNilai();
            if (m.lulus()) { lulus++; namaLulus.add(m.getNama()); }
            else { tdkLulus++; namaTdk.add(m.getNama()); }

            switch (m.getGrade()) {
                case "A": gradeLists.get(0).add(m.getNama()); break;
                case "B": gradeLists.get(1).add(m.getNama()); break;
                case "C": gradeLists.get(2).add(m.getNama()); break;
                case "D": gradeLists.get(3).add(m.getNama()); break;
                case "E": gradeLists.get(4).add(m.getNama()); break;
            }
        }

        double rata = (double) totalNilai / n;

        // Cetak ringkasan
        System.out.println("\nJumlah Mahasiswa : " + n);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus + (lulus>0 ? " yaitu " + String.join(", ", namaLulus) : ""));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tdkLulus + (tdkLulus>0 ? " yaitu " + String.join(", ", namaTdk) : ""));

        // Cetak statistik per grade dengan loop
        for (int i = 0; i < gradeLists.size(); i++) {
            if (!gradeLists.get(i).isEmpty()) {
                System.out.println("Jumlah Mahasiswa dengan Nilai " + gradeLetters[i] + " = " + gradeLists.get(i).size() + " yaitu " + String.join(", ", gradeLists.get(i)));
            }
        }

        // Tampilkan rata-rata dengan deret penjumlahan
        System.out.print("Rata-rata nilai mahasiswa adalah : ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getNilai());
            if (i < list.size()-1) System.out.print("+");
        }
        System.out.printf(" / %d = %.2f\n", n, rata);
        sc.close();
    }
}