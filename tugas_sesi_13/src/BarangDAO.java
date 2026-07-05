import java.sql.*;
import java.util.Scanner;

public class BarangDAO {

    private Connection conn;
    private Scanner input = new Scanner(System.in);

    public BarangDAO() {
        conn = Koneksi.getConnection();
    }

    // ===========================
    // TAMPIL DATA
    // ===========================
    public void tampilData() {

        String sql = "SELECT * FROM barang";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n==============================================");
            System.out.println("           DAFTAR BARANG");
            System.out.println("==============================================");
            System.out.printf("%-10s %-20s %-10s %-10s\n",
                    "Kode", "Nama Barang", "Harga", "Stok");
            System.out.println("----------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-10s %-20s %-10d %-10d\n",
                        rs.getString("kode"),
                        rs.getString("nama_barang"),
                        rs.getInt("harga"),
                        rs.getInt("stok"));

            }

            System.out.println("==============================================");

        } catch (SQLException e) {

            System.out.println("Gagal menampilkan data!");
            System.out.println(e.getMessage());

        }

    }

    // ===========================
    // TAMBAH DATA
    // ===========================
    public void tambahData() {

        try {

            Barang barang = new Barang();

            System.out.print("Kode Barang : ");
            barang.setKode(input.nextLine());

            System.out.print("Nama Barang : ");
            barang.setNamaBarang(input.nextLine());

            System.out.print("Harga : ");
            barang.setHarga(Integer.parseInt(input.nextLine()));

            System.out.print("Stok : ");
            barang.setStok(Integer.parseInt(input.nextLine()));

            String sql = "INSERT INTO barang VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, barang.getKode());
            ps.setString(2, barang.getNamaBarang());
            ps.setInt(3, barang.getHarga());
            ps.setInt(4, barang.getStok());

            ps.executeUpdate();

            System.out.println("\nData berhasil ditambahkan!");

        } catch (SQLException e) {

            System.out.println("Gagal menambah data!");
            System.out.println(e.getMessage());

        }

    }

    // ===========================
    // CARI DATA
    // ===========================
    public void cariData() {

        try {

            System.out.print("Masukkan Kode Barang : ");
            String kode = input.nextLine();

            String sql = "SELECT * FROM barang WHERE kode=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, kode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nData Ditemukan");
                System.out.println("-----------------------");
                System.out.println("Kode  : " + rs.getString("kode"));
                System.out.println("Nama  : " + rs.getString("nama_barang"));
                System.out.println("Harga : " + rs.getInt("harga"));
                System.out.println("Stok  : " + rs.getInt("stok"));

            } else {

                System.out.println("Data tidak ditemukan.");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    // ===========================
    // UBAH DATA
    // ===========================
    public void ubahData() {

        try {

            System.out.print("Masukkan Kode Barang : ");
            String kode = input.nextLine();

            String cek = "SELECT * FROM barang WHERE kode=?";

            PreparedStatement psCek = conn.prepareStatement(cek);
            psCek.setString(1, kode);

            ResultSet rs = psCek.executeQuery();

            if (rs.next()) {

                System.out.print("Nama Baru : ");
                String nama = input.nextLine();

                System.out.print("Harga Baru : ");
                int harga = Integer.parseInt(input.nextLine());

                System.out.print("Stok Baru : ");
                int stok = Integer.parseInt(input.nextLine());

                String sql = "UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE kode=?";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, nama);
                ps.setInt(2, harga);
                ps.setInt(3, stok);
                ps.setString(4, kode);

                ps.executeUpdate();

                System.out.println("\nData berhasil diubah!");

            } else {

                System.out.println("Kode barang tidak ditemukan.");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    // ===========================
    // HAPUS DATA
    // ===========================
    public void hapusData() {

        try {

            System.out.print("Masukkan Kode Barang : ");
            String kode = input.nextLine();

            String sql = "DELETE FROM barang WHERE kode=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, kode);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {

                System.out.println("Data berhasil dihapus.");

            } else {

                System.out.println("Data tidak ditemukan.");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

}