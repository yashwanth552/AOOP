public class Main {
    public static void main(String[] args) {
        IStudent student1 = new RegularStudent("1", "John Doe");
        ICourse course1 = new OnlineCourse("101", "Java Programming-E");

        Enrollment enrollment = new Enrollment(student1, course1);
        enrollment.enroll();

        System.out.println("Student " + student1.getName() + " enrolled in " + course1.getName());
    }
}