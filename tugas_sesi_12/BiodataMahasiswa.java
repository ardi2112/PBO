import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BiodataMahasiswa extends JFrame implements ActionListener {

    JLabel lblNim, lblNama, lblProdi, lblOutput;
    JTextField txtNim, txtNama, txtProdi;
    JButton btnTampilkan, btnReset;
    JTextArea txtOutput;

    public BiodataMahasiswa() {

        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Label
        lblNim = new JLabel("NIM");
        lblNama = new JLabel("Nama");
        lblProdi = new JLabel("Program Studi");
        lblOutput = new JLabel("Output");

        // TextField
        txtNim = new JTextField();
        txtNama = new JTextField();
        txtProdi = new JTextField();

        // Button
        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");

        // TextArea
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtOutput);

        // Posisi Komponen
        lblNim.setBounds(30, 30, 100, 25);
        txtNim.setBounds(150, 30, 250, 25);

        lblNama.setBounds(30, 70, 100, 25);
        txtNama.setBounds(150, 70, 250, 25);

        lblProdi.setBounds(30, 110, 100, 25);
        txtProdi.setBounds(150, 110, 250, 25);

        btnTampilkan.setBounds(150, 160, 100, 30);
        btnReset.setBounds(270, 160, 100, 30);

        lblOutput.setBounds(30, 210, 100, 25);
        scroll.setBounds(30, 240, 430, 100);

        // Tambahkan ke Panel
        panel.add(lblNim);
        panel.add(txtNim);

        panel.add(lblNama);
        panel.add(txtNama);

        panel.add(lblProdi);
        panel.add(txtProdi);

        panel.add(btnTampilkan);
        panel.add(btnReset);

        panel.add(lblOutput);
        panel.add(scroll);

        // Event
        btnTampilkan.addActionListener(this);
        btnReset.addActionListener(this);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnTampilkan) {

            String nim = txtNim.getText();
            String nama = txtNama.getText();
            String prodi = txtProdi.getText();

            txtOutput.setText(
                    "========== BIODATA MAHASISWA ==========\n\n" +
                    "NIM            : " + nim +
                    "\nNama           : " + nama +
                    "\nProgram Studi  : " + prodi);

        }

        if (e.getSource() == btnReset) {

            txtNim.setText("");
            txtNama.setText("");
            txtProdi.setText("");
            txtOutput.setText("");

            txtNim.requestFocus();
        }

    }

    public static void main(String[] args) {
        new BiodataMahasiswa().setVisible(true);
    }
}