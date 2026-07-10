package com.penjualan.view;

import com.penjualan.database.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        initComponent();
    }

    private void initComponent() {
        setTitle("Login Sistem Penjualan");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 30, 80, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 30, 170, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 170, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 120, 100, 30);
        add(btnLogin);

        // Event handling login menggunakan lambda expression
        btnLogin.addActionListener(e -> prosesLogin());
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            Db.msgError("Username dan Password tidak boleh kosong!");
            return;
        }

        try {
            String sql = "SELECT * FROM t_user WHERE username=? AND password=?";
            ResultSet rs = Db.executeQuery(sql, username, password);

            if (rs.next()) {
                Db.msgInfo("Login Berhasil! Selamat datang " + username);
                new MainFrame().setVisible(true);
                this.dispose(); // Menutup form login
            } else {
                Db.msgError("Username atau Password salah!");
            }
        } catch (SQLException ex) {
            Db.msgError("Terjadi kesalahan basis data: " + ex.getMessage());
        }
    }
}