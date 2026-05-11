package kampus;

// Mengimplementasikan konsep Abstract Class dan mengimplementasi Interface
public abstract class AnggotaKampus implements Civitas {
    // Mengimplementasikan konsep Encapsulation (private access modifier)
    private String id;
    private String nama;

    public AnggotaKampus(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Getter dan Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    // Abstract Method
    public abstract void aktivitasRutinku();
}