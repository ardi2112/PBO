public class Mahasiswa<T, U, V> {
    // Encapsulation: deklarasi variabel bersifat private
    private T nim;
    private U name;
    private V clas;

    // Setter dan Getter untuk NIM
    public void setNim(T nim) {
        this.nim = nim;
    }

    public T getNim() {
        return nim;
    }

    // Setter dan Getter untuk Name
    public void setName(U name) {
        this.name = name;
    }

    public U getName() {
        return name;
    }

    // Setter dan Getter untuk Clas
    public void setClas(V clas) {
        this.clas = clas;
    }

    public V getClas() {
        return clas;
    }
}