public class Student {
    private String studentId;
    private String name;
    private double grade;
    private String course;
    public Student() {
    }
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }
    public void displayStudent() {
        System.out.println("ID: " + studentId + ", Name: " + name + ", Grade: " + grade + ", Course: " + course + ", Letter Grade: " + calculateLetterGrade());
    }
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setStudentId("S101");
        s1.setName("Manohar");
        s1.setGrade(85.5);
        s1.setCourse("Java");
        s1.displayStudent();

        Student s2 = new Student("S102", "Reddy", 92.3, "Python");
        s2.displayStudent();

        System.out.println("Getter Example -> " + s2.getName());
        s2.setName("Reddy Kumar");
        System.out.println("Updated Name -> " + s2.getName());
    }
}
