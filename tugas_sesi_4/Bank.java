public class Bank {
    // Overloading transferUang
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan);
    }

    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
    }

    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan + " dengan berita: " + berita);
    }

    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // Bonus: hitung biaya transfer berdasarkan bank tujuan
    public int hitungBiayaTransfer(String bankTujuan) {
        if (bankTujuan.equalsIgnoreCase("BNI")) {
            return 5000;
        } else if (bankTujuan.equalsIgnoreCase("BCA")) {
            return 6000;
        } else {
            return 4000; // biaya default untuk bank lain
        }
    }
}