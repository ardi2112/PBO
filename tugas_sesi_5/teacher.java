public class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses = {};

    public Teacher(String name, String address) {
        super(name, address);
    }

    public boolean addCourse(String course) {
        // Cek apakah course sudah ada
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                System.out.println("Gagal: Mata kuliah \"" + course + "\" sudah ada.");
                return false; // Return false if the course already existedx
            }
        }

        // Expand array dan tambahkan
        String[] newCourses = new String[numCourses + 1];
        for (int i = 0; i < numCourses; i++) {
            newCourses[i] = courses[i];
        }
        newCourses[numCourses] = course;
        courses = newCourses;
        numCourses++;

        System.out.println("Mata kuliah \"" + course + "\" berhasil ditambahkan.");
        return true;
    }

    public boolean removeCourse(String course) {
        // Cari index course
        int index = -1;
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Gagal: Mata kuliah \"" + course + "\" tidak ditemukan.");
            return false; // Return false if the course does not exist
        }

        // Buat array baru tanpa course tersebut
        String[] newCourses = new String[numCourses - 1];
        int j = 0;
        for (int i = 0; i < numCourses; i++) {
            if (i != index) {
                newCourses[j++] = courses[i];
            }
        }
        courses = newCourses;
        numCourses--;

        System.out.println("Mata kuliah \"" + course + "\" berhasil dihapus.");
        return true;
    }

    public void printCourses() {
        if (numCourses == 0) {
            System.out.println("Tidak ada mata kuliah yang diampu.");
            return;
        }
        System.out.println("Daftar Mata Kuliah yang Diampu " + getName() + ":");
        for (int i = 0; i < numCourses; i++) {
            System.out.println("  " + (i + 1) + ". " + courses[i]);
        }
    }

    @Override
    public String toString() {
        return "Teacher: " + super.toString();
    }
}