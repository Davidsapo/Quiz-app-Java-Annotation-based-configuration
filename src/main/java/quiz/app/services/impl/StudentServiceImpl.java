package quiz.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.app.entity.Student;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.services.interfaces.StudentService;
import quiz.app.utils.CSVReader;
import quiz.app.utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

@Component("studentsService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CSVReader studentsCSVReader;
    @Autowired
    private CSVWriter csvWriter;

    @Override
    public List<Student> getStudents() throws CSVWriterException {
        ArrayList<Student> students = new ArrayList<>();
        studentsCSVReader.parse();
        CSVReader.ResultSet resultSet = studentsCSVReader.getResult();
        while (resultSet.next()) {
            Student newStudent = new Student(resultSet.get("name"), resultSet.get("surname"), Integer.parseInt(resultSet.get("score")));
            if (students.contains(newStudent)) {
                int index = students.indexOf(newStudent);
                Student existing = students.get(index);
                if (newStudent.score > existing.score)
                    existing.score = newStudent.score;
            } else {
                students.add(newStudent);
            }
        }
        students.sort((o1, o2) -> o2.score - o1.score);
        return students;
    }

    @Override
    public void writeStudent(Student student) throws CSVWriterException {
        csvWriter.write(student.getName(), student.getSurname(), String.valueOf(student.score));
    }
}
