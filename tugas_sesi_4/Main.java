public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        System.out.println("=== Bank Umum ===");
        bank.transferUang(100000, "1234567890");
        bank.transferUang(200000, "1234567890", "Mandiri");
        bank.transferUang(300000, "1234567890", "Mandiri", "Untuk keperluan kuliah");
        bank.sukuBunga();
        System.out.println("Biaya transfer ke BNI: " + bank.hitungBiayaTransfer("BNI"));
        System.out.println();

        BankBNI bni = new BankBNI();
        System.out.println("=== Bank BNI ===");
        bni.transferUang(150000, "9876543210", "BCA"); // meskipun argumen "BCA", output tetap bank BNI
        bni.sukuBunga();
        System.out.println("Biaya transfer BNI ke bank lain: " + bni.hitungBiayaTransfer("Mandiri"));
        System.out.println();

        BankBCA bca = new BankBCA();
        System.out.println("=== Bank BCA ===");
        bca.transferUang(250000, "1122334455", "BNI");
        bca.sukuBunga();
        System.out.println("Biaya transfer BCA ke bank lain: " + bca.hitungBiayaTransfer("Mandiri"));
    }
}