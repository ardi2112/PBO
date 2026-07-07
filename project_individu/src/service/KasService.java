package service;

import database.DatabaseConnection;
import model.Pemasukan;
import model.Pengeluaran;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KasService {

    // Memanggil Stored Procedure MySQL
    public void tambahTransaksi(String jenis, String keterangan, double jumlah) throws SQLException {
        String sql = "{CALL sp_tambah_transaksi(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, jenis);
            cstmt.setString(2, keterangan);
            cstmt.setDouble(3, jumlah);
            cstmt.execute();
        }
    }

    public List<Pemasukan> getDaftarPemasukan() throws SQLException {
        List<Pemasukan> list = new ArrayList<>();
        String sql = "SELECT * FROM pemasukan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pemasukan(
                        rs.getInt("id_pemasukan"),
                        rs.getString("tanggal"),
                        rs.getString("keterangan"),
                        rs.getDouble("jumlah")
                ));
            }
        }
        return list;
    }

    public List<Pengeluaran> getDaftarPengeluaran() throws SQLException {
        List<Pengeluaran> list = new ArrayList<>();
        String sql = "SELECT * FROM pengeluaran";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pengeluaran(
                        rs.getInt("id_pengeluaran"),
                        rs.getString("tanggal"),
                        rs.getString("keterangan"),
                        rs.getDouble("jumlah")
                ));
            }
        }
        return list;
    }

    // Mengambil data dari tabel Saldo (yang otomatis terupdate via Trigger)
    public double getTotalSaldo() throws SQLException {
        String sql = "SELECT total_saldo FROM saldo WHERE id_saldo = 1";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total_saldo");
            }
        }
        return 0;
    }

    // Menampilkan data dari MySQL View
    public void tampilkanLaporanKeuangan() throws SQLException {
        String sql = "SELECT * FROM vw_laporan_keuangan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n=================================== LAPORAN KEUANGAN ===================================");
            System.out.printf("| %-11s | %-4s | %-12s | %-25s | %-17s |\n", "JENIS", "ID", "TANGGAL", "KETERANGAN", "JUMLAH");
            System.out.println("----------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-11s | %-4d | %-12s | %-25s | Rp%-15.2f |\n",
                        rs.getString("jenis"),
                        rs.getInt("id"),
                        rs.getString("tanggal"),
                        rs.getString("keterangan"),
                        rs.getDouble("jumlah"));
            }
            System.out.println("================================================----------------------------------------");
        }
    }
}