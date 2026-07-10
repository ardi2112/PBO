CREATE DATABASE IF NOT EXISTS penjualan_db;
USE penjualan_db;

-- ==========================================
-- 1. PEMBUATAN TABEL UTAMA
-- ==========================================

CREATE TABLE t_user (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE t_barang (
    id_barang INT AUTO_INCREMENT PRIMARY KEY,
    nama_barang VARCHAR(100) NOT NULL,
    harga INT NOT NULL,
    stok INT NOT NULL
);

CREATE TABLE t_pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    telepon VARCHAR(15)
);

CREATE TABLE t_transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    tgl_transaksi TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_pelanggan INT,
    total_bayar INT DEFAULT 0,
    FOREIGN KEY (id_pelanggan) REFERENCES t_pelanggan(id_pelanggan) ON DELETE SET NULL
);

CREATE TABLE t_detail_transaksi (
    id_detail INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT,
    id_barang INT,
    jumlah INT NOT NULL,
    subtotal INT NOT NULL,
    FOREIGN KEY (id_transaksi) REFERENCES t_transaksi(id_transaksi) ON DELETE CASCADE,
    FOREIGN KEY (id_barang) REFERENCES t_barang(id_barang)
);

-- ==========================================
-- 2. PENGISIAN DATA AWAL (INITIAL DATA)
-- ==========================================

INSERT INTO t_user (username, password, role) VALUES ('admin', 'admin123', 'Admin');
INSERT INTO t_barang (nama_barang, harga, stok) VALUES ('Kopi Hitam', 5000, 50), ('Susu UHT', 6000, 30);
INSERT INTO t_pelanggan (nama_pelanggan, telepon) VALUES ('Umum', '0812345678');

-- ==========================================
-- 3. IMPLEMENTASI VIEW (Untuk Laporan Penjualan)
-- ==========================================

CREATE VIEW v_laporan_penjualan AS
SELECT 
    t.id_transaksi, 
    t.tgl_transaksi, 
    p.nama_pelanggan, 
    b.nama_barang, 
    dt.jumlah, 
    dt.subtotal
FROM t_detail_transaksi dt
JOIN t_transaksi t ON dt.id_transaksi = t.id_transaksi
JOIN t_barang b ON dt.id_barang = b.id_barang
LEFT JOIN t_pelanggan p ON t.id_pelanggan = p.id_pelanggan;

-- ==========================================
-- 4. IMPLEMENTASI FUNCTION (Hitung Total Pendapatan)
-- ==========================================

DELIMITER $$
CREATE FUNCTION fn_total_pendapatan() RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT SUM(total_bayar) INTO total FROM t_transaksi;
    RETURN IFNULL(total, 0);
END$$
DELIMITER ;

-- ==========================================
-- 5. IMPLEMENTASI TRIGGER (Otomatis Potong Stok)
-- ==========================================

DELIMITER $$
CREATE TRIGGER tr_kurangi_stok
AFTER INSERT ON t_detail_transaksi
FOR EACH ROW
BEGIN
    UPDATE t_barang SET stok = stok - NEW.jumlah WHERE id_barang = NEW.id_barang;
END$$
DELIMITER ;

-- ==========================================
-- 6. IMPLEMENTASI STORED PROCEDURE (CRUD & Transaksi)
-- ==========================================

DELIMITER $$
CREATE PROCEDURE sp_tambah_barang(IN p_nama VARCHAR(100), IN p_harga INT, IN p_stok INT)
BEGIN
    INSERT INTO t_barang (nama_barang, harga, stok) VALUES (p_nama, p_harga, p_stok);
END$$

CREATE PROCEDURE sp_tambah_transaksi(IN p_id_pelanggan INT, OUT p_id_baru INT)
BEGIN
    INSERT INTO t_transaksi (id_pelanggan) VALUES (p_id_pelanggan);
    SET p_id_baru = LAST_INSERT_ID();
END$$
DELIMITER ;