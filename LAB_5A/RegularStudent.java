import java.util.ArrayList;
import java.util.List;

public class RegularStudent implements IStudent {
    private Student student;

    public RegularStudent(String id, String name) {
        this.student = new Student(id, name);
    }

    @Override
    public String getId() {
        return student.getId();
    }

    @Override
    public String getName() {
        return student.getName();
    }

    @Override
    public List<ICourse> getCourses() {
        List<ICourse> courses = new ArrayList<>();
        for (Course course : student.getCourses()) {
            courses.add(new OnlineCourse(course.getId(), course.getName()));
        }
        return courses;
    }

    @Override
    public void enroll(Course course) {
        student.enroll(course);
    }

    public Student getStudent() {
        return student;
    }
}