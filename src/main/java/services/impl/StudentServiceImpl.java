package services.impl;

import entity.Student;
import exceptions.CSVWriterException;
import services.interfaces.StudentService;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private CSVReader studentsCSVReader;
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

    public void setStudentsCSVReader(CSVReader studentsCSVReader) {
        this.studentsCSVReader = studentsCSVReader;
    }

    public void setCsvWriter(CSVWriter csvWriter) {
        this.csvWriter = csvWriter;
    }
}
