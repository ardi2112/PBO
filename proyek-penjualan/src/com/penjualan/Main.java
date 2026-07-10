package com.penjualan;

import com.penjualan.view.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Menjalankan LoginFrame pertama kali saat aplikasi dimulai
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}