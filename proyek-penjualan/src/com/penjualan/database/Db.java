package com.penjualan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306/penjualan_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn;

    // Exception Handling Terpusat pada Koneksi JDBC
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (ClassNotFoundException | SQLException e) {
            msgError("Gagal terkoneksi ke database: " + e.getMessage());
        }
        return conn;
    }

    // Polimorfisme / Helper untuk penyederhanaan baris kode GUI Swing
    public static void msgInfo(String pesan) {
        JOptionPane.showMessageDialog(null, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgError(String pesan) {
        JOptionPane.showMessageDialog(null, pesan, "Error/Kesalahan", JOptionPane.ERROR_MESSAGE);
    }

    // Eksekutor Query Singkat untuk mempersingkat baris kode di View
    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        }
    }
}