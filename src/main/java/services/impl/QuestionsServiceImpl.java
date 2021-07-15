package services.impl;

import entity.Question;
import exceptions.ResourceReaderException;
import services.interfaces.QuestionsService;
import utils.CSVReader;
import utils.ResourceReader;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {

    private String questionsFile;
    private CSVReader csvReader;

    @Override
    public List<Question> getQuestions() throws ResourceReaderException {
        ArrayList<Question> questions = new ArrayList<>();
        csvReader.setCsv(ResourceReader.readFileToString(questionsFile));
        csvReader.parse();
        CSVReader.ResultSet resultSet = csvReader.getResult();
        while (resultSet.next()) {
            questions.add(new Question(resultSet.get("question"), Integer.parseInt(resultSet.get("correct")),
                    resultSet.get("answer1"), resultSet.get("answer2"), resultSet.get("answer3")));
        }
        return questions;
    }

    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public void setQuestionsFile(String questionsFile) {
        this.questionsFile = questionsFile;
    }
}
