package services.impl;

import entity.Student;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;
import services.interfaces.StudentService;
import utils.CSVReader;
import utils.CSVWriter;
import utils.ResourceReader;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private String studentsFile;
    private CSVReader csvReader;
    private CSVWriter csvWriter;

    @Override
    public List<Student> getStudents() throws ResourceReaderException {
        ArrayList<Student> students = new ArrayList<>();
        csvReader.setCsv(ResourceReader.readFileToString(studentsFile));
        csvReader.parse();
        CSVReader.ResultSet resultSet = csvReader.getResult();
        while (resultSet.next()) {
            students.add(new Student(resultSet.get("name"), resultSet.get("surname"), Integer.parseInt(resultSet.get("score"))));
        }
        return students;
    }

    @Override
    public void writeStudent(Student student) throws CSVWriterException {
        csvWriter.write(student.getName(), student.getSurname(), String.valueOf(student.score));
    }

    public void setStudentsFile(String studentsFile) {
        this.studentsFile = studentsFile;
    }

    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public void setCsvWriter(CSVWriter csvWriter) {
        this.csvWriter = csvWriter;
    }
}
