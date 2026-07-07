CREATE DATABASE IF NOT EXISTS db_kas_pribadi;
USE db_kas_pribadi;

-- 1. Membuat Tabel Pemasukan
CREATE TABLE IF NOT EXISTS pemasukan (
    id_pemasukan INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE NOT NULL,
    keterangan VARCHAR(255) NOT NULL,
    jumlah DOUBLE NOT NULL
);

-- 2. Membuat Tabel Pengeluaran
CREATE TABLE IF NOT EXISTS pengeluaran (
    id_pengeluaran INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE NOT NULL,
    keterangan VARCHAR(255) NOT NULL,
    jumlah DOUBLE NOT NULL
);

-- 3. Membuat Tabel Saldo & Inisialisasi Data Awal
CREATE TABLE IF NOT EXISTS saldo (
    id_saldo INT AUTO_INCREMENT PRIMARY KEY,
    total_saldo DOUBLE NOT NULL DEFAULT 0
);
INSERT INTO saldo (total_saldo) SELECT 0 WHERE NOT EXISTS (SELECT * FROM saldo);

DELIMITER $$

-- 4. Function: Menghitung Sisa Saldo saat ini
CREATE FUNCTION fn_hitung_saldo() 
RETURNS DOUBLE
DETERMINISTIC
BEGIN
    DECLARE total_masuk DOUBLE DEFAULT 0;
    DECLARE total_keluar DOUBLE DEFAULT 0;
    
    SELECT IFNULL(SUM(jumlah), 0) INTO total_masuk FROM pemasukan;
    SELECT IFNULL(SUM(jumlah), 0) INTO total_keluar FROM pengeluaran;
    
    RETURN total_masuk - total_keluar;
END$$

-- 5. Stored Procedure: Tambah Transaksi Genrik
CREATE PROCEDURE sp_tambah_transaksi(
    IN p_jenis VARCHAR(10), 
    IN p_keterangan VARCHAR(255), 
    IN p_jumlah DOUBLE
)
BEGIN
    IF p_jenis = 'MASUK' THEN
        INSERT INTO pemasukan (tanggal, keterangan, jumlah) VALUES (CURDATE(), p_keterangan, p_jumlah);
    ELSEIF p_jenis = 'KELUAR' THEN
        INSERT INTO pengeluaran (tanggal, keterangan, jumlah) VALUES (CURDATE(), p_keterangan, p_jumlah);
    END IF;
END$$

-- 6. Triggers: Otomatis Update Tabel Saldo ketika ada Data Baru
CREATE TRIGGER trg_after_insert_pemasukan
AFTER INSERT ON pemasukan
FOR EACH ROW
BEGIN
    UPDATE saldo SET total_saldo = fn_hitung_saldo() WHERE id_saldo = 1;
END$$

CREATE TRIGGER trg_after_insert_pengeluaran
AFTER INSERT ON pengeluaran
FOR EACH ROW
BEGIN
    UPDATE saldo SET total_saldo = fn_hitung_saldo() WHERE id_saldo = 1;
END$$

DELIMITER ;

-- 7. View: Laporan Gabungan Transaksi
CREATE VIEW vw_laporan_keuangan AS
SELECT 'Pemasukan' AS jenis, id_pemasukan AS id, tanggal, keterangan, jumlah FROM pemasukan
UNION ALL
SELECT 'Pengeluaran' AS jenis, id_pengeluaran AS id, tanggal, keterangan, jumlah FROM pengeluaran
ORDER BY tanggal ASC;
