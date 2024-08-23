public class Enrollment {
    private IStudent student;
    private ICourse course;

    public Enrollment(IStudent student, ICourse course) {
        this.student = student;
        this.course = course;
    }

    public void enroll() {
        student.enroll((Course) ((OnlineCourse) course).getCourse());
        course.enrollStudent((Student) ((RegularStudent) student).getStudent());
    }
}