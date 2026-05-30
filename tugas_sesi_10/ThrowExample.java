public class ThrowExample {
    static void demo() {
        try {
            NullPointerException t = new NullPointerException("Coba Throw");
            throw t; // Melempar exception secara eksplisit
        } catch (NullPointerException e) {
            System.out.println("Ada pesan error di demo(): " + e);
            throw e; // Melempar kembali (re-throw) ke main
        }
    }

    public static void main(String[] args) {
        try {
            demo();
        } catch (NullPointerException e) {
            System.out.println("Ditangkap di main: " + e);
        }
        System.out.println("Selesai");
    }
}