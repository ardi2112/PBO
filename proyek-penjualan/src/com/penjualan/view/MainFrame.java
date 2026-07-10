package com.penjualan.view;

import com.penjualan.database.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    // Komponen Tab Barang
    private JTextField txtB_Nama, txtB_Harga, txtB_Stok, txtB_Cari;
    private JTable tblBarang;
    private DefaultTableModel modelBarang;

    // Komponen Tab Pelanggan
    private JTextField txtP_Nama, txtP_Telepon, txtP_Cari;
    private JTable tblPelanggan;
    private DefaultTableModel modelPelanggan;

    public MainFrame() {
        initComponent();
        loadDataBarang();
        loadDataPelanggan();
    }

    private void initComponent() {
        setTitle("Menu Utama & Manajemen Data");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnKeKasir = new JButton("Buka Form Transaksi (Kasir)");
        btnKeKasir.setBounds(20, 10, 250, 30);
        add(btnKeKasir);
        btnKeKasir.addActionListener(e -> new TransaksiFrame().setVisible(true));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(20, 50, 700, 380);
        add(tabbedPane);

        // ISI TAB 1: MANAJEMEN BARANG
        JPanel panelBarang = new JPanel(null);
        JLabel lblB1 = new JLabel("Nama:"); lblB1.setBounds(10, 20, 80, 25); panelBarang.add(lblB1);
        txtB_Nama = new JTextField(); txtB_Nama.setBounds(80, 20, 150, 25); panelBarang.add(txtB_Nama);
        JLabel lblB2 = new JLabel("Harga:"); lblB2.setBounds(10, 50, 80, 25); panelBarang.add(lblB2);
        txtB_Harga = new JTextField(); txtB_Harga.setBounds(80, 50, 150, 25); panelBarang.add(txtB_Harga);
        JLabel lblB3 = new JLabel("Stok:"); lblB3.setBounds(10, 80, 80, 25); panelBarang.add(lblB3);
        txtB_Stok = new JTextField(); txtB_Stok.setBounds(80, 80, 150, 25); panelBarang.add(txtB_Stok);

        JButton btnB_Simpan = new JButton("Simpan"); btnB_Simpan.setBounds(80, 120, 80, 25); panelBarang.add(btnB_Simpan);
        JButton btnB_Hapus = new JButton("Hapus"); btnB_Hapus.setBounds(165, 120, 80, 25); panelBarang.add(btnB_Hapus);

        txtB_Cari = new JTextField(); txtB_Cari.setBounds(300, 20, 150, 25); panelBarang.add(txtB_Cari);
        JButton btnB_Cari = new JButton("Cari"); btnB_Cari.setBounds(460, 20, 70, 25); panelBarang.add(btnB_Cari);

        modelBarang = new DefaultTableModel(new String[]{"ID", "Nama Barang", "Harga", "Stok"}, 0);
        tblBarang = new JTable(modelBarang);
        JScrollPane spBarang = new JScrollPane(tblBarang);
        spBarang.setBounds(300, 50, 380, 280);
        panelBarang.add(spBarang);
        tabbedPane.addTab("Data Barang", panelBarang);

        // ISI TAB 2: MANAJEMEN PELANGGAN
        JPanel panelPelanggan = new JPanel(null);
        JLabel lblP1 = new JLabel("Nama:"); lblP1.setBounds(10, 20, 80, 25); panelPelanggan.add(lblP1);
        txtP_Nama = new JTextField(); txtP_Nama.setBounds(80, 20, 150, 25); panelPelanggan.add(txtP_Nama);
        JLabel lblP2 = new JLabel("Telepon:"); lblP2.setBounds(10, 50, 80, 25); panelPelanggan.add(lblP2);
        txtP_Telepon = new JTextField(); txtP_Telepon.setBounds(80, 50, 150, 25); panelPelanggan.add(txtP_Telepon);

        JButton btnP_Simpan = new JButton("Simpan"); btnP_Simpan.setBounds(80, 90, 80, 25); panelPelanggan.add(btnP_Simpan);
        JButton btnP_Hapus = new JButton("Hapus"); btnP_Hapus.setBounds(165, 90, 80, 25); panelPelanggan.add(btnP_Hapus);

        txtP_Cari = new JTextField(); txtP_Cari.setBounds(300, 20, 150, 25); panelPelanggan.add(txtP_Cari);
        JButton btnP_Cari = new JButton("Cari"); btnP_Cari.setBounds(460, 20, 70, 25); panelPelanggan.add(btnP_Cari);

        modelPelanggan = new DefaultTableModel(new String[]{"ID", "Nama Pelanggan", "Telepon"}, 0);
        tblPelanggan = new JTable(modelPelanggan);
        JScrollPane spPelanggan = new JScrollPane(tblPelanggan);
        spPelanggan.setBounds(300, 50, 380, 280);
        panelPelanggan.add(spPelanggan);
        tabbedPane.addTab("Data Pelanggan", panelPelanggan);

        // LOGIKA EVENT LISTENER TAB BARANG
        btnB_Simpan.addActionListener(e -> {
            try {
                // Memanggil Stored Procedure MySQL
                Db.executeUpdate("CALL sp_tambah_barang(?, ?, ?)", txtB_Nama.getText(), Integer.parseInt(txtB_Harga.getText()), Integer.parseInt(txtB_Stok.getText()));
                Db.msgInfo("Data barang berhasil ditambahkan!");
                loadDataBarang();
            } catch (Exception ex) { Db.msgError("Gagal menyimpan: " + ex.getMessage()); }
        });

        btnB_Hapus.addActionListener(e -> {
            int row = tblBarang.getSelectedRow();
            if (row == -1) { Db.msgError("Pilih baris tabel terlebih dahulu!"); return; }
            try {
                Db.executeUpdate("DELETE FROM t_barang WHERE id_barang=?", tblBarang.getValueAt(row, 0));
                loadDataBarang();
            } catch (SQLException ex) { Db.msgError(ex.getMessage()); }
        });

        btnB_Cari.addActionListener(e -> loadDataBarang());

        // LOGIKA EVENT LISTENER TAB PELANGGAN
        btnP_Simpan.addActionListener(e -> {
            try {
                Db.executeUpdate("INSERT INTO t_pelanggan(nama_pelanggan, telepon) VALUES(?,?)", txtP_Nama.getText(), txtP_Telepon.getText());
                loadDataPelanggan();
            } catch (SQLException ex) { Db.msgError(ex.getMessage()); }
        });

        btnP_Hapus.addActionListener(e -> {
            int row = tblPelanggan.getSelectedRow();
            if (row == -1) { Db.msgError("Pilih baris terlebih dahulu!"); return; }
            try {
                Db.executeUpdate("DELETE FROM t_pelanggan WHERE id_pelanggan=?", tblPelanggan.getValueAt(row, 0));
                loadDataPelanggan();
            } catch (SQLException ex) { Db.msgError(ex.getMessage()); }
        });

        btnP_Cari.addActionListener(e -> loadDataPelanggan());
    }

    private void loadDataBarang() {
        modelBarang.setRowCount(0);
        try {
            String keyword = "%" + txtB_Cari.getText() + "%";
            ResultSet rs = Db.executeQuery("SELECT * FROM t_barang WHERE nama_barang LIKE ?", keyword);
            while (rs.next()) {
                modelBarang.addRow(new Object[]{rs.getInt("id_barang"), rs.getString("nama_barang"), rs.getInt("harga"), rs.getInt("stok")});
            }
        } catch (SQLException e) { Db.msgError(e.getMessage()); }
    }

    private void loadDataPelanggan() {
        modelPelanggan.setRowCount(0);
        try {
            String keyword = "%" + txtP_Cari.getText() + "%";
            ResultSet rs = Db.executeQuery("SELECT * FROM t_pelanggan WHERE nama_pelanggan LIKE ?", keyword);
            while (rs.next()) {
                modelPelanggan.addRow(new Object[]{rs.getInt("id_pelanggan"), rs.getString("nama_pelanggan"), rs.getString("telepon")});
            }
        } catch (SQLException e) { Db.msgError(e.getMessage()); }
    }
}