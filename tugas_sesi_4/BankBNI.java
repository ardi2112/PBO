public class BankBNI extends Bank {
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga BNI adalah 4%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // Override: bank tujuan ditetapkan "BNI" (argumen diabaikan)
        int fee = hitungBiayaTransfer("BNI");
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank BNI. Biaya transfer: Rp " + fee);
    }
}