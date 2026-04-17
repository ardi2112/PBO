import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Laptop thinkpad = new Lenovo();
        LaptopUser andri = new LaptopUser(thinkpad);

        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("=================================");
        System.out.println("       LAPTOP CONTROL MENU       ");
        System.out.println("=================================");
        System.out.println("  ON   -> Menyalakan laptop");
        System.out.println("  OFF  -> Mematikan laptop");
        System.out.println("  UP   -> Menambah volume");
        System.out.println("  DOWN -> Mengurangi volume");
        System.out.println("  EXIT -> Keluar program");
        System.out.println("=================================");

        while (true) {
            System.out.print("\nMasukkan perintah: ");
            input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "ON":
                    andri.turnOnLaptop();
                    break;
                case "OFF":
                    andri.turnOffLaptop();
                    break;
                case "UP":
                    andri.makeLaptopLouder();
                    break;
                case "DOWN":
                    andri.makeLaptopSilence();
                    break;
                case "EXIT":
                    System.out.println("Program selesai. Terima kasih!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Perintah tidak dikenal. Gunakan: ON, OFF, UP, DOWN, atau EXIT");
            }
        }
    }
}
