package services.interfaces;

import entity.Student;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;

import java.util.List;

public interface StudentService {

    List<Student> getStudents() throws ResourceReaderException, CSVWriterException;

    void writeStudent(Student student) throws CSVWriterException;
}
