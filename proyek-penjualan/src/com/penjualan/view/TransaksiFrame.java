package com.penjualan.view;

import com.penjualan.database.Db;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TransaksiFrame extends JFrame {
    private JTextField txtIdPelanggan, txtIdBarang, txtJumlah;
    private JTable tblKeranjang;
    private DefaultTableModel modelKeranjang;
    private JLabel lblTotal;
    private int grandTotal = 0;

    public TransaksiFrame() {
        initComponent();
    }

    private void initComponent() {
        setTitle("Form Kasir - Transaksi Penjualan");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl1 = new JLabel("ID Pelanggan:"); lbl1.setBounds(30, 20, 100, 25); add(lbl1);
        txtIdPelanggan = new JTextField("1"); txtIdPelanggan.setBounds(140, 20, 100, 25); add(txtIdPelanggan);

        JLabel lbl2 = new JLabel("ID Barang:"); lbl2.setBounds(30, 60, 100, 25); add(lbl2);
        txtIdBarang = new JTextField(); txtIdBarang.setBounds(140, 60, 100, 25); add(txtIdBarang);

        JLabel lbl3 = new JLabel("Jumlah Beli:"); lbl3.setBounds(30, 100, 100, 25); add(lbl3);
        txtJumlah = new JTextField(); txtJumlah.setBounds(140, 100, 100, 25); add(txtJumlah);

        JButton btnTambah = new JButton("Tambah ke Keranjang");
        btnTambah.setBounds(30, 140, 210, 30);
        add(btnTambah);

        modelKeranjang = new DefaultTableModel(new String[]{"ID Barang", "Nama Barang", "Harga", "Jumlah", "Subtotal"}, 0);
        tblKeranjang = new JTable(modelKeranjang);
        JScrollPane sp = new JScrollPane(tblKeranjang);
        sp.setBounds(270, 20, 300, 280);
        add(sp);

        lblTotal = new JLabel("TOTAL BAYAR: Rp 0");
        lblTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblTotal.setBounds(270, 310, 300, 30);
        add(lblTotal);

        JButton btnCheckout = new JButton("Selesaikan & Simpan Transaksi");
        btnCheckout.setBounds(270, 350, 300, 35);
        add(btnCheckout);

        // EVENT LOGIKA: TAMBAH ITEM KE KERANJANG BELANJA
        btnTambah.addActionListener(e -> {
            try {
                ResultSet rs = Db.executeQuery("SELECT * FROM t_barang WHERE id_barang = ?", Integer.parseInt(txtIdBarang.getText()));
                if (rs.next()) {
                    int harga = rs.getInt("harga");
                    int qty = Integer.parseInt(txtJumlah.getText());
                    int subtotal = harga * qty;
                    
                    modelKeranjang.addRow(new Object[]{
                        rs.getInt("id_barang"), rs.getString("nama_barang"), harga, qty, subtotal
                    });
                    
                    grandTotal += subtotal;
                    lblTotal.setText("TOTAL BAYAR: Rp " + grandTotal);
                } else {
                    Db.msgError("Barang tidak ditemukan!");
                }
            } catch (Exception ex) { Db.msgError("Input tidak valid: " + ex.getMessage()); }
        });

        // EVENT LOGIKA: SIMPAN TRANSAKSI KE DATABASE (TRANSACTION HANDLING & CALL STORED PROCEDURE)
        btnCheckout.addActionListener(e -> {
            if (modelKeranjang.getRowCount() == 0) { Db.msgError("Keranjang masih kosong!"); return; }
            Connection conn = Db.getConnection();
            try {
                conn.setAutoCommit(false); // Mengaktifkan Transaction Handling manual
                
                // 1. Panggil Stored Procedure untuk membuat baris induk transaksi
                CallableStatement cstmt = conn.prepareCall("{CALL sp_tambah_transaksi(?, ?)}");
                cstmt.setInt(1, Integer.parseInt(txtIdPelanggan.getText()));
                cstmt.registerOutParameter(2, Types.INTEGER);
                cstmt.execute();
                int idTransaksiBaru = cstmt.getInt(2);

                // 2. Loop keranjang belanja untuk memasukkan data ke tabel detail transaksi
                for (int i = 0; i < modelKeranjang.getRowCount(); i++) {
                    int idBarang = (int) modelKeranjang.getValueAt(i, 0);
                    int qty = (int) modelKeranjang.getValueAt(i, 3);
                    int sub = (int) modelKeranjang.getValueAt(i, 4);

                    // Insert ini otomatis memicu TRIGGER tr_kurangi_stok di MySQL
                    Db.executeUpdate("INSERT INTO t_detail_transaksi(id_transaksi, id_barang, jumlah, subtotal) VALUES(?,?,?,?)",
                            idTransaksiBaru, idBarang, qty, sub);
                }

                // 3. Update total bayar akhir di tabel utama transaksi
                Db.executeUpdate("UPDATE t_transaksi SET total_bayar = ? WHERE id_transaksi = ?", grandTotal, idTransaksiBaru);
                
                conn.commit(); // Jika sukses semua, simpan permanen ke database
                Db.msgInfo("Transaksi Berhasil Disimpan! Stok barang terpotong otomatis oleh Trigger.");
                
                // Reset form setelah sukses
                modelKeranjang.setRowCount(0);
                grandTotal = 0;
                lblTotal.setText("TOTAL BAYAR: Rp 0");
            } catch (Exception ex) {
                try { conn.rollback(); } catch (SQLException rbe) { } // Batalkan jika ada eror
                Db.msgError("Transaksi Gagal Terjadi: " + ex.getMessage());
            }
        });
    }
}