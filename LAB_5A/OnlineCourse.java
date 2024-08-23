import java.util.List;

public class OnlineCourse implements ICourse {
    private Course course;

    public OnlineCourse(String id, String name) {
        this.course = new Course(id, name);
    }

    @Override
    public String getId() {
        return course.getId();
    }

    @Override
    public String getName() {
        return course.getName();
    }

    @Override
    public List<Student> getStudents() {
        return course.getStudents();
    }

    @Override
    public void enrollStudent(Student student) {
        course.enrollStudent(student);
    }

    public Course getCourse() {
        return course;
    }
}