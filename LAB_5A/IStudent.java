import java.util.List;

public interface IStudent {
    String getId();
    String getName();
    List<ICourse> getCourses();
    void enroll(Course course);
}