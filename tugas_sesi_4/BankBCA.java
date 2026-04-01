public class BankBCA extends Bank {
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga BCA adalah 4.5%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Override: bank tujuan ditetapkan "BCA" (argumen diabaikan)
        int fee = hitungBiayaTransfer("BCA");
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank BCA. Biaya transfer: Rp " + fee);
    }
}