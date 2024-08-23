import java.util.List;

// Interface Segregation Principle (ISP)
// Define specific interfaces for different functionalities
public interface ICourse {
    String getId();
    String getName();
    List<Student> getStudents();
    void enrollStudent(Student student);
}