package services.impl;

import entity.Question;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;
import services.interfaces.QuestionsService;
import utils.CSVReader;
import utils.ResourceReader;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {

    private CSVReader questionsCSVReader;

    @Override
    public List<Question> getQuestions() throws CSVWriterException {
        ArrayList<Question> questions = new ArrayList<>();
        questionsCSVReader.parse();
        CSVReader.ResultSet resultSet = questionsCSVReader.getResult();
        while (resultSet.next()) {
            questions.add(new Question(resultSet.get("question"), Integer.parseInt(resultSet.get("correct")),
                    resultSet.get("answer1"), resultSet.get("answer2"), resultSet.get("answer3")));
        }
        return questions;
    }

    public void setQuestionsCSVReader(CSVReader questionsCSVReader) {
        this.questionsCSVReader = questionsCSVReader;
    }
}
