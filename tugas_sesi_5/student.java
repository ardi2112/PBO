public class Student extends Person {
    private int numCourses = 0;
    private String[] courses = {};
    private int[] grades = {};

    public Student(String name, String address) {
        super(name, address);
    }

    public void addCourseGrade(String course, int grade) {
        // Expand arrays
        String[] newCourses = new String[numCourses + 1];
        int[] newGrades = new int[numCourses + 1];

        for (int i = 0; i < numCourses; i++) {
            newCourses[i] = courses[i];
            newGrades[i] = grades[i];
        }

        newCourses[numCourses] = course;
        newGrades[numCourses] = grade;

        courses = newCourses;
        grades = newGrades;
        numCourses++;

        System.out.println("Mata kuliah \"" + course + "\" dengan nilai " + grade + " berhasil ditambahkan.");
    }

    public void printGrades() {
        if (numCourses == 0) {
            System.out.println("Tidak ada mata kuliah yang terdaftar.");
            return;
        }
        System.out.println("Daftar Nilai " + getName() + ":");
        for (int i = 0; i < numCourses; i++) {
            System.out.println("  " + (i + 1) + ". " + courses[i] + " -> " + grades[i]);
        }
    }

    public double getAverageGrade() {
        if (numCourses == 0) return 0.0;
        int total = 0;
        for (int i = 0; i < numCourses; i++) {
            total += grades[i];
        }
        return (double) total / numCourses;
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}