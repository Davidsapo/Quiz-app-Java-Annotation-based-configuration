package quiz.app.services.interfaces;

import quiz.app.entity.Student;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.exceptions.ResourceReaderException;

import java.util.List;

public interface StudentService {

    List<Student> getStudents() throws ResourceReaderException, CSVWriterException;

    void writeStudent(Student student) throws CSVWriterException;
}
